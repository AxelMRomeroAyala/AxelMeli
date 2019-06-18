package com.axelromero.meli.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axelromero.meli.R;
import com.axelromero.meli.models.PaymentMethodProviderModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class PaymentProvidersAdapter extends RecyclerView.Adapter<PaymentProvidersAdapter.PaymentProviderViewHolder> {

    List<PaymentMethodProviderModel> paymentMethodProviderModelList;
    PaymentProviderAdapterInteractor interactor;

    public PaymentProvidersAdapter(List<PaymentMethodProviderModel> paymentMethodProviderModelList, PaymentProviderAdapterInteractor interactor) {
        this.paymentMethodProviderModelList = paymentMethodProviderModelList;
        this.interactor= interactor;
    }

    @NonNull
    @Override
    public PaymentProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_method_item_view, parent, false);
        return new PaymentProvidersAdapter.PaymentProviderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentProviderViewHolder holder, final int position) {
        holder.textView.setText(paymentMethodProviderModelList.get(position).getName());
        Glide.with(holder.imageView.getContext())
                .load(paymentMethodProviderModelList.get(position).getSecureThumbnail())
                .override(300)
                .into(holder.imageView);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor.onProviderSelected(paymentMethodProviderModelList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentMethodProviderModelList.size();
    }

    public class PaymentProviderViewHolder extends RecyclerView.ViewHolder {

        ViewGroup container;
        TextView textView;
        ImageView imageView;

        public PaymentProviderViewHolder(@NonNull View itemView) {
            super(itemView);

            container= itemView.findViewById(R.id.payment_method_container);
            textView = itemView.findViewById(R.id.payment_method_name);
            imageView = itemView.findViewById(R.id.payment_method_image);

        }
    }

    public interface PaymentProviderAdapterInteractor{
        void onProviderSelected(PaymentMethodProviderModel providerModel);
    }
}
