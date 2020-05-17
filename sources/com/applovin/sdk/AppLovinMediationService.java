package com.applovin.sdk;

import com.applovin.mediation.AppLovinMediationAdapter;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import java.util.Collection;

public interface AppLovinMediationService {

    public class AppLovinMediationAdapterInfo {
        /* renamed from: a */
        private final String f986a;
        /* renamed from: b */
        private final String f987b;
        /* renamed from: c */
        private final AppLovinMediationAdapterStatus f988c;
        /* renamed from: d */
        private final AppLovinMediationAdapter f989d;
        /* renamed from: e */
        private final AppLovinMediationAdapterConfig f990e;

        public AppLovinMediationAdapterInfo(String str, String str2, AppLovinMediationAdapterStatus appLovinMediationAdapterStatus) {
            this(str, str2, appLovinMediationAdapterStatus, null, null);
        }

        public AppLovinMediationAdapterInfo(String str, String str2, AppLovinMediationAdapterStatus appLovinMediationAdapterStatus, AppLovinMediationAdapter appLovinMediationAdapter, AppLovinMediationAdapterConfig appLovinMediationAdapterConfig) {
            if (str == null) {
                throw new IllegalArgumentException("No name specified");
            } else if (str2 == null) {
                throw new IllegalArgumentException("No class name specified");
            } else if (appLovinMediationAdapterStatus == null) {
                throw new IllegalArgumentException("No status specified");
            } else {
                this.f986a = str;
                this.f987b = str2;
                this.f988c = appLovinMediationAdapterStatus;
                this.f989d = appLovinMediationAdapter;
                this.f990e = appLovinMediationAdapterConfig;
            }
        }

        public AppLovinMediationAdapter getAdapter() {
            return this.f989d;
        }

        public AppLovinMediationAdapterConfig getAdapterConfiguration() {
            return this.f990e;
        }

        public String getClassName() {
            return this.f987b;
        }

        public String getName() {
            return this.f986a;
        }

        public AppLovinMediationAdapterStatus getStatus() {
            return this.f988c;
        }

        public String toString() {
            return "[Adapter Info - <" + this.f986a + " : " + this.f987b + "> with configuration: " + this.f990e + "]";
        }
    }

    public class AppLovinMediationAdapterStats {
        /* renamed from: a */
        private final String f991a;
        /* renamed from: b */
        private final long f992b;

        public AppLovinMediationAdapterStats(String str, long j) {
            if (str == null) {
                throw new IllegalArgumentException("No adapter name specified");
            }
            this.f991a = str;
            this.f992b = j;
        }

        public String getAdapterName() {
            return this.f991a;
        }

        public long getLastAdLoadMillis() {
            return this.f992b;
        }

        public String toString() {
            return "[Adapter Stats - <" + this.f991a + " : loaded in " + this.f992b + "milliseconds>]";
        }
    }

    public enum AppLovinMediationAdapterStatus {
        READY,
        ERROR_NOT_READY,
        ERROR_LOAD,
        MISSING
    }

    Collection getAdapterInfo();

    AppLovinMediationAdapterStats getLastAdapterStats();
}
