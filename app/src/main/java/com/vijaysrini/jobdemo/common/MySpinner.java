package com.vijaysrini.jobdemo.common;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by vijaysrinivasan on 12/28/15.
 */
public class MySpinner  extends ProgressDialog{
    public MySpinner(Context context) {
        super(context);
        setProgressStyle(ProgressDialog.STYLE_SPINNER);
        show();
    }


    public static void cancelSpinner(ProgressDialog progressDialog) {
        progressDialog.cancel();
    }
}
