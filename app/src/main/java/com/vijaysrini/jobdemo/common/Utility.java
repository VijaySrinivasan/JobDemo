package com.vijaysrini.jobdemo.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
import com.vijaysrini.jobdemo.aws.AWSMobileClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by vijaysrinivasan on 12/28/15.
 */
public class Utility {

    public static final String LOGTAG = "Utility";

    public static HttpsURLConnection getConnection(String productSearchUrl) {
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) (new URL(productSearchUrl)).openConnection();
            if (connection != null) {
                connection.setConnectTimeout(Constants.CONNECTION_TIMEOUT);
                connection.setReadTimeout(Constants.CONN_READ_TIMEOUT);
                connection.setDoInput(true);
            }
        } catch (IOException ioException) {
            Log.e(LOGTAG, ioException.getMessage());
        }
        if (connection != null) {
            return connection;
        } else {
            return null;
        }
    }

    /* This is the method called by the service*/
    public static String getResponse(Context context,String prodSearchUrl) {

        Log.d(LOGTAG, "getResponse starts");
        HttpsURLConnection connection = getConnection(prodSearchUrl);
        String response = null;
        try {
            connection = getConnection(prodSearchUrl);
            if (connection != null) {

                try {
                    int status = connection.getResponseCode();
                    if (status >= 300) {
                        String message = connection.getResponseMessage();
                        throw new Exception(message);
                    }

                    InputStream in = connection.getInputStream();
                    String encoding = connection.getContentEncoding();
                    int contentLength = connection.getContentLength();
                    if (encoding == null) encoding = "UTF-8";
                    byte[] buffer = new byte[1024];
                    int length = contentLength > 0 ? contentLength : 0;
                    ByteArrayOutputStream out = new ByteArrayOutputStream(length);
                    int downloadedBytes = 0;
                    int read;
                    while ((read = in.read(buffer)) != -1) {
                        downloadedBytes += read;
                        out.write(buffer, 0, read);
                    }
                    response = new String(out.toByteArray(), encoding);
                    //Log.d(LOGTAG, "The response is " + response);
                    // remove the surrounding unwanted text in response
                    response = removeUnwantedSurrText(response);
                    return response;
                } catch (Exception exception) {
                    Log.e(LOGTAG, exception.getMessage());
                    Toast.makeText(context, exception.getMessage().toString(), Toast.LENGTH_LONG).show();
                    return null;
                }
            }
        } catch (Exception exception) {
            Log.e(LOGTAG, exception.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }


    private static String removeUnwantedSurrText(String input) {

        //Log.d(LOGTAG,"substring0 till 13th= "+ input.substring(0,13));
        if (input.substring(0,13).equals("JSON_CALLBACK")) {
            //Log.d(LOGTAG,"subtring 14 to end = " + input.substring(14,input.length()-1));
            input = input.substring(14,input.length()-1);
            Log.d(LOGTAG,"removeUnwantedSurrText found the JSON_CALLBACK( and removed it ");
        }
        return input;
    }


    public static JSONObject getJson (Context context, String text) {
        JSONObject responseJson = null;
        try {
            responseJson = new JSONObject(text);
        } catch (JSONException jException) {
            Log.e(LOGTAG + ".getJson", "The input text follows below\n:" + text);
            Log.e(LOGTAG,jException.getMessage());
        }
        return responseJson;
    }

    public static Object getValueFromJson (Context context, JSONObject jsonObject, String key) {
        Object result = null;
        try {
            result = (Object) jsonObject.get(key);
        } catch (JSONException jException) {
            Log.e(LOGTAG + ".getFromJson", "Unable to cast value from json to object");
            Log.e(LOGTAG,jException.getMessage());
        }
        return result;
    }

    public static void logError(Context context, Exception exception) {
        Log.e(LOGTAG,exception.getMessage());
        Toast.makeText(context,exception.getMessage(), Toast.LENGTH_LONG).show();
        // Add analytics event
    }

    public static void logError(Context context, String errorMessage) {
        Log.e(LOGTAG, errorMessage);
        Toast.makeText(context,errorMessage, Toast.LENGTH_LONG).show();
        // Add analytics event
    }
}