package com.twitter.sdk.android.tweetui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.twitter.sdk.android.core.IntentUtils;
import com.twitter.sdk.android.tweetui.PlayerActivity.PlayerItem;
import com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener;
import com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener.Callback;
import com.twitter.sdk.android.tweetui.internal.VideoControlView;
import com.twitter.sdk.android.tweetui.internal.VideoView;
import io.fabric.sdk.android.Fabric;

class PlayerController {
    private static final String TAG = "PlayerController";
    final TextView callToActionView;
    final Callback callback;
    boolean isPlaying = true;
    View rootView;
    int seekPosition = 0;
    final VideoControlView videoControlView;
    final ProgressBar videoProgressView;
    final VideoView videoView;

    /* renamed from: com.twitter.sdk.android.tweetui.PlayerController$1 */
    class C10381 implements OnPreparedListener {
        C10381() {
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            PlayerController.this.videoProgressView.setVisibility(8);
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.PlayerController$2 */
    class C10392 implements OnInfoListener {
        C10392() {
        }

        public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
            if (what == 702) {
                PlayerController.this.videoProgressView.setVisibility(8);
                return true;
            } else if (what != 701) {
                return false;
            } else {
                PlayerController.this.videoProgressView.setVisibility(0);
                return true;
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.PlayerController$3 */
    class C10403 implements OnClickListener {
        C10403() {
        }

        public void onClick(View view) {
            if (PlayerController.this.videoView.isPlaying()) {
                PlayerController.this.videoView.pause();
            } else {
                PlayerController.this.videoView.start();
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.PlayerController$5 */
    class C10425 implements OnClickListener {
        C10425() {
        }

        public void onClick(View v) {
            if (PlayerController.this.callToActionView.getVisibility() == 0) {
                PlayerController.this.callToActionView.setVisibility(8);
            } else {
                PlayerController.this.callToActionView.setVisibility(0);
            }
        }
    }

    PlayerController(View rootView, Callback callback) {
        this.rootView = rootView;
        this.videoView = (VideoView) rootView.findViewById(C1043R.id.video_view);
        this.videoControlView = (VideoControlView) rootView.findViewById(C1043R.id.video_control_view);
        this.videoProgressView = (ProgressBar) rootView.findViewById(C1043R.id.video_progress_view);
        this.callToActionView = (TextView) rootView.findViewById(C1043R.id.call_to_action_view);
        this.callback = callback;
    }

    PlayerController(View rootView, VideoView videoView, VideoControlView videoControlView, ProgressBar videoProgressView, TextView callToActionView, Callback callback) {
        this.rootView = rootView;
        this.videoView = videoView;
        this.videoControlView = videoControlView;
        this.videoProgressView = videoProgressView;
        this.callToActionView = callToActionView;
        this.callback = callback;
    }

    void prepare(PlayerItem item) {
        try {
            setUpCallToAction(item);
            setUpMediaControl(item.looping);
            this.videoView.setOnTouchListener(SwipeToDismissTouchListener.createFromView(this.videoView, this.callback));
            this.videoView.setOnPreparedListener(new C10381());
            this.videoView.setOnInfoListener(new C10392());
            this.videoView.setVideoURI(Uri.parse(item.url), item.looping);
            this.videoView.requestFocus();
        } catch (Exception e) {
            Fabric.getLogger().mo4336e(TAG, "Error occurred during video playback", e);
        }
    }

    void onResume() {
        if (this.seekPosition != 0) {
            this.videoView.seekTo(this.seekPosition);
        }
        if (this.isPlaying) {
            this.videoView.start();
            this.videoControlView.update();
        }
    }

    void onPause() {
        this.isPlaying = this.videoView.isPlaying();
        this.seekPosition = this.videoView.getCurrentPosition();
        this.videoView.pause();
    }

    void onDestroy() {
        this.videoView.stopPlayback();
    }

    void setUpMediaControl(boolean looping) {
        if (looping) {
            setUpLoopControl();
        } else {
            setUpMediaControl();
        }
    }

    void setUpLoopControl() {
        this.videoControlView.setVisibility(4);
        this.videoView.setOnClickListener(new C10403());
    }

    void setUpMediaControl() {
        this.videoView.setMediaController(this.videoControlView);
    }

    void setUpCallToAction(PlayerItem item) {
        if (item.callToActionText != null && item.callToActionUrl != null) {
            this.callToActionView.setVisibility(0);
            this.callToActionView.setText(item.callToActionText);
            setUpCallToActionListener(item.callToActionUrl);
            setUpRootViewOnClickListener();
        }
    }

    void setUpCallToActionListener(final String callToActionUrl) {
        this.callToActionView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                IntentUtils.safeStartActivity(PlayerController.this.callToActionView.getContext(), new Intent("android.intent.action.VIEW", Uri.parse(callToActionUrl)));
            }
        });
    }

    void setUpRootViewOnClickListener() {
        this.rootView.setOnClickListener(new C10425());
    }
}
