package com.example.backgroundservices.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backgroundservices.R;
import com.example.backgroundservices.services.Service;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class ServiceFragment extends Fragment {
    public static final String ACTION_REGISTER = "ACTION_REGISTER";
    Button btn_Click;
    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null) {
                String action = intent.getAction();
                if (action != null)
                    switch (action) {
                        case ACTION_REGISTER:
                            if (intent.getStringExtra("status") != null) {
                                Toast.makeText(context,intent.getStringExtra("status") , Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }


            }
        }
    };
    public ServiceFragment() {
        // Required empty public constructor
    }

    public static ServiceFragment newInstance(String param1, String param2) {
        ServiceFragment fragment = new ServiceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_Click = view.findViewById(R.id.btn_Click);

        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Service.startPushService(getContext());
            }
        });
    }


    public static Intent setIntentToRegister(String status) {
        Intent intent = new Intent(ACTION_REGISTER);
        intent.putExtra("status", status);
        return intent;
    }

    public void registerBroadcastReceiver(@NonNull BroadcastReceiver receiver) {
        Log.e("registerBroad", "call");
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_REGISTER);
        localBroadcastManager.registerReceiver(receiver, intentFilter);
    }


    @Override
    public void onStart() {
        super.onStart();
        registerBroadcastReceiver(receiver);
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager
                .getInstance(getContext());
        localBroadcastManager.unregisterReceiver(receiver);
        Toast.makeText(getContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
    }
}