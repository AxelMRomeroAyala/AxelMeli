package com.axelromero.meli.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axelromero.meli.R;
import com.axelromero.meli.models.PaymentMethodModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.PaymentMethodViewHolder> {

    List<PaymentMethodModel> paymentMethodModelList;
    PaymentMethodAdapterInteractor interactor;

    public PaymentMethodsAdapter(List<PaymentMethodModel> paymentMethodModelList, PaymentMethodAdapterInteractor interactor) {
        this.paymentMethodModelList = paymentMethodModelList;
        this.interactor= interactor;
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_method_item_view, parent, false);
        return new PaymentMethodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, final int position) {

        holder.textView.setText(paymentMethodModelList.get(position).getName());
        Glide.with(holder.imageView.getContext())
                .load(paymentMethodModelList.get(position).getSecureThumbnail())
                .override(300)
                .into(holder.imageView);


        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactor.onMethodSelected(paymentMethodModelList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentMethodModelList.size();
    }

    public class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

        ViewGroup container;
        TextView textView;
        ImageView imageView;

        public PaymentMethodViewHolder(@NonNull View itemView) {
            super(itemView);

            container= itemView.findViewById(R.id.payment_method_container);
            textView = itemView.findViewById(R.id.payment_method_name);
            imageView = itemView.findViewById(R.id.payment_method_image);

        }
    }

    public interface PaymentMethodAdapterInteractor {
        void onMethodSelected(PaymentMethodModel methodModel);
    }
}
