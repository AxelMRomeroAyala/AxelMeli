package com.axelromero.meli.data;

import com.axelromero.meli.models.PaymentMethodInstallmentModel;
import com.axelromero.meli.models.PaymentMethodModel;
import com.axelromero.meli.models.PaymentMethodProviderModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MeLiAPICallInterface {

    @GET("/v1/payment_methods")
    Call<List<PaymentMethodModel>> getPaymentMethods();

    @GET("/v1/payment_methods/card_issuers")
    Call<List<PaymentMethodProviderModel>> getPaymentMethodProvider(@Query("payment_method_id") String paymentMethodId);

    @GET("/v1/payment_methods/installments")
    Call<List<PaymentMethodInstallmentModel>> getPaymentMethodInstallments(@Query("payment_method_id") String paymentMethodId);
}
