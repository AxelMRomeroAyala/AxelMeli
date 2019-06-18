package com.axelromero.meli.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.axelromero.meli.R;
import com.axelromero.meli.models.PaymentMethodInstallmentModel;
import com.axelromero.meli.models.PaymentMethodModel;
import com.axelromero.meli.models.PaymentMethodProviderModel;
import com.axelromero.meli.presenters.PaymentConfigurationActivityPresenter;
import com.google.gson.Gson;

public class PaymentConfigurationActivity extends AppCompatActivity implements PaymentConfigurationActivityPresenter.MainActivityInteractor {

    public static final String CONFIGURATION_MODEL = "config_model";
    private final static String PRICE = "price";

    private FrameLayout fragmentHolder;
    private PaymentConfigurationActivityPresenter presenter;

    private String price;
    private String methodId;
    private String issuerID;

    FragmentManager fragmentManager;

    public static Intent getCallingIntent(Context context, String price) {
        Intent intent = new Intent(context, PaymentConfigurationActivity.class);
        intent.putExtra(PRICE, price);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_configuration);

        price = getIntent().getStringExtra(PRICE);

        presenter = new PaymentConfigurationActivityPresenter(getApplication());

        fragmentHolder = findViewById(R.id.fragment_holder);
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, new SelectPaymentMethodFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void addFragment(Fragment fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_holder, fragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onMethodDecided(PaymentMethodModel methodModel) {
        methodId = methodModel.getId();
        addFragment(SelectProviderFragment.getFragment(methodId));
    }

    @Override
    public void onProviderDecided(PaymentMethodProviderModel providerModel) {
        issuerID = providerModel.getId();
        addFragment(SelectInstallmentFragment.getFragment(methodId, issuerID));
    }

    @Override
    public void onInstallmentDecided(PaymentMethodInstallmentModel installmentModel) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(CONFIGURATION_MODEL, new Gson().toJson(installmentModel));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
