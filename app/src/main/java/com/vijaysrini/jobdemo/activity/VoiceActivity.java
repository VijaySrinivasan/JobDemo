package com.vijaysrini.jobdemo.activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vijaysrini.jobdemo.R;

import java.util.ArrayList;
import java.util.List;

public class VoiceActivity extends AppCompatActivity {

    private static final int VOICE_REQUEST = 1;
    private static final int RECOGNIZE_VOICE_REQUEST_CODE = 1;
    //private static final int RECOGNIZE_VOICE_RESPONSE_OK = 0;

    Button speakButton;
    private ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        speakButton = (Button) findViewById(R.id.speakButton);
        resultList = (ListView) findViewById(R.id.voice_result_list);

        // Disable button if no recognition service is present
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),0);
        if (activities.size() == 0) {
            Toast.makeText(getApplicationContext(), "Recognizer Not Found",Toast.LENGTH_SHORT).show();
        }

        speakButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startVoiceRecognitionActivity();
                    }
                }
        );

        resultList.setOnItemClickListener(
                new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        goToIntent(((TextView) view).getText().toString());
                    }
                }
        );
    }

    private void goToIntent (String action) {
        Intent nextAction;
        switch(action.toLowerCase().trim()) {
            case ("order check book"):
            case ("order checkbook"):
            case ("order chequebook"):
            case ("order cheque book"):
                Log.i("goToIntent","order checkbook");
                //nextAction=new Intent(this,OrderCheckBook.class);
                break;

        }
        return;
    }

    private void startVoiceRecognitionActivity () {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Recognizing voice...");
        startActivityForResult(intent, RECOGNIZE_VOICE_REQUEST_CODE);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOGNIZE_VOICE_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            resultList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,matches));
        }
        super.onActivityResult(requestCode, resultCode,data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void goToRefill (View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("tbdkey","tbdvalue");
        startActivityForResult(intent,VOICE_REQUEST);
    }

}
