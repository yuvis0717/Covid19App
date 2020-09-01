package com.covid19app.models.news;

import java.util.List;

public class NewsResponse{
	private int totalResults;
	private List<ArticlesItem> articles;
	private String status;

	public int getTotalResults(){
		return totalResults;
	}

	public List<ArticlesItem> getArticles(){
		return articles;
	}

	public String getStatus(){
		return status;
	}
}