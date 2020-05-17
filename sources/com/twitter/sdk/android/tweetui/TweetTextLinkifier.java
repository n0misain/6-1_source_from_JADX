package com.twitter.sdk.android.tweetui;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import com.twitter.sdk.android.tweetui.internal.ClickableLinkSpan;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

final class TweetTextLinkifier {
    static final Pattern QUOTED_STATUS_URL = Pattern.compile("^https?://twitter\\.com(/#!)?/\\w+/status/\\d+$");
    static final Pattern VINE_URL = Pattern.compile("^https?://vine\\.co(/#!)?/v/\\w+$");

    /* renamed from: com.twitter.sdk.android.tweetui.TweetTextLinkifier$1 */
    static class C10491 implements Comparator<FormattedUrlEntity> {
        C10491() {
        }

        public int compare(FormattedUrlEntity lhs, FormattedUrlEntity rhs) {
            if (lhs == null && rhs != null) {
                return -1;
            }
            if (lhs != null && rhs == null) {
                return 1;
            }
            if (lhs == null && rhs == null) {
                return 0;
            }
            if (lhs.start >= rhs.start) {
                return lhs.start > rhs.start ? 1 : 0;
            } else {
                return -1;
            }
        }
    }

    private TweetTextLinkifier() {
    }

    static CharSequence linkifyUrls(FormattedTweetText tweetText, LinkClickListener listener, int linkColor, int linkHighlightColor, boolean stripVineCard) {
        if (tweetText == null) {
            return null;
        }
        if (TextUtils.isEmpty(tweetText.text)) {
            return tweetText.text;
        }
        SpannableStringBuilder spannable = new SpannableStringBuilder(tweetText.text);
        List<FormattedUrlEntity> combined = mergeAndSortEntities(tweetText.urlEntities, tweetText.mediaEntities);
        addUrlEntities(spannable, combined, getEntityToStrip(tweetText.text, combined, stripVineCard), listener, linkColor, linkHighlightColor);
        return trimEnd(spannable);
    }

    static CharSequence trimEnd(CharSequence charSequence) {
        int length = charSequence.length();
        while (length > 0 && charSequence.charAt(length - 1) <= ' ') {
            length--;
        }
        return length < charSequence.length() ? charSequence.subSequence(0, length) : charSequence;
    }

    static List<FormattedUrlEntity> mergeAndSortEntities(List<FormattedUrlEntity> urls, List<FormattedMediaEntity> media) {
        if (media == null) {
            return urls;
        }
        ArrayList<FormattedUrlEntity> combined = new ArrayList(urls);
        combined.addAll(media);
        Collections.sort(combined, new C10491());
        return combined;
    }

    private static void addUrlEntities(SpannableStringBuilder spannable, List<FormattedUrlEntity> entities, FormattedUrlEntity strippedEntity, LinkClickListener listener, int linkColor, int linkHighlightColor) {
        if (entities != null && !entities.isEmpty()) {
            int offset = 0;
            for (final FormattedUrlEntity url : entities) {
                int start = url.start - offset;
                int end = url.end - offset;
                if (start >= 0 && end <= spannable.length()) {
                    int len;
                    if (strippedEntity != null && strippedEntity.start == url.start) {
                        spannable.replace(start, end, "");
                        len = end - start;
                        end -= len;
                        offset += len;
                    } else if (!TextUtils.isEmpty(url.displayUrl)) {
                        spannable.replace(start, end, url.displayUrl);
                        len = end - (url.displayUrl.length() + start);
                        offset += len;
                        final LinkClickListener linkClickListener = listener;
                        spannable.setSpan(new ClickableLinkSpan(linkHighlightColor, linkColor, false) {
                            public void onClick(View widget) {
                                if (linkClickListener != null) {
                                    linkClickListener.onUrlClicked(url.url);
                                }
                            }
                        }, start, end - len, 33);
                    }
                }
            }
        }
    }

    static FormattedUrlEntity getEntityToStrip(String tweetText, List<FormattedUrlEntity> combined, boolean stripVineCard) {
        if (combined.isEmpty()) {
            return null;
        }
        FormattedUrlEntity urlEntity = (FormattedUrlEntity) combined.get(combined.size() - 1);
        if (stripLtrMarker(tweetText).endsWith(urlEntity.url)) {
            if (isPhotoEntity(urlEntity) || isQuotedStatus(urlEntity)) {
                return urlEntity;
            }
            if (stripVineCard && isVineCard(urlEntity)) {
                return urlEntity;
            }
        }
        return null;
    }

    static String stripLtrMarker(String tweetText) {
        if (tweetText.endsWith(Character.toString('‎'))) {
            return tweetText.substring(0, tweetText.length() - 1);
        }
        return tweetText;
    }

    static boolean isPhotoEntity(FormattedUrlEntity urlEntity) {
        return (urlEntity instanceof FormattedMediaEntity) && "photo".equals(((FormattedMediaEntity) urlEntity).type);
    }

    static boolean isQuotedStatus(FormattedUrlEntity urlEntity) {
        return QUOTED_STATUS_URL.matcher(urlEntity.expandedUrl).find();
    }

    static boolean isVineCard(FormattedUrlEntity urlEntity) {
        return VINE_URL.matcher(urlEntity.expandedUrl).find();
    }
}
