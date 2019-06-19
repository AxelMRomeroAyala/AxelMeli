package com.axelromero.meli.presenters;

import android.app.Application;

import com.axelromero.meli.AxelMeLiApplication;
import com.axelromero.meli.models.PaymentMethodInstallmentModel;
import com.axelromero.meli.models.PaymentMethodModel;
import com.axelromero.meli.models.PaymentMethodProviderModel;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class PaymentConfigurationActivityPresenter {

    @Inject
    Retrofit retrofitService;

    public PaymentConfigurationActivityPresenter(Application application){
        ((AxelMeLiApplication) application).getDataComponent().inject(this);
    }

    public interface MainActivityInteractor{
        void onMethodDecided(PaymentMethodModel methodModel);
        void onProviderDecided(PaymentMethodProviderModel providerModel);
        void onInstallmentDecided(PaymentMethodInstallmentModel.PayerCost payerCost);
    }

}
