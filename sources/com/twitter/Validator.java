package com.twitter;

import com.twitter.Extractor.Entity;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class Validator {
    public static final int MAX_TWEET_LENGTH = 140;
    private Extractor extractor = new Extractor();
    protected int shortUrlLength = 23;
    protected int shortUrlLengthHttps = 23;

    public int getTweetLength(String text) {
        text = Normalizer.normalize(text, Form.NFC);
        int length = text.codePointCount(0, text.length());
        for (Entity urlEntity : this.extractor.extractURLsWithIndices(text)) {
            length = (length + (urlEntity.start - urlEntity.end)) + (urlEntity.value.toLowerCase().startsWith("https://") ? this.shortUrlLengthHttps : this.shortUrlLength);
        }
        return length;
    }

    public boolean isValidTweet(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        for (char c : text.toCharArray()) {
            if (c == '￾' || c == '﻿' || c == '￿') {
                return false;
            }
            if (c >= '‪' && c <= '‮') {
                return false;
            }
        }
        if (getTweetLength(text) <= 140) {
            return true;
        }
        return false;
    }

    public int getShortUrlLength() {
        return this.shortUrlLength;
    }

    public void setShortUrlLength(int shortUrlLength) {
        this.shortUrlLength = shortUrlLength;
    }

    public int getShortUrlLengthHttps() {
        return this.shortUrlLengthHttps;
    }

    public void setShortUrlLengthHttps(int shortUrlLengthHttps) {
        this.shortUrlLengthHttps = shortUrlLengthHttps;
    }
}
