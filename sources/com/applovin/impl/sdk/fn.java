package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

class fn implements ContentHandler {
    /* renamed from: a */
    final /* synthetic */ fm f921a;

    fn(fm fmVar) {
        this.f921a = fmVar;
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        String trim = new String(Arrays.copyOfRange(cArr, 0, i2)).trim();
        if (AppLovinSdkUtils.isValidString(trim)) {
            this.f921a.f918c.append(trim);
        }
    }

    public void endDocument() throws SAXException {
        this.f921a.f916a.mo2288d("XmlParser", "Finished parsing in " + (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - this.f921a.f919d) + " seconds");
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        this.f921a.f920e = (fo) this.f921a.f917b.pop();
        this.f921a.f920e.m1115d(this.f921a.f918c.toString().trim());
        this.f921a.f918c.setLength(0);
    }

    public void endPrefixMapping(String str) throws SAXException {
    }

    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
    }

    public void processingInstruction(String str, String str2) throws SAXException {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void skippedEntity(String str) throws SAXException {
    }

    public void startDocument() throws SAXException {
        this.f921a.f916a.mo2288d("XmlParser", "Begin parsing...");
        this.f921a.f919d = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        fl flVar = null;
        try {
            if (!this.f921a.f917b.isEmpty()) {
                flVar = (fo) this.f921a.f917b.peek();
            }
            fl foVar = new fo(str2, this.f921a.m1108a(attributes), flVar);
            if (flVar != null) {
                flVar.m1114a(foVar);
            }
            this.f921a.f917b.push(foVar);
        } catch (Throwable e) {
            this.f921a.f916a.mo2290e("XmlParser", "Unable to process element <" + str2 + ">", e);
            throw new SAXException("Failed to start element", e);
        }
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
    }
}
