package com.axelromero.meli.presenters;

public class ValueInputPresenter {

    private ValueInputInteractor interactor;

    public ValueInputPresenter(ValueInputInteractor valueInputInteractor){
        this.interactor= valueInputInteractor;
    }

    public void validateInputtedValue(String inputedValue){

        try {
            int value = Integer.parseInt(inputedValue);
            if(value> 0){
                interactor.onInputValueIsValid();
            }
            else {
                interactor.onInputValueIsInvalid();
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
            interactor.onInputValueIsInvalid();
        }

    }


    public interface ValueInputInteractor{
        void onInputValueIsValid();
        void onInputValueIsInvalid();
    }
}
