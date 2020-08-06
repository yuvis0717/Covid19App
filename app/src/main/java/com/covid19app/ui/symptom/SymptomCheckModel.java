package com.covid19app.ui.symptom;

import java.util.List;

public class SymptomCheckModel {
    private String id;
    private String gender;
    private String name;
    private String age;
    private String contactWithPatient;
    private String publicExposedPlace;
    private String symptoms;
    private String risk;

    public List<String> getSymtomsList() {
        return symtomsList;
    }

    public void setSymtomsList(List<String> symtomsList) {
        this.symtomsList = symtomsList;
    }

    private List<String> symtomsList;


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContactWithPatient() {
        return contactWithPatient;
    }

    public void setContactWithPatient(String contactWithPatient) {
        this.contactWithPatient = contactWithPatient;
    }

    public String getPublicExposedPlace() {
        return publicExposedPlace;
    }

    public void setPublicExposedPlace(String publicExposedPlace) {
        this.publicExposedPlace = publicExposedPlace;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
