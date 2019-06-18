package com.axelromero.meli.models;

import java.io.Serializable;

public class PaymentConfigurationModel implements Serializable {

    private String inputtedValue;
    private PaymentMethodModel paymentMethodModel;
    private PaymentMethodProviderModel paymentMethodProviderModel;
    private PaymentMethodInstallmentModel paymentMethodInstallmentModel;

    public String getInputtedValue() {
        return inputtedValue;
    }

    public void setInputtedValue(String inputtedValue) {
        this.inputtedValue = inputtedValue;
    }

    public PaymentMethodModel getPaymentMethodModel() {
        return paymentMethodModel;
    }

    public void setPaymentMethodModel(PaymentMethodModel paymentMethodModel) {
        this.paymentMethodModel = paymentMethodModel;
    }

    public PaymentMethodProviderModel getPaymentMethodProviderModel() {
        return paymentMethodProviderModel;
    }

    public void setPaymentMethodProviderModel(PaymentMethodProviderModel paymentMethodProviderModel) {
        this.paymentMethodProviderModel = paymentMethodProviderModel;
    }

    public PaymentMethodInstallmentModel getPaymentMethodInstallmentModel() {
        return paymentMethodInstallmentModel;
    }

    public void setPaymentMethodInstallmentModel(PaymentMethodInstallmentModel paymentMethodInstallmentModel) {
        this.paymentMethodInstallmentModel = paymentMethodInstallmentModel;
    }
}
