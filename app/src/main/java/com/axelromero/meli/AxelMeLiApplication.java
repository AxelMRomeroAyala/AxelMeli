package com.axelromero.meli;

import android.app.Application;

import com.axelromero.meli.injection.DaggerDataComponent;
import com.axelromero.meli.injection.DataComponent;
import com.axelromero.meli.injection.DataModule;

public class AxelMeLiApplication extends Application {

    private static AxelMeLiApplication app;
    DataComponent dataComponent;

    public static AxelMeLiApplication getApp(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app= this;
        initDataComponent();
    }

    private void initDataComponent(){
        dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule(this))
                .build();
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}
