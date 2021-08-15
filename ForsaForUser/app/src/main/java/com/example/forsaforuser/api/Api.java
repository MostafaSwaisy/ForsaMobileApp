package com.example.forsaforuser.api;

import android.content.Context;

import com.example.forsaforuser.helper.Helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    //    private static final String BASE_URL = "http://192.168.0.112:80/api/";
    private static final String BASE_URL = "http://192.168.1.106:80/api/";
    // private static final String BASE_URL = "http://192.168.0.114:80/api/";
    private static Api INSTANCE;
    private static ApiInterface apiInterface;
    private final Helper helper;

    public Api(Context context) {
        helper = Helper.getINSTANCE(context);
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", "Bearer " + helper.getString("token"))
                        .build();
                return chain.proceed(newRequest);

            }
        };
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getApiInterface() {
        return apiInterface;
    }


    public static Api getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new Api(context);
        }
        return INSTANCE;
    }
}
