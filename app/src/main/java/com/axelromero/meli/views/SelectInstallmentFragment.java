package com.axelromero.meli.views;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.axelromero.meli.R;
import com.axelromero.meli.adapters.PaymentInstallmentAdapter;
import com.axelromero.meli.models.PaymentMethodInstallmentModel;
import com.axelromero.meli.models.PaymentMethodModel;
import com.axelromero.meli.models.PaymentMethodProviderModel;
import com.axelromero.meli.presenters.PaymentConfigurationActivityPresenter;
import com.axelromero.meli.presenters.SelectInstallmentPresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

public class SelectInstallmentFragment extends Fragment implements SelectInstallmentPresenter.SelectInstallmentInteractor, PaymentInstallmentAdapter.PaymentInstallmentAdapterInteractor {

    private static final String METHOD = "method";
    private static final String ISSUER = "issuer";

    private PaymentConfigurationActivityPresenter.MainActivityInteractor mainActivityInteractor;
    private SelectInstallmentPresenter presenter;

    private PaymentInstallmentAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView noDataMessage;
    private ImageView methodInfo, providerInfo;

    private PaymentMethodModel method;
    private PaymentMethodProviderModel issuer;

    static SelectInstallmentFragment getFragment(PaymentMethodModel method, PaymentMethodProviderModel issuer) {
        SelectInstallmentFragment fragment = new SelectInstallmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(METHOD, new Gson().toJson(method));
        bundle.putString(ISSUER, new Gson().toJson(issuer));
        fragment.setArguments(bundle);
        return fragment;
    }

    public SelectInstallmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        method = new Gson().fromJson(getArguments().getString(METHOD), PaymentMethodModel.class);
        issuer = new Gson().fromJson(getArguments().getString(ISSUER), PaymentMethodProviderModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_installment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new SelectInstallmentPresenter(getActivity().getApplication(), this);
        presenter.getPaymentInstalments(method.getId(), issuer.getId());

        recyclerView = view.findViewById(R.id.payment_installment_recycler);
        progressBar = view.findViewById(R.id.payment_installment_progress);
        noDataMessage = view.findViewById(R.id.payment_installment_no_data);
        methodInfo = view.findViewById(R.id.payment_method_info);
        providerInfo= view.findViewById(R.id.payment_provider_method_info);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        Glide.with(view).load(method.getThumbnail()).into(methodInfo);
        Glide.with(view).load(issuer.getThumbnail()).into(providerInfo);
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
    public void onInstallmentsLoaded(List<PaymentMethodInstallmentModel> installmentModelList) {

        noDataMessage.setVisibility(View.GONE);

        if (installmentModelList != null) {
            adapter = new PaymentInstallmentAdapter(installmentModelList.get(0), this);
            recyclerView.setAdapter(adapter);
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailToLoadInstallments() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.fail_installment, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoInstallments() {
        progressBar.setVisibility(View.GONE);
        noDataMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onInstallmentSelected(PaymentMethodInstallmentModel.PayerCost payerCost) {
        mainActivityInteractor.onInstallmentDecided(payerCost);
    }
}
