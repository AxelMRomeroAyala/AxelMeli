package com.axelromero.meli.views;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.axelromero.meli.R;
import com.axelromero.meli.adapters.PaymentMethodsAdapter;
import com.axelromero.meli.models.PaymentMethodModel;
import com.axelromero.meli.presenters.PaymentConfigurationActivityPresenter;
import com.axelromero.meli.presenters.SelectPaymentPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectPaymentMethodFragment extends Fragment implements SelectPaymentPresenter.SelectPaymentInteractor, PaymentMethodsAdapter.PaymentMethodAdapterInteractor {

    private PaymentConfigurationActivityPresenter.MainActivityInteractor mainActivityInteractor;
    private SelectPaymentPresenter presenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView noDataMessage;
    private PaymentMethodsAdapter adapter;

    public SelectPaymentMethodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.payment_method_recycler);
        progressBar= view.findViewById(R.id.payment_method_progress);
        noDataMessage= view.findViewById(R.id.payment_method_no_data);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        presenter = new SelectPaymentPresenter(getActivity().getApplication(), this);
        presenter.getPaymentMethods();

        startPostponedEnterTransition();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mainActivityInteractor = (PaymentConfigurationActivityPresenter.MainActivityInteractor) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MainActivityInteractor");
        }
    }

    @Override
    public void onPaymentMethodListLoaded(List<PaymentMethodModel> paymentMethodModelList) {
        adapter= new PaymentMethodsAdapter(paymentMethodModelList, this);
        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailedToRecoverPaymentMethods() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.fail_method , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoPaymentMethod() {
        progressBar.setVisibility(View.GONE);
        noDataMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMethodSelected(PaymentMethodModel methodModel) {
        mainActivityInteractor.onMethodDecided(methodModel);
    }

}
