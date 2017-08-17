package com.app.astro.api.payload;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rezarachman on 8/12/17.
 */

public class BaseResponse {

    @SerializedName("responseMessage")
    public String responseMessage;

    @SerializedName("responseCode")
    public String responseCode;

}
