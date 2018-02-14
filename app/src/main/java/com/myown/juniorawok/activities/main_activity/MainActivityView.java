package com.myown.juniorawok.activities.main_activity;

import com.myown.juniorawok.models.FlashHomeCombined;
import com.myown.juniorawok.network.GeneralNetworkInterface;
import com.myown.juniorawok.network.models.HomeAPIResponse;

/**
 * Created by Netaq on 2/13/2018.
 */

public interface MainActivityView extends GeneralNetworkInterface{

    void onCombinedProductsFetched(FlashHomeCombined flashHomeCombined);
    void onHomeProductsFetched(HomeAPIResponse homeAPIResponse);
}
