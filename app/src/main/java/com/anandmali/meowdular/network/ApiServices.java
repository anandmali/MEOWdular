package com.anandmali.meowdular.network;

import com.anandmali.meowdular.network.data.LoginData;
import com.anandmali.meowdular.network.data.PhoneNumberData;
import com.anandmali.meowdular.network.data.TokenData;
import com.anandmali.meowdular.network.data.VerifyOtpData;
import com.anandmali.meowdular.network.data.notes.TestProfileListData;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
//A mock API services just to mock network setup.
public interface ApiServices {

    @POST("users/phone_number_login")
    Single<LoginData> phoneNumberLogin(@Body PhoneNumberData phoneNumberData);

    @POST("users/verify_otp")
    Single<TokenData> verifyOtp(@Body VerifyOtpData verifyOtpData);

    @Headers("authentication_required: true")
    @GET("users/cat_profile")
    Single<TestProfileListData> testProfileList();

}
