package com.anandmali.meowdular.ui.view.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anandmali.meowdular.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}