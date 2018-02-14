package com.myown.juniorawok.activities.main_activity;

import com.myown.juniorawok.models.FlashHomeCombined;
import com.myown.juniorawok.network.RestClient;
import com.myown.juniorawok.network.ServicesInterface;
import com.myown.juniorawok.network.models.FlashAPIResponse;
import com.myown.juniorawok.network.models.HomeAPIResponse;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This is a presenter class for MainActivity.
 *
 */

class MainActivityPresenter {

    private MainActivityView mainActivityViewListener;

    MainActivityPresenter(MainActivityView mainActivityViewListener) {
        this.mainActivityViewListener = mainActivityViewListener;
    }

    /**
     * The following method is responsible of fetching flash and home products from the respective APIs.
     * RxJava with Retrofit Client is being used in this method to call the APIs.
     */
    void fetchProducts(){
        // Defining the observable for flash products API Call.
        Observable<FlashAPIResponse> flashAPIResponseObservable  = RestClient.getRetrofitClient()
                .create(ServicesInterface.class)
                .getFlashProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        // Defining the observable for home products API Call.
        Observable<HomeAPIResponse> homeAPIResponseObservable  = RestClient.getRetrofitClient()
                .create(ServicesInterface.class)
                .getHomeProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        // Defining the observable and its respective BiFunction for combining the responses from
        // flash and home APi.
        Observable<FlashHomeCombined> combined = Observable.zip(flashAPIResponseObservable,
                homeAPIResponseObservable, new BiFunction<FlashAPIResponse, HomeAPIResponse, FlashHomeCombined>() {
            @Override
            public FlashHomeCombined apply(FlashAPIResponse flashAPIResponse, HomeAPIResponse homeAPIResponse) throws Exception {
                return new FlashHomeCombined(flashAPIResponse,homeAPIResponse);
            }
        });

        combined.subscribe(new Observer<FlashHomeCombined>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(FlashHomeCombined flashHomeCombined) {
                // On successful response from home and flash api and combining the responses of both
                // Call the respective interface method which is being implemented by HomeActivity.
                mainActivityViewListener.onCombinedProductsFetched(flashHomeCombined);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();

                // On error check the type of error.
                if(e instanceof IOException)
                    // If IOException it means that the network is not available.
                    // Call the respective interface method which is being implemented by HomeActivity.
                    mainActivityViewListener.onNetworkFailure();
                else
                    // If some other Exception.
                    // Call the respective interface method which is being implemented by HomeActivity.
                    mainActivityViewListener.onProductsFetchFailed();

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * The following method is responsible fro fetching only the home products on pagination.
     * @param page
     */
    void fetchHomeProducts(int page){

        // Defining the Retrofit Call for the API.
        Call<HomeAPIResponse> homeAPIResponseCall = RestClient.getAdapter().getHomeProductsPaged(page);
        homeAPIResponseCall.enqueue(new Callback<HomeAPIResponse>() {
            @Override
            public void onResponse(Call<HomeAPIResponse> call, Response<HomeAPIResponse> response) {
                // On getting the response check that the response is successful or not.
                if(response.isSuccessful())
                    // If successful.
                    // Here we should also check the error codes returned by the API. But that information
                    // was not provided in the test.
                    // Call the respective interface method which is being implemented by HomeActivity.
                    mainActivityViewListener.onHomeProductsFetched(response.body());
                else
                    // If not successful.
                    // Call the respective interface method which is being implemented by HomeActivity.
                    mainActivityViewListener.onProductsFetchFailed();
            }

            @Override
            public void onFailure(Call<HomeAPIResponse> call, Throwable t) {
                // In case of failure.
                // Call the respective interface method which is being implemented by HomeActivity.
                mainActivityViewListener.onNetworkFailure();
            }
        });
    }

}
