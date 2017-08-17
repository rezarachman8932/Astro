package com.app.astro.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rezarachman on 8/12/17.
 */

public class SubChannel  {

    @SerializedName("channelId")
    public String channelId;

    @SerializedName("siChannelId")
    public String siChannelId;

    @SerializedName("channelTitle")
    public String channelTitle;

    @SerializedName("channelDescription")
    public String channelDescription;

    @SerializedName("channelLanguage")
    public String channelLanguage;

    @SerializedName("channelColor1")
    public String channelColor1;

    @SerializedName("channelColor2")
    public String channelColor2;

    @SerializedName("channelColor3")
    public String channelColor3;

    @SerializedName("channelCategory")
    public String channelCategory;

    @SerializedName("channelStbNumber")
    public String channelStbNumber;

    @SerializedName("channelHD")
    public String channelHD;

    @SerializedName("hdSimulcastChannel")
    public String hdSimulcastChannel;

    @SerializedName("channelStartDate")
    public String channelStartDate;

    @SerializedName("channelEndDate")
    public String channelEndDate;

    @SerializedName("channelExtRef")
    public List<ChannelExtRef> channelExtRef;

    @SerializedName("linearOttMapping")
    public List<Platform> linearOttMapping;

}
