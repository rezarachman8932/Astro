package com.app.astro.api.payload;

import com.app.astro.model.SubChannel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rezarachman on 8/12/17.
 */

public class ChannelsResponse extends BaseResponse {

    @SerializedName("channel")
    public List<SubChannel> channel;

}
