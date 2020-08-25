package com.covid19app.models;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
	private LocationDTO location;
	private String updatedDateTime;
	private StatsDTO stats;

	public void setLocation(LocationDTO location){
		this.location = location;
	}

	public LocationDTO getLocation(){
		return location;
	}

	public void setUpdatedDateTime(String updatedDateTime){
		this.updatedDateTime = updatedDateTime;
	}

	public String getUpdatedDateTime(){
		return updatedDateTime;
	}

	public void setStats(StatsDTO stats){
		this.stats = stats;
	}

	public StatsDTO getStats(){
		return stats;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDTO{" + 
			"location = '" + location + '\'' + 
			",updatedDateTime = '" + updatedDateTime + '\'' + 
			",stats = '" + stats + '\'' + 
			"}";
		}
}