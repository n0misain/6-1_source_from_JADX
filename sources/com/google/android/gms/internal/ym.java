package com.google.android.gms.internal;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

final class ym {
    private yi zzciO = null;
    private DataInputStream zzcjj = null;
    private yd zzcjk = null;
    private byte[] zzcjl = new byte[112];
    private xy zzcjm;
    private volatile boolean zzcjn = false;

    ym(yd ydVar) {
        this.zzcjk = ydVar;
    }

    private final int read(byte[] bArr, int i, int i2) throws IOException {
        this.zzcjj.readFully(bArr, i, i2);
        return i2;
    }

    private final void zzc(yj yjVar) {
        this.zzcjn = true;
        this.zzcjk.zzb(yjVar);
    }

    final void run() {
        this.zzciO = this.zzcjk.zzJp();
        while (!this.zzcjn) {
            try {
                int read = read(this.zzcjl, 0, 1) + 0;
                Object obj = (this.zzcjl[0] & 128) != 0 ? 1 : null;
                if (((this.zzcjl[0] & 112) != 0 ? 1 : null) != null) {
                    throw new yj("Invalid frame received");
                }
                byte b = (byte) (this.zzcjl[0] & 15);
                int read2 = read + read(this.zzcjl, read, 1);
                byte b2 = this.zzcjl[1];
                long j = 0;
                if (b2 < (byte) 126) {
                    j = (long) b2;
                } else if (b2 == (byte) 126) {
                    read(this.zzcjl, read2, 2);
                    j = (long) (((this.zzcjl[2] & 255) << 8) | (this.zzcjl[3] & 255));
                } else if (b2 == Byte.MAX_VALUE) {
                    int read3 = read(this.zzcjl, read2, 8) + read2;
                    byte[] bArr = this.zzcjl;
                    read3 -= 8;
                    j = ((long) (bArr[read3 + 7] & 255)) + (((((((((long) bArr[read3]) << 56) + (((long) (bArr[read3 + 1] & 255)) << 48)) + (((long) (bArr[read3 + 2] & 255)) << 40)) + (((long) (bArr[read3 + 3] & 255)) << 32)) + (((long) (bArr[read3 + 4] & 255)) << 24)) + ((long) ((bArr[read3 + 5] & 255) << 16))) + ((long) ((bArr[read3 + 6] & 255) << 8)));
                }
                byte[] bArr2 = new byte[((int) j)];
                read(bArr2, 0, (int) j);
                if (b == (byte) 8) {
                    this.zzcjk.zzJq();
                } else if (b == (byte) 10) {
                    continue;
                } else if (b != (byte) 1 && b != (byte) 2 && b != (byte) 9 && b != (byte) 0) {
                    throw new yj("Unsupported opcode: " + b);
                } else if (b == (byte) 9) {
                    if (obj == null) {
                        throw new yj("PING must not fragment across frames");
                    } else if (bArr2.length <= 125) {
                        this.zzcjk.zzE(bArr2);
                    } else {
                        throw new yj("PING frame too long");
                    }
                } else if (this.zzcjm != null && b != (byte) 0) {
                    throw new yj("Failed to continue outstanding frame");
                } else if (this.zzcjm == null && b == (byte) 0) {
                    throw new yj("Received continuing frame, but there's nothing to continue");
                } else {
                    if (this.zzcjm == null) {
                        this.zzcjm = b == (byte) 2 ? new xx() : new xz();
                    }
                    if (!this.zzcjm.zzC(bArr2)) {
                        throw new yj("Failed to decode frame");
                    } else if (obj != null) {
                        yl zzJn = this.zzcjm.zzJn();
                        this.zzcjm = null;
                        if (zzJn == null) {
                            throw new yj("Failed to decode whole message");
                        }
                        this.zzciO.zza(zzJn);
                    } else {
                        continue;
                    }
                }
            } catch (SocketTimeoutException e) {
            } catch (Throwable e2) {
                zzc(new yj("IO Error", e2));
            } catch (yj e3) {
                zzc(e3);
            }
        }
    }

    final void zzJx() {
        this.zzcjn = true;
    }

    final void zza(DataInputStream dataInputStream) {
        this.zzcjj = dataInputStream;
    }
}
