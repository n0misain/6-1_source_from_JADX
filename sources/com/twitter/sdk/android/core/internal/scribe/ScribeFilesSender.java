package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.internal.network.GuestAuthInterceptor;
import com.twitter.sdk.android.core.internal.network.OAuth1aInterceptor;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.QueueFile;
import io.fabric.sdk.android.services.common.QueueFile.ElementReader;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

class ScribeFilesSender implements FilesSender {
    private static final byte[] COMMA = new byte[]{(byte) 44};
    private static final byte[] END_JSON_ARRAY = new byte[]{(byte) 93};
    private static final String SEND_FILE_FAILURE_ERROR = "Failed sending files";
    private static final byte[] START_JSON_ARRAY = new byte[]{(byte) 91};
    private final TwitterAuthConfig authConfig;
    private final Context context;
    private final ExecutorService executorService;
    private final GuestSessionProvider guestSessionProvider;
    private final IdManager idManager;
    private final long ownerId;
    private final ScribeConfig scribeConfig;
    private final AtomicReference<ScribeService> scribeService = new AtomicReference();
    private final SessionManager<? extends Session<TwitterAuthToken>> sessionManager;
    private final SSLSocketFactory sslSocketFactory;

    static class ConfigRequestInterceptor implements Interceptor {
        private static final String CLIENT_UUID_HEADER = "X-Client-UUID";
        private static final String POLLING_HEADER = "X-Twitter-Polling";
        private static final String POLLING_HEADER_VALUE = "true";
        private static final String USER_AGENT_HEADER = "User-Agent";
        private final IdManager idManager;
        private final ScribeConfig scribeConfig;

        ConfigRequestInterceptor(ScribeConfig scribeConfig, IdManager idManager) {
            this.scribeConfig = scribeConfig;
            this.idManager = idManager;
        }

        public Response intercept(Chain chain) throws IOException {
            Builder builder = chain.request().newBuilder();
            if (!TextUtils.isEmpty(this.scribeConfig.userAgent)) {
                builder.header("User-Agent", this.scribeConfig.userAgent);
            }
            if (!TextUtils.isEmpty(this.idManager.getDeviceUUID())) {
                builder.header(CLIENT_UUID_HEADER, this.idManager.getDeviceUUID());
            }
            builder.header(POLLING_HEADER, "true");
            return chain.proceed(builder.build());
        }
    }

    interface ScribeService {
        @FormUrlEncoded
        @POST("/{version}/jot/{type}")
        @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
        Call<ResponseBody> upload(@Path("version") String str, @Path("type") String str2, @Field("log[]") String str3);

        @FormUrlEncoded
        @POST("/scribe/{sequence}")
        @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
        Call<ResponseBody> uploadSequence(@Path("sequence") String str, @Field("log[]") String str2);
    }

    public ScribeFilesSender(Context context, ScribeConfig scribeConfig, long ownerId, TwitterAuthConfig authConfig, SessionManager<? extends Session<TwitterAuthToken>> sessionManager, GuestSessionProvider guestSessionProvider, SSLSocketFactory sslSocketFactory, ExecutorService executorService, IdManager idManager) {
        this.context = context;
        this.scribeConfig = scribeConfig;
        this.ownerId = ownerId;
        this.authConfig = authConfig;
        this.sessionManager = sessionManager;
        this.guestSessionProvider = guestSessionProvider;
        this.sslSocketFactory = sslSocketFactory;
        this.executorService = executorService;
        this.idManager = idManager;
    }

    public boolean send(List<File> files) {
        if (hasApiAdapter()) {
            try {
                String scribeEvents = getScribeEventsAsJsonArrayString(files);
                CommonUtils.logControlled(this.context, scribeEvents);
                retrofit2.Response<ResponseBody> response = upload(scribeEvents);
                if (response.code() == Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                    return true;
                }
                CommonUtils.logControlledError(this.context, SEND_FILE_FAILURE_ERROR, null);
                if (response.code() == 500 || response.code() == 400) {
                    return true;
                }
            } catch (Exception e) {
                CommonUtils.logControlledError(this.context, SEND_FILE_FAILURE_ERROR, e);
            }
        } else {
            CommonUtils.logControlled(this.context, "Cannot attempt upload at this time");
        }
        return false;
    }

    String getScribeEventsAsJsonArrayString(List<File> files) throws IOException {
        Throwable th;
        final ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        final boolean[] appendComma = new boolean[1];
        out.write(START_JSON_ARRAY);
        for (File f : files) {
            QueueFile qf = null;
            try {
                QueueFile qf2 = new QueueFile(f);
                try {
                    qf2.forEach(new ElementReader() {
                        public void read(InputStream in, int length) throws IOException {
                            byte[] buf = new byte[length];
                            in.read(buf);
                            if (appendComma[0]) {
                                out.write(ScribeFilesSender.COMMA);
                            } else {
                                appendComma[0] = true;
                            }
                            out.write(buf);
                        }
                    });
                    CommonUtils.closeQuietly(qf2);
                } catch (Throwable th2) {
                    th = th2;
                    qf = qf2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        out.write(END_JSON_ARRAY);
        return out.toString(HttpRequest.CHARSET_UTF8);
        CommonUtils.closeQuietly(qf);
        throw th;
    }

    private boolean hasApiAdapter() {
        return getScribeService() != null;
    }

    void setScribeService(ScribeService restAdapter) {
        this.scribeService.set(restAdapter);
    }

    synchronized ScribeService getScribeService() {
        if (this.scribeService.get() == null) {
            OkHttpClient client;
            Session session = getSession(this.ownerId);
            if (isValidSession(session)) {
                client = new OkHttpClient.Builder().sslSocketFactory(this.sslSocketFactory).addInterceptor(new ConfigRequestInterceptor(this.scribeConfig, this.idManager)).addInterceptor(new OAuth1aInterceptor(session, this.authConfig)).build();
            } else {
                client = new OkHttpClient.Builder().sslSocketFactory(this.sslSocketFactory).addInterceptor(new ConfigRequestInterceptor(this.scribeConfig, this.idManager)).addInterceptor(new GuestAuthInterceptor(this.guestSessionProvider)).build();
            }
            this.scribeService.compareAndSet(null, new Retrofit.Builder().baseUrl(this.scribeConfig.baseUrl).client(client).build().create(ScribeService.class));
        }
        return (ScribeService) this.scribeService.get();
    }

    private Session getSession(long ownerId) {
        return this.sessionManager.getSession(ownerId);
    }

    private boolean isValidSession(Session session) {
        return (session == null || session.getAuthToken() == null) ? false : true;
    }

    retrofit2.Response<ResponseBody> upload(String scribeEvents) throws IOException {
        ScribeService service = getScribeService();
        if (TextUtils.isEmpty(this.scribeConfig.sequence)) {
            return service.upload(this.scribeConfig.pathVersion, this.scribeConfig.pathType, scribeEvents).execute();
        }
        return service.uploadSequence(this.scribeConfig.sequence, scribeEvents).execute();
    }
}
