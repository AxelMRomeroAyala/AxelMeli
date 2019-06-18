package com.axelromero.meli.injection;

import com.axelromero.meli.AxelMeLiApplication;
import com.axelromero.meli.presenters.PaymentConfigurationActivityPresenter;
import com.axelromero.meli.presenters.SelectInstallmentPresenter;
import com.axelromero.meli.presenters.SelectPaymentPresenter;
import com.axelromero.meli.presenters.SelectProviderPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={DataModule.class})

public interface DataComponent {

    void inject(AxelMeLiApplication axelMeLiApplication);
    void inject(PaymentConfigurationActivityPresenter paymentConfigurationActivityPresenter);
    void inject(SelectPaymentPresenter selectPaymentPresenter);
    void inject(SelectProviderPresenter selectProviderPresenter);
    void inject(SelectInstallmentPresenter selectInstallmentPresenter);


}
