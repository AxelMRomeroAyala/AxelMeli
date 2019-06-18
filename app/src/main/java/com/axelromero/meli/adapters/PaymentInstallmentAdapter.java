package com.axelromero.meli.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axelromero.meli.R;
import com.axelromero.meli.models.PaymentMethodInstallmentModel;

public class PaymentInstallmentAdapter extends RecyclerView.Adapter<PaymentInstallmentAdapter.PaymentInstallViewHolder> {

    PaymentMethodInstallmentModel paymentMethodInstallmentModel;
    PaymentInstallmentAdapterInteractor interactor;

    public PaymentInstallmentAdapter(PaymentMethodInstallmentModel methodInstallmentModel, PaymentInstallmentAdapterInteractor installmentAdapterInteractor) {
        this.paymentMethodInstallmentModel = methodInstallmentModel;
        this.interactor= installmentAdapterInteractor;
    }

    @NonNull
    @Override
    public PaymentInstallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.installment_item_view, parent, false);
        return new PaymentInstallmentAdapter.PaymentInstallViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentInstallViewHolder holder, int position) {
        PaymentMethodInstallmentModel.PayerCost payerCost = paymentMethodInstallmentModel.getPayerCosts().get(position);

        holder.quantity.setText(String.valueOf(payerCost.getInstallments()));

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor.onInstallmentSelected(paymentMethodInstallmentModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentMethodInstallmentModel.getPayerCosts().size();
    }

    public class PaymentInstallViewHolder extends RecyclerView.ViewHolder {

        ViewGroup container;
        TextView quantity, amount, finalAmount;


        public PaymentInstallViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.intallment_container);
            quantity = itemView.findViewById(R.id.installment_quantity);
            amount = itemView.findViewById(R.id.installment_amount);
            finalAmount = itemView.findViewById(R.id.installment_final_amount);

        }
    }

    public interface PaymentInstallmentAdapterInteractor{
        void onInstallmentSelected(PaymentMethodInstallmentModel installmentModel);
    }
}
