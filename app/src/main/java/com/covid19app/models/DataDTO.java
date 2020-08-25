package com.covid19app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataDTO implements Serializable {

	@SerializedName("paginationMeta")
	private PaginationMetaDTO paginationMeta;

	@SerializedName("last_update")
	private String lastUpdate;

	@SerializedName("rows")
	private List<RowsDTO> rows;

	public void setPaginationMeta(PaginationMetaDTO paginationMeta){
		this.paginationMeta = paginationMeta;
	}

	public PaginationMetaDTO getPaginationMeta(){
		return paginationMeta;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setRows(List<RowsDTO> rows){
		this.rows = rows;
	}

	public List<RowsDTO> getRows(){
		return rows;
	}

	@Override
 	public String toString(){
		return 
			"DataDTO{" + 
			"paginationMeta = '" + paginationMeta + '\'' + 
			",last_update = '" + lastUpdate + '\'' + 
			",rows = '" + rows + '\'' + 
			"}";
		}
}