package com.vijaysrini.jobdemo.common;

/**
 * Created by vijaysrinivasan on 1/2/16.
 */
public class BBProduct {

    String name;
    float price;
    String sku;
    String thumbnailUrl;

    // This default constructor is used for testing purpose.
    public BBProduct() {
        name = "New House 1";
        price = (float) 40;
        sku = "testsku";
        thumbnailUrl = "http://images.bestbuy.com/BestBuy_US/images/products/4144/4144946_s.gif";
    }

    public BBProduct(String name, float price, String sku, String thumbnailUrl) {
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.thumbnailUrl = thumbnailUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BBProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                '}';
    }
}
