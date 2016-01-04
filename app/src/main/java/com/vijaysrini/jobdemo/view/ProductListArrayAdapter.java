package com.vijaysrini.jobdemo.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vijaysrini.jobdemo.R;
import com.vijaysrini.jobdemo.common.BBProduct;

import java.util.ArrayList;

/**
 * Created by vijaysrinivasan on 1/1/16.
 */


public class ProductListArrayAdapter extends ArrayAdapter <BBProduct>
{

    private ArrayList<BBProduct> searchResult = new ArrayList<BBProduct>() ;
    private Context context;

    public ProductListArrayAdapter  (Context context, int resource,  ArrayList<BBProduct> searchResult) {
        super(context, resource, searchResult);
        this.searchResult = searchResult;
        this.context = context;
        Log.d("ProductListAdapter", "constructor ended with searchResult having " + searchResult.size() + " elements.");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Log.d("ProductListArrayAdapter", "getView starts for position " + position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_bbprod_search_result, parent, false);

        TextView textName= (TextView) rowView.findViewById(R.id.text_bbp_name);
        TextView textPrice = (TextView) rowView.findViewById(R.id.text_bbp_price);
        TextView textSku = (TextView) rowView.findViewById(R.id.text_bbp_sku);
        ImageView imageThumbnail = (ImageView) rowView.findViewById(R.id.icon);

        textName.setText((searchResult.get(position)).getName()) ;
        textPrice.setText (Float.toString((searchResult.get(position)).getPrice()) ) ;
        //imageThumbnail.setImageResource(R.drawable.cast_ic_notification_2);
        textSku.setText((searchResult.get(0)).getSku()) ;

        return rowView;

    }

}
