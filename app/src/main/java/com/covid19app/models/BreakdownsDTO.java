package com.covid19app.models;

import java.io.Serializable;

public class BreakdownsDTO implements Serializable {
	private LocationDTO location;
	private int totalConfirmedCases;
	private int newlyConfirmedCases;
	private int totalDeaths;
	private int newDeaths;
	private int totalRecoveredCases;
	private int newlyRecoveredCases;

	public void setLocation(LocationDTO location){
		this.location = location;
	}

	public LocationDTO getLocation(){
		return location;
	}

	public void setTotalConfirmedCases(int totalConfirmedCases){
		this.totalConfirmedCases = totalConfirmedCases;
	}

	public int getTotalConfirmedCases(){
		return totalConfirmedCases;
	}

	public void setNewlyConfirmedCases(int newlyConfirmedCases){
		this.newlyConfirmedCases = newlyConfirmedCases;
	}

	public int getNewlyConfirmedCases(){
		return newlyConfirmedCases;
	}

	public void setTotalDeaths(int totalDeaths){
		this.totalDeaths = totalDeaths;
	}

	public int getTotalDeaths(){
		return totalDeaths;
	}

	public void setNewDeaths(int newDeaths){
		this.newDeaths = newDeaths;
	}

	public int getNewDeaths(){
		return newDeaths;
	}

	public void setTotalRecoveredCases(int totalRecoveredCases){
		this.totalRecoveredCases = totalRecoveredCases;
	}

	public int getTotalRecoveredCases(){
		return totalRecoveredCases;
	}

	public void setNewlyRecoveredCases(int newlyRecoveredCases){
		this.newlyRecoveredCases = newlyRecoveredCases;
	}

	public int getNewlyRecoveredCases(){
		return newlyRecoveredCases;
	}

	@Override
 	public String toString(){
		return 
			"BreakdownsDTO{" + 
			"location = '" + location + '\'' + 
			",totalConfirmedCases = '" + totalConfirmedCases + '\'' + 
			",newlyConfirmedCases = '" + newlyConfirmedCases + '\'' + 
			",totalDeaths = '" + totalDeaths + '\'' + 
			",newDeaths = '" + newDeaths + '\'' + 
			",totalRecoveredCases = '" + totalRecoveredCases + '\'' + 
			",newlyRecoveredCases = '" + newlyRecoveredCases + '\'' + 
			"}";
		}
}