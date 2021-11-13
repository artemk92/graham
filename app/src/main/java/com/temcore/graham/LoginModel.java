package com.temcore.graham;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginModel implements LoginContract.Model {

    public static final String BASE_URL = "https://graham.bellintegrator.com/api/";
    private String token;
    private String gtp = "password";
    private String refresh_token = "";
    private String ct = "application/x-www-form-urlencoded";
    private String cl;
    private String host = "graham.bellintegrator.com";
    private String accept = "application/json";
    private String ae = "deflate, br";
    private String connection = "keep-alive";
    private String authRequestBody;

    public void login(String login, String password) {

        authRequestBody = "grant_type=" + gtp + "&username=" + login +
                "&password=" + password + "&refresh_token=" + refresh_token;
        cl = Integer.toString(authRequestBody.getBytes(StandardCharsets.US_ASCII).length);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Content-Type", ct)
                    .header("Accept", accept)
                    .header("Content-Length", cl)
                    .header("Host", host)
                    .header("Accept-Encoding", ae)
                    .header("Connection", connection)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = httpClient
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();

        Server authService = retrofit.create(Server.class);
        Call<AuthResponse> call = authService.getAuth(gtp, login, password, refresh_token);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    // запрос выполнился успешно, сервер вернул Status 200
                    token = response.body().getAccess_token();
                   // token = body.getAccess_token();
                } else {
                    // сервер вернул ошибку, можно реализовать вывод сообщения об ошибке пользователю
                    try {
                        Log.d("response", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // ошибка во время выполнения запроса
                Log.d("error", String.valueOf(t));
            }
        });
    }

    public String getCookie() {
        return token;
    }
}
