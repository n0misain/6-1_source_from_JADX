package com.cranevalley.dontendword.features.shared;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.orhanobut.logger.Logger;

public class AppMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Logger.m1220e("Data: " + remoteMessage.getData(), new Object[0]);
        }
        if (remoteMessage.getNotification() != null) {
            final String title = remoteMessage.getNotification().getTitle();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Toast.makeText(AppMessagingService.this.getApplicationContext(), title, 0).show();
                }
            });
        }
    }
}
