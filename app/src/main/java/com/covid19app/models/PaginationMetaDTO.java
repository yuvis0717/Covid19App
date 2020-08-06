package com.covid19app.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PaginationMetaDTO implements Serializable {

	@SerializedName("currentPage")
	private int currentPage;

	@SerializedName("currentPageSize")
	private int currentPageSize;

	@SerializedName("totalPages")
	private int totalPages;

	@SerializedName("totalRecords")
	private int totalRecords;

	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}

	public int getCurrentPage(){
		return currentPage;
	}

	public void setCurrentPageSize(int currentPageSize){
		this.currentPageSize = currentPageSize;
	}

	public int getCurrentPageSize(){
		return currentPageSize;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setTotalRecords(int totalRecords){
		this.totalRecords = totalRecords;
	}

	public int getTotalRecords(){
		return totalRecords;
	}

	@Override
 	public String toString(){
		return 
			"PaginationMetaDTO{" + 
			"currentPage = '" + currentPage + '\'' + 
			",currentPageSize = '" + currentPageSize + '\'' + 
			",totalPages = '" + totalPages + '\'' + 
			",totalRecords = '" + totalRecords + '\'' + 
			"}";
		}
}