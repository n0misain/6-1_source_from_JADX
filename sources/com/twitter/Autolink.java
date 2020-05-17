package com.twitter;

import com.facebook.share.internal.ShareConstants;
import com.twitter.Extractor.Entity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Autolink {
    public static final String DEFAULT_CASHTAG_CLASS = "tweet-url cashtag";
    public static final String DEFAULT_CASHTAG_URL_BASE = "https://twitter.com/#!/search?q=%24";
    public static final String DEFAULT_HASHTAG_CLASS = "tweet-url hashtag";
    public static final String DEFAULT_HASHTAG_URL_BASE = "https://twitter.com/#!/search?q=%23";
    public static final String DEFAULT_INVISIBLE_TAG_ATTRS = "style='position:absolute;left:-9999px;'";
    public static final String DEFAULT_LIST_CLASS = "tweet-url list-slug";
    public static final String DEFAULT_LIST_URL_BASE = "https://twitter.com/";
    public static final String DEFAULT_USERNAME_CLASS = "tweet-url username";
    public static final String DEFAULT_USERNAME_URL_BASE = "https://twitter.com/";
    protected String cashtagClass;
    protected String cashtagUrlBase;
    private Extractor extractor;
    protected String hashtagClass;
    protected String hashtagUrlBase;
    protected String invisibleTagAttrs;
    protected LinkAttributeModifier linkAttributeModifier;
    protected LinkTextModifier linkTextModifier;
    protected String listClass;
    protected String listUrlBase;
    protected boolean noFollow;
    protected String symbolTag;
    protected String textWithSymbolTag;
    protected String urlClass;
    protected String urlTarget;
    protected String usernameClass;
    protected boolean usernameIncludeSymbol;
    protected String usernameUrlBase;

    public interface LinkAttributeModifier {
        void modify(Entity entity, Map<String, String> map);
    }

    public interface LinkTextModifier {
        CharSequence modify(Entity entity, CharSequence charSequence);
    }

    private static CharSequence escapeHTML(CharSequence text) {
        StringBuilder builder = new StringBuilder(text.length() * 2);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '\"':
                    builder.append("&quot;");
                    break;
                case '&':
                    builder.append("&amp;");
                    break;
                case '\'':
                    builder.append("&#39;");
                    break;
                case '<':
                    builder.append("&lt;");
                    break;
                case '>':
                    builder.append("&gt;");
                    break;
                default:
                    builder.append(c);
                    break;
            }
        }
        return builder;
    }

    public Autolink() {
        this.urlClass = null;
        this.noFollow = true;
        this.usernameIncludeSymbol = false;
        this.symbolTag = null;
        this.textWithSymbolTag = null;
        this.urlTarget = null;
        this.linkAttributeModifier = null;
        this.linkTextModifier = null;
        this.extractor = new Extractor();
        this.urlClass = null;
        this.listClass = DEFAULT_LIST_CLASS;
        this.usernameClass = DEFAULT_USERNAME_CLASS;
        this.hashtagClass = DEFAULT_HASHTAG_CLASS;
        this.cashtagClass = DEFAULT_CASHTAG_CLASS;
        this.usernameUrlBase = "https://twitter.com/";
        this.listUrlBase = "https://twitter.com/";
        this.hashtagUrlBase = DEFAULT_HASHTAG_URL_BASE;
        this.cashtagUrlBase = DEFAULT_CASHTAG_URL_BASE;
        this.invisibleTagAttrs = DEFAULT_INVISIBLE_TAG_ATTRS;
        this.extractor.setExtractURLWithoutProtocol(false);
    }

    public String escapeBrackets(String text) {
        int len = text.length();
        if (len == 0) {
            return text;
        }
        StringBuilder sb = new StringBuilder(len + 16);
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (c == '>') {
                sb.append("&gt;");
            } else if (c == '<') {
                sb.append("&lt;");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public void linkToText(Entity entity, CharSequence text, Map<String, String> attributes, StringBuilder builder) {
        if (this.noFollow) {
            attributes.put("rel", "nofollow");
        }
        if (this.linkAttributeModifier != null) {
            this.linkAttributeModifier.modify(entity, attributes);
        }
        if (this.linkTextModifier != null) {
            text = this.linkTextModifier.modify(entity, text);
        }
        builder.append("<a");
        for (Entry<String, String> entry : attributes.entrySet()) {
            builder.append(" ").append(escapeHTML((CharSequence) entry.getKey())).append("=\"").append(escapeHTML((CharSequence) entry.getValue())).append("\"");
        }
        builder.append(">").append(text).append("</a>");
    }

    public void linkToTextWithSymbol(Entity entity, CharSequence symbol, CharSequence text, Map<String, String> attributes, StringBuilder builder) {
        CharSequence taggedSymbol;
        CharSequence taggedText;
        boolean includeSymbol = false;
        if (this.symbolTag == null || this.symbolTag.length() == 0) {
            taggedSymbol = symbol;
        } else {
            taggedSymbol = String.format("<%s>%s</%s>", new Object[]{this.symbolTag, symbol, this.symbolTag});
        }
        text = escapeHTML(text);
        if (this.textWithSymbolTag == null || this.textWithSymbolTag.length() == 0) {
            taggedText = text;
        } else {
            taggedText = String.format("<%s>%s</%s>", new Object[]{this.textWithSymbolTag, text, this.textWithSymbolTag});
        }
        if (this.usernameIncludeSymbol || !Regex.AT_SIGNS.matcher(symbol).matches()) {
            includeSymbol = true;
        }
        if (includeSymbol) {
            linkToText(entity, taggedSymbol.toString() + taggedText, attributes, builder);
            return;
        }
        builder.append(taggedSymbol);
        linkToText(entity, taggedText, attributes, builder);
    }

    public void linkToHashtag(Entity entity, String text, StringBuilder builder) {
        CharSequence hashChar = text.subSequence(entity.getStart().intValue(), entity.getStart().intValue() + 1);
        CharSequence hashtag = entity.getValue();
        Map<String, String> attrs = new LinkedHashMap();
        attrs.put(ShareConstants.WEB_DIALOG_PARAM_HREF, this.hashtagUrlBase + hashtag);
        attrs.put("title", "#" + hashtag);
        if (Regex.RTL_CHARACTERS.matcher(text).find()) {
            attrs.put("class", this.hashtagClass + " rtl");
        } else {
            attrs.put("class", this.hashtagClass);
        }
        linkToTextWithSymbol(entity, hashChar, hashtag, attrs, builder);
    }

    public void linkToCashtag(Entity entity, String text, StringBuilder builder) {
        CharSequence cashtag = entity.getValue();
        Map<String, String> attrs = new LinkedHashMap();
        attrs.put(ShareConstants.WEB_DIALOG_PARAM_HREF, this.cashtagUrlBase + cashtag);
        attrs.put("title", "$" + cashtag);
        attrs.put("class", this.cashtagClass);
        linkToTextWithSymbol(entity, "$", cashtag, attrs, builder);
    }

    public void linkToMentionAndList(Entity entity, String text, StringBuilder builder) {
        String mention = entity.getValue();
        CharSequence atChar = text.subSequence(entity.getStart().intValue(), entity.getStart().intValue() + 1);
        Map<String, String> attrs = new LinkedHashMap();
        if (entity.listSlug != null) {
            mention = mention + entity.listSlug;
            attrs.put("class", this.listClass);
            attrs.put(ShareConstants.WEB_DIALOG_PARAM_HREF, this.listUrlBase + mention);
        } else {
            attrs.put("class", this.usernameClass);
            attrs.put(ShareConstants.WEB_DIALOG_PARAM_HREF, this.usernameUrlBase + mention);
        }
        linkToTextWithSymbol(entity, atChar, mention, attrs, builder);
    }

    public void linkToURL(Entity entity, String text, StringBuilder builder) {
        CharSequence url = entity.getValue();
        CharSequence linkText = escapeHTML(url);
        if (!(entity.displayURL == null || entity.expandedURL == null)) {
            String displayURLSansEllipses = entity.displayURL.replace("…", "");
            int diplayURLIndexInExpandedURL = entity.expandedURL.indexOf(displayURLSansEllipses);
            if (diplayURLIndexInExpandedURL != -1) {
                String beforeDisplayURL = entity.expandedURL.substring(0, diplayURLIndexInExpandedURL);
                String afterDisplayURL = entity.expandedURL.substring(displayURLSansEllipses.length() + diplayURLIndexInExpandedURL);
                String precedingEllipsis = entity.displayURL.startsWith("…") ? "…" : "";
                String followingEllipsis = entity.displayURL.endsWith("…") ? "…" : "";
                String invisibleSpan = "<span " + this.invisibleTagAttrs + ">";
                StringBuilder sb = new StringBuilder("<span class='tco-ellipsis'>");
                sb.append(precedingEllipsis);
                sb.append(invisibleSpan).append("&nbsp;</span></span>");
                sb.append(invisibleSpan).append(escapeHTML(beforeDisplayURL)).append("</span>");
                sb.append("<span class='js-display-url'>").append(escapeHTML(displayURLSansEllipses)).append("</span>");
                sb.append(invisibleSpan).append(escapeHTML(afterDisplayURL)).append("</span>");
                sb.append("<span class='tco-ellipsis'>").append(invisibleSpan).append("&nbsp;</span>").append(followingEllipsis).append("</span>");
                linkText = sb;
            } else {
                linkText = entity.displayURL;
            }
        }
        Map<String, String> attrs = new LinkedHashMap();
        attrs.put(ShareConstants.WEB_DIALOG_PARAM_HREF, url.toString());
        if (this.urlClass != null) {
            attrs.put("class", this.urlClass);
        }
        if (!(this.urlClass == null || this.urlClass.length() == 0)) {
            attrs.put("class", this.urlClass);
        }
        if (!(this.urlTarget == null || this.urlTarget.length() == 0)) {
            attrs.put("target", this.urlTarget);
        }
        linkToText(entity, linkText, attrs, builder);
    }

    public String autoLinkEntities(String text, List<Entity> entities) {
        StringBuilder builder = new StringBuilder(text.length() * 2);
        int beginIndex = 0;
        for (Entity entity : entities) {
            builder.append(text.subSequence(beginIndex, entity.start));
            switch (entity.type) {
                case URL:
                    linkToURL(entity, text, builder);
                    break;
                case HASHTAG:
                    linkToHashtag(entity, text, builder);
                    break;
                case MENTION:
                    linkToMentionAndList(entity, text, builder);
                    break;
                case CASHTAG:
                    linkToCashtag(entity, text, builder);
                    break;
                default:
                    break;
            }
            beginIndex = entity.end;
        }
        builder.append(text.subSequence(beginIndex, text.length()));
        return builder.toString();
    }

    public String autoLink(String text) {
        text = escapeBrackets(text);
        return autoLinkEntities(text, this.extractor.extractEntitiesWithIndices(text));
    }

    public String autoLinkUsernamesAndLists(String text) {
        return autoLinkEntities(text, this.extractor.extractMentionsOrListsWithIndices(text));
    }

    public String autoLinkHashtags(String text) {
        return autoLinkEntities(text, this.extractor.extractHashtagsWithIndices(text));
    }

    public String autoLinkURLs(String text) {
        return autoLinkEntities(text, this.extractor.extractURLsWithIndices(text));
    }

    public String autoLinkCashtags(String text) {
        return autoLinkEntities(text, this.extractor.extractCashtagsWithIndices(text));
    }

    public String getUrlClass() {
        return this.urlClass;
    }

    public void setUrlClass(String urlClass) {
        this.urlClass = urlClass;
    }

    public String getListClass() {
        return this.listClass;
    }

    public void setListClass(String listClass) {
        this.listClass = listClass;
    }

    public String getUsernameClass() {
        return this.usernameClass;
    }

    public void setUsernameClass(String usernameClass) {
        this.usernameClass = usernameClass;
    }

    public String getHashtagClass() {
        return this.hashtagClass;
    }

    public void setHashtagClass(String hashtagClass) {
        this.hashtagClass = hashtagClass;
    }

    public String getCashtagClass() {
        return this.cashtagClass;
    }

    public void setCashtagClass(String cashtagClass) {
        this.cashtagClass = cashtagClass;
    }

    public String getUsernameUrlBase() {
        return this.usernameUrlBase;
    }

    public void setUsernameUrlBase(String usernameUrlBase) {
        this.usernameUrlBase = usernameUrlBase;
    }

    public String getListUrlBase() {
        return this.listUrlBase;
    }

    public void setListUrlBase(String listUrlBase) {
        this.listUrlBase = listUrlBase;
    }

    public String getHashtagUrlBase() {
        return this.hashtagUrlBase;
    }

    public void setHashtagUrlBase(String hashtagUrlBase) {
        this.hashtagUrlBase = hashtagUrlBase;
    }

    public String getCashtagUrlBase() {
        return this.cashtagUrlBase;
    }

    public void setCashtagUrlBase(String cashtagUrlBase) {
        this.cashtagUrlBase = cashtagUrlBase;
    }

    public boolean isNoFollow() {
        return this.noFollow;
    }

    public void setNoFollow(boolean noFollow) {
        this.noFollow = noFollow;
    }

    public void setUsernameIncludeSymbol(boolean usernameIncludeSymbol) {
        this.usernameIncludeSymbol = usernameIncludeSymbol;
    }

    public void setSymbolTag(String tag) {
        this.symbolTag = tag;
    }

    public void setTextWithSymbolTag(String tag) {
        this.textWithSymbolTag = tag;
    }

    public void setUrlTarget(String target) {
        this.urlTarget = target;
    }

    public void setLinkAttributeModifier(LinkAttributeModifier modifier) {
        this.linkAttributeModifier = modifier;
    }

    public void setLinkTextModifier(LinkTextModifier modifier) {
        this.linkTextModifier = modifier;
    }
}
