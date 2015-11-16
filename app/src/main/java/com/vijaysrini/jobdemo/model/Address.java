package com.vijaysrini.jobdemo.model;

/**
 * Created by vijaysrinivasan on 10/30/15.
 */
public class Address {
    public Address() {
    }

    public String getAddrline1() {
        return addrline1;
    }

    public void setAddrline1(String addrline1) {
        this.addrline1 = addrline1;
    }

    public String getAddrline2() {
        return addrline2;
    }

    public void setAddrline2(String addrline2) {
        this.addrline2 = addrline2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String addrline1 = null;
    private String addrline2 = null;
    private String state = null;
    private int zip;

    public Address(String addrline1, String addrline2, String state, int zip) {
        this.addrline1 = addrline1;
        this.addrline2 = addrline2;
        this.state = state;
        this.zip = zip;
    }
}
