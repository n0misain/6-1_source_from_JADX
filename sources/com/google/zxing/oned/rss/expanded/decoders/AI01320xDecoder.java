package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

final class AI01320xDecoder extends AI013x0xDecoder {
    AI01320xDecoder(BitArray information) {
        super(information);
    }

    protected void addWeightCode(StringBuilder buf, int weight) {
        if (weight < AbstractSpiCall.DEFAULT_TIMEOUT) {
            buf.append("(3202)");
        } else {
            buf.append("(3203)");
        }
    }

    protected int checkWeight(int weight) {
        return weight < AbstractSpiCall.DEFAULT_TIMEOUT ? weight : weight - 10000;
    }
}
