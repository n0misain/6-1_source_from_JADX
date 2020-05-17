package com.twitter.sdk.android.tweetcomposer;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.internal.UserUtils;
import com.twitter.sdk.android.core.internal.UserUtils.AvatarSize;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetcomposer.ComposerController.ComposerCallbacks;
import com.twitter.sdk.android.tweetcomposer.internal.util.ObservableScrollView;
import com.twitter.sdk.android.tweetcomposer.internal.util.ObservableScrollView.ScrollViewListener;
import java.util.Locale;

public class ComposerView extends LinearLayout {
    ImageView avatarView;
    ComposerCallbacks callbacks;
    ViewGroup cardView;
    TextView charCountView;
    ImageView closeView;
    View divider;
    private Picasso imageLoader;
    ColorDrawable mediaBg;
    ObservableScrollView scrollView;
    Button tweetButton;
    EditText tweetEditView;

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$1 */
    class C10201 implements OnClickListener {
        C10201() {
        }

        public void onClick(View view) {
            ComposerView.this.callbacks.onCloseClick();
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$2 */
    class C10212 implements OnClickListener {
        C10212() {
        }

        public void onClick(View view) {
            ComposerView.this.callbacks.onTweetPost(ComposerView.this.getTweetText());
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$3 */
    class C10223 implements OnEditorActionListener {
        C10223() {
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            ComposerView.this.callbacks.onTweetPost(ComposerView.this.getTweetText());
            return true;
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$4 */
    class C10234 implements TextWatcher {
        C10234() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            ComposerView.this.callbacks.onTextChanged(ComposerView.this.getTweetText());
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.ComposerView$5 */
    class C10245 implements ScrollViewListener {
        C10245() {
        }

        public void onScrollChanged(int scrollY) {
            if (scrollY > 0) {
                ComposerView.this.divider.setVisibility(0);
            } else {
                ComposerView.this.divider.setVisibility(4);
            }
        }
    }

    public ComposerView(Context context) {
        this(context, null);
    }

    public ComposerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ComposerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.imageLoader = Picasso.with(getContext());
        this.mediaBg = new ColorDrawable(context.getResources().getColor(C1025R.color.tw__composer_light_gray));
        inflate(context, C1025R.layout.tw__composer_view, this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        findSubviews();
        this.closeView.setOnClickListener(new C10201());
        this.tweetButton.setOnClickListener(new C10212());
        this.tweetEditView.setOnEditorActionListener(new C10223());
        this.tweetEditView.addTextChangedListener(new C10234());
        this.scrollView.setScrollViewListener(new C10245());
    }

    void findSubviews() {
        this.avatarView = (ImageView) findViewById(C1025R.id.tw__author_avatar);
        this.closeView = (ImageView) findViewById(C1025R.id.tw__composer_close);
        this.tweetEditView = (EditText) findViewById(C1025R.id.tw__edit_tweet);
        this.charCountView = (TextView) findViewById(C1025R.id.tw__char_count);
        this.tweetButton = (Button) findViewById(C1025R.id.tw__post_tweet);
        this.scrollView = (ObservableScrollView) findViewById(C1025R.id.tw__composer_scroll_view);
        this.divider = findViewById(C1025R.id.tw__composer_profile_divider);
        this.cardView = (ViewGroup) findViewById(C1025R.id.tw__card_view);
    }

    void setCallbacks(ComposerCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    void setProfilePhotoView(User user) {
        String url = UserUtils.getProfileImageUrlHttps(user, AvatarSize.REASONABLY_SMALL);
        if (this.imageLoader != null) {
            this.imageLoader.load(url).placeholder(this.mediaBg).into(this.avatarView);
        }
    }

    String getTweetText() {
        return this.tweetEditView.getText().toString();
    }

    void setTweetText(String text) {
        this.tweetEditView.setText(text);
    }

    void setCharCount(int remainingCount) {
        this.charCountView.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(remainingCount)}));
    }

    void setCharCountTextStyle(int textStyleResId) {
        this.charCountView.setTextAppearance(getContext(), textStyleResId);
    }

    void postTweetEnabled(boolean enabled) {
        this.tweetButton.setEnabled(enabled);
    }

    void setCardView(View card) {
        this.cardView.addView(card);
        this.cardView.setVisibility(0);
    }
}
