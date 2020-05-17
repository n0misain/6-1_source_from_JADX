package com.mikepenz.iconics.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuInflater;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.internal.IconicsViewsAttrsReader;
import com.mikepenz.iconics.view.C0973R;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class IconicsMenuInflaterUtil {
    private static final String XML_ITEM = "item";
    private static final String XML_MENU = "menu";

    public static void inflate(MenuInflater inflater, Context context, int menuId, Menu menu) {
        inflate(inflater, context, menuId, menu, false);
    }

    public static void inflate(MenuInflater inflater, Context context, int menuId, Menu menu, boolean checkSubMenus) {
        inflater.inflate(menuId, menu);
        try {
            XmlResourceParser parser = context.getResources().getXml(menuId);
            parseMenu(context, Xml.asAttributeSet(parser), parser, menu, checkSubMenus);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static void parseMenu(Context context, AttributeSet attrs, XmlPullParser parser, Menu menu, boolean checkSubMenus) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        boolean lookingForEndOfUnknownTag = false;
        String unknownTagName = null;
        while (eventType != 2) {
            eventType = parser.next();
            if (eventType == 1) {
                break;
            }
        }
        String tagName = parser.getName();
        if (tagName.equals(XML_MENU)) {
            eventType = parser.next();
            boolean reachedEndOfMenu = false;
            while (!reachedEndOfMenu) {
                switch (eventType) {
                    case 1:
                        throw new RuntimeException("Unexpected end of document");
                    case 2:
                        if (!lookingForEndOfUnknownTag) {
                            tagName = parser.getName();
                            Object obj = -1;
                            switch (tagName.hashCode()) {
                                case 3242771:
                                    if (tagName.equals(XML_ITEM)) {
                                        obj = null;
                                        break;
                                    }
                                    break;
                                case 3347807:
                                    if (tagName.equals(XML_MENU)) {
                                        obj = 1;
                                        break;
                                    }
                                    break;
                            }
                            switch (obj) {
                                case null:
                                    HashMap<String, String> attr = new HashMap();
                                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                                        attr.put(parser.getAttributeName(i), parser.getAttributeValue(i));
                                    }
                                    TypedArray a = context.obtainStyledAttributes(attrs, C0973R.styleable.IconicsImageView);
                                    IconicsDrawable normalBundle = new IconicsDrawable(context);
                                    IconicsViewsAttrsReader.readIconicsImageView(a, normalBundle);
                                    menu.findItem(Integer.parseInt(((String) attr.get("id")).replace("@", ""))).setIcon(normalBundle);
                                    a.recycle();
                                    break;
                                case 1:
                                    if (!checkSubMenus) {
                                        break;
                                    }
                                    parseMenu(context, attrs, parser, menu, checkSubMenus);
                                    break;
                                default:
                                    lookingForEndOfUnknownTag = true;
                                    unknownTagName = tagName;
                                    break;
                            }
                        }
                        break;
                    case 3:
                        tagName = parser.getName();
                        if (!lookingForEndOfUnknownTag || !tagName.equals(unknownTagName)) {
                            if (!tagName.equals(XML_MENU)) {
                                break;
                            }
                            reachedEndOfMenu = true;
                            break;
                        }
                        lookingForEndOfUnknownTag = false;
                        unknownTagName = null;
                        break;
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
            return;
        }
        throw new RuntimeException("Expecting menu, got " + tagName);
    }
}
