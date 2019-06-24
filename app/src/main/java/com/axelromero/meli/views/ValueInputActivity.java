package com.axelromero.meli.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.axelromero.meli.R;
import com.axelromero.meli.Utils;
import com.axelromero.meli.models.PaymentConfigurationModel;
import com.axelromero.meli.presenters.ValueInputPresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class ValueInputActivity extends AppCompatActivity implements ValueInputPresenter.ValueInputInteractor {

    private EditText valueInput;
    private Button valueOk;
    private ValueInputPresenter valueInputPresenter;

    private String inputtedValue;

    private PaymentConfigurationModel paymentConfigurationModel;

    private static final int CONFIGURE_PAYMENT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueInputPresenter = new ValueInputPresenter(this);

        valueInput = findViewById(R.id.input_value_edit_text);
        valueOk = findViewById(R.id.input_value_ok);

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

        valueOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputtedValue = valueInput.getText().toString();
                Utils.hideKeyboard(ValueInputActivity.this);
                startActivityForResult(PaymentConfigurationActivity.getCallingIntent(getBaseContext(), inputtedValue), CONFIGURE_PAYMENT_REQUEST);
            }
        });
    }

    public void showResultDialog(PaymentConfigurationModel model) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.configuration_dialog, null);
        dialogBuilder.setView(dialogView);

        TextView valueInfo = dialogView.findViewById(R.id.value_amount);
        TextView instalmentInfo = dialogView.findViewById(R.id.installment_info);

        ImageView methodInfo = dialogView.findViewById(R.id.method);
        ImageView providerInfo = dialogView.findViewById(R.id.provider);

        Glide.with(this).load(model.getPaymentMethodModel().getThumbnail()).into(methodInfo);
        Glide.with(this).load(model.getPaymentMethodProviderModel().getThumbnail()).into(providerInfo);

        valueInfo.setText(model.getInputtedValue());
        instalmentInfo.setText(getResources().getQuantityString(R.plurals.installment_dialog_info, model.getPayerCost().getInstallments(), model.getPayerCost().getInstallments()));

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CONFIGURE_PAYMENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                paymentConfigurationModel = new Gson().fromJson(data.getStringExtra(PaymentConfigurationActivity.CONFIGURATION_MODEL), PaymentConfigurationModel.class);
                showResultDialog(paymentConfigurationModel);

                valueInput.setText(null);
            }
        }

    }

    @Override
    public void onInputValueIsValid() {
        valueOk.setEnabled(true);
    }

    @Override
    public void onInputValueIsInvalid() {
        valueOk.setEnabled(false);
    }
}
