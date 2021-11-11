package com.temcore.graham;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Server {

    @Headers("User-Agent: GrahamByAK")

    @POST("authorization/oauth/token")
    Call<AuthResponse> getAuth(/*@Header("Content-Type") String ct,
                               @Header("Content-Length") String length,
                               @Header("Host") String host,
                               @Header("Accept") String accept,
                               @Header("Accept-Encoding") String ae,
                               @Header("Connection") String connection,*/
                               @Body String body);

}
