package com.axelromero.meli.presenters;

import android.app.Application;

import com.axelromero.meli.AxelMeLiApplication;
import com.axelromero.meli.data.MeLiAPICallInterface;
import com.axelromero.meli.models.PaymentMethodModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityPresenter {

    Retrofit retrofitService;

    public MainActivityPresenter(Application application){
        ((AxelMeLiApplication) application).getDataComponent().inject(this);
    }

    public void getPaymentMethods() {

        MeLiAPICallInterface service = retrofitService.create(MeLiAPICallInterface.class);

        Call<List<PaymentMethodModel>> call = service.getPaymentMethods();

        call.enqueue(new Callback<List<PaymentMethodModel>>() {
            @Override
            public void onResponse(Call<List<PaymentMethodModel>> call, Response<List<PaymentMethodModel>> response) {
                response.body();
            }

            @Override
            public void onFailure(Call<List<PaymentMethodModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getProviders(){

    }

    public void getInstallments(){

    }


    public interface MainActivityInteractor{
        void enableNextStep();
        void disableNextStep();
    }

}
