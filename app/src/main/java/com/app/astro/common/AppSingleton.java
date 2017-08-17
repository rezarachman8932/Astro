package com.app.astro.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.astro.model.ChannelItem;
import com.app.astro.provider.method.ProviderImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by rezarachman on 8/13/17.
 */

public class AppSingleton {

    private static AppSingleton sApp;
    private ProviderImpl mProvider;
    private Type mTypeChannels;
    private Gson mGson;
    private SharedPreferences.Editor mEditor;

    public static AppSingleton getInstance() {
        if (sApp == null) {
            sApp = new AppSingleton();
        }

        return sApp;
    }

    public ProviderImpl getProvider() {
        if (mProvider == null) {
            mProvider = new ProviderImpl();
        }

        return mProvider;
    }

    public Gson getInstanceGson() {
        if (mGson == null) {
            mGson = new Gson();
        }

        return mGson;
    }

    public SharedPreferences.Editor getDefaultEditor(Context context) {
        if (mEditor == null) {
            mEditor = getSharedPreferences(context).edit();
        }

        return mEditor;
    }

    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public Type getTypeFavorites() {
        if (mTypeChannels == null) {
            mTypeChannels = new TypeToken<List<ChannelItem>>(){}.getType();
        }

        return mTypeChannels;
    }

}
