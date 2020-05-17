package com.twitter.sdk.android.tweetcomposer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.squareup.picasso.Transformation;
import java.util.Arrays;

class RoundedCornerTransformation implements Transformation {
    final float[] radii;

    public static class Builder {
        int bottomLeftRadius;
        int bottomRightRadius;
        int topLeftRadius;
        int topRightRadius;

        public Builder setRadius(int radius) {
            this.topLeftRadius = radius;
            this.topRightRadius = radius;
            this.bottomRightRadius = radius;
            this.bottomLeftRadius = radius;
            return this;
        }

        public Builder setRadii(int topLeftRadius, int topRightRadius, int bottomRightRadius, int bottomLeftRadius) {
            this.topLeftRadius = topLeftRadius;
            this.topRightRadius = topRightRadius;
            this.bottomRightRadius = bottomRightRadius;
            this.bottomLeftRadius = bottomLeftRadius;
            return this;
        }

        RoundedCornerTransformation build() {
            if (this.topLeftRadius < 0 || this.topRightRadius < 0 || this.bottomRightRadius < 0 || this.bottomLeftRadius < 0) {
                throw new IllegalStateException("Radius must not be negative");
            }
            return new RoundedCornerTransformation(new float[]{(float) this.topLeftRadius, (float) this.topLeftRadius, (float) this.topRightRadius, (float) this.topRightRadius, (float) this.bottomRightRadius, (float) this.bottomRightRadius, (float) this.bottomLeftRadius, (float) this.bottomLeftRadius});
        }
    }

    RoundedCornerTransformation(float[] radii) {
        this.radii = radii;
    }

    public Bitmap transform(Bitmap source) {
        RectF rect = new RectF(0.0f, 0.0f, (float) source.getWidth(), (float) source.getHeight());
        Bitmap result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());
        BitmapShader bitmapShader = new BitmapShader(source, TileMode.CLAMP, TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);
        Path path = new Path();
        path.addRoundRect(rect, this.radii, Direction.CCW);
        new Canvas(result).drawPath(path, paint);
        source.recycle();
        return result;
    }

    public String key() {
        return "RoundedCornerTransformation(" + Arrays.toString(this.radii) + ")";
    }
}
