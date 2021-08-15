package com.example.forsaforuser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.forsaforuser.R;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.helper.Helper;
import com.example.forsaforuser.model.Login;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String adminEmail = "mostafa@gmail.com";
    private static final String adminPassword = "mostafa2017";
    private static final String LOG = "MyLoginActivity";
    AppCompatTextView btnLogin, create_account;
    EditText user_email, user_password;
    private Helper helper;


    @Override
    protected void onStart() {
        super.onStart();
        islogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(user_email.getText().toString())
                        || TextUtils.isEmpty(user_email.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "please Enter your Email and password correct", Toast.LENGTH_SHORT).show();
                } else {
                    login(user_email.getText().toString(), user_password.getText().toString());
                }
            }
        });
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void islogin() {
        if (helper.getString("login") != null) {
            if (helper.getString("login").equals("true")) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }
    }

    private void init() {
        helper = Helper.getINSTANCE(getApplicationContext());
        btnLogin = findViewById(R.id.btnLogin);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        create_account = findViewById(R.id.create_account);
    }

    private void login(String email, String password) {
        HashMap<String, String> loginHashMap = new HashMap<>();
        loginHashMap.put("email", email);
        loginHashMap.put("password", password);
        helper.setString("password", password);
        Call<Login> loginCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().LOGIN_CALL(loginHashMap);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.e(LOG, response.body().getMessage());
                        helper.setString("token", response.body().getUserProfile().getAccess_token());
                        helper.setString("login", "true");
                        helper.setString("name", response.body().getUserProfile().getUsername());
                        helper.setString("email", response.body().getUserProfile().getEmail());
                        helper.setString("mobile_number", response.body().getUserProfile().getMobileNum());
                        helper.setString("image", response.body().getUserProfile().getImage());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } catch (NullPointerException n) {
                        Toast.makeText(LoginActivity.this, "Something Wrong here", Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

                Log.d(LOG, t.getMessage());
            }
        });

    }
}