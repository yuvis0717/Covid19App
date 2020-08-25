package com.covid19app.models;

import java.util.List;
import java.io.Serializable;

public class StatsDTO implements Serializable {
	private int totalConfirmedCases;
	private int newlyConfirmedCases;
	private int totalDeaths;
	private int newDeaths;
	private int totalRecoveredCases;
	private int newlyRecoveredCases;
	private List<HistoryDTO> history;
	private List<BreakdownsDTO> breakdowns;

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

	public void setHistory(List<HistoryDTO> history){
		this.history = history;
	}

	public List<HistoryDTO> getHistory(){
		return history;
	}

	public void setBreakdowns(List<BreakdownsDTO> breakdowns){
		this.breakdowns = breakdowns;
	}

	public List<BreakdownsDTO> getBreakdowns(){
		return breakdowns;
	}

	@Override
 	public String toString(){
		return 
			"StatsDTO{" + 
			"totalConfirmedCases = '" + totalConfirmedCases + '\'' + 
			",newlyConfirmedCases = '" + newlyConfirmedCases + '\'' + 
			",totalDeaths = '" + totalDeaths + '\'' + 
			",newDeaths = '" + newDeaths + '\'' + 
			",totalRecoveredCases = '" + totalRecoveredCases + '\'' + 
			",newlyRecoveredCases = '" + newlyRecoveredCases + '\'' + 
			",history = '" + history + '\'' + 
			",breakdowns = '" + breakdowns + '\'' + 
			"}";
		}
}