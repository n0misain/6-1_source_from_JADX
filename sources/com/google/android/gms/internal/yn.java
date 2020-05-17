package com.google.android.gms.internal;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.TransportMediator;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

final class yn {
    private final Random random = new Random();
    private final Thread zzciT = yd.getThreadFactory().newThread(new yo(this));
    private yd zzcjk;
    private volatile boolean zzcjn = false;
    private BlockingQueue<ByteBuffer> zzcjo;
    private boolean zzcjp = false;
    private WritableByteChannel zzcjq;

    yn(yd ydVar, String str, int i) {
        yd.zzJo().zza(this.zzciT, new StringBuilder(String.valueOf(str).length() + 18).append(str).append("Writer-").append(i).toString());
        this.zzcjk = ydVar;
        this.zzcjo = new LinkedBlockingQueue();
    }

    private final void zzJA() {
        while (!this.zzcjn && !Thread.interrupted()) {
            try {
                zzJy();
            } catch (Throwable e) {
                this.zzcjk.zzb(new yj("IO Exception", e));
                return;
            } catch (InterruptedException e2) {
                return;
            }
        }
        for (int i = 0; i < this.zzcjo.size(); i++) {
            zzJy();
        }
    }

    private final void zzJy() throws InterruptedException, IOException {
        this.zzcjq.write((ByteBuffer) this.zzcjo.take());
    }

    final Thread zzJB() {
        return this.zzciT;
    }

    final void zzJz() {
        this.zzcjn = true;
    }

    final synchronized void zza(byte b, boolean z, byte[] bArr) throws IOException {
        synchronized (this) {
            int i = 6;
            int length = bArr.length;
            if (length >= TransportMediator.KEYCODE_MEDIA_PLAY) {
                i = length <= SupportMenu.USER_MASK ? 8 : 14;
            }
            ByteBuffer allocate = ByteBuffer.allocate(i + bArr.length);
            allocate.put((byte) (b | -128));
            if (length < TransportMediator.KEYCODE_MEDIA_PLAY) {
                allocate.put((byte) (length | 128));
            } else if (length <= SupportMenu.USER_MASK) {
                allocate.put((byte) -2);
                allocate.putShort((short) length);
            } else {
                allocate.put((byte) -1);
                allocate.putInt(0);
                allocate.putInt(length);
            }
            byte[] bArr2 = new byte[4];
            this.random.nextBytes(bArr2);
            allocate.put(bArr2);
            for (int i2 = 0; i2 < bArr.length; i2++) {
                allocate.put((byte) (bArr[i2] ^ bArr2[i2 % 4]));
            }
            allocate.flip();
            if (!this.zzcjn || (!this.zzcjp && b == (byte) 8)) {
                if (b == (byte) 8) {
                    this.zzcjp = true;
                }
                this.zzcjo.add(allocate);
            } else {
                throw new yj("Shouldn't be sending");
            }
        }
    }

    final void zzb(OutputStream outputStream) {
        this.zzcjq = Channels.newChannel(outputStream);
    }
}
