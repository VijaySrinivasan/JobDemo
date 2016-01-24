package com.vijaysrini.jobdemo.model;

/**
 * Created by vijaysrinivasan on 1/2/16.
 */
public class BBProduct {

    String name;
    float salePrice;
    String sku;
    String thumbnailImage;

    // This default constructor is used for testing purpose.
    public BBProduct() {
        name = "New House 1";
        salePrice = (float) 40;
        sku = "testsku";
        thumbnailImage = "http://images.bestbuy.com/BestBuy_US/images/products/4144/4144946_s.gif";
    }

    public BBProduct(String name, float salePrice, String sku, String thumbnailImage) {
        this.name = name;
        this.salePrice = salePrice;
        this.sku = sku;
        this.thumbnailImage = thumbnailImage;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
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
                ", salePrice=" + salePrice +
                ", sku='" + sku + '\'' +
                '}';
    }
}
