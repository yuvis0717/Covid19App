package com.covid19app.network;

import com.covid19app.models.CountryResponseDTO;
import com.covid19app.models.ResponseDTO;
import com.covid19app.models.news.NewsResponse;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    @GET("cases/countries-search?")
    public Call<CountryResponseDTO> getCovidCountryApi(@Query("search") String country);

    @GET
    public Call<ResponseDTO> getCoivdCases(@Url String url);

    @GET
    public Call<NewsResponse> getCovidNews(@Url String url);
}
