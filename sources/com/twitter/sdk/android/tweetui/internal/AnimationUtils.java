package com.twitter.sdk.android.tweetui.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

class AnimationUtils {
    AnimationUtils() {
    }

    public static ViewPropertyAnimator fadeOut(final View from, int duration) {
        if (from.getVisibility() != 0) {
            return null;
        }
        from.clearAnimation();
        ViewPropertyAnimator animator = from.animate();
        animator.alpha(0.0f).setDuration((long) duration).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                from.setVisibility(4);
                from.setAlpha(1.0f);
            }
        });
        return animator;
    }

    public static ViewPropertyAnimator fadeIn(View to, int duration) {
        if (to.getVisibility() != 0) {
            to.setAlpha(0.0f);
            to.setVisibility(0);
        }
        to.clearAnimation();
        ViewPropertyAnimator animator = to.animate();
        animator.alpha(1.0f).setDuration((long) duration).setListener(null);
        return animator;
    }
}
