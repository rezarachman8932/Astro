package com.app.astro.api.payload;

import com.app.astro.model.Channel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rezarachman on 8/12/17.
 */

public class ChannelListResponse extends BaseResponse {

    @SerializedName("channels")
    public List<Channel> channels;

}
