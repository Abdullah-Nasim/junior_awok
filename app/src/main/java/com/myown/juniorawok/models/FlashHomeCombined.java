package com.myown.juniorawok.models;

import com.myown.juniorawok.network.models.FlashAPIResponse;
import com.myown.juniorawok.network.models.HomeAPIResponse;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This calls contains both flash and home products combined at the same place.
 * I will try to combine flash and home API calls and gather the results in the object of this class
 * using RxJava.
 */

public class FlashHomeCombined {
    public FlashAPIResponse flashAPIResponse;
    public HomeAPIResponse homeAPIResponse;

    public FlashHomeCombined(FlashAPIResponse flashAPIResponse, HomeAPIResponse homeAPIResponse) {
        this.flashAPIResponse = flashAPIResponse;
        this.homeAPIResponse = homeAPIResponse;
    }
}
