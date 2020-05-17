package android.support.v7.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;

public class MediaRouteDiscoveryFragment extends Fragment {
    private final String ARGUMENT_SELECTOR = "selector";
    private Callback mCallback;
    private MediaRouter mRouter;
    private MediaRouteSelector mSelector;

    /* renamed from: android.support.v7.app.MediaRouteDiscoveryFragment$1 */
    class C02941 extends Callback {
        C02941() {
        }
    }

    public MediaRouter getMediaRouter() {
        ensureRouter();
        return this.mRouter;
    }

    private void ensureRouter() {
        if (this.mRouter == null) {
            this.mRouter = MediaRouter.getInstance(getContext());
        }
    }

    public MediaRouteSelector getRouteSelector() {
        ensureRouteSelector();
        return this.mSelector;
    }

    public void setRouteSelector(MediaRouteSelector selector) {
        if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        ensureRouteSelector();
        if (!this.mSelector.equals(selector)) {
            this.mSelector = selector;
            Bundle args = getArguments();
            if (args == null) {
                args = new Bundle();
            }
            args.putBundle("selector", selector.asBundle());
            setArguments(args);
            if (this.mCallback != null) {
                this.mRouter.removeCallback(this.mCallback);
                this.mRouter.addCallback(this.mSelector, this.mCallback, onPrepareCallbackFlags());
            }
        }
    }

    private void ensureRouteSelector() {
        if (this.mSelector == null) {
            Bundle args = getArguments();
            if (args != null) {
                this.mSelector = MediaRouteSelector.fromBundle(args.getBundle("selector"));
            }
            if (this.mSelector == null) {
                this.mSelector = MediaRouteSelector.EMPTY;
            }
        }
    }

    public Callback onCreateCallback() {
        return new C02941();
    }

    public int onPrepareCallbackFlags() {
        return 4;
    }

    public void onStart() {
        super.onStart();
        ensureRouteSelector();
        ensureRouter();
        this.mCallback = onCreateCallback();
        if (this.mCallback != null) {
            this.mRouter.addCallback(this.mSelector, this.mCallback, onPrepareCallbackFlags());
        }
    }

    public void onStop() {
        if (this.mCallback != null) {
            this.mRouter.removeCallback(this.mCallback);
            this.mCallback = null;
        }
        super.onStop();
    }
}
