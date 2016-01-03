package com.vijaysrini.jobdemo.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.vijaysrini.jobdemo.R;
import com.vijaysrini.jobdemo.common.BBProdList;
import com.vijaysrini.jobdemo.common.BBProduct;
import com.vijaysrini.jobdemo.common.BBProductSearchResult;
import com.vijaysrini.jobdemo.common.Constants;
import com.vijaysrini.jobdemo.common.Utility;
import com.vijaysrini.jobdemo.service.MyRestIntentService;
import com.vijaysrini.jobdemo.view.ProductListArrayAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class BBOpenActivity extends AppCompatActivity {
    ListView resultListView;
    ProgressDialog progressDialog;
    EditText searchEditText;
    String LOGTAG = "BBOpenActivity";
    MyReceiver myReceiver = new MyReceiver();
    ArrayList<BBProduct> productList = new ArrayList<BBProduct>();
    BBProductSearchResult searchResult = new BBProductSearchResult();
    ProductListArrayAdapter adapter;

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(LOGTAG + ".MyReceiver", "onReceive starts");
            progressDialog.cancel();
            String response = intent.getStringExtra(Constants.SEARCH_PRODUCTS_RESULT);
            Log.d(LOGTAG,"Response follows:\n" + response);

            JSONObject jsonResponse = Utility.getJson(context,response);

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            searchResult = gson.fromJson(response, BBProductSearchResult.class);
            Log.i(LOGTAG, "SUCCESS converting result to java. Search result has " + searchResult.productCount() + " items.");
            productList = searchResult.getProducts();
            adapter.clear();
            adapter.addAll(productList);
            adapter.notifyDataSetChanged();
        }
    }

    /*@TODO:
        search text error msg - client side
        Show list
        action on list item.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbopen);
        setTitle("Demo for product browsing");
        searchEditText = (EditText) findViewById(R.id.searchEditText);
        resultListView = (ListView) findViewById(R.id.bb_prod_list);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Android Demo");

        //Register an intent filter for receiving product search response.
        IntentFilter prodSearchResponseFilter = new IntentFilter (Constants.BROADCAST_SEARCH_PRODUCTS);
        //Add a datascheme filter?  Why do we need this?
        //prodSearchResponseFilter.addDataScheme("http");

        //Instantiate a DownloadReceiver
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, prodSearchResponseFilter);
        Log.d(LOGTAG, "Registered a receiver for product search");

        // Now take care of the listview.
        productList.add(new BBProduct());
        productList.add(new BBProduct());

        adapter = new ProductListArrayAdapter(this, R.layout.row_search_result,productList);
        resultListView.setAdapter(adapter);

        //Add listener to the list click
        resultListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final String item = (String) parent.getItemAtPosition(position);
                        Log.d(LOGTAG, "Received a tap on " + item + " in the search result list");
                    }
                }
        );

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


    public void search (View view) {
        Log.d(LOGTAG, "search starts");
        Intent prodSearch = new Intent(this, MyRestIntentService.class);
        prodSearch.setData(Uri.parse(searchEditText.getText().toString()));
        prodSearch.setAction(Constants.ACTION_SEARCH_PRODUCTS);
        startService(prodSearch);
        //progressDialog.show();
        view.animate().setDuration(2000).alpha(0);

    }
}
