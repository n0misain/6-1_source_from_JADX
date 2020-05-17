package com.github.paolorotolo.appintro;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import com.github.paolorotolo.appintro.AppIntroBase.PagerOnPageChangeListener;
import java.lang.reflect.Field;

public final class AppIntroViewPager extends ViewPager {
    private static final int ON_ILLEGALLY_REQUESTED_NEXT_PAGE_MAX_INTERVAL = 1000;
    private float currentTouchDownX;
    private long illegallyRequestedNextPageLastCalled;
    private int lockPage = 0;
    private ScrollerCustomDuration mScroller = null;
    private OnNextPageRequestedListener nextPageRequestedListener;
    private boolean nextPagingEnabled = true;
    private OnPageChangeListener pageChangeListener;
    private boolean pagingEnabled = true;

    public interface OnNextPageRequestedListener {
        boolean onCanRequestNextPage();

        void onIllegallyRequestedNextPage();
    }

    public AppIntroViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewPagerScroller();
    }

    public void addOnPageChangeListener(PagerOnPageChangeListener listener) {
        super.addOnPageChangeListener(listener);
        this.pageChangeListener = listener;
    }

    public void goToNextSlide() {
        if (isRtl(getResources())) {
            setCurrentItem(getCurrentItem() - 1);
        } else {
            setCurrentItem(getCurrentItem() + 1);
        }
    }

    public void goToPreviousSlide() {
        try {
            if (isRtl(getResources())) {
                setCurrentItem(getCurrentItem() + 1);
            } else {
                setCurrentItem(getCurrentItem() - 1);
            }
        } catch (Exception e) {
            Log.e("AppIntroViewPager", "goToPreviousSlide: An error occured while switching to the previous slide. Was isFirstSlide checked before the call?");
        }
    }

    public boolean isFirstSlide(int size) {
        if (isRtl(getResources())) {
            if ((getCurrentItem() - size) + 1 == 0) {
                return true;
            }
            return false;
        } else if (getCurrentItem() != 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setCurrentItem(int item) {
        boolean invokeMeLater = false;
        if (super.getCurrentItem() == 0 && item == 0) {
            invokeMeLater = true;
        }
        super.setCurrentItem(item);
        if (invokeMeLater && this.pageChangeListener != null) {
            this.pageChangeListener.onPageSelected(0);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            this.currentTouchDownX = event.getX();
            return super.onInterceptTouchEvent(event);
        } else if (!checkPagingState(event) && !checkCanRequestNextPage(event)) {
            return super.onInterceptTouchEvent(event);
        } else {
            checkIllegallyRequestedNextPage(event);
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            this.currentTouchDownX = event.getX();
            return super.onTouchEvent(event);
        } else if (!checkPagingState(event) && !checkCanRequestNextPage(event)) {
            return super.onTouchEvent(event);
        } else {
            checkIllegallyRequestedNextPage(event);
            return false;
        }
    }

    private boolean checkPagingState(MotionEvent event) {
        if (!this.pagingEnabled) {
            return true;
        }
        if (!this.nextPagingEnabled) {
            if (event.getAction() == 0) {
                this.currentTouchDownX = event.getX();
            }
            if (event.getAction() == 2 && detectSwipeToEnd(event)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCanRequestNextPage(MotionEvent event) {
        return (this.nextPageRequestedListener == null || this.nextPageRequestedListener.onCanRequestNextPage()) ? false : true;
    }

    private void checkIllegallyRequestedNextPage(MotionEvent event) {
        if (event.getAction() == 2 && Math.abs(event.getX() - this.currentTouchDownX) >= ((float) 25) && System.currentTimeMillis() - this.illegallyRequestedNextPageLastCalled >= 1000) {
            this.illegallyRequestedNextPageLastCalled = System.currentTimeMillis();
            if (this.nextPageRequestedListener != null) {
                this.nextPageRequestedListener.onIllegallyRequestedNextPage();
            }
        }
    }

    private void initViewPagerScroller() {
        try {
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);
            this.mScroller = new ScrollerCustomDuration(getContext(), (Interpolator) interpolator.get(null));
            scroller.set(this, this.mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean detectSwipeToEnd(MotionEvent event) {
        boolean result = false;
        try {
            float diffX = event.getX() - this.currentTouchDownX;
            if (Math.abs(diffX) > 0.0f && diffX < 0.0f) {
                result = true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (isRtl(getResources())) {
            return !result;
        } else {
            return result;
        }
    }

    static boolean isRtl(Resources resources) {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        if (resources.getConfiguration().getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public void setOnNextPageRequestedListener(OnNextPageRequestedListener nextPageRequestedListener) {
        this.nextPageRequestedListener = nextPageRequestedListener;
    }

    public void setScrollDurationFactor(double scrollFactor) {
        this.mScroller.setScrollDurationFactor(scrollFactor);
    }

    public boolean isNextPagingEnabled() {
        return this.nextPagingEnabled;
    }

    public void setNextPagingEnabled(boolean nextPagingEnabled) {
        this.nextPagingEnabled = nextPagingEnabled;
        if (!nextPagingEnabled) {
            this.lockPage = getCurrentItem();
        }
    }

    public boolean isPagingEnabled() {
        return this.pagingEnabled;
    }

    public void setPagingEnabled(boolean pagingEnabled) {
        this.pagingEnabled = pagingEnabled;
    }

    public int getLockPage() {
        return this.lockPage;
    }

    public void setLockPage(int lockPage) {
        this.lockPage = lockPage;
    }
}
