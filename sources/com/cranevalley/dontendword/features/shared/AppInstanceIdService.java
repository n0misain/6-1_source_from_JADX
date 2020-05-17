package com.cranevalley.dontendword.features.shared;

import com.cranevalley.dontendword.helpers.FirebaseHelper;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class AppInstanceIdService extends FirebaseInstanceIdService {
    public void onTokenRefresh() {
        FirebaseHelper.addToken(FirebaseInstanceId.getInstance().getToken());
    }
}
