package com.twitter.sdk.android.tweetui.internal;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import com.twitter.sdk.android.tweetui.internal.VideoControlView.MediaPlayerControl;

public class VideoView extends SurfaceView implements MediaPlayerControl {
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private String TAG;
    private GestureDetector gestureDetector;
    private int mAudioSession;
    private OnBufferingUpdateListener mBufferingUpdateListener;
    private OnCompletionListener mCompletionListener;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private OnErrorListener mErrorListener;
    private OnInfoListener mInfoListener;
    private boolean mLooping;
    private VideoControlView mMediaController;
    private MediaPlayer mMediaPlayer;
    private OnCompletionListener mOnCompletionListener;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOnInfoListener;
    private OnPreparedListener mOnPreparedListener;
    OnPreparedListener mPreparedListener;
    Callback mSHCallback;
    private int mSeekWhenPrepared;
    OnVideoSizeChangedListener mSizeChangedListener;
    private int mSurfaceHeight;
    private SurfaceHolder mSurfaceHolder;
    private int mSurfaceWidth;
    private int mTargetState;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoWidth;

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$1 */
    class C10621 implements OnVideoSizeChangedListener {
        C10621() {
        }

        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            VideoView.this.mVideoWidth = mp.getVideoWidth();
            VideoView.this.mVideoHeight = mp.getVideoHeight();
            if (VideoView.this.mVideoWidth != 0 && VideoView.this.mVideoHeight != 0) {
                VideoView.this.getHolder().setFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
                VideoView.this.requestLayout();
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$2 */
    class C10632 implements OnPreparedListener {
        C10632() {
        }

        public void onPrepared(MediaPlayer mp) {
            VideoView.this.mCurrentState = 2;
            if (VideoView.this.mOnPreparedListener != null) {
                VideoView.this.mOnPreparedListener.onPrepared(VideoView.this.mMediaPlayer);
            }
            if (VideoView.this.mMediaController != null) {
                VideoView.this.mMediaController.setEnabled(true);
            }
            VideoView.this.mVideoWidth = mp.getVideoWidth();
            VideoView.this.mVideoHeight = mp.getVideoHeight();
            int seekToPosition = VideoView.this.mSeekWhenPrepared;
            if (seekToPosition != 0) {
                VideoView.this.seekTo(seekToPosition);
            }
            if (VideoView.this.mVideoWidth != 0 && VideoView.this.mVideoHeight != 0) {
                VideoView.this.getHolder().setFixedSize(VideoView.this.mVideoWidth, VideoView.this.mVideoHeight);
                if (VideoView.this.mSurfaceWidth != VideoView.this.mVideoWidth || VideoView.this.mSurfaceHeight != VideoView.this.mVideoHeight) {
                    return;
                }
                if (VideoView.this.mTargetState == 3) {
                    VideoView.this.start();
                    if (VideoView.this.mMediaController != null) {
                        VideoView.this.mMediaController.show();
                    }
                } else if (!VideoView.this.isPlaying()) {
                    if ((seekToPosition != 0 || VideoView.this.getCurrentPosition() > 0) && VideoView.this.mMediaController != null) {
                        VideoView.this.mMediaController.show();
                    }
                }
            } else if (VideoView.this.mTargetState == 3) {
                VideoView.this.start();
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$3 */
    class C10643 implements OnCompletionListener {
        C10643() {
        }

        public void onCompletion(MediaPlayer mp) {
            VideoView.this.mCurrentState = 5;
            VideoView.this.mTargetState = 5;
            if (VideoView.this.mOnCompletionListener != null) {
                VideoView.this.mOnCompletionListener.onCompletion(VideoView.this.mMediaPlayer);
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$4 */
    class C10654 implements OnInfoListener {
        C10654() {
        }

        public boolean onInfo(MediaPlayer mp, int arg1, int arg2) {
            if (VideoView.this.mOnInfoListener != null) {
                VideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
            }
            return true;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$5 */
    class C10665 implements OnErrorListener {
        C10665() {
        }

        public boolean onError(MediaPlayer mp, int framework_err, int impl_err) {
            Log.d(VideoView.this.TAG, "Error: " + framework_err + "," + impl_err);
            VideoView.this.mCurrentState = -1;
            VideoView.this.mTargetState = -1;
            if (VideoView.this.mMediaController != null) {
                VideoView.this.mMediaController.hide();
            }
            return (VideoView.this.mOnErrorListener == null || VideoView.this.mOnErrorListener.onError(VideoView.this.mMediaPlayer, framework_err, impl_err)) ? true : true;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$6 */
    class C10676 implements OnBufferingUpdateListener {
        C10676() {
        }

        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            VideoView.this.mCurrentBufferPercentage = percent;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$7 */
    class C10687 extends SimpleOnGestureListener {
        C10687() {
        }

        public boolean onSingleTapUp(MotionEvent e) {
            if (VideoView.this.isInPlaybackState() && VideoView.this.mMediaController != null) {
                VideoView.this.toggleMediaControlsVisiblity();
            }
            return false;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.VideoView$8 */
    class C10698 implements Callback {
        C10698() {
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
            VideoView.this.mSurfaceWidth = w;
            VideoView.this.mSurfaceHeight = h;
            boolean isValidState;
            if (VideoView.this.mTargetState == 3) {
                isValidState = true;
            } else {
                isValidState = false;
            }
            boolean hasValidSize;
            if (VideoView.this.mVideoWidth == w && VideoView.this.mVideoHeight == h) {
                hasValidSize = true;
            } else {
                hasValidSize = false;
            }
            if (VideoView.this.mMediaPlayer != null && isValidState && hasValidSize) {
                if (VideoView.this.mSeekWhenPrepared != 0) {
                    VideoView.this.seekTo(VideoView.this.mSeekWhenPrepared);
                }
                VideoView.this.start();
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.show();
                }
            }
        }

        public void surfaceCreated(SurfaceHolder holder) {
            VideoView.this.mSurfaceHolder = holder;
            VideoView.this.openVideo();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            VideoView.this.mSurfaceHolder = null;
            if (VideoView.this.mMediaController != null) {
                VideoView.this.mMediaController.hide();
            }
            VideoView.this.release(true);
        }
    }

    public VideoView(Context context) {
        super(context);
        this.TAG = "VideoView";
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mSizeChangedListener = new C10621();
        this.mPreparedListener = new C10632();
        this.mCompletionListener = new C10643();
        this.mInfoListener = new C10654();
        this.mErrorListener = new C10665();
        this.mBufferingUpdateListener = new C10676();
        this.gestureDetector = new GestureDetector(getContext(), new C10687());
        this.mSHCallback = new C10698();
        initVideoView();
    }

    public VideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.TAG = "VideoView";
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mSizeChangedListener = new C10621();
        this.mPreparedListener = new C10632();
        this.mCompletionListener = new C10643();
        this.mInfoListener = new C10654();
        this.mErrorListener = new C10665();
        this.mBufferingUpdateListener = new C10676();
        this.gestureDetector = new GestureDetector(getContext(), new C10687());
        this.mSHCallback = new C10698();
        initVideoView();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(this.mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(this.mVideoHeight, heightMeasureSpec);
        if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
            int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
            if (widthSpecMode == 1073741824 && heightSpecMode == 1073741824) {
                width = widthSpecSize;
                height = heightSpecSize;
                if (this.mVideoWidth * height < this.mVideoHeight * width) {
                    width = (this.mVideoWidth * height) / this.mVideoHeight;
                } else if (this.mVideoWidth * height > this.mVideoHeight * width) {
                    height = (this.mVideoHeight * width) / this.mVideoWidth;
                }
            } else if (widthSpecMode == 1073741824) {
                width = widthSpecSize;
                height = (this.mVideoHeight * width) / this.mVideoWidth;
                if (heightSpecMode == Integer.MIN_VALUE && height > heightSpecSize) {
                    height = heightSpecSize;
                }
            } else if (heightSpecMode == 1073741824) {
                height = heightSpecSize;
                width = (this.mVideoWidth * height) / this.mVideoHeight;
                if (widthSpecMode == Integer.MIN_VALUE && width > widthSpecSize) {
                    width = widthSpecSize;
                }
            } else {
                width = this.mVideoWidth;
                height = this.mVideoHeight;
                if (heightSpecMode == Integer.MIN_VALUE && height > heightSpecSize) {
                    height = heightSpecSize;
                    width = (this.mVideoWidth * height) / this.mVideoHeight;
                }
                if (widthSpecMode == Integer.MIN_VALUE && width > widthSpecSize) {
                    width = widthSpecSize;
                    height = (this.mVideoHeight * width) / this.mVideoWidth;
                }
            }
        }
        setMeasuredDimension(width, height);
    }

    private void initVideoView() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        getHolder().addCallback(this.mSHCallback);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    public void setVideoURI(Uri uri, boolean looping) {
        this.mUri = uri;
        this.mLooping = looping;
        this.mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
        }
    }

    private void openVideo() {
        if (this.mUri != null && this.mSurfaceHolder != null) {
            release(false);
            try {
                this.mMediaPlayer = new MediaPlayer();
                if (this.mAudioSession != 0) {
                    this.mMediaPlayer.setAudioSessionId(this.mAudioSession);
                } else {
                    this.mAudioSession = this.mMediaPlayer.getAudioSessionId();
                }
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mCurrentBufferPercentage = 0;
                this.mMediaPlayer.setLooping(this.mLooping);
                this.mMediaPlayer.setDataSource(getContext(), this.mUri);
                this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = 1;
                attachMediaController();
            } catch (Exception ex) {
                Log.w(this.TAG, "Unable to open content: " + this.mUri, ex);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        }
    }

    public void setMediaController(VideoControlView controller) {
        if (this.mMediaController != null) {
            this.mMediaController.hide();
        }
        this.mMediaController = controller;
        attachMediaController();
    }

    private void attachMediaController() {
        if (this.mMediaPlayer != null && this.mMediaController != null) {
            this.mMediaController.setMediaPlayer(this);
            this.mMediaController.setEnabled(isInPlaybackState());
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return this.gestureDetector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }

    public void setOnPreparedListener(OnPreparedListener l) {
        this.mOnPreparedListener = l;
    }

    public void setOnCompletionListener(OnCompletionListener l) {
        this.mOnCompletionListener = l;
    }

    public void setOnErrorListener(OnErrorListener l) {
        this.mOnErrorListener = l;
    }

    public void setOnInfoListener(OnInfoListener l) {
        this.mOnInfoListener = l;
    }

    private void release(boolean cleartargetstate) {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (cleartargetstate) {
                this.mTargetState = 0;
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isKeyCodeSupported = (keyCode == 4 || keyCode == 24 || keyCode == 25 || keyCode == 82 || keyCode == 5 || keyCode == 6) ? false : true;
        if (isInPlaybackState() && isKeyCodeSupported && this.mMediaController != null) {
            if (keyCode == 79 || keyCode == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                    return true;
                }
                start();
                this.mMediaController.hide();
                return true;
            } else if (keyCode == TransportMediator.KEYCODE_MEDIA_PLAY) {
                if (this.mMediaPlayer.isPlaying()) {
                    return true;
                }
                start();
                this.mMediaController.hide();
                return true;
            } else if (keyCode != 86 && keyCode != TransportMediator.KEYCODE_MEDIA_PAUSE) {
                toggleMediaControlsVisiblity();
            } else if (!this.mMediaPlayer.isPlaying()) {
                return true;
            } else {
                pause();
                this.mMediaController.show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void toggleMediaControlsVisiblity() {
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
        } else {
            this.mMediaController.show();
        }
    }

    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
        }
        this.mTargetState = 3;
    }

    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        this.mTargetState = 4;
    }

    public int getDuration() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int msec) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(msec);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = msec;
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    private boolean isInPlaybackState() {
        return (this.mMediaPlayer == null || this.mCurrentState == -1 || this.mCurrentState == 0 || this.mCurrentState == 1) ? false : true;
    }
}
