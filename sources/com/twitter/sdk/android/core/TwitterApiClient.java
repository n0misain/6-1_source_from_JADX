package com.twitter.sdk.android.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.internal.network.OkHttpClientHelper;
import com.twitter.sdk.android.core.models.BindingValues;
import com.twitter.sdk.android.core.models.BindingValuesAdapter;
import com.twitter.sdk.android.core.models.SafeListAdapter;
import com.twitter.sdk.android.core.models.SafeMapAdapter;
import com.twitter.sdk.android.core.services.AccountService;
import com.twitter.sdk.android.core.services.CollectionService;
import com.twitter.sdk.android.core.services.ConfigurationService;
import com.twitter.sdk.android.core.services.FavoriteService;
import com.twitter.sdk.android.core.services.ListService;
import com.twitter.sdk.android.core.services.MediaService;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.core.services.StatusesService;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwitterApiClient {
    final Retrofit retrofit;
    final ConcurrentHashMap<Class, Object> services;

    public TwitterApiClient() {
        this(OkHttpClientHelper.getOkHttpClient(TwitterCore.getInstance().getGuestSessionProvider(), TwitterCore.getInstance().getSSLSocketFactory()), new TwitterApi());
    }

    public TwitterApiClient(OkHttpClient client) {
        this(OkHttpClientHelper.getCustomOkHttpClient(client, TwitterCore.getInstance().getGuestSessionProvider(), TwitterCore.getInstance().getSSLSocketFactory()), new TwitterApi());
    }

    public TwitterApiClient(TwitterSession session) {
        this(OkHttpClientHelper.getOkHttpClient(session, TwitterCore.getInstance().getAuthConfig(), TwitterCore.getInstance().getSSLSocketFactory()), new TwitterApi());
    }

    public TwitterApiClient(TwitterSession session, OkHttpClient client) {
        this(OkHttpClientHelper.getCustomOkHttpClient(client, session, TwitterCore.getInstance().getAuthConfig(), TwitterCore.getInstance().getSSLSocketFactory()), new TwitterApi());
    }

    TwitterApiClient(OkHttpClient client, TwitterApi twitterApi) {
        this.services = buildConcurrentMap();
        this.retrofit = buildRetrofit(client, twitterApi);
    }

    private Retrofit buildRetrofit(OkHttpClient httpClient, TwitterApi twitterApi) {
        return new Builder().client(httpClient).baseUrl(twitterApi.getBaseHostUrl()).addConverterFactory(GsonConverterFactory.create(buildGson())).build();
    }

    private Gson buildGson() {
        return new GsonBuilder().registerTypeAdapterFactory(new SafeListAdapter()).registerTypeAdapterFactory(new SafeMapAdapter()).registerTypeAdapter(BindingValues.class, new BindingValuesAdapter()).create();
    }

    private ConcurrentHashMap buildConcurrentMap() {
        return new ConcurrentHashMap();
    }

    public AccountService getAccountService() {
        return (AccountService) getService(AccountService.class);
    }

    public FavoriteService getFavoriteService() {
        return (FavoriteService) getService(FavoriteService.class);
    }

    public StatusesService getStatusesService() {
        return (StatusesService) getService(StatusesService.class);
    }

    public SearchService getSearchService() {
        return (SearchService) getService(SearchService.class);
    }

    public ListService getListService() {
        return (ListService) getService(ListService.class);
    }

    public CollectionService getCollectionService() {
        return (CollectionService) getService(CollectionService.class);
    }

    public ConfigurationService getConfigurationService() {
        return (ConfigurationService) getService(ConfigurationService.class);
    }

    public MediaService getMediaService() {
        return (MediaService) getService(MediaService.class);
    }

    protected <T> T getService(Class<T> cls) {
        if (!this.services.contains(cls)) {
            this.services.putIfAbsent(cls, this.retrofit.create(cls));
        }
        return this.services.get(cls);
    }
}
