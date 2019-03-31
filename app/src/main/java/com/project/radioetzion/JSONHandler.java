package com.project.radioetzion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface JSONHandler {

    @GET("broadcast/getVodList/0/100")
    Call<List<JSONData>> getJson();
}