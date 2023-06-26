package com.anandmali.meowdular.network;

import androidx.lifecycle.MutableLiveData;

public class StateLiveDate<T> extends MutableLiveData<NetworkState<T>> {

    public void postSuccess(T response) {
        postValue(new NetworkState<T>().success(response));
    }

    public void postError(String errorMessage) {
        postValue(new NetworkState<T>().error(errorMessage));
    }

}
