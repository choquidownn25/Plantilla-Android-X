package com.example.plantilla.utilidad;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.plantilla.account.activity.model.LoginInfo;

public class    SessionPrefs {

    public static final String PREFS_NAME = null;
    public static final String USER_NAME = "user_name";
    public static final String PREF_AFFILIATE_ID = "PREF_USER_ID";
    public static final String PREF_AFFILIATE_NAME = "PREF_AFFILIATE_NAME";
    public static final String PREF_AFFILIATE_ADDRESS = "PREF_AFFILIATE_ADDRESS";
    public static final String PREF_AFFILIATE_GENDER = "PREF_AFFILIATE_GENDER";
    public static final String PREF_AFFILAITE_TOKEN = "PREF_AFFILAITE_TOKEN";
    public static final String PREF_PASSORD = "PREF_PASSORD";
    public static final String PREF_AFFILAITE_EMAIL = "PREF_AFFILAITE_EMAIL";

    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    private SessionPrefs(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_AFFILAITE_TOKEN, null));
    }

    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    public void saveAffiliate(LoginInfo affiliate) {
        if (affiliate != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(PREF_AFFILIATE_ID, affiliate.getId());
            editor.putString(PREF_AFFILIATE_NAME, affiliate.getUserName());
            editor.putString(PREF_PASSORD, affiliate.getPassword());
            //editor.putString(PREF_AFFILAITE_EMAIL, affiliate.());

            editor.putString(PREF_AFFILAITE_TOKEN, affiliate.getAccessToken());
            editor.apply();

            mIsLoggedIn = true;
        }
    }

    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_AFFILIATE_ID, null);
        editor.putString(PREF_AFFILIATE_NAME, null);
        editor.putString(PREF_AFFILIATE_ADDRESS, null);
        editor.putString(PREF_AFFILIATE_GENDER, null);
        editor.putString(PREF_AFFILAITE_TOKEN, null);
        editor.putString(PREF_AFFILAITE_EMAIL, null);
        editor.apply();
    }

    public String getId (){return mPrefs.getString(PREF_AFFILIATE_ID, null);
    }

    public String getToken(){
        return mPrefs.getString(PREF_AFFILAITE_TOKEN, null);
    }


    public String getUserName(){
        return  mPrefs.getString(PREF_AFFILIATE_NAME, null);
    }

    public String getPrefPassord(){
        return mPrefs.getString(PREF_PASSORD, null);
    }

    public String getEmail() { return  mPrefs.getString(PREF_AFFILAITE_EMAIL, null);}
}
