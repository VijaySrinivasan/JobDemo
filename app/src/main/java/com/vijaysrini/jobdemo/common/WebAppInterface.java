package com.vijaysrini.jobdemo.common;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by vijaysrinivasan on 11/14/15.
 */
public class WebAppInterface {
    Context context;

    public WebAppInterface(Context c) {
        context = c;
    }

    // This is the method that will be called by the javascript
    @JavascriptInterface
    public void showToast(String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
