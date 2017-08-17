package com.app.astro.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.app.astro.R;
import com.app.astro.adapter.ChannelListAdapter;
import com.app.astro.api.payload.ChannelListResponse;
import com.app.astro.common.AppUtil;
import com.app.astro.model.ChannelItem;
import com.app.astro.provider.listener.FavouriteListener;
import com.app.astro.provider.listener.ResponseListener;

import java.util.List;

import retrofit2.Response;

public class ActChannelList extends ActBase {

    private ToggleButton vButtonSortName;
    private ToggleButton vButtonSortNumber;
    private RecyclerView vRecyclerViewChannel;
    private List<ChannelItem> mChannelItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_channel_list);

        initView();
        setupView();

        getChannelList();
    }

    private void initView() {
        vRecyclerViewChannel = (RecyclerView) findViewById(R.id.recycler_view_channel);
        vButtonSortNumber = (ToggleButton) findViewById(R.id.toggle_sort_number);
        vButtonSortName = (ToggleButton) findViewById(R.id.toggle_sort_name);
    }

    private void setupView() {
        vButtonSortName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onSortList(b, SORT_BY_NAME);
            }
        });

        vButtonSortNumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onSortList(b, SORT_BY_NUMBER);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                vRecyclerViewChannel.getContext(),
                layoutManager.getOrientation());
        vRecyclerViewChannel.setLayoutManager(layoutManager);
        vRecyclerViewChannel.addItemDecoration(dividerItemDecoration);
    }

    private void getChannelList() {
        getInstanceProvider().getChannelList(new ResponseListener<ChannelListResponse>() {
            @Override
            public void onSuccess(Response<ChannelListResponse> response) {
                if (mChannelItems != null && mChannelItems.size() > 0) {
                    mChannelItems.clear();
                }

                mChannelItems = generateList(response.body().channels);
                List<ChannelItem> finalData = AppUtil.getFinalData(getApplicationContext(), mChannelItems);
                invalidateDataList(finalData);
            }
            @Override
            public void onFailed(Throwable throwable) {
                throwable.getMessage();
            }
        });
    }

    private void invalidateDataList(final List<ChannelItem> items) {
        ChannelListAdapter adapter = new ChannelListAdapter(this, items);
        adapter.setFavouriteListener(new FavouriteListener() {
            @Override
            public void onFavouriteTextClick(int position) {
                onSetFavourite(items, position);
            }
        });
        vRecyclerViewChannel.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void onSetFavourite(List<ChannelItem> items, int position) {
        ChannelItem item = items.get(position);
        AppUtil.setFavouriteChannel(getApplicationContext(), item);
        getChannelList();
    }

    private void onSortList(boolean checked, String sortedType) {
        if (checked) {
            if (sortedType.equals(SORT_BY_NAME)) {
                List<ChannelItem> sortedItems = getSortedList(mChannelItems, SORT_BY_NAME);
                invalidateDataList(sortedItems);
            } else if (sortedType.equals(SORT_BY_NUMBER)) {
                List<ChannelItem> sortedItems = getSortedList(mChannelItems, SORT_BY_NUMBER);
                invalidateDataList(sortedItems);
            }
        } else {
            getChannelList();
        }
    }

}
