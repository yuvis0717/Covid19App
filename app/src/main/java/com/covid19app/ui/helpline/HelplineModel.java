package com.covid19app.ui.helpline;

public class HelplineModel {
    private String name;
    private String number;

    public HelplineModel() {
    }

    public HelplineModel(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
