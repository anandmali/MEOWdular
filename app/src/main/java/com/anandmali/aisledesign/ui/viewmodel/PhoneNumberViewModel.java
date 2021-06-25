package com.anandmali.aisledesign.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anandmali.aisledesign.network.LoginRepository;
import com.anandmali.aisledesign.network.NetworkState;
import com.anandmali.aisledesign.network.StateLiveDate;
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
    private final LoginBinding loginBinding;

    private final StateLiveDate<Boolean> numberStatus = new StateLiveDate<>();
    public LiveData<NetworkState<Boolean>> getStatus() {
        return numberStatus;
    }

    @Inject
    public PhoneNumberViewModel(LoginRepository loginRepository, LoginBinding loginBinding) {
        this.loginRepository = loginRepository;
        this.loginBinding = loginBinding;
        loginBinding.setLoading(false);
    }

    public LoginBinding getLoginBinding() {
        return this.loginBinding;
    }

    public void getOtp() {

        String number = loginBinding.getPhoneNumber();

        if (isEmpty(number)
                || !isValidPhoneNumber(number)
                || !isTestPhoneNumber(number)) {
            numberStatus.postError("Invalid number");
            return;
        }

        loginBinding.setLoading(true);
        loginBinding.setSubmitted(true);
        PhoneNumberData phoneNumberData = new PhoneNumberData();
        phoneNumberData.setNumber("+91" + number);

        Disposable disposable = loginRepository.doLogin(phoneNumberData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError);

        compositeDisposable.add(disposable);

    }

    private void handleSuccess(boolean status) {
        if (status) {
            numberStatus.postSuccess(true);
        } else {
            numberStatus.postError("Wrong number");
        }
    }

    private void handleError(Throwable throwable) {
        numberStatus.postError(throwable.getMessage());
    }

    private boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[6789]\\d{9}$";
        return mobile.matches(regEx);
    }

    //This for testing purpose only
    private boolean isTestPhoneNumber(String number) {
        String testNumber = "9876543212";
        return number.equalsIgnoreCase(testNumber);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (compositeDisposable != null)
            compositeDisposable.clear();

    }

}
