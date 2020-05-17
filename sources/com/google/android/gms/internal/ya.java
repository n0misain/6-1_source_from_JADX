package com.google.android.gms.internal;

import io.fabric.sdk.android.services.network.UrlUtils;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

final class ya extends ThreadLocal<CharsetDecoder> {
    ya() {
    }

    protected final /* synthetic */ Object initialValue() {
        CharsetDecoder newDecoder = Charset.forName(UrlUtils.UTF8).newDecoder();
        newDecoder.onMalformedInput(CodingErrorAction.REPORT);
        newDecoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        return newDecoder;
    }
}
