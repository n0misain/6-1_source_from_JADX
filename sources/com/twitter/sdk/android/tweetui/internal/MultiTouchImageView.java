package com.twitter.sdk.android.tweetui.internal;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.twitter.sdk.android.tweetui.internal.SwipeToDismissTouchListener.SwipeableViewProvider;

public class MultiTouchImageView extends ImageView implements SwipeableViewProvider {
    private static final float DOUBLE_TAP_SCALE_FACTOR = 2.0f;
    private static final float MINIMUM_SCALE_FACTOR = 1.0f;
    private static final long SCALE_ANIMATION_DURATION = 300;
    boolean allowIntercept;
    final Matrix baseMatrix;
    final Matrix drawMatrix;
    final RectF drawRect;
    final GestureDetector gestureDetector;
    final float[] matrixValues;
    final ScaleGestureDetector scaleGestureDetector;
    final Matrix updateMatrix;
    final RectF viewRect;

    /* renamed from: com.twitter.sdk.android.tweetui.internal.MultiTouchImageView$1 */
    class C10541 extends SimpleOnScaleGestureListener {
        C10541() {
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            MultiTouchImageView.this.setScale(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            MultiTouchImageView.this.setImageMatrix();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector detector) {
            if (MultiTouchImageView.this.getScale() < MultiTouchImageView.MINIMUM_SCALE_FACTOR) {
                MultiTouchImageView.this.reset();
                MultiTouchImageView.this.setImageMatrix();
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.MultiTouchImageView$2 */
    class C10552 extends SimpleOnGestureListener {
        C10552() {
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float dx, float dy) {
            MultiTouchImageView.this.setTranslate(-dx, -dy);
            MultiTouchImageView.this.setImageMatrix();
            if (MultiTouchImageView.this.allowIntercept && !MultiTouchImageView.this.scaleGestureDetector.isInProgress()) {
                MultiTouchImageView.this.requestDisallowInterceptTouchEvent(false);
            }
            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {
            if (MultiTouchImageView.this.getScale() > MultiTouchImageView.MINIMUM_SCALE_FACTOR) {
                MultiTouchImageView.this.animateScale(MultiTouchImageView.this.getScale(), MultiTouchImageView.MINIMUM_SCALE_FACTOR, e.getX(), e.getY());
            } else {
                MultiTouchImageView.this.animateScale(MultiTouchImageView.this.getScale(), MultiTouchImageView.DOUBLE_TAP_SCALE_FACTOR, e.getX(), e.getY());
            }
            return true;
        }
    }

    public MultiTouchImageView(Context context) {
        this(context, null);
    }

    public MultiTouchImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTouchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.drawMatrix = new Matrix();
        this.baseMatrix = new Matrix();
        this.updateMatrix = new Matrix();
        this.viewRect = new RectF();
        this.drawRect = new RectF();
        this.matrixValues = new float[9];
        this.allowIntercept = false;
        this.scaleGestureDetector = new ScaleGestureDetector(context, new C10541());
        this.gestureDetector = new GestureDetector(context, new C10552());
    }

    boolean isInitializationComplete() {
        Drawable drawable = getDrawable();
        return drawable != null && drawable.getIntrinsicWidth() > 0;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (isInitializationComplete()) {
            initializeViewRect();
            initializeBaseMatrix(getDrawable());
            setImageMatrix();
        }
    }

    void initializeViewRect() {
        this.viewRect.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
    }

    void initializeBaseMatrix(Drawable drawable) {
        RectF srcRect = new RectF(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        this.baseMatrix.reset();
        this.baseMatrix.setRectToRect(srcRect, this.viewRect, ScaleToFit.CENTER);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isInitializationComplete()) {
            return false;
        }
        requestDisallowInterceptTouchEvent(true);
        boolean retVal = this.scaleGestureDetector.onTouchEvent(event);
        if (this.gestureDetector.onTouchEvent(event) || retVal) {
            retVal = true;
        } else {
            retVal = false;
        }
        if (retVal || super.onTouchEvent(event)) {
            return true;
        }
        return false;
    }

    void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    void setScale(float ds, float px, float py) {
        this.updateMatrix.postScale(ds, ds, px, py);
    }

    float getScale() {
        this.updateMatrix.getValues(this.matrixValues);
        return this.matrixValues[0];
    }

    void setTranslate(float dx, float dy) {
        this.updateMatrix.postTranslate(dx, dy);
    }

    void reset() {
        this.updateMatrix.reset();
    }

    void updateMatrixBounds() {
        RectF rect = getDrawRect(getDrawMatrix());
        float dy = 0.0f;
        float dx = 0.0f;
        if (rect.height() <= this.viewRect.height()) {
            dy = ((this.viewRect.height() - rect.height()) / DOUBLE_TAP_SCALE_FACTOR) - rect.top;
        } else if (rect.top > 0.0f) {
            dy = -rect.top;
        } else if (rect.bottom < this.viewRect.height()) {
            dy = this.viewRect.height() - rect.bottom;
        }
        if (rect.width() <= this.viewRect.width()) {
            this.allowIntercept = true;
            dx = ((this.viewRect.width() - rect.width()) / DOUBLE_TAP_SCALE_FACTOR) - rect.left;
        } else if (rect.left > 0.0f) {
            this.allowIntercept = true;
            dx = -rect.left;
        } else if (rect.right < this.viewRect.width()) {
            this.allowIntercept = true;
            dx = this.viewRect.width() - rect.right;
        } else {
            this.allowIntercept = false;
        }
        setTranslate(dx, dy);
    }

    RectF getDrawRect(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            this.drawRect.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
            matrix.mapRect(this.drawRect);
        }
        return this.drawRect;
    }

    Matrix getDrawMatrix() {
        this.drawMatrix.set(this.baseMatrix);
        this.drawMatrix.postConcat(this.updateMatrix);
        return this.drawMatrix;
    }

    void setImageMatrix() {
        updateMatrixBounds();
        setScaleType(ScaleType.MATRIX);
        setImageMatrix(getDrawMatrix());
    }

    void animateScale(float start, float end, final float px, final float py) {
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{start, end});
        animator.setDuration(SCALE_ANIMATION_DURATION);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                MultiTouchImageView.this.setScale(((Float) valueAnimator.getAnimatedValue()).floatValue() / MultiTouchImageView.this.getScale(), px, py);
                MultiTouchImageView.this.setImageMatrix();
            }
        });
        animator.start();
    }

    public boolean canBeSwiped() {
        return getScale() == MINIMUM_SCALE_FACTOR;
    }
}
