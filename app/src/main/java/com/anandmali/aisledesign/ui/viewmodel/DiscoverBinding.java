package com.anandmali.aisledesign.ui.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.anandmali.aisledesign.BR;
import com.anandmali.aisledesign.network.data.notes.TestProfileListData;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ActivityRetainedScoped;

@ActivityRetainedScoped
public class DiscoverBinding extends BaseObservable {

    private boolean isLoading;
    private String bannerPhotoUrl;
    private String bannerName;

    @Inject
    public DiscoverBinding() {
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
    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
        notifyPropertyChanged(BR.bannerName);
    }

    @Bindable
    public String getBannerPhotoUrl() {
        return bannerPhotoUrl;
    }

    public void setBannerPhotoUrl(String bannerPhotoUrl) {
        this.bannerPhotoUrl = bannerPhotoUrl;
        notifyPropertyChanged(BR.bannerPhotoUrl);
    }

    public void setViewBindings(TestProfileListData profileListData) {
        Log.e("====> main bind", profileListData.getCatProfiles().getName());
        String firstName = profileListData.getCatProfiles().getName();
        int age = profileListData.getCatProfiles().getAge();
        String name = firstName + ", " + age;
        setBannerName(name);

        String url = profileListData.getCatProfiles().getAvatar();
        setBannerPhotoUrl(url);
    }

}
