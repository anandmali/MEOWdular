package com.anandmali.aisledesign.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.anandmali.aisledesign.network.NetworkState;
import com.anandmali.aisledesign.network.NotesRepository;
import com.anandmali.aisledesign.network.StateLiveDate;
import com.anandmali.aisledesign.network.data.notes.TestProfileListData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class DiscoverViewModel extends ViewModel {

    private final NotesRepository repository;
    private final DiscoverBinding discoverBinding;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final StateLiveDate<TestProfileListData> profileListStatus = new StateLiveDate<>();

    public LiveData<NetworkState<TestProfileListData>> getStatus() {
        return profileListStatus;
    }

    @Inject
    public DiscoverViewModel(NotesRepository repository, DiscoverBinding discoverBinding) {
        this.repository = repository;
        this.discoverBinding = discoverBinding;
        getTestProfileList();
    }

    private void getTestProfileList() {
        discoverBinding.setLoading(true);
        Disposable disposable = repository.getTestProfileList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleError);

        compositeDisposable.add(disposable);
    }

    private void handleSuccess(TestProfileListData profileListData) {
        Log.e("====> Success VM main cat", profileListData.getCatProfiles().getName());
        Log.e("====> Success VM list size", profileListData.getMoreCats().size()+"");
        Log.e("====> Success VM first list", profileListData.getMoreCats().get(0).getName()+"");

        profileListStatus.postSuccess(profileListData);
    }

    private void handleError(Throwable throwable) {
        profileListStatus.postError(throwable.getMessage());
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
