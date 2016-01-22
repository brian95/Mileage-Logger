package com.example.brianmote.mileagelogger.Helpers;

import android.app.ProgressDialog;

/**
 * Created by Brian Mote on 1/21/2016.
 */
public class ProgressDialogHelper {
    ProgressDialog dialog;

    public ProgressDialogHelper(ProgressDialog dialog){
        this.dialog = dialog;
    }

    public void buildDialog(){
        dialog.setTitle("Loading");
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.show();
    }
}
