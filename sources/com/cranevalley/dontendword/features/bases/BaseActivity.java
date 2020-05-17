package com.cranevalley.dontendword.features.bases;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.orhanobut.logger.Logger;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    public void showProgressDialog(final String message) {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressDialog(this);
        }
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    BaseActivity.this.mProgressDialog.setMessage(message);
                    BaseActivity.this.mProgressDialog.setIndeterminate(true);
                    BaseActivity.this.mProgressDialog.setCancelable(false);
                    BaseActivity.this.mProgressDialog.setCanceledOnTouchOutside(false);
                    BaseActivity.this.mProgressDialog.show();
                } catch (Exception exception) {
                    Logger.m1220e(exception.getLocalizedMessage(), new Object[0]);
                }
            }
        });
    }

    public void hideProgressDialog() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
    }
}
