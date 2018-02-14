package com.myown.juniorawok.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.myown.juniorawok.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abdullah on 2/13/2018.
 */

public class RestClient {

    private static ServicesInterface servicesInterface;
    private static Retrofit retrofit;

    static {

        //Configuring the Rest Client
        setUpRestClient();
    }

    private static void setUpRestClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        servicesInterface = retrofit.create(ServicesInterface.class);

    }

    public static Retrofit getRetrofitClient() {
        return retrofit;
    }
    public static ServicesInterface getAdapter() {
        return servicesInterface;
    }

}
