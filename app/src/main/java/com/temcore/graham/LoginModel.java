package com.temcore.graham;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel implements LoginContract.Model {

    public static final String BASE_URL = "https://graham.bellintegrator.com/api/";
    private String gtp = "password";
    /*private String login;
    private String password;*/
    private String refresh_token = "";
    private String ct = "application/x-www-form-urlencoded";
    private String cl;
    private String host = "graham.bellintegrator.com";
    private String accept = "*/*";
    private String ae = "gzip, deflate, br";
    private String connection = "keep-alive";
    private String id;
    private String authRequestBody;

    public String login(String login, String password) {
        /*this.login = login;
        this.password = password;*/
        authRequestBody = "grant_type=" + gtp + "&username=" + login.replace("@","%40") +
        "&password=" + password + "&refresh_token=" + refresh_token;
        cl = Integer.toString(authRequestBody.getBytes(StandardCharsets.US_ASCII).length);

       /* OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Настраиваем запросы
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization", "auth-token")
                        .method(original.method(), original.body())
                        .build();

                okhttp3.Response response = chain.proceed(request);

                return response;
            }
        });*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(client)
                .build();

        Server authService = retrofit.create(Server.class);
        Call<AuthResponse> call = authService.getAuth(ct, cl, host, accept, ae, connection, authRequestBody);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    // запрос выполнился успешно, сервер вернул Status 200
                    id = response.body().getUserId();
                } else {
                    // сервер вернул ошибку
                    id = Integer.toString(response.code());
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // ошибка во время выполнения запроса
                id = "1";
            }
        });
        return id;
    }
}
