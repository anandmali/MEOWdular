package com.anandmali.aisledesign.ui.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageSrc(ImageView imageView, String remoteImageSource) {
        Glide.with(imageView.getContext())
                .load(remoteImageSource)
                .into(imageView);
    }

    @BindingAdapter("android:blrSrc")
    public static void setBlurImageSrc(ImageView imageView, String remoteImageSource) {
        Glide.with(imageView.getContext())
                .load(remoteImageSource)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(3)))
                .into(imageView);
    }

}
