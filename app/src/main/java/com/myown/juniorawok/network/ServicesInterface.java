package com.myown.juniorawok.network;

import com.myown.juniorawok.network.api.Endpoints;
import com.myown.juniorawok.network.models.FlashAPIResponse;
import com.myown.juniorawok.network.models.HomeAPIResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This is the ServicesInterface for our application.
 */

public interface ServicesInterface {

    @Headers("App-Version: 110")
    @GET(Endpoints.FLASH)
    Observable<FlashAPIResponse> getFlashProducts();

    @Headers("App-Version: 110")
    @GET(Endpoints.HOME)
    Observable<HomeAPIResponse> getHomeProducts();

    @Headers("App-Version: 110")
    @GET(Endpoints.HOME)
    Call<HomeAPIResponse> getHomeProductsPaged(@Query("PAGED") int page);


}
