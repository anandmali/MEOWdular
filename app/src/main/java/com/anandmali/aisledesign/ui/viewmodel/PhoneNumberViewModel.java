package com.anandmali.aisledesign.ui.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anandmali.aisledesign.network.NetworkState;
import com.anandmali.aisledesign.network.StateLiveDate;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

import static android.text.TextUtils.isEmpty;

@HiltViewModel
public class PhoneNumberViewModel extends ViewModel {

    public final MutableLiveData<String> phoneNumber = new MutableLiveData<>();
    public final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private final StateLiveDate<Boolean> numberStatus = new StateLiveDate<>();

    public LiveData<NetworkState<Boolean>> getStatus() {
        return numberStatus;
    }

    @Inject
    public PhoneNumberViewModel() {
        isLoading.postValue(false);
    }

    public void getOtp() {
        if (!isEmpty(phoneNumber.getValue()) && Patterns.PHONE.matcher(phoneNumber.getValue()).matches()) {
            isLoading.postValue(true);
            numberStatus.postSuccess(true);
        } else {
            isLoading.postValue(false);
            numberStatus.postError("Wrong number");
        }
    }

}
