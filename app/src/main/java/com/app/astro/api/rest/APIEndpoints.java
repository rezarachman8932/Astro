package com.app.astro.api.rest;

import com.app.astro.api.payload.ChannelListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rezarachman on 8/13/17.
 */

public interface APIEndpoints {

    @GET("/ams/v3/getChannelList")
    Call<ChannelListResponse> getChannelList();

}
