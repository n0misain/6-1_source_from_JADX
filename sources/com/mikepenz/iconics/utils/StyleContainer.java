package com.mikepenz.iconics.utils;

import android.text.ParcelableSpan;
import android.text.style.CharacterStyle;
import com.mikepenz.iconics.typeface.ITypeface;

public class StyleContainer {
    public int endIndex;
    public int flags = 33;
    public ITypeface font;
    public String icon;
    public ParcelableSpan span;
    public int startIndex;
    public CharacterStyle style;

    public StyleContainer(int startIndex, int endIndex, String icon, ITypeface font, int flags) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.icon = icon;
        this.font = font;
        this.flags = flags;
    }

    public StyleContainer(int startIndex, int endIndex, String icon, ITypeface font) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.icon = icon;
        this.font = font;
    }

    public StyleContainer(int startIndex, int endIndex, ParcelableSpan span) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.span = span;
    }

    public StyleContainer(int startIndex, int endIndex, ParcelableSpan span, int flags) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.span = span;
        this.flags = flags;
    }

    public StyleContainer(int startIndex, int endIndex, CharacterStyle style) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.style = style;
    }

    public StyleContainer(int startIndex, int endIndex, CharacterStyle style, int flags) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.style = style;
        this.flags = flags;
    }
}
