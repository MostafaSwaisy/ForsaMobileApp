package com.example.forsaforuser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forsaforuser.R;
import com.example.forsaforuser.api.Api;
import com.example.forsaforuser.helper.Helper;
import com.example.forsaforuser.model.register.Register;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG = "MyRegisterActivity";
    EditText register_email, register_password, register_confirm_password, register_name, register_phone;
    Button register_button;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(LOG, "click on register Button ");
                if (register_password.getText().toString().equals(register_confirm_password.getText().toString())) {
                    register(register_email.getText().toString(),
                            register_password.getText().toString(),
                            register_phone.getText().toString(),
                            register_name.getText().toString());

                } else {
                    Toast.makeText(RegisterActivity.this, R.string.password_message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);
        register_name = findViewById(R.id.register_name);
        register_phone = findViewById(R.id.register_phone);
        register_button = findViewById(R.id.register_button);
        register_confirm_password = findViewById(R.id.register_confirm_password);
        helper = Helper.getINSTANCE(getApplicationContext());
    }

    private void register(String email, String password, String mobileNumber, String name) {
        Log.e(LOG, "Enter in register Button ");
        HashMap register_hashmap = new HashMap<String, String>();
        register_hashmap.put("email", email);
        register_hashmap.put("password", password);
        register_hashmap.put("mobile_number", mobileNumber);
        register_hashmap.put("name", name);
        Call<Register> registerCall = Api.getINSTANCE(getApplicationContext()).getApiInterface().register(register_hashmap);
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                System.out.println(response.body().getMessage() + "");
                if (response.isSuccessful()) {
                    Log.d(LOG, response.body().getMessage());
                    helper.setString("token", response.body().getData().getAccess_token());
                    Log.e("token", response.body().getData().getAccess_token());
                    helper.setString("login", "true");
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}