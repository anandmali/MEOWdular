package com.anandmali.aisledesign.network;

import com.anandmali.aisledesign.network.data.LoginData;
import com.anandmali.aisledesign.network.data.PhoneNumberData;
import com.anandmali.aisledesign.network.data.TokenData;
import com.anandmali.aisledesign.network.data.VerifyOtpData;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class LoginRepository {

    private final ApiServices apiServices;

    @Inject
    public LoginRepository(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    public Single<Boolean> doLogin(PhoneNumberData phoneNumberData) {
        return apiServices.phoneNumberLogin(phoneNumberData)
                .map(LoginData::getStatus);
    }

    public Single<String> verifyOtp(VerifyOtpData verifyOtpData) {
        return apiServices.verifyOtp(verifyOtpData)
                .map(TokenData::getToken);
    }

}
