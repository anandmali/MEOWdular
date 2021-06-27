package com.anandmali.aisledesign.ui.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void setRemoteImage(ImageView imageView, String remoteImageSource) {
        Glide.with(imageView.getContext())
                .load(remoteImageSource)
                .into(imageView);
    }
}
