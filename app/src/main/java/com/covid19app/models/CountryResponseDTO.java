package com.covid19app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryResponseDTO implements Serializable {

	@SerializedName("data")
	private DataDTO data;

	@SerializedName("status")
	private String status;

	public void setData(DataDTO data){
		this.data = data;
	}

	public DataDTO getData(){
		return data;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"CountryResponseDTO{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}