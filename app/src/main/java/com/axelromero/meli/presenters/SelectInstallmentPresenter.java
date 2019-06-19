package com.axelromero.meli.presenters;

import android.app.Application;

import com.axelromero.meli.AxelMeLiApplication;
import com.axelromero.meli.data.MeLiAPICallInterface;
import com.axelromero.meli.models.PaymentMethodInstallmentModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SelectInstallmentPresenter {
    @Inject
    Retrofit retrofitService;

    SelectInstallmentInteractor interactor;

    public SelectInstallmentPresenter(Application application, SelectInstallmentInteractor installmentInteractor) {
        ((AxelMeLiApplication) application).getDataComponent().inject(this);
        this.interactor = installmentInteractor;
    }

    public void getPaymentInstalments(String method, String issuerId) {
        MeLiAPICallInterface service = retrofitService.create(MeLiAPICallInterface.class);

        Call<List<PaymentMethodInstallmentModel>> call = service.getPaymentMethodInstallments(method, issuerId);

        call.enqueue(new Callback<List<PaymentMethodInstallmentModel>>() {
            @Override
            public void onResponse(Call<List<PaymentMethodInstallmentModel>> call, Response<List<PaymentMethodInstallmentModel>> response) {

                if (response.body() != null && !response.body().isEmpty()) {
                    interactor.onInstallmentsLoaded(response.body());
                } else {
                    interactor.onNoInstallments();
                }
            }

            @Override
            public void onFailure(Call<List<PaymentMethodInstallmentModel>> call, Throwable t) {
                t.printStackTrace();
                interactor.onFailToLoadInstallments();
            }
        });
    }

    public interface SelectInstallmentInteractor {
        void onInstallmentsLoaded(List<PaymentMethodInstallmentModel> installmentModelList);

        void onFailToLoadInstallments();

        void onNoInstallments();
    }

}
