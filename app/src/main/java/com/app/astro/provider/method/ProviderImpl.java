package com.app.astro.provider.method;

import com.app.astro.api.payload.ChannelListResponse;
import com.app.astro.api.rest.APIClient;
import com.app.astro.api.rest.APIEndpoints;
import com.app.astro.provider.listener.ResponseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rezarachman on 8/13/17.
 */

public class ProviderImpl {

    private APIEndpoints getService() {
        return APIClient.getClient().create(APIEndpoints.class);
    }

    public void getChannelList(final ResponseListener<ChannelListResponse> listener) {
        Call<ChannelListResponse> response = getService().getChannelList();
        response.enqueue(new Callback<ChannelListResponse>() {
            @Override
            public void onResponse(Call<ChannelListResponse> call, Response<ChannelListResponse> response) {
                listener.onSuccess(response);
            }
            @Override
            public void onFailure(Call<ChannelListResponse> call, Throwable t) {
                listener.onFailed(t);
            }
        });
    }

}
