package com.axelromero.meli.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.axelromero.meli.R;
import com.axelromero.meli.presenters.MainActivityPresenter;
import com.axelromero.meli.presenters.ValueInputPresenter;

public class ValueInputFragment extends Fragment implements ValueInputPresenter.ValueInputInteractor {

    EditText valueInput;
    ValueInputPresenter valueInputPresenter;
    MainActivityPresenter.MainActivityInteractor mainActivityInteractor;

    public ValueInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        valueInputPresenter= new ValueInputPresenter(this);
        return inflater.inflate(R.layout.fragment_value_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        valueInput= view.findViewById(R.id.input_value_edit_text);

        valueInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                valueInputPresenter.validateInputtedValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    @Override
    public void onInputValueIsValid() {
        mainActivityInteractor.enableNextStep();
    }

    @Override
    public void onInputValueIsInvalid() {
        mainActivityInteractor.disableNextStep();
    }
}
