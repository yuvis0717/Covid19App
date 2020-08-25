package com.covid19app.models;

import java.io.Serializable;

public class LocationDTO implements Serializable {
	private Object jsonMemberLong;
	private String countryOrRegion;
	private Object provinceOrState;
	private Object county;
	private String isoCode;
	private Object lat;

	public void setJsonMemberLong(Object jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public Object getJsonMemberLong(){
		return jsonMemberLong;
	}

	public void setCountryOrRegion(String countryOrRegion){
		this.countryOrRegion = countryOrRegion;
	}

	public String getCountryOrRegion(){
		return countryOrRegion;
	}

	public void setProvinceOrState(Object provinceOrState){
		this.provinceOrState = provinceOrState;
	}

	public Object getProvinceOrState(){
		return provinceOrState;
	}

	public void setCounty(Object county){
		this.county = county;
	}

	public Object getCounty(){
		return county;
	}

	public void setIsoCode(String isoCode){
		this.isoCode = isoCode;
	}

	public String getIsoCode(){
		return isoCode;
	}

	public void setLat(Object lat){
		this.lat = lat;
	}

	public Object getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"LocationDTO{" + 
			"long = '" + jsonMemberLong + '\'' + 
			",countryOrRegion = '" + countryOrRegion + '\'' + 
			",provinceOrState = '" + provinceOrState + '\'' + 
			",county = '" + county + '\'' + 
			",isoCode = '" + isoCode + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}