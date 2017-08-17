package com.app.astro.common;

import android.content.Context;
import com.app.astro.model.ChannelItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezarachman on 8/17/17.
 */

public class AppUtil {

    private static AppSingleton getSingleton() {
        return AppSingleton.getInstance();
    }

    private static void addChannelToFavourites(List<ChannelItem> channelItems, ChannelItem channel, Context context) {
        channel.setFavourite(true);
        channelItems.add(channel);
        invalidateList(context, channelItems);
    }

    private static void removeChannelFromFavourites(List<ChannelItem> channelItems, ChannelItem channel, Context context) {
        List<ChannelItem> deletedList = new ArrayList<>();
        for (ChannelItem item : channelItems) {
            if (item.getChannelId().equals(channel.getChannelId())) {
                item.setFavourite(false);
                deletedList.add(item);
            }
        }
        channelItems.removeAll(deletedList);
        invalidateList(context, channelItems);
    }

    private static void invalidateList(Context context, List<ChannelItem> channelItems) {
        String savedList = getSingleton().getInstanceGson().toJson(channelItems);
        getSingleton().getDefaultEditor(context).putString(Constant.FAVOURITE_LIST, savedList);
        getSingleton().getDefaultEditor(context).commit();
    }

    private static boolean isContainItem(ChannelItem item, List<ChannelItem> channelItems) {
        for (ChannelItem channelItem : channelItems) {
            if (channelItem.getChannelId().equals(item.getChannelId())) {
                return true;
            }
        }

        return false;
    }

    private static List<ChannelItem> getFavouriteList(Context context) {
        String list = getSingleton().getSharedPreferences(context).getString(Constant.FAVOURITE_LIST, "");
        List<ChannelItem> channelItems = null;

        if (list.length() > 0) {
            channelItems = getSingleton().getInstanceGson().fromJson(list, getSingleton().getTypeFavorites());
        }

        getSingleton().getDefaultEditor(context).commit();

        return channelItems;
    }

    public static void setFavouriteChannel(Context context, ChannelItem channel) {
        String list = getSingleton().getSharedPreferences(context).getString(Constant.FAVOURITE_LIST, "");
        List<ChannelItem> channelItems;

        if (list.length() > 0) {
            channelItems = getSingleton().getInstanceGson().fromJson(list, getSingleton().getTypeFavorites());
            if (isContainItem(channel, channelItems)) {
                removeChannelFromFavourites(channelItems, channel, context);
            } else {
                addChannelToFavourites(channelItems, channel, context);
            }
        } else {
            channelItems = new ArrayList<>();
            addChannelToFavourites(channelItems, channel, context);
        }
    }

    public static List<ChannelItem> getFinalData(Context context, List<ChannelItem> channelItems) {
        List<ChannelItem> favouriteList = getFavouriteList(context);
        List<ChannelItem> results = new ArrayList<>();

        if (favouriteList != null) {
            for (ChannelItem item : channelItems) {
                for (ChannelItem favItem : favouriteList) {
                    if (item.getChannelId().equals(favItem.getChannelId())) {
                        item.setFavourite(true);
                    }
                }
                results.add(item);
            }

            return results;
        } else {
            return channelItems;
        }
    }

}
