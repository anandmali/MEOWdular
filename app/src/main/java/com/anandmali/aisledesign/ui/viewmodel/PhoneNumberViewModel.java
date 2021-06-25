package com.anandmali.aisledesign.ui.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anandmali.aisledesign.network.LoginRepository;
import com.anandmali.aisledesign.network.NetworkState;
import com.anandmali.aisledesign.network.StateLiveDate;
import com.anandmali.aisledesign.network.data.LoginData;
import com.anandmali.aisledesign.network.data.PhoneNumberData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.text.TextUtils.isEmpty;

@HiltViewModel
public class PhoneNumberViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final LoginRepository loginRepository;

    public final MutableLiveData<String> phoneNumber = new MutableLiveData<>();
    public final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private final StateLiveDate<Boolean> numberStatus = new StateLiveDate<>();

    public LiveData<NetworkState<Boolean>> getStatus() {
        return numberStatus;
    }

    @Inject
    public PhoneNumberViewModel(LoginRepository loginRepository) {
        isLoading.postValue(false);
        this.loginRepository = loginRepository;
    }

    public void getOtp() {

        if (!isEmpty(phoneNumber.getValue()) && Patterns.PHONE.matcher(phoneNumber.getValue()).matches()) {

            isLoading.postValue(true);
            PhoneNumberData phoneNumberData = new PhoneNumberData();
            phoneNumberData.setNumber("+919876543212");


            Disposable disposable = loginRepository.doLogin(phoneNumberData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleSuccess, this::handleError);

            compositeDisposable.add(disposable);

        } else {
            isLoading.postValue(false);
            numberStatus.postError("Wrong number");
        }
    }

    private void handleSuccess(LoginData loginData) {
        if (loginData.getStatus()) {
            numberStatus.postSuccess(true);
        }
    }

    private void handleError(Throwable throwable) {
        numberStatus.postError("Wrong number");
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (compositeDisposable != null)
            compositeDisposable.clear();

    }
}
