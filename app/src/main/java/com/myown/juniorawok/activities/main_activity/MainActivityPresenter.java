package com.myown.juniorawok.activities.main_activity;

import com.myown.juniorawok.models.FlashHomeCombined;
import com.myown.juniorawok.network.RestClient;
import com.myown.juniorawok.network.ServicesInterface;
import com.myown.juniorawok.network.models.FlashAPIResponse;
import com.myown.juniorawok.network.models.HomeAPIResponse;

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
 * Created by Netaq on 2/13/2018.
 */

class MainActivityPresenter {

    private MainActivityView mainActivityViewListener;

    MainActivityPresenter(MainActivityView mainActivityViewListener) {
        this.mainActivityViewListener = mainActivityViewListener;
    }

    void fetchProducts(){
        Observable<FlashAPIResponse> flashAPIResponseObservable  = RestClient.getRetrofitClient()
                .create(ServicesInterface.class)
                .getFlashProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        Observable<HomeAPIResponse> homeAPIResponseObservable  = RestClient.getRetrofitClient()
                .create(ServicesInterface.class)
                .getHomeProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

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
                mainActivityViewListener.onCombinedProductsFetched(flashHomeCombined);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    void fetchHomeProducts(int page){
        Call<HomeAPIResponse> homeAPIResponseCall = RestClient.getAdapter().getHomeProductsPaged(page);
        homeAPIResponseCall.enqueue(new Callback<HomeAPIResponse>() {
            @Override
            public void onResponse(Call<HomeAPIResponse> call, Response<HomeAPIResponse> response) {
                mainActivityViewListener.onHomeProductsFetched(response.body());
            }

            @Override
            public void onFailure(Call<HomeAPIResponse> call, Throwable t) {
                mainActivityViewListener.onNetworkFailure();
            }
        });
    }

}
