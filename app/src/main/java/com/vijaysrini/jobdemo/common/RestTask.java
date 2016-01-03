package com.vijaysrini.jobdemo.common;

import android.os.AsyncTask;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.transform.Result;

/**
 * Created by vijaysrinivasan on 12/3/15.
 */
public class RestTask extends AsyncTask<URL, Integer, Object> {

    private HttpURLConnection connection;
    private static final String TAG = "RestTask";

    public interface ResponseCallback {
        public void onRequestSuccess (String response);
        public void onRequestError(Exception error);
    }

    public interface ProgressCallback {
        public void onProgressUpdate(int progress);
    }

    //Activity callbacks. Use weakreference to avoid blocking operations
    private WeakReference<ResponseCallback> mResponseCallback;
    private WeakReference<ProgressCallback> mProgressCallback;

    public RestTask(HttpURLConnection conn) {
        Log.d(TAG,"Constructing RestTask.");
        connection = conn;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    protected String doInBackground(URL... url) {

        Log.d(TAG,"doInBackground starts");
        // Get response data
        String output = null;
        try {
            int status = connection.getResponseCode();
            if (status >= 300) {
                String message = connection.getResponseMessage();
                throw  new Exception(message);
            }

            InputStream in = connection.getInputStream();
            String encoding = connection.getContentEncoding();
            int contentLength = connection.getContentLength();
            if (encoding == null) {
                encoding = "UTF-8";
            }

            byte[] buffer = new byte[1024];

            int length = contentLength > 0 ? contentLength : 0;
            ByteArrayOutputStream out = new ByteArrayOutputStream(length);

            int downloadedBytes = 0;
            int read;
            while ((read = in.read(buffer)) != -1) {
                downloadedBytes += read;
                publishProgress((downloadedBytes * 100) / contentLength);
                out.write(buffer, 0, read);
            }
            output = new String(out.toByteArray(), encoding);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return e.toString();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            Log.d(TAG,"response from service is " + output);
            return output;
        }
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    @Override
    protected void onPostExecute(Object o) {
        Log.d("RestTask","onPostExecute with Object");
        super.onPostExecute(o);
    }

    protected void onPostExecute(Result result) {
        Log.d("RestTask","onPostExecute with Result");
        //showDialog("Downloaded " + result + " bytes");
    }

    public void setResponseCallback(ResponseCallback callback) {
        mResponseCallback = new WeakReference<ResponseCallback>(callback);
    }

    public void setProgressCallback(ProgressCallback callback) {
        mProgressCallback = new WeakReference<ProgressCallback>(callback);
    }



}
