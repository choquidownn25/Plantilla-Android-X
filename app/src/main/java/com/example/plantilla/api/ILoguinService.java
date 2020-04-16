package com.example.plantilla.api;

import com.example.plantilla.account.activity.model.LoginInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ILoguinService {
    @POST("Token")
    @FormUrlEncoded
    Call<LoginInfo> basicLogin(@Field("grant_type") String grant_type,
                               @Field("userName") String userName,
                               @Field("password") String password);
}
