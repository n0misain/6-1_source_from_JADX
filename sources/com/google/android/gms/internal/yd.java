package com.google.android.gms.internal;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.lang.Thread.State;
import java.net.Socket;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class yd {
    private static final AtomicInteger zzciL = new AtomicInteger(0);
    private static final Charset zzciM = Charset.forName(HttpRequest.CHARSET_UTF8);
    private static ThreadFactory zzciU = Executors.defaultThreadFactory();
    private static yc zzciV = new ye();
    private volatile Socket socket = null;
    private final int zzaBC = zzciL.incrementAndGet();
    private volatile int zzciN = yh.zzciY;
    private yi zzciO = null;
    private final URI zzciP;
    private final ym zzciQ;
    private final yn zzciR;
    private final yk zzciS;
    private final Thread zzciT = zzciU.newThread(new yf(this));

    public yd(URI uri, String str, Map<String, String> map) {
        this.zzciP = uri;
        this.zzciS = new yk(uri, null, map);
        this.zzciQ = new ym(this);
        this.zzciR = new yn(this, "TubeSock", this.zzaBC);
    }

    private final Socket createSocket() {
        Throwable th;
        String str;
        String valueOf;
        String scheme = this.zzciP.getScheme();
        String host = this.zzciP.getHost();
        int port = this.zzciP.getPort();
        if (scheme != null && scheme.equals("ws")) {
            try {
                return new Socket(host, port == -1 ? 80 : port);
            } catch (Throwable e) {
                th = e;
                str = "unknown host: ";
                valueOf = String.valueOf(host);
                throw new yj(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
            } catch (Throwable e2) {
                host = String.valueOf(this.zzciP);
                throw new yj(new StringBuilder(String.valueOf(host).length() + 31).append("error while creating socket to ").append(host).toString(), e2);
            }
        } else if (scheme == null || !scheme.equals("wss")) {
            String str2 = "unsupported protocol: ";
            valueOf = String.valueOf(scheme);
            throw new yj(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            if (port == -1) {
                port = 443;
            }
            try {
                SSLSocket sSLSocket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(host, port);
                if (HttpsURLConnection.getDefaultHostnameVerifier().verify(host, sSLSocket.getSession())) {
                    return sSLSocket;
                }
                scheme = String.valueOf(this.zzciP);
                throw new yj(new StringBuilder(String.valueOf(scheme).length() + 39).append("Error while verifying secure socket to ").append(scheme).toString());
            } catch (Throwable e22) {
                th = e22;
                str = "unknown host: ";
                valueOf = String.valueOf(host);
                throw new yj(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
            } catch (Throwable e222) {
                host = String.valueOf(this.zzciP);
                throw new yj(new StringBuilder(String.valueOf(host).length() + 38).append("error while creating secure socket to ").append(host).toString(), e222);
            }
        }
    }

    static ThreadFactory getThreadFactory() {
        return zzciU;
    }

    static yc zzJo() {
        return zzciV;
    }

    private final synchronized void zzJr() {
        if (this.zzciN != yh.zzcjc) {
            this.zzciQ.zzJx();
            this.zzciR.zzJz();
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
            this.zzciN = yh.zzcjc;
            this.zzciO.onClose();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzJt() {
        /*
        r12 = this;
        r4 = 1;
        r11 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r3 = 0;
        r1 = r12.createSocket();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        monitor-enter(r12);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r12.socket = r1;	 Catch:{ all -> 0x0025 }
        r2 = r12.zzciN;	 Catch:{ all -> 0x0025 }
        r5 = com.google.android.gms.internal.yh.zzcjc;	 Catch:{ all -> 0x0025 }
        if (r2 != r5) goto L_0x0032;
    L_0x0011:
        r1 = r12.socket;	 Catch:{ IOException -> 0x001e }
        r1.close();	 Catch:{ IOException -> 0x001e }
        r1 = 0;
        r12.socket = r1;	 Catch:{ all -> 0x0025 }
        monitor-exit(r12);	 Catch:{ all -> 0x0025 }
        r12.close();
    L_0x001d:
        return;
    L_0x001e:
        r1 = move-exception;
        r2 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0025 }
        r2.<init>(r1);	 Catch:{ all -> 0x0025 }
        throw r2;	 Catch:{ all -> 0x0025 }
    L_0x0025:
        r1 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x0025 }
        throw r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x0028:
        r1 = move-exception;
        r2 = r12.zzciO;	 Catch:{ all -> 0x00c1 }
        r2.zza(r1);	 Catch:{ all -> 0x00c1 }
        r12.close();
        goto L_0x001d;
    L_0x0032:
        monitor-exit(r12);	 Catch:{ all -> 0x0025 }
        r7 = new java.io.DataInputStream;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = r1.getInputStream();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r7.<init>(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r8 = r1.getOutputStream();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r12.zzciS;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r1.zzJv();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r8.write(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r1 = new byte[r1];	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = new java.util.ArrayList;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2.<init>();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r5 = r3;
        r6 = r1;
        r1 = r3;
    L_0x0055:
        if (r1 != 0) goto L_0x00eb;
    L_0x0057:
        r9 = r7.read();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r10 = -1;
        if (r9 != r10) goto L_0x0089;
    L_0x005e:
        r1 = new com.google.android.gms.internal.yj;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = "Connection closed before handshake was complete";
        r1.<init>(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        throw r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x0066:
        r1 = move-exception;
        r3 = r12.zzciO;	 Catch:{ all -> 0x00c1 }
        r4 = new com.google.android.gms.internal.yj;	 Catch:{ all -> 0x00c1 }
        r5 = "error while connecting: ";
        r2 = r1.getMessage();	 Catch:{ all -> 0x00c1 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ all -> 0x00c1 }
        r6 = r2.length();	 Catch:{ all -> 0x00c1 }
        if (r6 == 0) goto L_0x01c9;
    L_0x007b:
        r2 = r5.concat(r2);	 Catch:{ all -> 0x00c1 }
    L_0x007f:
        r4.<init>(r2, r1);	 Catch:{ all -> 0x00c1 }
        r3.zza(r4);	 Catch:{ all -> 0x00c1 }
        r12.close();
        goto L_0x001d;
    L_0x0089:
        r9 = (byte) r9;
        r6[r5] = r9;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r5 = r5 + 1;
        r9 = r5 + -1;
        r9 = r6[r9];	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r10 = 10;
        if (r9 != r10) goto L_0x00c6;
    L_0x0096:
        r9 = r5 + -2;
        r9 = r6[r9];	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r10 = 13;
        if (r9 != r10) goto L_0x00c6;
    L_0x009e:
        r5 = new java.lang.String;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r9 = zzciM;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r5.<init>(r6, r9);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r6 = r5.trim();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r9 = "";
        r6 = r6.equals(r9);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        if (r6 == 0) goto L_0x00b9;
    L_0x00b1:
        r1 = r4;
    L_0x00b2:
        r5 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r5 = new byte[r5];	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r6 = r5;
        r5 = r3;
        goto L_0x0055;
    L_0x00b9:
        r5 = r5.trim();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2.add(r5);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        goto L_0x00b2;
    L_0x00c1:
        r1 = move-exception;
        r12.close();
        throw r1;
    L_0x00c6:
        if (r5 != r11) goto L_0x0055;
    L_0x00c8:
        r1 = new java.lang.String;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = zzciM;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1.<init>(r6, r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = new com.google.android.gms.internal.yj;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r3 = "Unexpected long line in handshake: ";
        r1 = java.lang.String.valueOf(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r4 = r1.length();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        if (r4 == 0) goto L_0x00e5;
    L_0x00dd:
        r1 = r3.concat(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x00e1:
        r2.<init>(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        throw r2;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x00e5:
        r1 = new java.lang.String;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1.<init>(r3);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        goto L_0x00e1;
    L_0x00eb:
        r1 = 0;
        r1 = r2.get(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = (java.lang.String) r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r4 = 9;
        r5 = 12;
        r1 = r1.substring(r4, r5);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r1.intValue();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r4 = 407; // 0x197 float:5.7E-43 double:2.01E-321;
        if (r1 != r4) goto L_0x010e;
    L_0x0106:
        r1 = new com.google.android.gms.internal.yj;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = "connection failed: proxy authentication not supported";
        r1.<init>(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        throw r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x010e:
        r4 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r1 != r4) goto L_0x011a;
    L_0x0112:
        r1 = new com.google.android.gms.internal.yj;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = "connection failed: 404 not found";
        r1.<init>(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        throw r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x011a:
        r4 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r1 == r4) goto L_0x0139;
    L_0x011e:
        r2 = new com.google.android.gms.internal.yj;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r3 = 50;
        r4 = new java.lang.StringBuilder;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r4.<init>(r3);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r3 = "connection failed: unknown status code ";
        r3 = r4.append(r3);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r3.append(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r1.toString();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2.<init>(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        throw r2;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x0139:
        r1 = 0;
        r2.remove(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r4 = new java.util.HashMap;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r4.<init>();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r0 = r2;
        r0 = (java.util.ArrayList) r0;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r0;
        r5 = r1.size();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x014a:
        if (r3 >= r5) goto L_0x0165;
    L_0x014c:
        r2 = r1.get(r3);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r3 = r3 + 1;
        r2 = (java.lang.String) r2;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r6 = ": ";
        r9 = 2;
        r2 = r2.split(r6, r9);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r6 = 0;
        r6 = r2[r6];	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r9 = 1;
        r2 = r2[r9];	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r4.put(r6, r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        goto L_0x014a;
    L_0x0165:
        r1 = "Upgrade";
        r1 = r4.get(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = (java.lang.String) r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = java.util.Locale.US;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r1.toLowerCase(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = "websocket";
        r1 = r1.equals(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        if (r1 != 0) goto L_0x0184;
    L_0x017c:
        r1 = new com.google.android.gms.internal.yj;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = "connection failed: missing header field in server handshake: Upgrade";
        r1.<init>(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        throw r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x0184:
        r1 = "Connection";
        r1 = r4.get(r1);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = (java.lang.String) r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = java.util.Locale.US;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r1.toLowerCase(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = "upgrade";
        r1 = r1.equals(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        if (r1 != 0) goto L_0x01a3;
    L_0x019b:
        r1 = new com.google.android.gms.internal.yj;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r2 = "connection failed: missing header field in server handshake: Connection";
        r1.<init>(r2);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        throw r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
    L_0x01a3:
        r1 = r12.zzciR;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1.zzb(r8);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r12.zzciQ;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1.zza(r7);	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = com.google.android.gms.internal.yh.zzcja;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r12.zzciN = r1;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r12.zzciR;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r1.zzJB();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1.start();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r12.zzciO;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1.zzGy();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1 = r12.zzciQ;	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r1.run();	 Catch:{ yj -> 0x0028, IOException -> 0x0066 }
        r12.close();
        goto L_0x001d;
    L_0x01c9:
        r2 = new java.lang.String;	 Catch:{ all -> 0x00c1 }
        r2.<init>(r5);	 Catch:{ all -> 0x00c1 }
        goto L_0x007f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.yd.zzJt():void");
    }

    private final synchronized void zza(byte b, byte[] bArr) {
        if (this.zzciN != yh.zzcja) {
            this.zzciO.zza(new yj("error while sending data: not connected"));
        } else {
            try {
                this.zzciR.zza(b, true, bArr);
            } catch (Throwable e) {
                this.zzciO.zza(new yj("Failed to send frame", e));
                close();
            }
        }
    }

    public static void zza(ThreadFactory threadFactory, yc ycVar) {
        zzciU = threadFactory;
        zzciV = ycVar;
    }

    public final synchronized void close() {
        switch (yg.zzciX[this.zzciN - 1]) {
            case 1:
                this.zzciN = yh.zzcjc;
                break;
            case 2:
                zzJr();
                break;
            case 3:
                try {
                    this.zzciN = yh.zzcjb;
                    this.zzciR.zzJz();
                    this.zzciR.zza((byte) 8, true, new byte[0]);
                    break;
                } catch (Throwable e) {
                    this.zzciO.zza(new yj("Failed to send close frame", e));
                    break;
                }
        }
    }

    public final synchronized void connect() {
        if (this.zzciN != yh.zzciY) {
            this.zzciO.zza(new yj("connect() already called"));
            close();
        } else {
            yc ycVar = zzciV;
            Thread thread = this.zzciT;
            String valueOf = String.valueOf("TubeSockReader-");
            ycVar.zza(thread, new StringBuilder(String.valueOf(valueOf).length() + 11).append(valueOf).append(this.zzaBC).toString());
            this.zzciN = yh.zzciZ;
            this.zzciT.start();
        }
    }

    final synchronized void zzE(byte[] bArr) {
        zza((byte) 10, bArr);
    }

    final yi zzJp() {
        return this.zzciO;
    }

    final void zzJq() {
        zzJr();
    }

    public final void zzJs() throws InterruptedException {
        if (this.zzciR.zzJB().getState() != State.NEW) {
            this.zzciR.zzJB().join();
        }
        this.zzciT.join();
    }

    public final void zza(yi yiVar) {
        this.zzciO = yiVar;
    }

    final void zzb(yj yjVar) {
        this.zzciO.zza(yjVar);
        if (this.zzciN == yh.zzcja) {
            close();
        }
        zzJr();
    }

    public final synchronized void zzgM(String str) {
        zza((byte) 1, str.getBytes(zzciM));
    }
}
