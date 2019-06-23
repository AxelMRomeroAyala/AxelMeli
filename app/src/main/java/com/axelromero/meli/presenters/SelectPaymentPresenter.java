package com.axelromero.meli.presenters;

import android.app.Application;

import com.axelromero.meli.AxelMeLiApplication;
import com.axelromero.meli.data.MeLiAPICallInterface;
import com.axelromero.meli.models.PaymentMethodModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SelectPaymentPresenter {

    @Inject
    Retrofit retrofitService;
    SelectPaymentInteractor interactor;

    public SelectPaymentPresenter(Application application, SelectPaymentInteractor interactor) {
        ((AxelMeLiApplication) application).getDataComponent().inject(this);
        this.interactor = interactor;
    }

    public void getPaymentMethods() {

        MeLiAPICallInterface service = retrofitService.create(MeLiAPICallInterface.class);

        Call<List<PaymentMethodModel>> call = service.getPaymentMethods();

        call.enqueue(new Callback<List<PaymentMethodModel>>() {
            @Override
            public void onResponse(Call<List<PaymentMethodModel>> call, Response<List<PaymentMethodModel>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    interactor.onPaymentMethodListLoaded(response.body());
                } else {
                    interactor.onNoPaymentMethod();
                }
            }

            @Override
            public void onFailure(Call<List<PaymentMethodModel>> call, Throwable t) {
                t.printStackTrace();
                interactor.onFailedToRecoverPaymentMethods();
            }
        });
    }

    public interface SelectPaymentInteractor {
        void onPaymentMethodListLoaded(List<PaymentMethodModel> paymentMethodModelList);
        void onFailedToRecoverPaymentMethods();
        void onNoPaymentMethod();
    }
}
