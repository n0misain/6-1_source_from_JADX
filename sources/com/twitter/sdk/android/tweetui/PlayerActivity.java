package com.twitter.sdk.android.tweetui;

import android.app.Activity;
import android.os.Bundle;
import com.twitter.sdk.android.core.internal.scribe.ScribeItem;
import com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener.Callback;
import java.io.Serializable;

public class PlayerActivity extends Activity {
    public static final String PLAYER_ITEM = "PLAYER_ITEM";
    public static final String SCRIBE_ITEM = "SCRIBE_ITEM";
    static final VideoScribeClient videoScribeClient = new VideoScribeClientImpl(TweetUi.getInstance());
    PlayerController playerController;

    /* renamed from: com.twitter.sdk.android.tweetui.PlayerActivity$1 */
    class C10371 implements Callback {
        C10371() {
        }

        public void onDismiss() {
            PlayerActivity.this.finish();
            PlayerActivity.this.overridePendingTransition(0, C1043R.anim.tw__slide_out);
        }

        public void onMove(float translationY) {
        }
    }

    public static class PlayerItem implements Serializable {
        public String callToActionText;
        public String callToActionUrl;
        public boolean looping;
        public String url;

        public PlayerItem(String url, boolean looping) {
            this.url = url;
            this.looping = looping;
        }

        public PlayerItem(String url, boolean looping, String callToActionText, String callToActionUrl) {
            this.url = url;
            this.looping = looping;
            this.callToActionText = callToActionText;
            this.callToActionUrl = callToActionUrl;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1043R.layout.tw__player_activity);
        PlayerItem item = (PlayerItem) getIntent().getSerializableExtra(PLAYER_ITEM);
        this.playerController = new PlayerController(findViewById(16908290), new C10371());
        this.playerController.prepare(item);
        scribeCardPlayImpression((ScribeItem) getIntent().getSerializableExtra(SCRIBE_ITEM));
    }

    protected void onResume() {
        super.onResume();
        this.playerController.onResume();
    }

    protected void onPause() {
        this.playerController.onPause();
        super.onPause();
    }

    public void onDestroy() {
        this.playerController.onDestroy();
        super.onDestroy();
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, C1043R.anim.tw__slide_out);
    }

    private void scribeCardPlayImpression(ScribeItem scribeItem) {
        videoScribeClient.play(scribeItem);
    }
}
