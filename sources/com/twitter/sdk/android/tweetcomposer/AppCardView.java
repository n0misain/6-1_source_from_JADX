package com.twitter.sdk.android.tweetcomposer;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.tweetcomposer.RoundedCornerTransformation.Builder;

public class AppCardView extends LinearLayout {
    ImageView appImageView;
    ViewGroup appInfoLayout;
    TextView appInstallButton;
    TextView appNameView;
    TextView appStoreNameView;

    public AppCardView(Context context) {
        this(context, null);
    }

    public AppCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppCardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    void init(Context context) {
        setOrientation(1);
        inflate(context, C1025R.layout.tw__app_card, this);
        findSubviews();
        setButtonColor();
    }

    void findSubviews() {
        this.appImageView = (ImageView) findViewById(C1025R.id.tw__app_image);
        this.appNameView = (TextView) findViewById(C1025R.id.tw__app_name);
        this.appStoreNameView = (TextView) findViewById(C1025R.id.tw__app_store_name);
        this.appInstallButton = (TextView) findViewById(C1025R.id.tw__app_install_button);
        this.appInfoLayout = (ViewGroup) findViewById(C1025R.id.tw__app_info_layout);
    }

    void setCard(Card card) {
        setImage(Uri.parse(card.imageUri));
        setAppName(card.appName);
    }

    void setImage(Uri uri) {
        int radius = getResources().getDimensionPixelSize(C1025R.dimen.tw__card_radius_medium);
        Picasso.with(getContext()).load(uri).transform(new Builder().setRadii(radius, radius, 0, 0).build()).fit().centerCrop().into(this.appImageView);
    }

    void setAppName(String name) {
        this.appNameView.setText(name);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = getResources().getDimensionPixelSize(C1025R.dimen.tw__card_maximum_width);
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (maxWidth > 0 && maxWidth < measuredWidth) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.getMode(widthMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    void setButtonColor() {
        this.appInstallButton.setTextColor(getResources().getColor(C1025R.color.tw__composer_blue_text));
    }
}
