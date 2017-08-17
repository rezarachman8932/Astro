package com.app.astro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.astro.R;
import com.app.astro.model.ChannelItem;
import com.app.astro.provider.listener.FavouriteListener;

import java.util.List;

/**
 * Created by rezarachman on 8/13/17.
 */

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ChannelListHolder> {

    private List<ChannelItem> mItems;
    private Context mContext;
    private FavouriteListener mFavouriteListener;

    public ChannelListAdapter(Context context, List<ChannelItem> items) {
        mContext = context;
        mItems = items;
    }

    public void setFavouriteListener(FavouriteListener favouriteListener) {
        mFavouriteListener = favouriteListener;
    }

    @Override
    public ChannelListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel_list, parent, false);
        return new ChannelListHolder(view);
    }

    @Override
    public void onBindViewHolder(ChannelListHolder holder, final int position) {
        ChannelItem item = mItems.get(position);

        if (item != null) {
            holder.textTitle.setText(item.getChannelTitle());
            holder.textNumber.setText(item.getChannelStbNumber());

            if (item.isFavourite()) {
                holder.textFavourite.setTextColor(mContext.getColor(android.R.color.holo_green_dark));
            } else {
                holder.textFavourite.setTextColor(mContext.getColor(R.color.colorPrimary));
            }

            holder.textFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFavouriteListener != null) {
                        mFavouriteListener.onFavouriteTextClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ChannelListHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textNumber;
        TextView textFavourite;

        ChannelListHolder(View v) {
            super(v);

            textTitle   = (TextView) v.findViewById(R.id.item_text_title);
            textNumber  = (TextView) v.findViewById(R.id.item_text_number);
            textFavourite  = (TextView) v.findViewById(R.id.item_text_favourite);
        }
    }

}
