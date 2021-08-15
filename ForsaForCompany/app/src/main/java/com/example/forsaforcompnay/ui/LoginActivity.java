package com.example.forsaforcompnay.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forsaforcompnay.R;
import com.example.forsaforcompnay.api.Api;
import com.example.forsaforcompnay.helper.Helper;
import com.example.forsaforcompnay.model.Login;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView btnLogin, error, btnRegstrion;
    EditText email, password;
    private String emailUser;
    private static final String LOG = "MyLoginActivity";
    private String passwordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        btnRegstrion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Api.getOurUrl() + "company/register")));
            }
        });
    }

    private void init() {

        btnLogin = findViewById(R.id.btnLogin);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        error = findViewById(R.id.error);
        btnRegstrion = findViewById(R.id.btnRegstrion);
    }

    private void Login() {
        passwordUser = password.getText().toString();
        emailUser = email.getText().toString();
        HashMap<String, String> loginHashMap = new HashMap<>();
        loginHashMap.put("email", emailUser);
        loginHashMap.put("password", passwordUser);

        Call<Login> loginCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().LOGIN_CALL(loginHashMap);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Toast.makeText(getApplicationContext(), response.isSuccessful() + "", Toast.LENGTH_LONG).show();
                Log.d("ttthhhg", response.isSuccessful() + "");
                try {
                    if (response.isSuccessful()) {
                        error.setVisibility(View.GONE);
                        Helper.getInstance(getApplicationContext()).setString("token", response.body().getProfileCompany().getAccess_token());
                        Helper.getInstance(getApplicationContext()).setString("login", "true");
                        Helper.getInstance(getApplicationContext()).setString("Name", response.body().getProfileCompany().getName());
                        Helper.getInstance(getApplicationContext()).setString("Status", response.body().getProfileCompany().getStatus());
                        Helper.getInstance(getApplicationContext()).setString("URL", response.body().getProfileCompany().getLogoimage() + "");
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class).putExtra("ttt", response.body().getProfileCompany().getAccess_token()));
                        finish();
                    } else {
                        Log.d(LOG, response.errorBody() + "");
                        error.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    error.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                System.out.println("mostafa test " + t.getMessage());

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        islogin();
    }

    private void islogin() {
        if (Helper.getInstance(getApplicationContext()).getString("login") != null) {
            if (Helper.getInstance(getApplicationContext()).getString("login").equals("true")) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        }
    }
}