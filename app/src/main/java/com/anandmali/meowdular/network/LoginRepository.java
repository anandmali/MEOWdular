package com.anandmali.meowdular.network;

import com.anandmali.meowdular.network.data.LoginData;
import com.anandmali.meowdular.network.data.PhoneNumberData;
import com.anandmali.meowdular.network.data.TokenData;
import com.anandmali.meowdular.network.data.VerifyOtpData;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class LoginRepository {

    private final ApiServiceMock apiServiceMock;

    @Inject
    public LoginRepository(ApiServiceMock apiServiceMock) {
        this.apiServiceMock = apiServiceMock;
    }

    public Single<Boolean> doLogin(PhoneNumberData phoneNumberData) {
        return apiServiceMock.phoneNumberLogin(phoneNumberData)
                .map(LoginData::getStatus);
    }

    public Single<String> verifyOtp(VerifyOtpData verifyOtpData) {
        return apiServiceMock.verifyOtp(verifyOtpData)
                .map(TokenData::getToken);
    }

}
