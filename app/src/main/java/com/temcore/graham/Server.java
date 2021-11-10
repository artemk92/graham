package com.temcore.graham;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Server {

    @Headers("User-Agent: GrahamByAK")
    @POST("authorization/oauth/token")
    public Call<AuthResponse> getAuth(@Header("Content-Type") String ct,
                                      @Header("Accept") String accept, @Header("Accept-Encoding") String ae,
                                      @Header("Connection") String connection,  @Body String grant_type,
                                      @Body String email, @Body String password, @Body String refresh_token);

}