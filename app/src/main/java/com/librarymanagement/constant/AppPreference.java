package com.librarymanagement.constant;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Keval on 12/17/2017.
 */

public class AppPreference {


    public static final String FILE_NAME = "LibraryManagement";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public AppPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    boolean login = false;
    boolean filledProfile = false ;
    String userType = "";

    public boolean isLogin() {
        return sharedPreferences.getBoolean("isLogin", false);
    }

    public void setLogin(boolean login) {
        this.login = login;
        editor.putBoolean("isLogin", login).commit();
    }

    public String getUserType() {
        return sharedPreferences.getString("userType" , "");
    }

    public void setUserType(String userType) {
        this.userType = userType;
        editor.putString("userType" , userType).commit();
    }

    public boolean isFilledProfile() {
        return sharedPreferences.getBoolean("profileFilled" , false);

    }

    public void setFilledProfile(boolean filledProfile) {
        this.filledProfile = filledProfile;
        editor.putBoolean("profileFilled" , filledProfile).commit();
    }


    public void clearData(Context context){
        SharedPreferences.Editor prefs = context.getSharedPreferences(FILE_NAME , 0).edit();
        prefs.clear();
        prefs.commit();
    }


}
