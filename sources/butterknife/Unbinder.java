package butterknife;

import android.support.annotation.UiThread;

public interface Unbinder {
    public static final Unbinder EMPTY = new C04371();

    /* renamed from: butterknife.Unbinder$1 */
    static class C04371 implements Unbinder {
        C04371() {
        }

        public void unbind() {
        }
    }

    @UiThread
    void unbind();
}
