package com.librarymanagement.apis;

import com.librarymanagement.constant.ApiURLs;
import com.librarymanagement.constant.StringConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by keval on 12-04-2018.
 */

public class ServiceGenerator {

    public static AdminAPI getAPIClass() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiURLs.BASE_URL)
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .build();

        return retrofit.create(AdminAPI.class);

    }


    private static OkHttpClient getRequestHeader() {
        OkHttpClient httpClient = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();
        return httpClient;

    }


}
