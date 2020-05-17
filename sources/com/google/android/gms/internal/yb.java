package com.google.android.gms.internal;

import io.fabric.sdk.android.services.network.UrlUtils;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

final class yb extends ThreadLocal<CharsetEncoder> {
    yb() {
    }

    protected final /* synthetic */ Object initialValue() {
        CharsetEncoder newEncoder = Charset.forName(UrlUtils.UTF8).newEncoder();
        newEncoder.onMalformedInput(CodingErrorAction.REPORT);
        newEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);
        return newEncoder;
    }
}
