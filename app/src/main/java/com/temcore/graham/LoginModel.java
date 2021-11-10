package com.temcore.graham;

import android.os.AsyncTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel implements LoginContract.Model {

    public static final String BASE_URL = "https://graham.bellintegrator.com/api/";
    private String gtp = "password";
    private String login;
    private String password;
    private String refresh_token = "";
    private String ct = "application/x-www-form-urlencoded";
    private String accept = "*/*";
    private String ae = "gzip, deflate, br";
    private String connection = "keep-alive";
    private String id;

    public String login(String login, String password) {
        this.login = login;
        this.password = password;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Server authService = retrofit.create(Server.class);
        Call<AuthResponse> call = authService.getAuth(ct, accept, ae, connection, gtp, login, password, refresh_token);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    // запрос выполнился успешно, сервер вернул Status 200
                    id = response.body().getUserId();
                } else {
                    // сервер вернул ошибку
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // ошибка во время выполнения запроса
            }
        });
        return id;
    }
}
