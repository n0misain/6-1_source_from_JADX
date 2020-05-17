package com.mikepenz.iconics;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.Log;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;
import com.mikepenz.iconics.utils.Utils;
import io.fabric.sdk.android.services.events.EventsFilesManager;

public class IconicsDrawable extends Drawable {
    @Deprecated
    public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;
    @Deprecated
    public static final int ANDROID_ACTIONBAR_ICON_SIZE_PADDING_DP = 1;
    @Dimension(unit = 0)
    public static final int TOOLBAR_ICON_PADDING = 1;
    @Dimension(unit = 0)
    public static final int TOOLBAR_ICON_SIZE = 24;
    private int mAlpha = 255;
    private int mBackgroundColor;
    private Paint mBackgroundPaint;
    private ColorFilter mColorFilter;
    private Context mContext;
    private int mContourColor;
    private Paint mContourPaint;
    private int mContourWidth;
    private boolean mDrawContour;
    private IIcon mIcon;
    private ColorStateList mIconColor;
    private int mIconOffsetX = 0;
    private int mIconOffsetY = 0;
    private int mIconPadding;
    private Paint mIconPaint;
    private Rect mPaddingBounds;
    private Path mPath;
    private RectF mPathBounds;
    private String mPlainIcon;
    private boolean mRespectFontBounds = false;
    private int mRoundedCornerRx = -1;
    private int mRoundedCornerRy = -1;
    private int mSizeX = -1;
    private int mSizeY = -1;
    private ColorStateList mTint;
    private ColorFilter mTintFilter;
    private Mode mTintMode = Mode.SRC_IN;

    public IconicsDrawable(Context context) {
        this.mContext = context.getApplicationContext();
        prepare();
        icon(Character.valueOf(' '));
    }

    public IconicsDrawable(Context context, Character icon) {
        this.mContext = context.getApplicationContext();
        prepare();
        icon(icon);
    }

    public IconicsDrawable(Context context, String icon) {
        this.mContext = context.getApplicationContext();
        prepare();
        try {
            ITypeface font = Iconics.findFont(context, icon.substring(0, 3));
            icon = icon.replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            icon(font.getIcon(icon));
        } catch (Exception e) {
            Log.e(Iconics.TAG, "Wrong icon name: " + icon);
        }
    }

    public IconicsDrawable(Context context, IIcon icon) {
        this.mContext = context.getApplicationContext();
        prepare();
        icon(icon);
    }

    protected IconicsDrawable(Context context, ITypeface typeface, IIcon icon) {
        this.mContext = context.getApplicationContext();
        prepare();
        icon(typeface, icon);
    }

    private void prepare() {
        this.mIconPaint = new TextPaint(1);
        this.mIconPaint.setStyle(Style.FILL);
        this.mIconPaint.setTextAlign(Align.CENTER);
        this.mIconPaint.setUnderlineText(false);
        this.mIconPaint.setAntiAlias(true);
        this.mBackgroundPaint = new Paint(1);
        this.mContourPaint = new Paint(1);
        this.mContourPaint.setStyle(Style.STROKE);
        this.mPath = new Path();
        this.mPathBounds = new RectF();
        this.mPaddingBounds = new Rect();
    }

    public IconicsDrawable icon(String icon) {
        try {
            ITypeface font = Iconics.findFont(this.mContext, icon.substring(0, 3));
            icon = icon.replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            icon(font.getIcon(icon));
        } catch (Exception e) {
            Log.e(Iconics.TAG, "Wrong icon name: " + icon);
        }
        return this;
    }

    public IconicsDrawable icon(Character icon) {
        return iconText(icon.toString());
    }

    public IconicsDrawable iconText(String icon) {
        this.mPlainIcon = icon;
        this.mIcon = null;
        this.mIconPaint.setTypeface(Typeface.DEFAULT);
        invalidateSelf();
        return this;
    }

    public IconicsDrawable icon(IIcon icon) {
        this.mIcon = icon;
        this.mPlainIcon = null;
        this.mIconPaint.setTypeface(icon.getTypeface().getTypeface(this.mContext));
        invalidateSelf();
        return this;
    }

    protected IconicsDrawable icon(ITypeface typeface, IIcon icon) {
        this.mIcon = icon;
        this.mIconPaint.setTypeface(typeface.getTypeface(this.mContext));
        invalidateSelf();
        return this;
    }

    public IconicsDrawable respectFontBounds(boolean respectBounds) {
        this.mRespectFontBounds = respectBounds;
        invalidateSelf();
        return this;
    }

    public IconicsDrawable color(@ColorInt int color) {
        this.mIconColor = ColorStateList.valueOf(color);
        updateIconColor();
        return this;
    }

    public IconicsDrawable color(ColorStateList colors) {
        if (colors != null) {
            this.mIconColor = colors;
            updateIconColor();
        }
        return this;
    }

    public IconicsDrawable colorRes(@ColorRes int colorRes) {
        return color(ContextCompat.getColor(this.mContext, colorRes));
    }

    public int getColor() {
        return this.mIconColor.getDefaultColor();
    }

    public ColorStateList getColorList() {
        return this.mIconColor;
    }

    public int getContourColor() {
        return this.mContourColor;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public IconicsDrawable iconOffsetXRes(@DimenRes int iconOffsetXRes) {
        return iconOffsetXPx(this.mContext.getResources().getDimensionPixelSize(iconOffsetXRes));
    }

    public IconicsDrawable iconOffsetXDp(@Dimension(unit = 0) int iconOffsetXDp) {
        return iconOffsetXPx(Utils.convertDpToPx(this.mContext, (float) iconOffsetXDp));
    }

    public IconicsDrawable iconOffsetXPx(@Dimension(unit = 1) int iconOffsetX) {
        this.mIconOffsetX = iconOffsetX;
        return this;
    }

    public IconicsDrawable iconOffsetYRes(@DimenRes int iconOffsetYRes) {
        return iconOffsetYPx(this.mContext.getResources().getDimensionPixelSize(iconOffsetYRes));
    }

    public IconicsDrawable iconOffsetYDp(@Dimension(unit = 0) int iconOffsetYDp) {
        return iconOffsetYPx(Utils.convertDpToPx(this.mContext, (float) iconOffsetYDp));
    }

    public IconicsDrawable iconOffsetYPx(@Dimension(unit = 1) int iconOffsetY) {
        this.mIconOffsetY = iconOffsetY;
        return this;
    }

    public IconicsDrawable paddingRes(@DimenRes int dimenRes) {
        return paddingPx(this.mContext.getResources().getDimensionPixelSize(dimenRes));
    }

    public IconicsDrawable paddingDp(@Dimension(unit = 0) int iconPadding) {
        return paddingPx(Utils.convertDpToPx(this.mContext, (float) iconPadding));
    }

    public IconicsDrawable paddingPx(@Dimension(unit = 1) int iconPadding) {
        if (this.mIconPadding != iconPadding) {
            this.mIconPadding = iconPadding;
            if (this.mDrawContour) {
                this.mIconPadding += this.mContourWidth;
            }
            invalidateSelf();
        }
        return this;
    }

    @Deprecated
    public IconicsDrawable actionBarSize() {
        return sizeDp(24);
    }

    public IconicsDrawable actionBar() {
        sizeDp(24);
        paddingDp(1);
        return this;
    }

    public IconicsDrawable sizeRes(@DimenRes int dimenRes) {
        return sizePx(this.mContext.getResources().getDimensionPixelSize(dimenRes));
    }

    public IconicsDrawable sizeDp(@Dimension(unit = 0) int size) {
        return sizePx(Utils.convertDpToPx(this.mContext, (float) size));
    }

    public IconicsDrawable sizePx(@Dimension(unit = 1) int size) {
        this.mSizeX = size;
        this.mSizeY = size;
        setBounds(0, 0, size, size);
        invalidateSelf();
        return this;
    }

    public IconicsDrawable sizeResX(@DimenRes int dimenResX) {
        return sizePxX(this.mContext.getResources().getDimensionPixelSize(dimenResX));
    }

    public IconicsDrawable sizeDpX(@Dimension(unit = 0) int sizeX) {
        return sizePxX(Utils.convertDpToPx(this.mContext, (float) sizeX));
    }

    public IconicsDrawable sizePxX(@Dimension(unit = 1) int sizeX) {
        this.mSizeX = sizeX;
        setBounds(0, 0, this.mSizeX, this.mSizeY);
        invalidateSelf();
        return this;
    }

    public IconicsDrawable sizeResY(@DimenRes int dimenResY) {
        return sizePxY(this.mContext.getResources().getDimensionPixelSize(dimenResY));
    }

    public IconicsDrawable sizeDpY(@Dimension(unit = 0) int sizeY) {
        return sizePxY(Utils.convertDpToPx(this.mContext, (float) sizeY));
    }

    public IconicsDrawable sizePxY(@Dimension(unit = 1) int sizeY) {
        this.mSizeY = sizeY;
        setBounds(0, 0, this.mSizeX, this.mSizeY);
        invalidateSelf();
        return this;
    }

    public IconicsDrawable contourColor(@ColorInt int contourColor) {
        this.mContourPaint.setColor(Color.rgb(Color.red(contourColor), Color.green(contourColor), Color.blue(contourColor)));
        this.mContourPaint.setAlpha(Color.alpha(contourColor));
        this.mContourColor = contourColor;
        invalidateSelf();
        return this;
    }

    public IconicsDrawable contourColorRes(@ColorRes int contourColorRes) {
        return contourColor(ContextCompat.getColor(this.mContext, contourColorRes));
    }

    public IconicsDrawable backgroundColor(@ColorInt int backgroundColor) {
        this.mBackgroundPaint.setColor(backgroundColor);
        this.mBackgroundColor = backgroundColor;
        this.mRoundedCornerRx = 0;
        this.mRoundedCornerRy = 0;
        return this;
    }

    public IconicsDrawable backgroundColorRes(@ColorRes int backgroundColorRes) {
        return backgroundColor(ContextCompat.getColor(this.mContext, backgroundColorRes));
    }

    public IconicsDrawable roundedCornersRxRes(@DimenRes int roundedCornerRxRes) {
        this.mRoundedCornerRx = this.mContext.getResources().getDimensionPixelSize(roundedCornerRxRes);
        return this;
    }

    public IconicsDrawable roundedCornersRxDp(@Dimension(unit = 0) int roundedCornerRxDp) {
        this.mRoundedCornerRx = Utils.convertDpToPx(this.mContext, (float) roundedCornerRxDp);
        return this;
    }

    public IconicsDrawable roundedCornersRxPx(@Dimension(unit = 1) int roundedCornerRxPx) {
        this.mRoundedCornerRx = roundedCornerRxPx;
        return this;
    }

    public IconicsDrawable roundedCornersRyRes(@DimenRes int roundedCornerRyRes) {
        this.mRoundedCornerRy = this.mContext.getResources().getDimensionPixelSize(roundedCornerRyRes);
        return this;
    }

    public IconicsDrawable roundedCornersRyDp(@Dimension(unit = 0) int roundedCornerRyDp) {
        this.mRoundedCornerRy = Utils.convertDpToPx(this.mContext, (float) roundedCornerRyDp);
        return this;
    }

    public IconicsDrawable roundedCornersRyPx(@Dimension(unit = 1) int roundedCornerRyPx) {
        this.mRoundedCornerRy = roundedCornerRyPx;
        return this;
    }

    public IconicsDrawable roundedCornersRes(@DimenRes int roundedCornerRes) {
        this.mRoundedCornerRx = this.mContext.getResources().getDimensionPixelSize(roundedCornerRes);
        this.mRoundedCornerRy = this.mRoundedCornerRx;
        return this;
    }

    public IconicsDrawable roundedCornersDp(@Dimension(unit = 0) int roundedCornerDp) {
        this.mRoundedCornerRx = Utils.convertDpToPx(this.mContext, (float) roundedCornerDp);
        this.mRoundedCornerRy = this.mRoundedCornerRx;
        return this;
    }

    public IconicsDrawable roundedCornersPx(@Dimension(unit = 1) int roundedCornerPx) {
        this.mRoundedCornerRx = roundedCornerPx;
        this.mRoundedCornerRy = this.mRoundedCornerRx;
        return this;
    }

    public IconicsDrawable contourWidthRes(@DimenRes int contourWidthRes) {
        return contourWidthPx(this.mContext.getResources().getDimensionPixelSize(contourWidthRes));
    }

    public IconicsDrawable contourWidthDp(@Dimension(unit = 0) int contourWidthDp) {
        return contourWidthPx(Utils.convertDpToPx(this.mContext, (float) contourWidthDp));
    }

    public IconicsDrawable contourWidthPx(@Dimension(unit = 1) int contourWidth) {
        this.mContourWidth = contourWidth;
        this.mContourPaint.setStrokeWidth((float) this.mContourWidth);
        drawContour(true);
        invalidateSelf();
        return this;
    }

    public IconicsDrawable drawContour(boolean drawContour) {
        if (this.mDrawContour != drawContour) {
            this.mDrawContour = drawContour;
            if (this.mDrawContour) {
                this.mIconPadding += this.mContourWidth;
            } else {
                this.mIconPadding -= this.mContourWidth;
            }
            invalidateSelf();
        }
        return this;
    }

    public IconicsDrawable colorFilter(ColorFilter cf) {
        setColorFilter(cf);
        return this;
    }

    public IconicsDrawable alpha(int alpha) {
        setAlpha(alpha);
        return this;
    }

    public IconicsDrawable style(Style style) {
        this.mIconPaint.setStyle(style);
        return this;
    }

    public IconicsDrawable typeface(Typeface typeface) {
        this.mIconPaint.setTypeface(typeface);
        return this;
    }

    public void draw(Canvas canvas) {
        if (this.mIcon != null || this.mPlainIcon != null) {
            Rect viewBounds = getBounds();
            updatePaddingBounds(viewBounds);
            updateTextSize(viewBounds);
            offsetIcon(viewBounds);
            if (this.mBackgroundPaint != null && this.mRoundedCornerRy > -1 && this.mRoundedCornerRx > -1) {
                canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) viewBounds.width(), (float) viewBounds.height()), (float) this.mRoundedCornerRx, (float) this.mRoundedCornerRy, this.mBackgroundPaint);
            }
            this.mPath.close();
            if (this.mDrawContour) {
                canvas.drawPath(this.mPath, this.mContourPaint);
            }
            this.mIconPaint.setAlpha(this.mAlpha);
            this.mIconPaint.setColorFilter(this.mColorFilter == null ? this.mTintFilter : this.mColorFilter);
            canvas.drawPath(this.mPath, this.mIconPaint);
        }
    }

    public void setTint(int tintColor) {
        setTintList(ColorStateList.valueOf(tintColor));
    }

    public void setTintList(ColorStateList tint) {
        this.mTint = tint;
        this.mTintFilter = updateTintFilter(tint, this.mTintMode);
        invalidateSelf();
    }

    public void setTintMode(@NonNull Mode tintMode) {
        this.mTintMode = tintMode;
        this.mTintFilter = updateTintFilter(this.mTint, tintMode);
        invalidateSelf();
    }

    protected void onBoundsChange(Rect bounds) {
        offsetIcon(bounds);
        this.mPath.close();
        super.onBoundsChange(bounds);
    }

    public boolean isStateful() {
        return true;
    }

    public boolean setState(@NonNull int[] stateSet) {
        return super.setState(stateSet) || !((this.mIconColor == null || !this.mIconColor.isStateful()) && this.mColorFilter == null && this.mTintFilter == null);
    }

    public int getOpacity() {
        if (this.mTintFilter != null || this.mIconPaint.getColorFilter() != null) {
            return -3;
        }
        switch (getAlpha()) {
            case 0:
                return -2;
            case 255:
                return -1;
            default:
                return -3;
        }
    }

    protected boolean onStateChange(int[] stateSet) {
        boolean ret = false;
        if (this.mIconColor != null && this.mIconColor.isStateful()) {
            updateIconColor();
            ret = true;
        }
        if (this.mTint == null || this.mTintMode == null) {
            return ret;
        }
        this.mTintFilter = updateTintFilter(this.mTint, this.mTintMode);
        invalidateSelf();
        return true;
    }

    public int getIntrinsicWidth() {
        return this.mSizeX;
    }

    public int getIntrinsicHeight() {
        return this.mSizeY;
    }

    public void setAlpha(int alpha) {
        this.mIconPaint.setAlpha(alpha);
        this.mAlpha = alpha;
        invalidateSelf();
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public IIcon getIcon() {
        return this.mIcon;
    }

    public String getPlainIcon() {
        return this.mPlainIcon;
    }

    public int getCompatAlpha() {
        return this.mAlpha;
    }

    public void setColorFilter(ColorFilter cf) {
        this.mColorFilter = cf;
        invalidateSelf();
    }

    public void clearColorFilter() {
        this.mColorFilter = null;
        invalidateSelf();
    }

    public Bitmap toBitmap() {
        if (this.mSizeX == -1 || this.mSizeY == -1) {
            actionBar();
        }
        Bitmap bitmap = Bitmap.createBitmap(getIntrinsicWidth(), getIntrinsicHeight(), Config.ARGB_8888);
        style(Style.FILL);
        Canvas canvas = new Canvas(bitmap);
        setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        draw(canvas);
        return bitmap;
    }

    private void updatePaddingBounds(Rect viewBounds) {
        if (this.mIconPadding >= 0 && this.mIconPadding * 2 <= viewBounds.width() && this.mIconPadding * 2 <= viewBounds.height()) {
            this.mPaddingBounds.set(viewBounds.left + this.mIconPadding, viewBounds.top + this.mIconPadding, viewBounds.right - this.mIconPadding, viewBounds.bottom - this.mIconPadding);
        }
    }

    private void updateTextSize(Rect viewBounds) {
        float textSize = ((float) viewBounds.height()) * ((float) (this.mRespectFontBounds ? 1 : 2));
        this.mIconPaint.setTextSize(textSize);
        String textValue = this.mIcon != null ? String.valueOf(this.mIcon.getCharacter()) : String.valueOf(this.mPlainIcon);
        this.mIconPaint.getTextPath(textValue, 0, textValue.length(), 0.0f, (float) viewBounds.height(), this.mPath);
        this.mPath.computeBounds(this.mPathBounds, true);
        if (!this.mRespectFontBounds) {
            float delta;
            float deltaWidth = ((float) this.mPaddingBounds.width()) / this.mPathBounds.width();
            float deltaHeight = ((float) this.mPaddingBounds.height()) / this.mPathBounds.height();
            if (deltaWidth < deltaHeight) {
                delta = deltaWidth;
            } else {
                delta = deltaHeight;
            }
            this.mIconPaint.setTextSize(textSize * delta);
            this.mIconPaint.getTextPath(textValue, 0, textValue.length(), 0.0f, (float) viewBounds.height(), this.mPath);
            this.mPath.computeBounds(this.mPathBounds, true);
        }
    }

    private void offsetIcon(Rect viewBounds) {
        this.mPath.offset(((float) this.mIconOffsetX) + ((((float) viewBounds.centerX()) - (this.mPathBounds.width() / 2.0f)) - this.mPathBounds.left), ((float) this.mIconOffsetY) + ((((float) viewBounds.centerY()) - (this.mPathBounds.height() / 2.0f)) - this.mPathBounds.top));
    }

    private void updateIconColor() {
        boolean invalidate = false;
        int color = this.mIconColor.getColorForState(getState(), this.mIconColor.getDefaultColor());
        int iconColor = Color.rgb(Color.red(color), Color.green(color), Color.blue(color));
        if (iconColor != this.mIconPaint.getColor()) {
            this.mIconPaint.setColor(iconColor);
            invalidate = true;
        }
        int alpha = Color.alpha(color);
        if (alpha != 255 && alpha != this.mAlpha) {
            setAlpha(alpha);
        } else if (invalidate) {
            invalidateSelf();
        }
    }

    private PorterDuffColorFilter updateTintFilter(ColorStateList tint, Mode tintMode) {
        if (tint == null || tintMode == null) {
            return null;
        }
        return new PorterDuffColorFilter(tint.getColorForState(getState(), 0), tintMode);
    }

    public IconicsDrawable clone() {
        IconicsDrawable iconicsDrawable = new IconicsDrawable(this.mContext).paddingPx(this.mIconPadding).roundedCornersRxPx(this.mRoundedCornerRx).roundedCornersRyPx(this.mRoundedCornerRy).sizePxX(this.mSizeX).sizePxY(this.mSizeY).iconOffsetXPx(this.mIconOffsetX).iconOffsetYPx(this.mIconOffsetY).contourColor(this.mContourColor).contourWidthPx(this.mContourWidth).backgroundColor(this.mBackgroundColor).color(this.mIconColor).alpha(this.mAlpha).drawContour(this.mDrawContour).typeface(this.mIconPaint.getTypeface());
        if (this.mIcon != null) {
            iconicsDrawable.icon(this.mIcon);
        } else if (this.mPlainIcon != null) {
            iconicsDrawable.iconText(this.mPlainIcon);
        }
        return iconicsDrawable;
    }
}
