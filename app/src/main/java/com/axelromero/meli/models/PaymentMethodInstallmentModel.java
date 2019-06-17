package com.axelromero.meli.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentMethodInstallmentModel {


    @SerializedName("payment_method_id")
    @Expose
    private String paymentMethodId;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("issuer")
    @Expose
    private PaymentMethodProviderModel issuer;
    @SerializedName("processing_mode")
    @Expose
    private String processingMode;
    @SerializedName("merchant_account_id")
    @Expose
    private Object merchantAccountId;
    @SerializedName("payer_costs")
    @Expose
    private List<PayerCost> payerCosts = null;
    @SerializedName("agreements")
    @Expose
    private Object agreements;

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public PaymentMethodProviderModel getIssuer() {
        return issuer;
    }

    public void setIssuer(PaymentMethodProviderModel issuer) {
        this.issuer = issuer;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String processingMode) {
        this.processingMode = processingMode;
    }

    public Object getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(Object merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public List<PayerCost> getPayerCosts() {
        return payerCosts;
    }

    public void setPayerCosts(List<PayerCost> payerCosts) {
        this.payerCosts = payerCosts;
    }

    public Object getAgreements() {
        return agreements;
    }

    public void setAgreements(Object agreements) {
        this.agreements = agreements;
    }

    public class PayerCost {

        @SerializedName("installments")
        @Expose
        private Integer installments;
        @SerializedName("installment_rate")
        @Expose
        private Double installmentRate;
        @SerializedName("discount_rate")
        @Expose
        private Integer discountRate;
        @SerializedName("reimbursement_rate")
        @Expose
        private Object reimbursementRate;
        @SerializedName("labels")
        @Expose
        private List<String> labels = null;
        @SerializedName("installment_rate_collector")
        @Expose
        private List<String> installmentRateCollector = null;
        @SerializedName("min_allowed_amount")
        @Expose
        private Integer minAllowedAmount;
        @SerializedName("max_allowed_amount")
        @Expose
        private Integer maxAllowedAmount;
        @SerializedName("recommended_message")
        @Expose
        private String recommendedMessage;
        @SerializedName("installment_amount")
        @Expose
        private Double installmentAmount;
        @SerializedName("total_amount")
        @Expose
        private Double totalAmount;

        public Integer getInstallments() {
            return installments;
        }

        public void setInstallments(Integer installments) {
            this.installments = installments;
        }

        public Double getInstallmentRate() {
            return installmentRate;
        }

        public void setInstallmentRate(Double installmentRate) {
            this.installmentRate = installmentRate;
        }

        public Integer getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(Integer discountRate) {
            this.discountRate = discountRate;
        }

        public Object getReimbursementRate() {
            return reimbursementRate;
        }

        public void setReimbursementRate(Object reimbursementRate) {
            this.reimbursementRate = reimbursementRate;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }

        public List<String> getInstallmentRateCollector() {
            return installmentRateCollector;
        }

        public void setInstallmentRateCollector(List<String> installmentRateCollector) {
            this.installmentRateCollector = installmentRateCollector;
        }

        public Integer getMinAllowedAmount() {
            return minAllowedAmount;
        }

        public void setMinAllowedAmount(Integer minAllowedAmount) {
            this.minAllowedAmount = minAllowedAmount;
        }

        public Integer getMaxAllowedAmount() {
            return maxAllowedAmount;
        }

        public void setMaxAllowedAmount(Integer maxAllowedAmount) {
            this.maxAllowedAmount = maxAllowedAmount;
        }

        public String getRecommendedMessage() {
            return recommendedMessage;
        }

        public void setRecommendedMessage(String recommendedMessage) {
            this.recommendedMessage = recommendedMessage;
        }

        public Double getInstallmentAmount() {
            return installmentAmount;
        }

        public void setInstallmentAmount(Double installmentAmount) {
            this.installmentAmount = installmentAmount;
        }

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

    }
}
