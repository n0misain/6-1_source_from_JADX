package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ei {
    /* renamed from: a */
    private final String f850a = "TaskManager";
    /* renamed from: b */
    private final AppLovinSdkImpl f851b;
    /* renamed from: c */
    private final AppLovinLogger f852c;
    /* renamed from: d */
    private final ScheduledThreadPoolExecutor f853d;
    /* renamed from: e */
    private final ScheduledThreadPoolExecutor f854e;
    /* renamed from: f */
    private final ScheduledThreadPoolExecutor f855f;

    ei(AppLovinSdkImpl appLovinSdkImpl) {
        this.f851b = appLovinSdkImpl;
        this.f852c = appLovinSdkImpl.getLogger();
        this.f853d = m988a("main");
        this.f854e = m988a("back");
        this.f855f = m988a("postbacks");
    }

    /* renamed from: a */
    private long m986a(ej ejVar) {
        return ejVar == ej.MAIN ? this.f853d.getTaskCount() - this.f853d.getCompletedTaskCount() : ejVar == ej.BACKGROUND ? this.f854e.getTaskCount() - this.f854e.getCompletedTaskCount() : ejVar == ej.POSTBACKS ? this.f855f.getTaskCount() - this.f855f.getCompletedTaskCount() : 0;
    }

    /* renamed from: a */
    private ScheduledThreadPoolExecutor m988a(String str) {
        return new ScheduledThreadPoolExecutor(1, new ek(this, str));
    }

    /* renamed from: a */
    private static void m989a(Runnable runnable, long j, ScheduledExecutorService scheduledExecutorService) {
        if (j > 0) {
            scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } else {
            scheduledExecutorService.submit(runnable);
        }
    }

    /* renamed from: a */
    void m991a(di diVar) {
        if (diVar != null) {
            try {
                this.f852c.mo2291i("TaskManager", "Executing " + diVar.m676a() + " immediately...");
                diVar.run();
                this.f852c.mo2291i("TaskManager", diVar.m676a() + " finished executing...");
                return;
            } catch (Throwable th) {
                this.f852c.mo2290e("TaskManager", "Task failed execution", th);
                return;
            }
        }
        this.f852c.mo2289e("TaskManager", "Attempted to execute null task immediately");
    }

    /* renamed from: a */
    void m992a(di diVar, ej ejVar) {
        m993a(diVar, ejVar, 0);
    }

    /* renamed from: a */
    void m993a(di diVar, ej ejVar, long j) {
        if (diVar == null) {
            throw new IllegalArgumentException("No task specified");
        } else if (j < 0) {
            throw new IllegalArgumentException("Invalid delay specified: " + j);
        } else if (ejVar == ej.MAIN || ejVar == ej.BACKGROUND || ejVar == ej.POSTBACKS) {
            this.f852c.mo2288d("TaskManager", "Scheduling " + diVar.f646c + " on " + ejVar + " queue in " + j + "ms with new queue size " + (m986a(ejVar) + 1));
            Runnable emVar = new em(this, diVar, ejVar);
            if (ejVar == ej.MAIN) {
                m989a(emVar, j, this.f853d);
            } else if (ejVar == ej.BACKGROUND) {
                m989a(emVar, j, this.f854e);
            } else if (ejVar == ej.POSTBACKS) {
                m989a(emVar, j, this.f855f);
            }
        } else {
            throw new IllegalArgumentException("Invalid queue specified");
        }
    }

    /* renamed from: a */
    void m994a(eg egVar, long j) {
        if (egVar == null) {
            throw new IllegalArgumentException("No task specified");
        }
        m989a((Runnable) egVar, j, this.f853d);
    }
}
