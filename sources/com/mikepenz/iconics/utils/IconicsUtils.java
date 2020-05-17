package com.mikepenz.iconics.utils;

import android.content.Context;
import android.text.Editable;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.util.Log;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IconicsUtils {
    public static char ICON_END = '}';
    public static char ICON_START = '{';

    public static LinkedList<StyleContainer> findIconsFromEditable(Editable editable, HashMap<String, ITypeface> fonts) {
        LinkedList<StyleContainer> styleContainers = new LinkedList();
        LinkedList<StyleContainer> existingSpans = new LinkedList();
        for (ParcelableSpan span : (ParcelableSpan[]) editable.getSpans(0, editable.length(), ParcelableSpan.class)) {
            existingSpans.add(new StyleContainer(editable.getSpanStart(span), editable.getSpanEnd(span), span, editable.getSpanFlags(span)));
        }
        for (CharacterStyle span2 : (CharacterStyle[]) editable.getSpans(0, editable.length(), CharacterStyle.class)) {
            existingSpans.add(new StyleContainer(editable.getSpanStart(span2), editable.getSpanEnd(span2), span2, editable.getSpanFlags(span2)));
        }
        try {
            editable.clearSpans();
        } catch (Exception e) {
        }
        int iconStart = -1;
        int i = 0;
        while (i < editable.length()) {
            Character c = Character.valueOf(editable.charAt(i));
            if (c.charValue() == ICON_START) {
                iconStart = i;
            } else if (c.charValue() == ICON_END) {
                if (iconStart > -1) {
                    StyleContainer styleContainer = placeFontIcon(editable, iconStart, i, fonts);
                    if (styleContainer != null) {
                        styleContainers.add(styleContainer);
                        Iterator it = existingSpans.iterator();
                        while (it.hasNext()) {
                            StyleContainer existingStyleContainer = (StyleContainer) it.next();
                            if (existingStyleContainer.startIndex > i) {
                                existingStyleContainer.startIndex -= i - iconStart;
                                existingStyleContainer.endIndex -= i - iconStart;
                            } else if (existingStyleContainer.endIndex > i) {
                                existingStyleContainer.endIndex -= i - iconStart;
                            }
                        }
                        i = iconStart;
                    }
                }
                iconStart = -1;
            }
            i++;
        }
        styleContainers.addAll(existingSpans);
        return styleContainers;
    }

    private static StyleContainer placeFontIcon(Editable editable, int iconStart, int iconEnd, HashMap<String, ITypeface> fonts) {
        if (iconEnd - iconStart >= 6) {
            String iconString = editable.subSequence(iconStart + 1, iconEnd).toString().replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            String fontKey = editable.subSequence(iconStart + 1, iconStart + 4).toString();
            try {
                ITypeface typeface = (ITypeface) fonts.get(fontKey);
                if (typeface != null) {
                    IIcon icon = typeface.getIcon(iconString);
                    if (icon != null) {
                        editable.replace(iconStart, iconEnd + 1, String.valueOf(icon.getCharacter()));
                        return new StyleContainer(iconStart, iconStart + 1, iconString, (ITypeface) fonts.get(fontKey));
                    }
                    Log.e(Iconics.TAG, "Wrong icon name: " + iconString);
                } else {
                    Log.e(Iconics.TAG, "Wrong fontId: " + iconString);
                }
            } catch (IllegalArgumentException e) {
                Log.e(Iconics.TAG, "Wrong icon name: " + iconString);
            }
        }
        return null;
    }

    public static TextStyleContainer findIcons(Spanned spannable, HashMap<String, ITypeface> fonts) {
        LinkedList<StyleContainer> styleContainers = new LinkedList();
        LinkedList<StyleContainer> existingSpans = new LinkedList();
        for (ParcelableSpan span : (ParcelableSpan[]) spannable.getSpans(0, spannable.length(), ParcelableSpan.class)) {
            existingSpans.add(new StyleContainer(spannable.getSpanStart(span), spannable.getSpanEnd(span), span, spannable.getSpanFlags(span)));
        }
        for (CharacterStyle span2 : (CharacterStyle[]) spannable.getSpans(0, spannable.length(), CharacterStyle.class)) {
            existingSpans.add(new StyleContainer(spannable.getSpanStart(span2), spannable.getSpanEnd(span2), span2, spannable.getSpanFlags(span2)));
        }
        SpannableStringBuilder spannedString = new SpannableStringBuilder();
        SpannableStringBuilder tempIconString = new SpannableStringBuilder();
        int removedChars = 0;
        for (int i = 0; i < spannable.length(); i++) {
            Character c = Character.valueOf(spannable.charAt(i));
            if (c.charValue() == ICON_START) {
                spannedString.append(tempIconString);
                tempIconString = new SpannableStringBuilder();
                tempIconString.append(c.charValue());
            } else if (c.charValue() == ICON_END) {
                tempIconString.append(c.charValue());
                if (tempIconString.length() > 5) {
                    StyleContainer styleContainer = placeFontIcon(spannedString, tempIconString, fonts);
                    if (styleContainer != null) {
                        styleContainers.add(styleContainer);
                        Iterator it = existingSpans.iterator();
                        while (it.hasNext()) {
                            StyleContainer existingStyleContainer = (StyleContainer) it.next();
                            if (existingStyleContainer.startIndex > i - removedChars) {
                                existingStyleContainer.startIndex = (existingStyleContainer.startIndex - tempIconString.length()) + 1;
                            }
                            if (existingStyleContainer.endIndex > i - removedChars) {
                                existingStyleContainer.endIndex = (existingStyleContainer.endIndex - tempIconString.length()) + 1;
                            }
                        }
                        removedChars += tempIconString.length() - 1;
                    }
                } else {
                    spannedString.append(tempIconString);
                }
                tempIconString = new SpannableStringBuilder();
            } else if (tempIconString.length() == 0) {
                spannedString.append(c.charValue());
            } else {
                tempIconString.append(c.charValue());
            }
        }
        spannedString.append(tempIconString);
        styleContainers.addAll(existingSpans);
        return new TextStyleContainer(spannedString, styleContainers);
    }

    private static StyleContainer placeFontIcon(SpannableStringBuilder spannedString, SpannableStringBuilder tempIconString, HashMap<String, ITypeface> fonts) {
        if (tempIconString.length() >= 6) {
            String iconString = tempIconString.subSequence(1, tempIconString.length() - 1).toString().replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            String fontKey = tempIconString.subSequence(1, 4).toString();
            try {
                ITypeface typeface = (ITypeface) fonts.get(fontKey);
                if (typeface != null) {
                    IIcon icon = typeface.getIcon(iconString);
                    if (icon != null) {
                        spannedString.append(icon.getCharacter());
                        return new StyleContainer(spannedString.length() - 1, spannedString.length(), iconString, (ITypeface) fonts.get(fontKey));
                    }
                    Log.e(Iconics.TAG, "Wrong icon name: " + iconString);
                } else {
                    Log.e(Iconics.TAG, "Wrong fontId: " + iconString);
                }
            } catch (IllegalArgumentException e) {
                Log.e(Iconics.TAG, "Wrong icon name: " + iconString);
            }
        }
        spannedString.append(tempIconString);
        return null;
    }

    public static void applyStyles(Context ctx, Spannable text, List<StyleContainer> styleContainers, List<CharacterStyle> styles, HashMap<String, List<CharacterStyle>> stylesFor) {
        for (StyleContainer styleContainer : styleContainers) {
            if (styleContainer.style != null) {
                text.setSpan(styleContainer.style, styleContainer.startIndex, styleContainer.endIndex, styleContainer.flags);
            } else if (styleContainer.span != null) {
                text.setSpan(styleContainer.span, styleContainer.startIndex, styleContainer.endIndex, styleContainer.flags);
            } else {
                text.setSpan(new IconicsTypefaceSpan("sans-serif", styleContainer.font.getTypeface(ctx)), styleContainer.startIndex, styleContainer.endIndex, 33);
            }
            if (stylesFor != null && stylesFor.containsKey(styleContainer.icon)) {
                for (CharacterStyle style : (List) stylesFor.get(styleContainer.icon)) {
                    text.setSpan(CharacterStyle.wrap(style), styleContainer.startIndex, styleContainer.endIndex, styleContainer.flags);
                }
            } else if (styles != null) {
                for (CharacterStyle style2 : styles) {
                    text.setSpan(CharacterStyle.wrap(style2), styleContainer.startIndex, styleContainer.endIndex, styleContainer.flags);
                }
            }
        }
    }
}
