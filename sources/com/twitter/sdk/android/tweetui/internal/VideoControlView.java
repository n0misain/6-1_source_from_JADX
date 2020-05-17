package com.twitter.sdk.android.tweetui.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.applovin.sdk.AppLovinErrorCodes;
import com.twitter.sdk.android.tweetui.C1043R;

public class VideoControlView extends FrameLayout {
    static final int FADE_DURATION_MS = 150;
    static final long PROGRESS_BAR_TICKS = 1000;
    private static final int SHOW_PROGRESS_MSG = 1001;
    TextView currentTime;
    TextView duration;
    @SuppressLint({"HandlerLeak"})
    private final Handler handler = new C10591();
    MediaPlayerControl player;
    SeekBar seekBar;
    ImageButton stateControl;

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoControlView$1 */
    class C10591 extends Handler {
        C10591() {
        }

        public void handleMessage(Message msg) {
            if (msg.what == 1001 && VideoControlView.this.player != null) {
                VideoControlView.this.updateProgress();
                VideoControlView.this.updateStateControl();
                if (VideoControlView.this.isShowing() && VideoControlView.this.player.isPlaying()) {
                    sendMessageDelayed(obtainMessage(1001), 500);
                }
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoControlView$2 */
    class C10602 implements OnClickListener {
        C10602() {
        }

        public void onClick(View view) {
            if (VideoControlView.this.player.isPlaying()) {
                VideoControlView.this.player.pause();
            } else {
                VideoControlView.this.player.start();
            }
            VideoControlView.this.show();
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoControlView$3 */
    class C10613 implements OnSeekBarChangeListener {
        C10613() {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                long position = ((long) (VideoControlView.this.player.getDuration() * progress)) / VideoControlView.PROGRESS_BAR_TICKS;
                VideoControlView.this.player.seekTo((int) position);
                VideoControlView.this.setCurrentTime((int) position);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            VideoControlView.this.handler.removeMessages(1001);
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            VideoControlView.this.handler.sendEmptyMessage(1001);
        }
    }

    public interface MediaPlayerControl {
        int getBufferPercentage();

        int getCurrentPosition();

        int getDuration();

        boolean isPlaying();

        void pause();

        void seekTo(int i);

        void start();
    }

    public VideoControlView(Context context) {
        super(context);
    }

    public VideoControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMediaPlayer(MediaPlayerControl player) {
        this.player = player;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        initSubviews();
    }

    void initSubviews() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C1043R.layout.tw__video_control, this);
        this.stateControl = (ImageButton) findViewById(C1043R.id.tw__state_control);
        this.currentTime = (TextView) findViewById(C1043R.id.tw__current_time);
        this.duration = (TextView) findViewById(C1043R.id.tw__duration);
        this.seekBar = (SeekBar) findViewById(C1043R.id.tw__progress);
        this.seekBar.setMax(1000);
        this.seekBar.setOnSeekBarChangeListener(createProgressChangeListener());
        this.stateControl.setOnClickListener(createStateControlClickListener());
        setDuration(0);
        setCurrentTime(0);
        setProgress(0, 0, 0);
    }

    OnClickListener createStateControlClickListener() {
        return new C10602();
    }

    OnSeekBarChangeListener createProgressChangeListener() {
        return new C10613();
    }

    void updateProgress() {
        int duration = this.player.getDuration();
        int currentTime = this.player.getCurrentPosition();
        int bufferPercentage = this.player.getBufferPercentage();
        setDuration(duration);
        setCurrentTime(currentTime);
        setProgress(currentTime, duration, bufferPercentage);
    }

    void setDuration(int durationMillis) {
        this.duration.setText(MediaTimeUtils.getPlaybackTime((long) durationMillis));
    }

    void setCurrentTime(int currentTimeMillis) {
        this.currentTime.setText(MediaTimeUtils.getPlaybackTime((long) currentTimeMillis));
    }

    void setProgress(int currentTimeMillis, int durationMillis, int bufferPercentage) {
        this.seekBar.setProgress((int) (durationMillis > 0 ? (PROGRESS_BAR_TICKS * ((long) currentTimeMillis)) / ((long) durationMillis) : 0));
        this.seekBar.setSecondaryProgress(bufferPercentage * 10);
    }

    void updateStateControl() {
        if (this.player.isPlaying()) {
            setPauseDrawable();
        } else if (this.player.getCurrentPosition() > Math.max(this.player.getDuration() + AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT, 0)) {
            setReplayDrawable();
        } else {
            setPlayDrawable();
        }
    }

    void setPlayDrawable() {
        this.stateControl.setImageResource(C1043R.drawable.tw__video_play_btn);
        this.stateControl.setContentDescription(getContext().getString(C1043R.string.tw__play));
    }

    void setPauseDrawable() {
        this.stateControl.setImageResource(C1043R.drawable.tw__video_pause_btn);
        this.stateControl.setContentDescription(getContext().getString(C1043R.string.tw__pause));
    }

    void setReplayDrawable() {
        this.stateControl.setImageResource(C1043R.drawable.tw__video_replay_btn);
        this.stateControl.setContentDescription(getContext().getString(C1043R.string.tw__replay));
    }

    void hide() {
        this.handler.removeMessages(1001);
        AnimationUtils.fadeOut(this, FADE_DURATION_MS);
    }

    void show() {
        this.handler.sendEmptyMessage(1001);
        AnimationUtils.fadeIn(this, FADE_DURATION_MS);
    }

    public boolean isShowing() {
        return getVisibility() == 0;
    }

    public void update() {
        this.handler.sendEmptyMessage(1001);
    }
}
