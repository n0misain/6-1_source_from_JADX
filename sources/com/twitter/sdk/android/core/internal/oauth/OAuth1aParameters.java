package com.twitter.sdk.android.core.internal.oauth;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.network.HttpRequest.Base64;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class OAuth1aParameters {
    private static final SecureRandom RAND = new SecureRandom();
    private static final String SIGNATURE_METHOD = "HMAC-SHA1";
    private static final String VERSION = "1.0";
    private final TwitterAuthConfig authConfig;
    private final TwitterAuthToken authToken;
    private final String callback;
    private final String method;
    private final Map<String, String> postParams;
    private final String url;

    public OAuth1aParameters(TwitterAuthConfig authConfig, TwitterAuthToken authToken, String callback, String method, String url, Map<String, String> postParams) {
        this.authConfig = authConfig;
        this.authToken = authToken;
        this.callback = callback;
        this.method = method;
        this.url = url;
        this.postParams = postParams;
    }

    public String getAuthorizationHeader() {
        String nonce = getNonce();
        String timestamp = getTimestamp();
        return constructAuthorizationHeader(nonce, timestamp, calculateSignature(constructSignatureBase(nonce, timestamp)));
    }

    private String getNonce() {
        return String.valueOf(System.nanoTime()) + String.valueOf(Math.abs(RAND.nextLong()));
    }

    private String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    String constructSignatureBase(String nonce, String timestamp) {
        URI uri = URI.create(this.url);
        TreeMap<String, String> params = UrlUtils.getQueryParams(uri, true);
        if (this.postParams != null) {
            params.putAll(this.postParams);
        }
        if (this.callback != null) {
            params.put(OAuthConstants.PARAM_CALLBACK, this.callback);
        }
        params.put(OAuthConstants.PARAM_CONSUMER_KEY, this.authConfig.getConsumerKey());
        params.put(OAuthConstants.PARAM_NONCE, nonce);
        params.put(OAuthConstants.PARAM_SIGNATURE_METHOD, SIGNATURE_METHOD);
        params.put(OAuthConstants.PARAM_TIMESTAMP, timestamp);
        if (!(this.authToken == null || this.authToken.token == null)) {
            params.put(OAuthConstants.PARAM_TOKEN, this.authToken.token);
        }
        params.put(OAuthConstants.PARAM_VERSION, VERSION);
        return this.method.toUpperCase(Locale.ENGLISH) + '&' + UrlUtils.percentEncode(uri.getScheme() + "://" + uri.getHost() + uri.getPath()) + '&' + getEncodedQueryParams(params);
    }

    private String getEncodedQueryParams(TreeMap<String, String> params) {
        StringBuilder paramsBuf = new StringBuilder();
        int numParams = params.size();
        int current = 0;
        for (Entry<String, String> entry : params.entrySet()) {
            paramsBuf.append(UrlUtils.percentEncode(UrlUtils.percentEncode((String) entry.getKey()))).append("%3D").append(UrlUtils.percentEncode(UrlUtils.percentEncode((String) entry.getValue())));
            current++;
            if (current < numParams) {
                paramsBuf.append("%26");
            }
        }
        return paramsBuf.toString();
    }

    String calculateSignature(String signatureBase) {
        try {
            String key = getSigningKey();
            byte[] signatureBaseBytes = signatureBase.getBytes(UrlUtils.UTF8);
            SecretKey secretKey = new SecretKeySpec(key.getBytes(UrlUtils.UTF8), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] signatureBytes = mac.doFinal(signatureBaseBytes);
            return new String(Base64.encodeBytesToBytes(signatureBytes, 0, signatureBytes.length), UrlUtils.UTF8);
        } catch (InvalidKeyException e) {
            Fabric.getLogger().mo4336e(TwitterCore.TAG, "Failed to calculate signature", e);
            return "";
        } catch (NoSuchAlgorithmException e2) {
            Fabric.getLogger().mo4336e(TwitterCore.TAG, "Failed to calculate signature", e2);
            return "";
        } catch (UnsupportedEncodingException e3) {
            Fabric.getLogger().mo4336e(TwitterCore.TAG, "Failed to calculate signature", e3);
            return "";
        }
    }

    private String getSigningKey() {
        return UrlUtils.urlEncode(this.authConfig.getConsumerSecret()) + '&' + UrlUtils.urlEncode(this.authToken != null ? this.authToken.secret : null);
    }

    String constructAuthorizationHeader(String nonce, String timestamp, String signature) {
        StringBuilder sb = new StringBuilder("OAuth");
        appendParameter(sb, OAuthConstants.PARAM_CALLBACK, this.callback);
        appendParameter(sb, OAuthConstants.PARAM_CONSUMER_KEY, this.authConfig.getConsumerKey());
        appendParameter(sb, OAuthConstants.PARAM_NONCE, nonce);
        appendParameter(sb, OAuthConstants.PARAM_SIGNATURE, signature);
        appendParameter(sb, OAuthConstants.PARAM_SIGNATURE_METHOD, SIGNATURE_METHOD);
        appendParameter(sb, OAuthConstants.PARAM_TIMESTAMP, timestamp);
        appendParameter(sb, OAuthConstants.PARAM_TOKEN, this.authToken != null ? this.authToken.token : null);
        appendParameter(sb, OAuthConstants.PARAM_VERSION, VERSION);
        return sb.substring(0, sb.length() - 1);
    }

    private void appendParameter(StringBuilder sb, String name, String value) {
        if (value != null) {
            sb.append(' ').append(UrlUtils.percentEncode(name)).append("=\"").append(UrlUtils.percentEncode(value)).append("\",");
        }
    }
}
