package com.axelromero.meli.views;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.axelromero.meli.R;
import com.axelromero.meli.presenters.MainActivityPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectInstallmentFragment extends Fragment {

    MainActivityPresenter.MainActivityInteractor mainActivityInteractor;

    public SelectInstallmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_installment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mainActivityInteractor = (MainActivityPresenter.MainActivityInteractor) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MainActivityInteractor");
        }
    }

}
