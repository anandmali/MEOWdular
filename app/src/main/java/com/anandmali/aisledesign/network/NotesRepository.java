package com.anandmali.aisledesign.network;

import com.anandmali.aisledesign.network.data.notes.TestProfileListData;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class NotesRepository {

    private final ApiServices apiServices;

    @Inject
    public NotesRepository(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    public Single<TestProfileListData> getTestProfileList() {
        return apiServices.testProfileList();
    }

}
