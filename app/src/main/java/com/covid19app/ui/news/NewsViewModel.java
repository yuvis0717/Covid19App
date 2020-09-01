package com.covid19app.ui.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.covid19app.models.news.ArticlesItem;
import com.covid19app.models.news.NewsResponse;
import com.covid19app.network.APIClient;
import com.covid19app.network.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {
    private APIInterface apiInterface;
    private MutableLiveData<List<ArticlesItem>> responseNewsResponseLiveData;

    public NewsViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        responseNewsResponseLiveData = new MutableLiveData<>();
    }

    public void callCovidRecordApi() {
        apiInterface.getCovidNews("http://newsapi.org/v2/everything?q=covid-19&from=2020-08-30&sortBy=popularity&apiKey=3b735654910f46a3a127dbfcbaf03874")
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        System.out.println("==>" + response.body());
                        if (response.isSuccessful() && response.body() != null && response.body().getArticles() != null) {
                            System.out.println("==>Api call");
                            List<ArticlesItem> list = response.body().getArticles();
                            responseNewsResponseLiveData.setValue(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                    }
                });
    }

    public MutableLiveData<List<ArticlesItem>> getNewsResponseMutableLiveData() {
        return responseNewsResponseLiveData;
    }
}
