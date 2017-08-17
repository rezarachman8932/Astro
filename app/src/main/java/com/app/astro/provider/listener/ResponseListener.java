package com.app.astro.provider.listener;

import retrofit2.Response;

/**
 * Created by rezarachman on 8/13/17.
 */

public interface ResponseListener<T> {
    void onSuccess(Response<T> response);
    void onFailed(Throwable throwable);
}
