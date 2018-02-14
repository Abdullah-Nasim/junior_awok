package com.myown.juniorawok.activities.main_activity;

import com.myown.juniorawok.models.FlashHomeCombined;
import com.myown.juniorawok.network.GeneralNetworkInterface;
import com.myown.juniorawok.network.models.HomeAPIResponse;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This Interface is being implemented by MainActivity.
 * MainActivity Implements this interface to receive CallBacks on different events completed by
 * MainActivityPresenter.
 *
 */

public interface MainActivityView extends GeneralNetworkInterface{

    void onCombinedProductsFetched(FlashHomeCombined flashHomeCombined);
    void onHomeProductsFetched(HomeAPIResponse homeAPIResponse);
    void onProductsFetchFailed();
}
