package com.mikepenz.iconics;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;
import com.mikepenz.iconics.utils.GenericsUtil;
import com.mikepenz.iconics.utils.IconicsUtils;
import com.mikepenz.iconics.utils.TextStyleContainer;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public final class Iconics {
    private static HashMap<String, ITypeface> FONTS = new HashMap();
    private static boolean INIT_DONE = false;
    public static final String TAG = Iconics.class.getSimpleName();

    public static class IconicsBuilder {
        private Context ctx;
        private List<ITypeface> fonts = new LinkedList();
        private List<CharacterStyle> styles = new LinkedList();
        private HashMap<String, List<CharacterStyle>> stylesFor = new HashMap();

        public IconicsBuilder ctx(Context ctx) {
            this.ctx = ctx;
            return this;
        }

        public IconicsBuilder style(CharacterStyle... styles) {
            if (styles != null && styles.length > 0) {
                Collections.addAll(this.styles, styles);
            }
            return this;
        }

        public IconicsBuilder styleFor(IIcon styleFor, CharacterStyle... styles) {
            return styleFor(styleFor.getName(), styles);
        }

        public IconicsBuilder styleFor(String styleFor, CharacterStyle... styles) {
            styleFor = styleFor.replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            if (!this.stylesFor.containsKey(styleFor)) {
                this.stylesFor.put(styleFor, new LinkedList());
            }
            if (styles != null && styles.length > 0) {
                for (CharacterStyle style : styles) {
                    ((List) this.stylesFor.get(styleFor)).add(style);
                }
            }
            return this;
        }

        public IconicsBuilder font(ITypeface font) {
            this.fonts.add(font);
            return this;
        }

        public IconicsBuilderString on(Spanned on) {
            return new IconicsBuilderString(this.ctx, this.fonts, on, this.styles, this.stylesFor);
        }

        public IconicsBuilderString on(String on) {
            return on(new SpannableString(on));
        }

        public IconicsBuilderString on(CharSequence on) {
            return on(on.toString());
        }

        public IconicsBuilderString on(StringBuilder on) {
            return on(on.toString());
        }

        public IconicsBuilderView on(TextView on) {
            return new IconicsBuilderView(this.ctx, this.fonts, on, this.styles, this.stylesFor);
        }

        public IconicsBuilderView on(Button on) {
            return new IconicsBuilderView(this.ctx, this.fonts, on, this.styles, this.stylesFor);
        }
    }

    public static class IconicsBuilderString {
        private Context ctx;
        private List<ITypeface> fonts;
        private Spanned text;
        private List<CharacterStyle> withStyles;
        private HashMap<String, List<CharacterStyle>> withStylesFor;

        public IconicsBuilderString(Context ctx, List<ITypeface> fonts, Spanned text, List<CharacterStyle> styles, HashMap<String, List<CharacterStyle>> stylesFor) {
            this.ctx = ctx;
            this.fonts = fonts;
            this.text = text;
            this.withStyles = styles;
            this.withStylesFor = stylesFor;
        }

        public Spanned build() {
            HashMap<String, ITypeface> mappedFonts = new HashMap();
            for (ITypeface font : this.fonts) {
                mappedFonts.put(font.getMappingPrefix(), font);
            }
            return Iconics.style(this.ctx, mappedFonts, this.text, this.withStyles, this.withStylesFor);
        }
    }

    public static class IconicsBuilderView {
        private Context ctx;
        private List<ITypeface> fonts;
        private TextView view;
        private List<CharacterStyle> withStyles;
        private HashMap<String, List<CharacterStyle>> withStylesFor;

        public IconicsBuilderView(Context ctx, List<ITypeface> fonts, TextView view, List<CharacterStyle> styles, HashMap<String, List<CharacterStyle>> stylesFor) {
            this.ctx = ctx;
            this.fonts = fonts;
            this.view = view;
            this.withStyles = styles;
            this.withStylesFor = stylesFor;
        }

        public void build() {
            HashMap<String, ITypeface> mappedFonts = new HashMap();
            for (ITypeface font : this.fonts) {
                mappedFonts.put(font.getMappingPrefix(), font);
            }
            if (this.view.getText() instanceof Spanned) {
                this.view.setText(Iconics.style(this.ctx, mappedFonts, (Spanned) this.view.getText(), this.withStyles, this.withStylesFor));
            } else {
                this.view.setText(Iconics.style(this.ctx, mappedFonts, new SpannableString(this.view.getText()), this.withStyles, this.withStylesFor));
            }
            if (this.view instanceof Button) {
                this.view.setAllCaps(false);
            }
        }
    }

    public static void init(Context ctx) {
        if (!INIT_DONE) {
            for (String fontsClassPath : GenericsUtil.getFields(ctx)) {
                try {
                    ITypeface typeface = (ITypeface) Class.forName(fontsClassPath).newInstance();
                    validateFont(typeface);
                    FONTS.put(typeface.getMappingPrefix(), typeface);
                } catch (Exception e) {
                    Log.e("Android-Iconics", "Can't init: " + fontsClassPath);
                }
            }
            INIT_DONE = true;
        }
    }

    private static HashMap<String, ITypeface> init(Context ctx, HashMap<String, ITypeface> fonts) {
        init(ctx);
        if (fonts == null || fonts.size() == 0) {
            return FONTS;
        }
        return fonts;
    }

    public static boolean iconExists(Context context, String icon) {
        try {
            findFont(context, icon.substring(0, 3)).getIcon(icon.replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean registerFont(ITypeface font) {
        validateFont(font);
        FONTS.put(font.getMappingPrefix(), font);
        return true;
    }

    private static void validateFont(ITypeface font) {
        if (font.getMappingPrefix().length() != 3) {
            throw new IllegalArgumentException("The mapping prefix of a font must be three characters long.");
        }
    }

    public static Collection<ITypeface> getRegisteredFonts(Context ctx) {
        init(ctx);
        return FONTS.values();
    }

    public static ITypeface findFont(Context ctx, String key) {
        init(ctx);
        return (ITypeface) FONTS.get(key);
    }

    public static ITypeface findFont(IIcon icon) {
        return icon.getTypeface();
    }

    private Iconics() {
    }

    public static Spanned style(Context ctx, Spanned textSpanned) {
        return style(ctx, null, textSpanned, null, null);
    }

    public static Spanned style(Context ctx, HashMap<String, ITypeface> fonts, Spanned textSpanned, List<CharacterStyle> styles, HashMap<String, List<CharacterStyle>> stylesFor) {
        TextStyleContainer textStyleContainer = IconicsUtils.findIcons(textSpanned, init(ctx, fonts));
        SpannableString sb = SpannableString.valueOf(textStyleContainer.spannableStringBuilder);
        IconicsUtils.applyStyles(ctx, sb, textStyleContainer.styleContainers, styles, stylesFor);
        return sb;
    }

    public static void styleEditable(Context ctx, Editable editable) {
        styleEditable(ctx, null, editable, null, null);
    }

    public static void styleEditable(Context ctx, HashMap<String, ITypeface> fonts, Editable textSpanned, List<CharacterStyle> styles, HashMap<String, List<CharacterStyle>> stylesFor) {
        IconicsUtils.applyStyles(ctx, textSpanned, IconicsUtils.findIconsFromEditable(textSpanned, init(ctx, fonts)), styles, stylesFor);
    }
}
