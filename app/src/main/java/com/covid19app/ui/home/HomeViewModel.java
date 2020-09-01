package com.covid19app.ui.home;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.covid19app.models.BreakdownsDTO;
import com.covid19app.models.CountryResponseDTO;
import com.covid19app.models.HistoryDTO;
import com.covid19app.models.ResponseDTO;
import com.covid19app.network.APIClient;
import com.covid19app.network.APIInterface;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private APIInterface apiInterface;
    private MutableLiveData<BreakdownsDTO> responseDTOMutableLiveData;

    public HomeViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        responseDTOMutableLiveData = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void callCovidRecordApi(final String countryName, final String countryCode) {
        apiInterface.getCoivdCases("https://api.smartable.ai/coronavirus/stats/global?Subscription-Key=1a3e9cd5b22c4a9b86885d5ce7a1ce3d")
                .enqueue(new Callback<ResponseDTO>() {
                    @Override
                    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                        System.out.println("==>" + response.body());
                        if (response.isSuccessful() && response.body() != null &&
                                response.body().getStats() != null && response.body().getStats().getBreakdowns() != null) {
                            System.out.println("==>Api call");
                            List<BreakdownsDTO> list = response.body().getStats().getBreakdowns();
                            boolean isNotFound = true;
                            for (BreakdownsDTO mBean : list) {
                                if (mBean != null && mBean.getLocation() != null) {
                                    String name = TextUtils.isEmpty(countryName) ? "Canada" : countryName;
                                    if (mBean.getLocation().getCountryOrRegion().contains(name)) {
                                        responseDTOMutableLiveData.setValue(mBean);
                                        isNotFound = false;
                                        break;
                                    } else if (mBean.getLocation().getIsoCode() != null && mBean.getLocation().getIsoCode().equalsIgnoreCase(countryCode)) {
                                        responseDTOMutableLiveData.setValue(mBean);
                                        isNotFound = false;
                                        break;
                                    }
                                }
                            }
                            if (isNotFound) {
                                BreakdownsDTO mBean = new BreakdownsDTO();
                                mBean.setTotalConfirmedCases(0);
                                mBean.setTotalDeaths(0);
                                mBean.setTotalRecoveredCases(0);
                                responseDTOMutableLiveData.setValue(mBean);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDTO> call, Throwable t) {

                    }
                });
    }

    public MutableLiveData<BreakdownsDTO> getResponseDTOMutableLiveData() {
        return responseDTOMutableLiveData;
    }
}