package com.anandmali.aisledesign.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anandmali.aisledesign.network.LoginRepository;
import com.anandmali.aisledesign.network.NetworkState;
import com.anandmali.aisledesign.network.StateLiveDate;
import com.anandmali.aisledesign.network.data.VerifyOtpData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static android.text.TextUtils.isEmpty;

@HiltViewModel
public class OtpViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final LoginRepository loginRepository;
    private LoginBinding loginBinding;

    private final StateLiveDate<String> otpStatus = new StateLiveDate<>();

    public LiveData<NetworkState<String>> getStatus() {
        return otpStatus;
    }

    @Inject
    public OtpViewModel(LoginRepository loginRepository, LoginBinding loginBinding) {
        this.loginRepository = loginRepository;
        this.loginBinding = loginBinding;
    }

    public void verifyOtp() {

        String otp = loginBinding.getOtp();

        if (isEmpty(otp)
                || !isValidTestOtp(otp)) {
            otpStatus.postError("Invalid otp");
            return;
        }

        loginBinding.setLoading(true);
        loginBinding.setSubmitted(true);

        VerifyOtpData verifyOtpData = new VerifyOtpData();
        verifyOtpData.setNumber(loginBinding.getPhoneNumber().replaceAll(" ", ""));
        verifyOtpData.setOtp(loginBinding.getOtp());

        Disposable disposable = loginRepository.verifyOtp(verifyOtpData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError);

        compositeDisposable.add(disposable);

    }

    private void handleSuccess(String status) {
        if (!isEmpty(status)) {
            otpStatus.postSuccess(status);
        } else {
            otpStatus.postError("Wrong otp");
        }
    }

    private void handleError(Throwable throwable) {
        otpStatus.postError(throwable.getMessage());
    }

    //For testing otp value is hard coded
    private boolean isValidTestOtp(String otp) {
        return otp.length() == 4 && otp.equalsIgnoreCase("1234");
    }

}
