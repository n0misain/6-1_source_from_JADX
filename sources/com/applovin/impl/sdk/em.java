package com.applovin.impl.sdk;

class em implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ei f863a;
    /* renamed from: b */
    private final String f864b;
    /* renamed from: c */
    private final di f865c;
    /* renamed from: d */
    private final ej f866d;

    em(ei eiVar, di diVar, ej ejVar) {
        this.f863a = eiVar;
        this.f864b = diVar.m676a();
        this.f865c = diVar;
        this.f866d = ejVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r8 = 1;
        r2 = java.lang.System.currentTimeMillis();
        com.applovin.impl.sdk.C0516u.m1157a();	 Catch:{ Throwable -> 0x010f }
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f851b;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.m471e();	 Catch:{ Throwable -> 0x010f }
        if (r0 != 0) goto L_0x01c6;
    L_0x0015:
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f851b;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.isEnabled();	 Catch:{ Throwable -> 0x010f }
        if (r0 == 0) goto L_0x00f4;
    L_0x0021:
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f852c;	 Catch:{ Throwable -> 0x010f }
        r1 = r10.f864b;	 Catch:{ Throwable -> 0x010f }
        r4 = "Task started execution...";
        r0.mo2291i(r1, r4);	 Catch:{ Throwable -> 0x010f }
        r0 = r10.f865c;	 Catch:{ Throwable -> 0x010f }
        r0.run();	 Catch:{ Throwable -> 0x010f }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x010f }
        r4 = r0 - r2;
        r0 = r10.f865c;	 Catch:{ Throwable -> 0x010f }
        r0 = r0 instanceof com.applovin.impl.sdk.fi;	 Catch:{ Throwable -> 0x010f }
        if (r0 == 0) goto L_0x0058;
    L_0x003f:
        r0 = r10.f865c;	 Catch:{ Throwable -> 0x010f }
        r0 = (com.applovin.impl.sdk.fi) r0;	 Catch:{ Throwable -> 0x010f }
        r1 = com.applovin.impl.sdk.fd.m1052a();	 Catch:{ Throwable -> 0x010f }
        r0 = r0.mo2281c();	 Catch:{ Throwable -> 0x010f }
        r6 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r6 = r6.f851b;	 Catch:{ Throwable -> 0x010f }
        r6 = com.applovin.impl.sdk.C0518x.m1176a(r6);	 Catch:{ Throwable -> 0x010f }
        r1.m1054a(r0, r4, r6);	 Catch:{ Throwable -> 0x010f }
    L_0x0058:
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f852c;	 Catch:{ Throwable -> 0x010f }
        r1 = r10.f864b;	 Catch:{ Throwable -> 0x010f }
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x010f }
        r6.<init>();	 Catch:{ Throwable -> 0x010f }
        r7 = "Task executed successfully in ";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x010f }
        r6 = r6.append(r4);	 Catch:{ Throwable -> 0x010f }
        r7 = "ms.";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x010f }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x010f }
        r0.mo2291i(r1, r6);	 Catch:{ Throwable -> 0x010f }
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f851b;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.m468b();	 Catch:{ Throwable -> 0x010f }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x010f }
        r1.<init>();	 Catch:{ Throwable -> 0x010f }
        r6 = r10.f864b;	 Catch:{ Throwable -> 0x010f }
        r1 = r1.append(r6);	 Catch:{ Throwable -> 0x010f }
        r6 = "_count";
        r1 = r1.append(r6);	 Catch:{ Throwable -> 0x010f }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x010f }
        r0.m878a(r1);	 Catch:{ Throwable -> 0x010f }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x010f }
        r1.<init>();	 Catch:{ Throwable -> 0x010f }
        r6 = r10.f864b;	 Catch:{ Throwable -> 0x010f }
        r1 = r1.append(r6);	 Catch:{ Throwable -> 0x010f }
        r6 = "_time";
        r1 = r1.append(r6);	 Catch:{ Throwable -> 0x010f }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x010f }
        r0.m879a(r1, r4);	 Catch:{ Throwable -> 0x010f }
    L_0x00b6:
        r0 = r10.f863a;
        r1 = r10.f866d;
        r0 = r0.m986a(r1);
        r0 = r0 - r8;
        r2 = r10.f863a;
        r2 = r2.f852c;
        r3 = "TaskManager";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r10.f866d;
        r4 = r4.append(r5);
        r5 = " queue finished task ";
        r4 = r4.append(r5);
        r5 = r10.f865c;
        r5 = r5.m676a();
        r4 = r4.append(r5);
        r5 = " with queue size ";
        r4 = r4.append(r5);
        r0 = r4.append(r0);
        r0 = r0.toString();
        r2.mo2291i(r3, r0);
    L_0x00f3:
        return;
    L_0x00f4:
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f851b;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.m472f();	 Catch:{ Throwable -> 0x010f }
        if (r0 == 0) goto L_0x0179;
    L_0x0100:
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f851b;	 Catch:{ Throwable -> 0x010f }
        r0.m473g();	 Catch:{ Throwable -> 0x010f }
    L_0x0109:
        r0 = r10.f865c;	 Catch:{ Throwable -> 0x010f }
        r0.mo2280b();	 Catch:{ Throwable -> 0x010f }
        goto L_0x00b6;
    L_0x010f:
        r0 = move-exception;
        r1 = r10.f863a;	 Catch:{ all -> 0x0187 }
        r1 = r1.f852c;	 Catch:{ all -> 0x0187 }
        r4 = r10.f864b;	 Catch:{ all -> 0x0187 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0187 }
        r5.<init>();	 Catch:{ all -> 0x0187 }
        r6 = "Task failed execution in ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0187 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0187 }
        r2 = r6 - r2;
        r2 = r5.append(r2);	 Catch:{ all -> 0x0187 }
        r3 = "ms.";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0187 }
        r2 = r2.toString();	 Catch:{ all -> 0x0187 }
        r1.mo2290e(r4, r2, r0);	 Catch:{ all -> 0x0187 }
        r0 = r10.f863a;
        r1 = r10.f866d;
        r0 = r0.m986a(r1);
        r0 = r0 - r8;
        r2 = r10.f863a;
        r2 = r2.f852c;
        r3 = "TaskManager";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = r10.f866d;
        r4 = r4.append(r5);
        r5 = " queue finished task ";
        r4 = r4.append(r5);
        r5 = r10.f865c;
        r5 = r5.m676a();
        r4 = r4.append(r5);
        r5 = " with queue size ";
        r4 = r4.append(r5);
        r0 = r4.append(r0);
        r0 = r0.toString();
        r2.mo2291i(r3, r0);
        goto L_0x00f3;
    L_0x0179:
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f852c;	 Catch:{ Throwable -> 0x010f }
        r1 = r10.f864b;	 Catch:{ Throwable -> 0x010f }
        r4 = "Task not executed, SDK is disabled";
        r0.mo2294w(r1, r4);	 Catch:{ Throwable -> 0x010f }
        goto L_0x0109;
    L_0x0187:
        r0 = move-exception;
        r1 = r10.f863a;
        r2 = r10.f866d;
        r2 = r1.m986a(r2);
        r2 = r2 - r8;
        r1 = r10.f863a;
        r1 = r1.f852c;
        r4 = "TaskManager";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r10.f866d;
        r5 = r5.append(r6);
        r6 = " queue finished task ";
        r5 = r5.append(r6);
        r6 = r10.f865c;
        r6 = r6.m676a();
        r5 = r5.append(r6);
        r6 = " with queue size ";
        r5 = r5.append(r6);
        r2 = r5.append(r2);
        r2 = r2.toString();
        r1.mo2291i(r4, r2);
        throw r0;
    L_0x01c6:
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r0 = r0.f852c;	 Catch:{ Throwable -> 0x010f }
        r1 = r10.f864b;	 Catch:{ Throwable -> 0x010f }
        r4 = "Task re-scheduled...";
        r0.mo2291i(r1, r4);	 Catch:{ Throwable -> 0x010f }
        r0 = r10.f863a;	 Catch:{ Throwable -> 0x010f }
        r1 = r10.f865c;	 Catch:{ Throwable -> 0x010f }
        r4 = r10.f866d;	 Catch:{ Throwable -> 0x010f }
        r6 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
        r0.m993a(r1, r4, r6);	 Catch:{ Throwable -> 0x010f }
        goto L_0x00b6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.em.run():void");
    }
}
