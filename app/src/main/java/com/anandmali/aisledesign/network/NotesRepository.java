package com.anandmali.aisledesign.network;

import com.anandmali.aisledesign.network.data.notes.TestProfileListData;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class NotesRepository {

    private final ApiServiceMock apiServiceMock;

    @Inject
    public NotesRepository(ApiServiceMock apiServiceMock) {
        this.apiServiceMock = apiServiceMock;
    }

    public Single<TestProfileListData> getTestProfileList() {
        return apiServiceMock.testProfileList();
    }

}
