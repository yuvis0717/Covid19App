package com.covid19app.ui.testcenter;

public class TestCentersModel {
    private String name;
    private String address;

    public TestCentersModel() {
    }

    public TestCentersModel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
