package com.covid19app.network;

import com.covid19app.models.CountryResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("cases/countries-search?")
    public Call<CountryResponseDTO> getCovidCountryApi(@Query("search") String country);
}
