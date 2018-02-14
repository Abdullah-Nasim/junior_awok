package com.myown.juniorawok.utils;

import android.content.Context;
import android.content.Intent;

import com.myown.juniorawok.activities.main_activity.MainActivity;

/**
 * Created by Abdullah on 2/13/2018.
 */

public class NavigationController {

    /**
     *This method is responsible for starting Main Activity.
     * Since it is a static method, So it can be envoked from anywhere.
     * @param context
     */
    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
