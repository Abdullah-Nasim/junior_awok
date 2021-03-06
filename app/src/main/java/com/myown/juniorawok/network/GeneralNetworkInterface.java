package com.myown.juniorawok.network;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This is a general network interface which shall be implemented by all of the interfaces which are
 * responsible fors handling the CallBack of a network call.
 * Instead of defining the methods of this interface in all other interfaces, We can extend the rest
 * from this interface.
 */

public interface GeneralNetworkInterface {
    void onNetworkFailure();
}
