package com.vijaysrini.jobdemo.common;

import com.vijaysrini.jobdemo.model.BBProduct;

import java.util.ArrayList;

/**
 * Created by vijaysrinivasan on 1/2/16.
 */
public class BBProductSearchResult {
    String from;
    String to;
    int total;
    int currentPage;
    int totalPages;
    float queryTime;
    float totalTime;
    boolean partial;
    String canonicalUrl;
    ArrayList<BBProduct> products;

    public BBProductSearchResult() {
        products = new ArrayList<BBProduct>();
        from = "";
        to="";
        canonicalUrl = "";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public float getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(float queryTime) {
        this.queryTime = queryTime;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public boolean isPartial() {
        return partial;
    }

    public void setPartial(boolean partial) {
        this.partial = partial;
    }

    public String getCanonicalUrl() {
        return canonicalUrl;
    }

    public void setCanonicalUrl(String canonicalUrl) {
        this.canonicalUrl = canonicalUrl;
    }

    public ArrayList<BBProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<BBProduct> products) {
        this.products = products;
    }

    public int productCount() {
        return products.size();
    }
}
