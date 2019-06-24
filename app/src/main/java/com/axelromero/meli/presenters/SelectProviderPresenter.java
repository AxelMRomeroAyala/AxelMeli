package com.axelromero.meli.presenters;

import android.app.Application;

import com.axelromero.meli.AxelMeLiApplication;
import com.axelromero.meli.data.MeLiAPICallInterface;
import com.axelromero.meli.models.PaymentMethodProviderModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SelectProviderPresenter {

    @Inject
    private Retrofit retrofitService;

    private SelectProviderInteractor interactor;

    public SelectProviderPresenter(Application application, SelectProviderInteractor interactor) {
        ((AxelMeLiApplication) application).getDataComponent().inject(this);
        this.interactor = interactor;
    }

    public void getProviders(String methodId) {
        MeLiAPICallInterface service = retrofitService.create(MeLiAPICallInterface.class);

        Call<List<PaymentMethodProviderModel>> call = service.getPaymentMethodProvider(methodId);

        call.enqueue(new Callback<List<PaymentMethodProviderModel>>() {
            @Override
            public void onResponse(Call<List<PaymentMethodProviderModel>> call, Response<List<PaymentMethodProviderModel>> response) {
                if(response.body() != null && !response.body().isEmpty()){
                    interactor.onProviderListLoaded(response.body());
                }
                else {
                    interactor.onNoProviders();
                }
            }

            @Override
            public void onFailure(Call<List<PaymentMethodProviderModel>> call, Throwable t) {
                t.printStackTrace();
                interactor.onFailedToLoadProviders();
            }
        });
    }

    public interface SelectProviderInteractor {
        void onProviderListLoaded(List<PaymentMethodProviderModel> methodProviderModels);
        void onFailedToLoadProviders();
        void onNoProviders();
    }
}
