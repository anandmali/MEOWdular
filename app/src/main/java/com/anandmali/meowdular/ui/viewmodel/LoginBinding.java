package com.anandmali.meowdular.ui.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.anandmali.meowdular.BR;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;

@ActivityRetainedScoped
public class LoginBinding extends BaseObservable {

    private String phoneNumber;
    private boolean isLoading;
    private boolean isSubmitted;
    private String otp;
    private String timer;

    @Inject
    public LoginBinding() {
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        this.isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        this.isSubmitted = submitted;
        notifyPropertyChanged(BR.loading);
    }

    @Bindable
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
        notifyPropertyChanged(BR.otp);
    }

    @Bindable
    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
        notifyPropertyChanged(BR.timer);
    }
}
