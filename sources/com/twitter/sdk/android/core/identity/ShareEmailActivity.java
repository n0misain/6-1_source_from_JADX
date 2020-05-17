package com.twitter.sdk.android.core.identity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.TextView;
import com.twitter.sdk.android.core.C0999R;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import io.fabric.sdk.android.Fabric;

public class ShareEmailActivity extends Activity {
    static final String EXTRA_RESULT_RECEIVER = "result_receiver";
    static final String EXTRA_SESSION_ID = "session_id";
    ShareEmailController controller;
    private TwitterSession session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0999R.layout.tw__activity_share_email);
        try {
            Intent startIntent = getIntent();
            ResultReceiver resultReceiver = getResultReceiver(startIntent);
            this.session = getSession(startIntent);
            this.controller = new ShareEmailController(new ShareEmailClient(this.session), resultReceiver);
            setUpShareEmailDesc(this, (TextView) findViewById(C0999R.id.tw__share_email_desc));
        } catch (IllegalArgumentException e) {
            Fabric.getLogger().mo4336e(TwitterCore.TAG, "Failed to create ShareEmailActivity.", e);
            finish();
        }
    }

    private ResultReceiver getResultReceiver(Intent intent) {
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra(EXTRA_RESULT_RECEIVER);
        if (resultReceiver != null) {
            return resultReceiver;
        }
        throw new IllegalArgumentException("ResultReceiver must not be null. This activity should not be started directly.");
    }

    private TwitterSession getSession(Intent intent) {
        long sessionId = intent.getLongExtra(EXTRA_SESSION_ID, -1);
        TwitterSession session = (TwitterSession) TwitterCore.getInstance().getSessionManager().getSession(sessionId);
        if (session != null) {
            return session;
        }
        throw new IllegalArgumentException("No TwitterSession for id:" + sessionId);
    }

    void setUpShareEmailDesc(Context context, TextView shareEmailDescView) {
        PackageManager packageManager = context.getPackageManager();
        shareEmailDescView.setText(getResources().getString(C0999R.string.tw__share_email_desc, new Object[]{packageManager.getApplicationLabel(context.getApplicationInfo()), this.session.getUserName()}));
    }

    public void onClickNotNow(View view) {
        this.controller.cancelRequest();
        finish();
    }

    public void onClickAllow(View view) {
        this.controller.executeRequest();
        finish();
    }

    public void onBackPressed() {
        this.controller.cancelRequest();
        super.onBackPressed();
    }
}
