package com.myown.juniorawok.models;

import com.myown.juniorawok.network.models.FlashAPIResponse;
import com.myown.juniorawok.network.models.HomeAPIResponse;

/**
 * Created by Netaq on 2/13/2018.
 */

public class FlashHomeCombined {
    public FlashAPIResponse flashAPIResponse;
    public HomeAPIResponse homeAPIResponse;

    public FlashHomeCombined(FlashAPIResponse flashAPIResponse, HomeAPIResponse homeAPIResponse) {
        this.flashAPIResponse = flashAPIResponse;
        this.homeAPIResponse = homeAPIResponse;
    }
}
