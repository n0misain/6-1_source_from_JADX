package com.cranevalley.dontendword.features.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.cranevalley.dontendword.features.bases.BaseActivity;
import com.cranevalley.dontendword.features.initialization.InitializationActivity;

public class LaunchActivity extends BaseActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, InitializationActivity.class));
        finish();
    }
}
