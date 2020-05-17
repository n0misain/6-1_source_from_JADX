package com.twitter.sdk.android.tweetui;

import android.text.TextUtils;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.MentionEntity;
import com.twitter.sdk.android.core.models.SymbolEntity;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.UrlEntity;
import java.net.IDN;
import java.text.BreakIterator;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.HttpUrl;

public class BasicTimelineFilter implements TimelineFilter {
    private final Set<String> handleConstraints;
    private final Set<String> hashTagConstraints;
    private final Set<String> keywordConstraints;
    private final Set<String> urlConstraints;
    private final BreakIterator wordIterator;

    static class IgnoreCaseComparator implements Comparator<String> {
        private final Collator collator;

        IgnoreCaseComparator(Locale locale) {
            this.collator = Collator.getInstance(locale);
            this.collator.setStrength(0);
        }

        public int compare(String string1, String string2) {
            return this.collator.compare(string1, string2);
        }
    }

    public BasicTimelineFilter(FilterValues filterValues) {
        this(filterValues, Locale.getDefault());
    }

    public BasicTimelineFilter(FilterValues filterValues, Locale locale) {
        Comparator<String> comparator = new IgnoreCaseComparator(locale);
        this.wordIterator = BreakIterator.getWordInstance(locale);
        this.keywordConstraints = new TreeSet(comparator);
        this.keywordConstraints.addAll(filterValues.keywords);
        this.hashTagConstraints = new TreeSet(comparator);
        for (String hashtag : filterValues.hashtags) {
            this.hashTagConstraints.add(normalizeHashtag(hashtag));
        }
        this.handleConstraints = new HashSet(filterValues.handles.size());
        for (String handle : filterValues.handles) {
            this.handleConstraints.add(normalizeHandle(handle));
        }
        this.urlConstraints = new HashSet(filterValues.urls.size());
        for (String url : filterValues.urls) {
            this.urlConstraints.add(normalizeUrl(url));
        }
    }

    public List<Tweet> filter(List<Tweet> tweets) {
        List<Tweet> filteredTweets = new ArrayList();
        for (int idx = 0; idx < tweets.size(); idx++) {
            Tweet tweet = (Tweet) tweets.get(idx);
            if (!shouldFilterTweet(tweet)) {
                filteredTweets.add(tweet);
            }
        }
        return Collections.unmodifiableList(filteredTweets);
    }

    public int totalFilters() {
        return ((this.keywordConstraints.size() + this.hashTagConstraints.size()) + this.urlConstraints.size()) + this.handleConstraints.size();
    }

    boolean shouldFilterTweet(Tweet tweet) {
        if (tweet.user != null && containsMatchingScreenName(tweet.user.screenName)) {
            return true;
        }
        if (tweet.entities == null || (!containsMatchingHashtag(tweet.entities.hashtags) && !containsMatchingSymbol(tweet.entities.symbols) && !containsMatchingUrl(tweet.entities.urls) && !containsMatchingMention(tweet.entities.userMentions))) {
            return containsMatchingText(tweet);
        }
        return true;
    }

    boolean containsMatchingText(Tweet tweet) {
        this.wordIterator.setText(tweet.text);
        int start = this.wordIterator.first();
        int end = this.wordIterator.next();
        while (end != -1) {
            if (this.keywordConstraints.contains(tweet.text.substring(start, end))) {
                return true;
            }
            start = end;
            end = this.wordIterator.next();
        }
        return false;
    }

    boolean containsMatchingHashtag(List<HashtagEntity> hashtags) {
        for (HashtagEntity entity : hashtags) {
            if (this.hashTagConstraints.contains(entity.text)) {
                return true;
            }
        }
        return false;
    }

    boolean containsMatchingSymbol(List<SymbolEntity> symbols) {
        for (SymbolEntity entity : symbols) {
            if (this.hashTagConstraints.contains(entity.text)) {
                return true;
            }
        }
        return false;
    }

    boolean containsMatchingUrl(List<UrlEntity> urls) {
        for (UrlEntity entity : urls) {
            if (this.urlConstraints.contains(normalizeUrl(entity.expandedUrl))) {
                return true;
            }
        }
        return false;
    }

    boolean containsMatchingMention(List<MentionEntity> mentions) {
        for (MentionEntity entity : mentions) {
            if (this.handleConstraints.contains(normalizeHandle(entity.screenName))) {
                return true;
            }
        }
        return false;
    }

    boolean containsMatchingScreenName(String screenName) {
        return this.handleConstraints.contains(normalizeHandle(screenName));
    }

    static String normalizeUrl(String url) {
        try {
            HttpUrl parsedUrl = HttpUrl.parse(url);
            if (parsedUrl == null || parsedUrl.host() == null) {
                return IDN.toASCII(url).toLowerCase(Locale.US);
            }
            return parsedUrl.host().toLowerCase(Locale.US);
        } catch (IllegalArgumentException e) {
            return url;
        }
    }

    static String normalizeHashtag(String hashtag) {
        if (TextUtils.isEmpty(hashtag)) {
            return hashtag;
        }
        char firstChar = hashtag.charAt(0);
        if (firstChar == '#' || firstChar == '＃' || firstChar == '$') {
            return hashtag.substring(1, hashtag.length());
        }
        return hashtag;
    }

    static String normalizeHandle(String handle) {
        if (TextUtils.isEmpty(handle)) {
            return handle;
        }
        char firstChar = handle.charAt(0);
        if (firstChar == '@' || firstChar == '＠') {
            handle = handle.substring(1, handle.length());
        }
        return handle.toLowerCase(Locale.US);
    }
}
