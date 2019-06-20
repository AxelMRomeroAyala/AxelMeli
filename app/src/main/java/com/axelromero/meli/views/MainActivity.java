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
import android.widget.TextView;

import com.axelromero.meli.R;
import com.axelromero.meli.Utils;
import com.axelromero.meli.models.PaymentConfigurationModel;
import com.axelromero.meli.presenters.ValueInputPresenter;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements ValueInputPresenter.ValueInputInteractor {

    EditText valueInput;
    Button valueOk;
    ValueInputPresenter valueInputPresenter;

    String inputtedValue;

    PaymentConfigurationModel paymentConfigurationModel;

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
                Utils.hideKeyboard(MainActivity.this);
                startActivityForResult(PaymentConfigurationActivity.getCallingIntent(getBaseContext(), inputtedValue), CONFIGURE_PAYMENT_REQUEST);
            }
        });
    }

    public void buildDialog(PaymentConfigurationModel model) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.configuration_dialog, null);
        dialogBuilder.setView(dialogView);

        TextView textView = dialogView.findViewById(R.id.dialog_text);
        String message = getString(R.string.value) + model.getInputtedValue() + "\n" +
                getString(R.string.method) + model.getPaymentMethodModel().getName() + "\n" +
                getString(R.string.provider) + model.getPaymentMethodProviderModel().getName() + "\n" +
                getString(R.string.installments) + model.getPayerCost().getRecommendedMessage() + "\n";
        textView.setText(message);
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
                buildDialog(paymentConfigurationModel);

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
