package com.david0926.sunrinthon2021.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.david0926.sunrinthon2021.data.UserModel;
import com.google.gson.Gson;

public class UserCache {

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setUser(Context context, UserModel model) {
        Gson gson = new Gson();
        String json = gson.toJson(model);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("user_json", json).apply();
    }

    public static void setUser(Context context, UserModel model, String token) {
        Gson gson = new Gson();
        String json = gson.toJson(model);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("user_json", json).putString("user_token", token).apply();
    }

    public static UserModel getUser(Context context) {
        Gson gson = new Gson();
        return gson.fromJson(getSharedPreferences(context).getString("user_json", ""), UserModel.class);
    }

    public static String getToken(Context context) {
        return getSharedPreferences(context).getString("user_token", "");
    }

    public static void clearUser(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("user_json", null).putString("user_token", null).apply();
    }
}
