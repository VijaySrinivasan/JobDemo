package com.vijaysrini.jobdemo.model;

import com.vijaysrini.jobdemo.model.BBProduct;

import java.util.ArrayList;

/**
 * Created by vijaysrinivasan on 1/2/16.
 */
public class BBProdList {
    ArrayList<BBProduct> products = null;

    public BBProdList() {
    }

    public BBProdList(ArrayList<BBProduct> products) {
        this.products = products;
    }

    public ArrayList<BBProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<BBProduct> products) {
        this.products = products;
    }
    public int size () {
        return products.size();
    }
}
