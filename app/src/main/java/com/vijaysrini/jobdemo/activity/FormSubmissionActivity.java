package com.vijaysrini.jobdemo.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vijaysrini.jobdemo.R;
import com.vijaysrini.jobdemo.common.Network;
import com.vijaysrini.jobdemo.common.RestTask;

public class FormSubmissionActivity extends AppCompatActivity implements RestTask.ProgressCallback,RestTask.ResponseCallback{

    ProgressDialog progressDialog;
    String TAG = "FormSubmission";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_submission);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_json, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAppSettings(View view) {
        try {
            Log.d(TAG,"Starting getAppSettings");
            String appsettingURL = getResources().getString(R.string.appsettings).toString();
            RestTask getAppSettingsTask = Network.obtainGetTask(appsettingURL);
            if (getAppSettingsTask != null) {
                getAppSettingsTask.setResponseCallback(this);
                getAppSettingsTask.setProgressCallback(this);
                getAppSettingsTask.execute();
            } else {
                Log.d(TAG,"RestTask came back as null");
            }
        } catch (Exception e) {
            //@TODO: Send error to analytics
            Log.d(TAG,e.getMessage());
            Toast.makeText(this,"Network errror occurred",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestSuccess(String response) {
        Log.d(TAG,"onRequestSuccess" + response);
        TextView textView = (TextView) findViewById(R.id.response);
        textView.setText(response);
    }

    @Override
    public void onRequestError (Exception error) {
        Log.d(TAG,"onRequestError");
    }

    @Override
    public void onProgressUpdate (int progress) {
        Log.d(TAG,"onProgressUpdate");
        if (progress >= 0) {
            if (progressDialog !=null) {
                progressDialog.dismiss();
                progressDialog = null;
            }

        }
    }
}
