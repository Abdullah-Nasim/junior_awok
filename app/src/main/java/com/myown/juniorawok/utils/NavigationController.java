package com.myown.juniorawok.utils;

import android.content.Context;
import android.content.Intent;

import com.myown.juniorawok.activities.main_activity.MainActivity;

/**
 * Created by Abdullah on 2/13/2018.
 *
 * This is a navigation controller of our application.
 * The whole navigation of the application will be controlled by this class.
 * This class will contain static methods which shall be responsible from starting the new activities,
 * doing fragment transaction and other stuff related to application navigation.
 */

public class NavigationController {

    /**
     * This method is responsible for starting Main Activity.
     * Since it is a static method, So it can be called from anywhere.
     * @param context contains the context of an activity which is trying to start MainActivity.
     */
    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
