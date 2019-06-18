package com.axelromero.meli.views;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.axelromero.meli.R;
import com.axelromero.meli.adapters.PaymentInstallmentAdapter;
import com.axelromero.meli.models.PaymentMethodInstallmentModel;
import com.axelromero.meli.presenters.PaymentConfigurationActivityPresenter;
import com.axelromero.meli.presenters.SelectInstallmentPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectInstallmentFragment extends Fragment implements SelectInstallmentPresenter.SelectInstallmentInteractor, PaymentInstallmentAdapter.PaymentInstallmentAdapterInteractor {

    private static final String METHOD_ID = "method_id";
    private static final String ISSUER_ID = "issuer_id";

    PaymentConfigurationActivityPresenter.MainActivityInteractor mainActivityInteractor;
    SelectInstallmentPresenter presenter;

    PaymentInstallmentAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    private String methodId;
    private String issuerId;

    public static SelectInstallmentFragment getFragment(String methodId, String issuerId) {
        SelectInstallmentFragment fragment = new SelectInstallmentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(METHOD_ID, methodId);
        bundle.putString(ISSUER_ID, issuerId);
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
        methodId = getArguments().getString(METHOD_ID);
        issuerId = getArguments().getString(ISSUER_ID);
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
        presenter.getPaymentInstalments(methodId, issuerId);

        recyclerView = view.findViewById(R.id.payment_installment_recycler);
        progressBar = view.findViewById(R.id.payment_installment_progress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

        if (installmentModelList != null) {
            adapter = new PaymentInstallmentAdapter(installmentModelList.get(0), this);
            recyclerView.setAdapter(adapter);
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailToLoadInstallments() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onInstallmentSelected(PaymentMethodInstallmentModel installmentModel) {
        mainActivityInteractor.onInstallmentDecided(installmentModel);
    }
}
