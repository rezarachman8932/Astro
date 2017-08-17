package com.app.astro.model;

/**
 * Created by rezarachman on 8/13/17.
 */

public class ChannelItem {

    private String channelId;
    private String channelTitle;
    private String channelStbNumber;
    private boolean isFavourite;

    public ChannelItem(String channelId, String channelTitle, String channelStbNumber, boolean isFavourite) {
        this.channelId = channelId;
        this.channelTitle = channelTitle;
        this.channelStbNumber = channelStbNumber;
        this.isFavourite = isFavourite;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getChannelStbNumber() {
        return channelStbNumber;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

}
