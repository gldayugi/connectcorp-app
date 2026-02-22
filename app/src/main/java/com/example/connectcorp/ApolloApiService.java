package com.example.connectcorp;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApolloApiService {

    @Headers({
            "Content-Type: application/json"
    })
    @POST("v1/mixed_people/search")
    Call<ResponseBody> searchPeople(
            @Header("x-api-key") String apiKey,
            @Body RequestBody body
    );

    @POST("v1/organizations/search")
    Call<ResponseBody> searchCompanies(
            @Header("x-api-key") String apiKey,
            @Body RequestBody body
    );
}
