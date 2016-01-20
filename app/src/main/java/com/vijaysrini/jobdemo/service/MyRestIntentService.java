package com.vijaysrini.jobdemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.vijaysrini.jobdemo.common.Constants;
import com.vijaysrini.jobdemo.common.Utility;

import javax.net.ssl.HttpsURLConnection;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyRestIntentService extends IntentService {
    // Actions handled by this class
    private static final String ACTION_BAZ = "com.vijaysrini.jobdemo.service.action.BAZ";

    //Params used by this class
    private static final String EXTRA_SEARCH_TEXT = "com.vijaysrini.jobdemo.service.extra.bbopen.SEARCH_TEXT";
    private static final String EXTRA_PARAM2 = "com.vijaysrini.jobdemo.service.extra.PARAM2";

    private static final String LOGTAG = "MyRestIntentService";

    public MyRestIntentService() {
        super("MyRestIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(LOGTAG,"onHandleIntent starts");
        if (Looper.myLooper() == null) Looper.prepare();
        if (intent != null) {
            final String action = intent.getAction();
            final String data = intent.getDataString();
            Log.d(LOGTAG,"intent action is " + action.toString() + " and data is " + data);
            if (Constants.ACTION_SEARCH_PRODUCTS.equals(action)) {
                //final String searchText = intent.getStringExtra(EXTRA_SEARCH_TEXT);
                //Log.d(LOGTAG,"getStringExtra = " + searchText);
                Log.d(LOGTAG,"getDataString = " + data);
                String response = searchProducts(data);
                if (response != null) {

                    //TODO: broadcastResponse(Constants.ACTION_SEARCH_PRODUCTS);
                    Intent broadcastIntent = new Intent(Constants.BROADCAST_SEARCH_PRODUCTS)
                            //put extra
                    .putExtra(Constants.SEARCH_PRODUCTS_RESULT,response);
                    // @TODO remove the task of converting string to Json in Utility
                    // or extend Intent allowing it to get JSONObject
                    if (LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)) {
                        Log.d(LOGTAG, "Sent the broadcast that the product search response was received.");
                    } else {
                        Utility.logError(this,"Unable to send broadcast of product search success.");
                    }
                    Looper.loop();
                }
            } else if (ACTION_BAZ.equals(action)) {
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param2);
            }
        }
    }

    /**
     * Starts this product search with the given parameters. If
     * the service is already performing a task this action will be queued.
     */
    public static void startSearchProducts(Context context, String searchText) {
        Log.d(LOGTAG,"startSearchProducts starts");
        Intent restIntentService = new Intent(context, MyRestIntentService.class);
        restIntentService.setAction(Constants.ACTION_SEARCH_PRODUCTS);
        restIntentService.putExtra(EXTRA_SEARCH_TEXT, searchText);
        context.startService(restIntentService);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyRestIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }


    /**
     * Constructs the product search request and calls the get webservice response.
     */
    private String searchProducts(String searchText) {
        Log.d(LOGTAG, "searchProducts starts with searchText="+searchText);
        Uri.Builder  uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https")
                .authority(Constants.BESTBUY_HOST)
                .appendPath(Constants.BESTBUY_SERV_VER)
                .appendPath(Constants.BESTBUY_PROD_SERV_PATH + "((search=" + searchText + "))")
                .appendQueryParameter("apiKey", Constants.BESTBUY_API_KEY)
                        //.appendQueryParameter("facet", "salePrice")
                        //.appendQueryParameter("callback","JSON_CALLBACK")
                .appendQueryParameter("show","name,salePrice,thumbnailImage,sku")
                .appendQueryParameter("format", "json");
        String prodSearchUrl = uriBuilder.build().toString();
        Log.d(LOGTAG,"prodSearchUrl is " + prodSearchUrl);

        HttpsURLConnection connection = null;
        String response = Utility.getResponse(this,prodSearchUrl);
        if (response != null) Log.d(LOGTAG,"response has " + response.length() + " keys");
        return response;
        }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz( String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
