package okhttp3;

import java.io.IOException;

public interface Authenticator {
    public static final Authenticator NONE = new C11061();

    /* renamed from: okhttp3.Authenticator$1 */
    static class C11061 implements Authenticator {
        C11061() {
        }

        public Request authenticate(Route route, Response response) {
            return null;
        }
    }

    Request authenticate(Route route, Response response) throws IOException;
}
