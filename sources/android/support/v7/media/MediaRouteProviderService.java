package android.support.v7.media;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.media.MediaRouteProvider.Callback;
import android.support.v7.media.MediaRouteProvider.RouteController;
import android.support.v7.media.MediaRouteProviderDescriptor.Builder;
import android.support.v7.media.MediaRouter.ControlRequestCallback;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class MediaRouteProviderService extends Service {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final int PRIVATE_MSG_CLIENT_DIED = 1;
    public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
    static final String TAG = "MediaRouteProviderSrv";
    private final ArrayList<ClientRecord> mClients = new ArrayList();
    private MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
    final PrivateHandler mPrivateHandler = new PrivateHandler();
    MediaRouteProvider mProvider;
    private final ProviderCallback mProviderCallback = new ProviderCallback();
    private final ReceiveHandler mReceiveHandler = new ReceiveHandler(this);
    private final Messenger mReceiveMessenger = new Messenger(this.mReceiveHandler);

    private final class ClientRecord implements DeathRecipient {
        private final SparseArray<RouteController> mControllers = new SparseArray();
        public MediaRouteDiscoveryRequest mDiscoveryRequest;
        public final Messenger mMessenger;
        public final int mVersion;

        public ClientRecord(Messenger messenger, int version) {
            this.mMessenger = messenger;
            this.mVersion = version;
        }

        public boolean register() {
            try {
                this.mMessenger.getBinder().linkToDeath(this, 0);
                return true;
            } catch (RemoteException e) {
                binderDied();
                return false;
            }
        }

        public void dispose() {
            int count = this.mControllers.size();
            for (int i = 0; i < count; i++) {
                ((RouteController) this.mControllers.valueAt(i)).onRelease();
            }
            this.mControllers.clear();
            this.mMessenger.getBinder().unlinkToDeath(this, 0);
            setDiscoveryRequest(null);
        }

        public boolean hasMessenger(Messenger other) {
            return this.mMessenger.getBinder() == other.getBinder();
        }

        public boolean createRouteController(String routeId, String routeGroupId, int controllerId) {
            if (this.mControllers.indexOfKey(controllerId) < 0) {
                RouteController controller;
                if (routeGroupId == null) {
                    controller = MediaRouteProviderService.this.mProvider.onCreateRouteController(routeId);
                } else {
                    controller = MediaRouteProviderService.this.mProvider.onCreateRouteController(routeId, routeGroupId);
                }
                if (controller != null) {
                    this.mControllers.put(controllerId, controller);
                    return true;
                }
            }
            return false;
        }

        public boolean releaseRouteController(int controllerId) {
            RouteController controller = (RouteController) this.mControllers.get(controllerId);
            if (controller == null) {
                return false;
            }
            this.mControllers.remove(controllerId);
            controller.onRelease();
            return true;
        }

        public RouteController getRouteController(int controllerId) {
            return (RouteController) this.mControllers.get(controllerId);
        }

        public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest request) {
            if (this.mDiscoveryRequest == request || (this.mDiscoveryRequest != null && this.mDiscoveryRequest.equals(request))) {
                return false;
            }
            this.mDiscoveryRequest = request;
            return MediaRouteProviderService.this.updateCompositeDiscoveryRequest();
        }

        public void binderDied() {
            MediaRouteProviderService.this.mPrivateHandler.obtainMessage(1, this.mMessenger).sendToTarget();
        }

        public String toString() {
            return MediaRouteProviderService.getClientId(this.mMessenger);
        }
    }

    private final class PrivateHandler extends Handler {
        PrivateHandler() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    MediaRouteProviderService.this.onBinderDied((Messenger) msg.obj);
                    return;
                default:
                    return;
            }
        }
    }

    private final class ProviderCallback extends Callback {
        ProviderCallback() {
        }

        public void onDescriptorChanged(MediaRouteProvider provider, MediaRouteProviderDescriptor descriptor) {
            MediaRouteProviderService.this.sendDescriptorChanged(descriptor);
        }
    }

    private static final class ReceiveHandler extends Handler {
        private final WeakReference<MediaRouteProviderService> mServiceRef;

        public ReceiveHandler(MediaRouteProviderService service) {
            this.mServiceRef = new WeakReference(service);
        }

        public void handleMessage(Message msg) {
            Messenger messenger = msg.replyTo;
            if (MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
                int what = msg.what;
                int requestId = msg.arg1;
                int arg = msg.arg2;
                Object obj = msg.obj;
                Bundle data = msg.peekData();
                if (!processMessage(what, messenger, requestId, arg, obj, data)) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, MediaRouteProviderService.getClientId(messenger) + ": Message failed, what=" + what + ", requestId=" + requestId + ", arg=" + arg + ", obj=" + obj + ", data=" + data);
                    }
                    MediaRouteProviderService.sendGenericFailure(messenger, requestId);
                }
            } else if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, "Ignoring message without valid reply messenger.");
            }
        }

        private boolean processMessage(int what, Messenger messenger, int requestId, int arg, Object obj, Bundle data) {
            MediaRouteProviderService service = (MediaRouteProviderService) this.mServiceRef.get();
            if (service != null) {
                switch (what) {
                    case 1:
                        return service.onRegisterClient(messenger, requestId, arg);
                    case 2:
                        return service.onUnregisterClient(messenger, requestId);
                    case 3:
                        String routeId = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
                        String routeGroupId = data.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_LIBRARY_GROUP);
                        if (routeId != null) {
                            return service.onCreateRouteController(messenger, requestId, arg, routeId, routeGroupId);
                        }
                        break;
                    case 4:
                        return service.onReleaseRouteController(messenger, requestId, arg);
                    case 5:
                        return service.onSelectRoute(messenger, requestId, arg);
                    case 6:
                        int reason;
                        if (data == null) {
                            reason = 0;
                        } else {
                            reason = data.getInt(MediaRouteProviderProtocol.CLIENT_DATA_UNSELECT_REASON, 0);
                        }
                        return service.onUnselectRoute(messenger, requestId, arg, reason);
                    case 7:
                        int volume = data.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, -1);
                        if (volume >= 0) {
                            return service.onSetRouteVolume(messenger, requestId, arg, volume);
                        }
                        break;
                    case 8:
                        int delta = data.getInt(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, 0);
                        if (delta != 0) {
                            return service.onUpdateRouteVolume(messenger, requestId, arg, delta);
                        }
                        break;
                    case 9:
                        if (obj instanceof Intent) {
                            return service.onRouteControlRequest(messenger, requestId, arg, (Intent) obj);
                        }
                        break;
                    case 10:
                        if (obj == null || (obj instanceof Bundle)) {
                            MediaRouteDiscoveryRequest request = MediaRouteDiscoveryRequest.fromBundle((Bundle) obj);
                            if (request == null || !request.isValid()) {
                                request = null;
                            }
                            return service.onSetDiscoveryRequest(messenger, requestId, request);
                        }
                }
            }
            return false;
        }
    }

    public abstract MediaRouteProvider onCreateMediaRouteProvider();

    public MediaRouteProvider getMediaRouteProvider() {
        return this.mProvider;
    }

    public IBinder onBind(Intent intent) {
        if (intent.getAction().equals("android.media.MediaRouteProviderService")) {
            if (this.mProvider == null) {
                MediaRouteProvider provider = onCreateMediaRouteProvider();
                if (provider != null) {
                    String providerPackage = provider.getMetadata().getPackageName();
                    if (providerPackage.equals(getPackageName())) {
                        this.mProvider = provider;
                        this.mProvider.setCallback(this.mProviderCallback);
                    } else {
                        throw new IllegalStateException("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: " + providerPackage + ".  Service package name: " + getPackageName() + ".");
                    }
                }
            }
            if (this.mProvider != null) {
                return this.mReceiveMessenger.getBinder();
            }
        }
        return null;
    }

    public boolean onUnbind(Intent intent) {
        if (this.mProvider != null) {
            this.mProvider.setCallback(null);
        }
        return super.onUnbind(intent);
    }

    boolean onRegisterClient(Messenger messenger, int requestId, int version) {
        if (version >= 1 && findClient(messenger) < 0) {
            ClientRecord client = new ClientRecord(messenger, version);
            if (client.register()) {
                this.mClients.add(client);
                if (DEBUG) {
                    Log.d(TAG, client + ": Registered, version=" + version);
                }
                if (requestId == 0) {
                    return true;
                }
                sendReply(messenger, 2, requestId, 1, createDescriptorBundleForClient(this.mProvider.getDescriptor(), client), null);
                return true;
            }
        }
        return false;
    }

    boolean onUnregisterClient(Messenger messenger, int requestId) {
        int index = findClient(messenger);
        if (index < 0) {
            return false;
        }
        ClientRecord client = (ClientRecord) this.mClients.remove(index);
        if (DEBUG) {
            Log.d(TAG, client + ": Unregistered");
        }
        client.dispose();
        sendGenericSuccess(messenger, requestId);
        return true;
    }

    void onBinderDied(Messenger messenger) {
        int index = findClient(messenger);
        if (index >= 0) {
            ClientRecord client = (ClientRecord) this.mClients.remove(index);
            if (DEBUG) {
                Log.d(TAG, client + ": Binder died");
            }
            client.dispose();
        }
    }

    boolean onCreateRouteController(Messenger messenger, int requestId, int controllerId, String routeId, String routeGroupId) {
        ClientRecord client = getClient(messenger);
        if (client == null || !client.createRouteController(routeId, routeGroupId, controllerId)) {
            return false;
        }
        if (DEBUG) {
            Log.d(TAG, client + ": Route controller created, controllerId=" + controllerId + ", routeId=" + routeId + ", routeGroupId=" + routeGroupId);
        }
        sendGenericSuccess(messenger, requestId);
        return true;
    }

    boolean onReleaseRouteController(Messenger messenger, int requestId, int controllerId) {
        ClientRecord client = getClient(messenger);
        if (client == null || !client.releaseRouteController(controllerId)) {
            return false;
        }
        if (DEBUG) {
            Log.d(TAG, client + ": Route controller released" + ", controllerId=" + controllerId);
        }
        sendGenericSuccess(messenger, requestId);
        return true;
    }

    boolean onSelectRoute(Messenger messenger, int requestId, int controllerId) {
        ClientRecord client = getClient(messenger);
        if (client != null) {
            RouteController controller = client.getRouteController(controllerId);
            if (controller != null) {
                controller.onSelect();
                if (DEBUG) {
                    Log.d(TAG, client + ": Route selected" + ", controllerId=" + controllerId);
                }
                sendGenericSuccess(messenger, requestId);
                return true;
            }
        }
        return false;
    }

    boolean onUnselectRoute(Messenger messenger, int requestId, int controllerId, int reason) {
        ClientRecord client = getClient(messenger);
        if (client != null) {
            RouteController controller = client.getRouteController(controllerId);
            if (controller != null) {
                controller.onUnselect(reason);
                if (DEBUG) {
                    Log.d(TAG, client + ": Route unselected" + ", controllerId=" + controllerId);
                }
                sendGenericSuccess(messenger, requestId);
                return true;
            }
        }
        return false;
    }

    boolean onSetRouteVolume(Messenger messenger, int requestId, int controllerId, int volume) {
        ClientRecord client = getClient(messenger);
        if (client != null) {
            RouteController controller = client.getRouteController(controllerId);
            if (controller != null) {
                controller.onSetVolume(volume);
                if (DEBUG) {
                    Log.d(TAG, client + ": Route volume changed" + ", controllerId=" + controllerId + ", volume=" + volume);
                }
                sendGenericSuccess(messenger, requestId);
                return true;
            }
        }
        return false;
    }

    boolean onUpdateRouteVolume(Messenger messenger, int requestId, int controllerId, int delta) {
        ClientRecord client = getClient(messenger);
        if (client != null) {
            RouteController controller = client.getRouteController(controllerId);
            if (controller != null) {
                controller.onUpdateVolume(delta);
                if (DEBUG) {
                    Log.d(TAG, client + ": Route volume updated" + ", controllerId=" + controllerId + ", delta=" + delta);
                }
                sendGenericSuccess(messenger, requestId);
                return true;
            }
        }
        return false;
    }

    boolean onRouteControlRequest(Messenger messenger, int requestId, int controllerId, Intent intent) {
        final ClientRecord client = getClient(messenger);
        if (client != null) {
            RouteController controller = client.getRouteController(controllerId);
            if (controller != null) {
                ControlRequestCallback callback = null;
                if (requestId != 0) {
                    final int i = controllerId;
                    final Intent intent2 = intent;
                    final Messenger messenger2 = messenger;
                    final int i2 = requestId;
                    callback = new ControlRequestCallback() {
                        public void onResult(Bundle data) {
                            if (MediaRouteProviderService.DEBUG) {
                                Log.d(MediaRouteProviderService.TAG, client + ": Route control request succeeded" + ", controllerId=" + i + ", intent=" + intent2 + ", data=" + data);
                            }
                            if (MediaRouteProviderService.this.findClient(messenger2) >= 0) {
                                MediaRouteProviderService.sendReply(messenger2, 3, i2, 0, data, null);
                            }
                        }

                        public void onError(String error, Bundle data) {
                            if (MediaRouteProviderService.DEBUG) {
                                Log.d(MediaRouteProviderService.TAG, client + ": Route control request failed" + ", controllerId=" + i + ", intent=" + intent2 + ", error=" + error + ", data=" + data);
                            }
                            if (MediaRouteProviderService.this.findClient(messenger2) < 0) {
                                return;
                            }
                            if (error != null) {
                                Bundle bundle = new Bundle();
                                bundle.putString("error", error);
                                MediaRouteProviderService.sendReply(messenger2, 4, i2, 0, data, bundle);
                                return;
                            }
                            MediaRouteProviderService.sendReply(messenger2, 4, i2, 0, data, null);
                        }
                    };
                }
                if (controller.onControlRequest(intent, callback)) {
                    if (DEBUG) {
                        Log.d(TAG, client + ": Route control request delivered" + ", controllerId=" + controllerId + ", intent=" + intent);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    boolean onSetDiscoveryRequest(Messenger messenger, int requestId, MediaRouteDiscoveryRequest request) {
        ClientRecord client = getClient(messenger);
        if (client == null) {
            return false;
        }
        boolean actuallyChanged = client.setDiscoveryRequest(request);
        if (DEBUG) {
            Log.d(TAG, client + ": Set discovery request, request=" + request + ", actuallyChanged=" + actuallyChanged + ", compositeDiscoveryRequest=" + this.mCompositeDiscoveryRequest);
        }
        sendGenericSuccess(messenger, requestId);
        return true;
    }

    void sendDescriptorChanged(MediaRouteProviderDescriptor descriptor) {
        int count = this.mClients.size();
        for (int i = 0; i < count; i++) {
            ClientRecord client = (ClientRecord) this.mClients.get(i);
            sendReply(client.mMessenger, 5, 0, 0, createDescriptorBundleForClient(descriptor, client), null);
            if (DEBUG) {
                Log.d(TAG, client + ": Sent descriptor change event, descriptor=" + descriptor);
            }
        }
    }

    private Bundle createDescriptorBundleForClient(MediaRouteProviderDescriptor descriptor, ClientRecord client) {
        if (descriptor == null) {
            return null;
        }
        List<MediaRouteDescriptor> routes = descriptor.getRoutes();
        int i = routes.size() - 1;
        while (i >= 0) {
            if (client.mVersion < ((MediaRouteDescriptor) routes.get(i)).getMinClientVersion() || client.mVersion > ((MediaRouteDescriptor) routes.get(i)).getMaxClientVersion()) {
                routes.remove(i);
            }
            i--;
        }
        Bundle bundle = descriptor.asBundle();
        bundle.remove("routes");
        return new Builder(MediaRouteProviderDescriptor.fromBundle(bundle)).addRoutes(routes).build().asBundle();
    }

    boolean updateCompositeDiscoveryRequest() {
        MediaRouteDiscoveryRequest composite = null;
        MediaRouteSelector.Builder selectorBuilder = null;
        boolean activeScan = false;
        int count = this.mClients.size();
        for (int i = 0; i < count; i++) {
            MediaRouteDiscoveryRequest request = ((ClientRecord) this.mClients.get(i)).mDiscoveryRequest;
            if (request != null && (!request.getSelector().isEmpty() || request.isActiveScan())) {
                activeScan |= request.isActiveScan();
                if (composite == null) {
                    composite = request;
                } else {
                    if (selectorBuilder == null) {
                        selectorBuilder = new MediaRouteSelector.Builder(composite.getSelector());
                    }
                    selectorBuilder.addSelector(request.getSelector());
                }
            }
        }
        if (selectorBuilder != null) {
            composite = new MediaRouteDiscoveryRequest(selectorBuilder.build(), activeScan);
        }
        if (this.mCompositeDiscoveryRequest == composite || (this.mCompositeDiscoveryRequest != null && this.mCompositeDiscoveryRequest.equals(composite))) {
            return false;
        }
        this.mCompositeDiscoveryRequest = composite;
        this.mProvider.setDiscoveryRequest(composite);
        return true;
    }

    private ClientRecord getClient(Messenger messenger) {
        int index = findClient(messenger);
        return index >= 0 ? (ClientRecord) this.mClients.get(index) : null;
    }

    int findClient(Messenger messenger) {
        int count = this.mClients.size();
        for (int i = 0; i < count; i++) {
            if (((ClientRecord) this.mClients.get(i)).hasMessenger(messenger)) {
                return i;
            }
        }
        return -1;
    }

    static void sendGenericFailure(Messenger messenger, int requestId) {
        if (requestId != 0) {
            sendReply(messenger, 0, requestId, 0, null, null);
        }
    }

    private static void sendGenericSuccess(Messenger messenger, int requestId) {
        if (requestId != 0) {
            sendReply(messenger, 1, requestId, 0, null, null);
        }
    }

    static void sendReply(Messenger messenger, int what, int requestId, int arg, Object obj, Bundle data) {
        Message msg = Message.obtain();
        msg.what = what;
        msg.arg1 = requestId;
        msg.arg2 = arg;
        msg.obj = obj;
        msg.setData(data);
        try {
            messenger.send(msg);
        } catch (DeadObjectException e) {
        } catch (RemoteException ex) {
            Log.e(TAG, "Could not send message to " + getClientId(messenger), ex);
        }
    }

    static String getClientId(Messenger messenger) {
        return "Client connection " + messenger.getBinder().toString();
    }
}
