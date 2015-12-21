package com.vijaysrini.jobdemo.activity;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vijaysrini.jobdemo.R;
import com.vijaysrini.jobdemo.service.DownloadService;

/**
 * @class DownloadActivity
 *
 * @brief A class that enables a user to download a bitmap image using
 *        the DownloadService.
 */
public class DownloadActivity extends Activity {

    private EditText mUrlEditText; //User's selection of URL to download
    private ImageView mImageView; //Image that's been downloaded
    private String mDefaultUrl = "http://www.dre.vanderbilt.edu/~schmidt/ka.png"; //Default URL.
    private ProgressDialog mProgressDialog; //Display progress of download

    //uses its handleMessage() hook method to process messages sent to it from the DownloadService.
    Handler mDownloadHandler = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
        mUrlEditText = (EditText) findViewById(R.id.mUrlEditText);
        mImageView = (ImageView) findViewById(R.id.mImageView);
        mDownloadHandler = new DownloadHandler(this);
    }

    void showErrorToast(String errorString) {
        Toast.makeText(this, errorString, Toast.LENGTH_LONG).show();
    }

    void displayImage(Bitmap image)
    {
        if (mImageView == null)
            showErrorToast("Problem with Application, please contact the Developer.");
        else if (image != null)
                mImageView.setImageBitmap(image);
            else
                showErrorToast("image is corrupted, please check the requested URL.");
    }

    public void resetImage(View view) {
        mImageView.setImageResource(R.drawable.arrow_icon_white);
    }

    public void downloadImage(View view) {
        // Obtain the requested URL from the user input.
        String url = getUrlString();

        Log.e(DownloadActivity.class.getSimpleName(), "Downloading " + url);
        hideKeyboard();

        // Inform the user that the download is starting.
        showDialog("downloading via startService()");

        // Create an Intent to download an image in the background via
        // a Service.  The downloaded image is later diplayed in the
        // UI Thread via the downloadHandler() method defined below.
        Intent intent = DownloadService.makeIntent(this, Uri.parse(url), mDownloadHandler);

        // Start the DownloadService.
        startService(intent);
    }

    /**
     * @class DownloadHandler
     * @brief A nested class that inherits from Handler and uses its
     *        handleMessage() hook method to process Messages sent to
     *        it from the DownloadService.
     */
    private static class DownloadHandler extends Handler {

        private WeakReference<DownloadActivity> mActivity;

        // Class constructor constructs mActivity as weak reference to the activity
        public DownloadHandler(DownloadActivity activity) {
            mActivity = new WeakReference<DownloadActivity>(activity);
        }

        public void handleMessage(Message message) {
            DownloadActivity activity = mActivity.get();
            if (activity == null) return;

            // Try to extract the pathname from the message.
            String pathname = DownloadService.getPathname(message);
            if (pathname == null) activity.showDialog("failed download");

            // Stop displaying the progress dialog since service has finished.
            activity.dismissDialog();

            // Display the image in the UI Thread.
            activity.displayImage(BitmapFactory.decodeFile(pathname));
        }
    };


    public void showDialog(String message) {
        mProgressDialog = ProgressDialog.show(this, "Download", message,true);
    }

    public void dismissDialog() {
        if (mProgressDialog != null) mProgressDialog.dismiss();
    }

    // Hide the keyboard after a user has finished typing the url.
    private void hideKeyboard() {
        InputMethodManager mgr = (InputMethodManager) getSystemService (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(mUrlEditText.getWindowToken(), 0);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    String getUrlString() {
        String s = mUrlEditText.getText().toString();
        if (s.equals("")) s = mDefaultUrl;
        return s;
    }

}
