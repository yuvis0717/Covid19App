package com.covid19app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.covid19app.models.CountryResponseDTO;
import com.covid19app.network.APIClient;
import com.covid19app.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private APIInterface apiInterface;
    private MutableLiveData<CountryResponseDTO> responseDTOMutableLiveData;

    public HomeViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        responseDTOMutableLiveData = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void callCovidRecordApi() {
        apiInterface.getCovidCountryApi("canada").enqueue(new Callback<CountryResponseDTO>() {
            @Override
            public void onResponse(Call<CountryResponseDTO> call, Response<CountryResponseDTO> response) {
                System.out.println("==>Api call onResponse");
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("==>Api call");
                    responseDTOMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CountryResponseDTO> call, Throwable t) {
                System.out.println("==>Api call onFailure");
            }
        });
    }

    public MutableLiveData<CountryResponseDTO> getResponseDTOMutableLiveData() {
        return responseDTOMutableLiveData;
    }
}