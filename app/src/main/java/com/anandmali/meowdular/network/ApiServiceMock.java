package com.anandmali.meowdular.network;

import com.anandmali.meowdular.network.data.LoginData;
import com.anandmali.meowdular.network.data.PhoneNumberData;
import com.anandmali.meowdular.network.data.TokenData;
import com.anandmali.meowdular.network.data.VerifyOtpData;
import com.anandmali.meowdular.network.data.notes.CatProfiles;
import com.anandmali.meowdular.network.data.notes.TestProfileListData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

//Mock API service returning mocked data locally without calling the network API.
public class ApiServiceMock implements ApiServices {

    @Inject
    public ApiServiceMock() {
    }


    @Override
    public Single<LoginData> phoneNumberLogin(PhoneNumberData phoneNumberData) {
        return Single.create(emitter -> {
            LoginData data = new LoginData();
            data.setStatus(true);
            emitter.onSuccess(data);
        });
    }

    @Override
    public Single<TokenData> verifyOtp(VerifyOtpData verifyOtpData) {
        return Single.create(emitter -> {
            TokenData data = new TokenData();
            data.setToken(UUID.randomUUID().toString());
            emitter.onSuccess(data);
        });
    }

    @Override
    public Single<TestProfileListData> testProfileList() {
        return Single.create(emitter -> {
            TestProfileListData data = new TestProfileListData();

            CatProfiles cat = new CatProfiles();
            cat.setName("Simba");
            cat.setAge(2);
            cat.setAvatar("https://cataas.com/cat/cute");

            data.setCatProfiles(cat);

            List<CatProfiles> profiles = new ArrayList<>();

            CatProfiles cat1 = new CatProfiles();
            cat1.setName("Milo");
            cat1.setAge(4);
            cat1.setAvatar("https://cataas.com/cat");

            CatProfiles cat2 = new CatProfiles();
            cat2.setName("Oreo");
            cat2.setAge(5);
            cat2.setAvatar("https://cataas.com/cat");

            CatProfiles cat3 = new CatProfiles();
            cat3.setName("Luna");
            cat3.setAge(5);
            cat3.setAvatar("https://cataas.com/cat");

            CatProfiles cat4 = new CatProfiles();
            cat4.setName("Oscar");
            cat4.setAge(4);
            cat4.setAvatar("https://cataas.com/cat");

            CatProfiles cat5 = new CatProfiles();
            cat5.setName("Willow");
            cat5.setAge(1);
            cat5.setAvatar("https://cataas.com/cat");

            profiles.add(cat1);
            profiles.add(cat2);
            profiles.add(cat3);
            profiles.add(cat4);
            profiles.add(cat5);

            data.setMoreCats(profiles);

            emitter.onSuccess(data);
        });
    }
}
