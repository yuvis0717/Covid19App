package com.covid19app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RowsDTO implements Serializable {

    @SerializedName("country")
    private String country;

    @SerializedName("country_abbreviation")
    private String countryAbbreviation;

    @SerializedName("total_cases")
    private String totalCases;

    @SerializedName("new_cases")
    private String newCases;

    @SerializedName("total_deaths")
    private String totalDeaths;

    @SerializedName("new_deaths")
    private String newDeaths;

    @SerializedName("total_recovered")
    private String totalRecovered;

    @SerializedName("active_cases")
    private String activeCases;

    @SerializedName("serious_critical")
    private String seriousCritical;

    @SerializedName("cases_per_mill_pop")
    private String casesPerMillPop;

    @SerializedName("flag")
    private String flag;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountryAbbreviation(String countryAbbreviation) {
        this.countryAbbreviation = countryAbbreviation;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setSeriousCritical(String seriousCritical) {
        this.seriousCritical = seriousCritical;
    }

    public String getSeriousCritical() {
        return seriousCritical;
    }

    public void setCasesPerMillPop(String casesPerMillPop) {
        this.casesPerMillPop = casesPerMillPop;
    }

    public String getCasesPerMillPop() {
        return casesPerMillPop;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return
                "RowsDTO{" +
                        "country = '" + country + '\'' +
                        ",country_abbreviation = '" + countryAbbreviation + '\'' +
                        ",total_cases = '" + totalCases + '\'' +
                        ",new_cases = '" + newCases + '\'' +
                        ",total_deaths = '" + totalDeaths + '\'' +
                        ",new_deaths = '" + newDeaths + '\'' +
                        ",total_recovered = '" + totalRecovered + '\'' +
                        ",active_cases = '" + activeCases + '\'' +
                        ",serious_critical = '" + seriousCritical + '\'' +
                        ",cases_per_mill_pop = '" + casesPerMillPop + '\'' +
                        ",flag = '" + flag + '\'' +
                        "}";
    }
}