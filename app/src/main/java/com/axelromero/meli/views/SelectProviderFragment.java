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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.axelromero.meli.R;
import com.axelromero.meli.adapters.PaymentProvidersAdapter;
import com.axelromero.meli.models.PaymentMethodModel;
import com.axelromero.meli.models.PaymentMethodProviderModel;
import com.axelromero.meli.presenters.PaymentConfigurationActivityPresenter;
import com.axelromero.meli.presenters.SelectProviderPresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectProviderFragment extends Fragment implements SelectProviderPresenter.SelectProviderInteractor, PaymentProvidersAdapter.PaymentProviderAdapterInteractor {

    private static final String METHOD_ID = "method";

    private PaymentConfigurationActivityPresenter.MainActivityInteractor mainActivityInteractor;
    private SelectProviderPresenter presenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView noDataMessage;
    private ImageView methodInfo;
    private PaymentProvidersAdapter adapter;

    private PaymentMethodModel method;

    public static SelectProviderFragment getFragment(PaymentMethodModel method) {
        SelectProviderFragment fragment = new SelectProviderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(METHOD_ID, new Gson().toJson(method));
        fragment.setArguments(bundle);
        return fragment;
    }

    public SelectProviderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        method = new Gson().fromJson(getArguments().getString(METHOD_ID), PaymentMethodModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_provider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.payment_provider_recycler);
        progressBar = view.findViewById(R.id.payment_provider_progress);
        noDataMessage = view.findViewById(R.id.payment_provider_no_data);
        methodInfo = view.findViewById(R.id.payment_method_info);

        methodInfo.setTransitionName(method.getName());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        presenter = new SelectProviderPresenter(getActivity().getApplication(), this);
        presenter.getProviders(method.getId());

        Glide.with(view).load(method.getThumbnail()).into(methodInfo);
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
    public void onProviderListLoaded(List<PaymentMethodProviderModel> methodProviderModels) {
        adapter = new PaymentProvidersAdapter(methodProviderModels, this);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailedToLoadProviders() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), R.string.fail_provider, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoProviders() {
        progressBar.setVisibility(View.GONE);
        noDataMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProviderSelected(PaymentMethodProviderModel providerModel) {
        mainActivityInteractor.onProviderDecided(providerModel);
    }
}
