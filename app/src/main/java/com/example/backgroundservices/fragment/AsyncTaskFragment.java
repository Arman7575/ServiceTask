package com.example.backgroundservices.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.backgroundservices.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AsyncTaskFragment extends Fragment {

    EditText et_One;
    Button btn_Click;
    TextView tv_Text;
    ProgressBar progressBar;

    public AsyncTaskFragment() {
        // Required empty public constructor
    }

    public static AsyncTaskFragment newInstance() {
        AsyncTaskFragment fragment = new AsyncTaskFragment();
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
        return inflater.inflate(R.layout.fragment_async_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_One = view.findViewById(R.id.et_One);
        btn_Click = view.findViewById(R.id.btn_Click);
        tv_Text = view.findViewById(R.id.tv_Text);
        progressBar = view.findViewById(R.id.progress);
        tv_Text.setVisibility(View.GONE);

        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_One.getText().toString().trim())) {
                    Snackbar.make(v, getResources().getString(R.string.please_enter_something), BaseTransientBottomBar.LENGTH_SHORT).show();
                } else {
                    MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                    myAsyncTasks.execute();

                }
            }
        });
    }

    public class MyAsyncTasks extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String text = et_One.getText().toString().trim();
            return text;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            tv_Text.setVisibility(View.VISIBLE);
            tv_Text.setText(s);
        }
    }


}
