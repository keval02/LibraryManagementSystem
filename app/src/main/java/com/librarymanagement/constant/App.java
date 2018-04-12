package com.librarymanagement.constant;

import android.app.Application;
import android.content.Context;


import com.librarymanagement.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Keval on 12/16/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("assets/source_sans_pro_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(base));
    }
}
