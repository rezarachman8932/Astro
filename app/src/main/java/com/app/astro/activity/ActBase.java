package com.app.astro.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.astro.common.AppSingleton;
import com.app.astro.model.Channel;
import com.app.astro.model.ChannelItem;
import com.app.astro.provider.method.ProviderImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by rezarachman on 8/13/17.
 */

public abstract class ActBase extends AppCompatActivity {

    protected static String SORT_BY_NAME    = "name";
    protected static String SORT_BY_NUMBER  = "number";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ProviderImpl getInstanceProvider() {
        return AppSingleton.getInstance().getProvider();
    }

    protected List<ChannelItem> generateList(List<Channel> channels) {
        List<ChannelItem> items = new ArrayList<>();

        if (channels != null && channels.size() > 0) {
            for (Channel channel : channels) {
                items.add(new ChannelItem(
                        channel.channelId,
                        channel.channelTitle,
                        channel.channelStbNumber,
                        false));
            }
        }

        return items;
    }

    protected List<ChannelItem> getSortedList(List<ChannelItem> items, String sortedType) {
        if (items != null && items.size() > 0) {

            if (sortedType.equals(SORT_BY_NAME)) {
                Collections.sort(items, new Comparator<ChannelItem>() {
                    @Override
                    public int compare(ChannelItem t0, ChannelItem t1) {
                        return t0.getChannelTitle().compareTo(t1.getChannelTitle());
                    }
                });
            } else if (sortedType.equals(SORT_BY_NUMBER)) {
                Collections.sort(items, new Comparator<ChannelItem>() {
                    @Override
                    public int compare(ChannelItem t0, ChannelItem t1) {
                        return t0.getChannelStbNumber().compareTo(t1.getChannelStbNumber());
                    }
                });
            }

            return items;
        }

        return null;
    }

}
