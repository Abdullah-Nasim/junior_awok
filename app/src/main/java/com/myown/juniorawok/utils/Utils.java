package com.myown.juniorawok.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import com.myown.juniorawok.R;

/**
 * Created by Abdullah on 2/13/2018.
 */

public class Utils {

    /**
     * This method is responsible for making a dialog box with provided message and OK button.
     * @param msg is the message which we wants this method to display in dialog box.
     * @param context is the context of the respective activity which is calling this method.
     */
    public static void showAlertDialog(String msg, Context context){

        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(R.string.error)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
