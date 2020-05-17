package com.github.paolorotolo.appintro;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressIndicatorController implements IndicatorController {
    public static final int DEFAULT_COLOR = 1;
    private static final int FIRST_PAGE_NUM = 0;
    private ProgressBar mProgressBar;
    int selectedDotColor = 1;
    int unselectedDotColor = 1;

    public View newInstance(@NonNull Context context) {
        this.mProgressBar = (ProgressBar) View.inflate(context, C0864R.layout.progress_indicator, null);
        if (this.selectedDotColor != 1) {
            this.mProgressBar.getProgressDrawable().setColorFilter(this.selectedDotColor, Mode.SRC_IN);
        }
        if (this.unselectedDotColor != 1) {
            this.mProgressBar.getIndeterminateDrawable().setColorFilter(this.unselectedDotColor, Mode.SRC_IN);
        }
        return this.mProgressBar;
    }

    public void initialize(int slideCount) {
        this.mProgressBar.setMax(slideCount);
        selectPosition(0);
    }

    public void selectPosition(int index) {
        this.mProgressBar.setProgress(index + 1);
    }

    public void setSelectedIndicatorColor(int color) {
        this.selectedDotColor = color;
        if (this.mProgressBar != null) {
            this.mProgressBar.getProgressDrawable().setColorFilter(color, Mode.SRC_IN);
        }
    }

    public void setUnselectedIndicatorColor(int color) {
        this.unselectedDotColor = color;
        if (this.mProgressBar != null) {
            this.mProgressBar.getIndeterminateDrawable().setColorFilter(color, Mode.SRC_IN);
        }
    }
}
