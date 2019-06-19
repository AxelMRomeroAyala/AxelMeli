package com.axelromero.meli.models;

import java.io.Serializable;

public class PaymentConfigurationModel implements Serializable {

    private String inputtedValue;
    private PaymentMethodModel paymentMethodModel;
    private PaymentMethodProviderModel paymentMethodProviderModel;
    private PaymentMethodInstallmentModel.PayerCost payerCost;

    public PaymentConfigurationModel(String inputtedValue, PaymentMethodModel paymentMethodModel, PaymentMethodProviderModel providerModel, PaymentMethodInstallmentModel.PayerCost payerCost){
        this.inputtedValue= inputtedValue;
        this.paymentMethodModel = paymentMethodModel;
        this.paymentMethodProviderModel = providerModel;
        this.payerCost= payerCost;
    }

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

    public PaymentMethodInstallmentModel.PayerCost getPayerCost() {
        return payerCost;
    }

    public void setPayerCost(PaymentMethodInstallmentModel.PayerCost payerCost) {
        this.payerCost = payerCost;
    }
}
