package com.mikepenz.iconics.typeface;

import android.content.Context;
import android.graphics.Typeface;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Collection;
import java.util.HashMap;

public class GenericFont implements ITypeface {
    private String mAuthor;
    private HashMap<String, Character> mChars;
    private String mFontFile;
    private String mFontName;
    private String mMappingPrefix;
    private Typeface typeface;

    public class Icon implements IIcon {
        private char aChar;
        private String mName;
        private ITypeface mTypeface;

        public Icon(char c) {
            this.aChar = c;
        }

        public Icon(String name, char c) {
            this.mName = name;
            this.aChar = c;
        }

        public Icon withTypeface(ITypeface typeface) {
            this.mTypeface = typeface;
            return this;
        }

        public String getFormattedName() {
            return "{" + getName() + "}";
        }

        public String getName() {
            if (this.mName != null) {
                return this.mName;
            }
            return String.valueOf(this.aChar);
        }

        public char getCharacter() {
            return this.aChar;
        }

        public ITypeface getTypeface() {
            if (this.mTypeface != null) {
                return this.mTypeface;
            }
            return GenericFont.this;
        }
    }

    protected GenericFont() {
        this.typeface = null;
        this.mChars = new HashMap();
    }

    public GenericFont(String mappingPrefix, String fontFile) {
        this("GenericFont", "GenericAuthor", mappingPrefix, fontFile);
    }

    public GenericFont(String fontName, String author, String mappingPrefix, String fontFile) {
        this.typeface = null;
        this.mChars = new HashMap();
        if (mappingPrefix.length() != 3) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("MappingPrefix must be 3 char long");
        }
        this.mFontName = fontName;
        this.mAuthor = author;
        this.mMappingPrefix = mappingPrefix;
        this.mFontFile = fontFile;
    }

    public void registerIcon(String name, char aChar) {
        this.mChars.put(this.mMappingPrefix + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + name, Character.valueOf(aChar));
    }

    public IIcon getIcon(String key) {
        return new Icon(((Character) this.mChars.get(key)).charValue()).withTypeface(this);
    }

    public HashMap<String, Character> getCharacters() {
        return new HashMap();
    }

    public String getMappingPrefix() {
        return this.mMappingPrefix;
    }

    public String getFontName() {
        return this.mFontName;
    }

    public String getVersion() {
        return "1.0.0";
    }

    public int getIconCount() {
        return this.mChars.size();
    }

    public Collection<String> getIcons() {
        return this.mChars.keySet();
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public String getUrl() {
        return "";
    }

    public String getDescription() {
        return "";
    }

    public String getLicense() {
        return "";
    }

    public String getLicenseUrl() {
        return "";
    }

    public Typeface getTypeface(Context context) {
        if (this.typeface == null) {
            try {
                this.typeface = Typeface.createFromAsset(context.getAssets(), this.mFontFile);
            } catch (Exception e) {
                return null;
            }
        }
        return this.typeface;
    }
}
