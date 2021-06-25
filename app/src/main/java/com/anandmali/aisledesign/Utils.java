package com.anandmali.aisledesign;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Utils {

    @Inject
    public Utils() {
    }

    public void hideKeyboardFrom(Context context, View view) {
        try {
            if (context != null && view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            Log.e("Closing keyboard", e.getMessage());
        }
    }
}
