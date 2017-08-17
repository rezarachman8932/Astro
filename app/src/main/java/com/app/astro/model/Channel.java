package com.app.astro.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rezarachman on 8/12/17.
 */

public class Channel {

    @SerializedName("channelId")
    public String channelId;

    @SerializedName("channelTitle")
    public String channelTitle;

    @SerializedName("channelStbNumber")
    public String channelStbNumber;

}
