package com.covid19app.models;

import java.io.Serializable;

public class HistoryDTO implements Serializable {
	private String date;
	private int confirmed;
	private int deaths;
	private int recovered;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setConfirmed(int confirmed){
		this.confirmed = confirmed;
	}

	public int getConfirmed(){
		return confirmed;
	}

	public void setDeaths(int deaths){
		this.deaths = deaths;
	}

	public int getDeaths(){
		return deaths;
	}

	public void setRecovered(int recovered){
		this.recovered = recovered;
	}

	public int getRecovered(){
		return recovered;
	}

	@Override
 	public String toString(){
		return 
			"HistoryDTO{" + 
			"date = '" + date + '\'' + 
			",confirmed = '" + confirmed + '\'' + 
			",deaths = '" + deaths + '\'' + 
			",recovered = '" + recovered + '\'' + 
			"}";
		}
}