package com.example.backgroundservices.services;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.backgroundservices.fragment.ServiceFragment;

public class Service extends JobIntentService {

    public static final String ACTION_START_PUSH_SERVICE = "ACTION_START_PUSH_SERVICE";
    private static final int jobId = 100;

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (TextUtils.isEmpty(intent.getAction()))
            return;

        switch (intent.getAction()) {
            case ACTION_START_PUSH_SERVICE:
                notifyToRegister(getApplicationContext(),"Service started");
                break;

        }
    }
    public static void notifyToRegister(Context context, String status) {
        LocalBroadcastManager lBroadcastManager = LocalBroadcastManager.getInstance(context);
        lBroadcastManager.sendBroadcast(ServiceFragment.setIntentToRegister(status));

    }

    public static void startPushService(Context context) {
        Intent intent = new Intent(context, Service.class);
        intent.setAction(ACTION_START_PUSH_SERVICE);
        enqueueWork(context, Service.class, jobId, intent);
    }


}
