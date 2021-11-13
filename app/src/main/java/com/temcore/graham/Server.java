package com.temcore.graham;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Server {

    @Headers("User-Agent: GrahamByAK")
    @FormUrlEncoded
    @POST("authorization/oauth/token")
    Call<AuthResponse> getAuth(@Field("grant_type") String gt,
                               @Field("username") String user,
                               @Field("password") String password,
                               @Field("refresh_token") String refresh_token);

}
