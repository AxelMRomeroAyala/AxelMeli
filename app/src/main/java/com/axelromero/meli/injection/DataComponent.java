package com.axelromero.meli.injection;

import com.axelromero.meli.AxelMeLiApplication;
import com.axelromero.meli.presenters.MainActivityPresenter;
import com.axelromero.meli.views.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={DataModule.class})

public interface DataComponent {

    void inject(AxelMeLiApplication axelMeLiApplication);
    void inject(MainActivityPresenter mainActivityPresenter);

}
