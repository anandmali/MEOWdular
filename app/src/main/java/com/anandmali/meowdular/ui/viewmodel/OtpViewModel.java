package com.anandmali.meowdular.ui.viewmodel;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anandmali.meowdular.network.LoginRepository;
import com.anandmali.meowdular.network.NetworkState;
import com.anandmali.meowdular.network.StateLiveDate;
import com.anandmali.meowdular.network.data.VerifyOtpData;

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
    private final LoginBinding loginBinding;
    private CountDownTimer countDownTimer;

    private final StateLiveDate<String> otpStatus = new StateLiveDate<>();

    public LiveData<NetworkState<String>> getStatus() {
        return otpStatus;
    }

    @Inject
    public OtpViewModel(LoginRepository loginRepository, LoginBinding loginBinding) {
        this.loginRepository = loginRepository;
        this.loginBinding = loginBinding;
        startOtpTimer();
    }

    public void verifyOtp() {

        String otp = loginBinding.getOtp();

        if (isEmpty(otp) || !isValidTestOtp(otp)) {
            otpStatus.postError("Invalid otp");
            return;
        }

        loginBinding.setLoading(true);
        loginBinding.setSubmitted(true);

        VerifyOtpData verifyOtpData = new VerifyOtpData();
        verifyOtpData.setNumber(loginBinding.getPhoneNumber().replaceAll(" ", ""));
        verifyOtpData.setOtp(otp);

        Disposable disposable = loginRepository.verifyOtp(verifyOtpData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError);

        compositeDisposable.add(disposable);

    }

    private void handleSuccess(String token) {
        if (!isEmpty(token)) {
            otpStatus.postSuccess(token);
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

    private void startOtpTimer() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = (millisUntilFinished / 1000);
                @SuppressLint("DefaultLocale")
                String formattedSeconds = String.format("%02d", seconds);
                String remainingTime = "00:" + formattedSeconds;
                loginBinding.setTimer(remainingTime);
            }

            @Override
            public void onFinish() {
                Log.i("Timer finish => ", "Finished");
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
