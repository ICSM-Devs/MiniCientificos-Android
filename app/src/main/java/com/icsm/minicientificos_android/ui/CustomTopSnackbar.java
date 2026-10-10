package com.icsm.minicientificos_android.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.icsm.minicientificos_android.databinding.CustomTopSnackbarBinding;

public class CustomTopSnackbar {

    public static void show(Activity activity, String productName) {
        if (activity == null) return;

        ViewGroup rootView = activity.findViewById(android.R.id.content);
        if (rootView == null) return;

        CustomTopSnackbarBinding binding = CustomTopSnackbarBinding.inflate(LayoutInflater.from(activity));
        binding.tvSnackbarMessage.setText("✨ ¡" + productName + " agregado al carrito!");

        final View snackbarView = binding.getRoot();
        rootView.addView(snackbarView);

        // Position above screen initially
        snackbarView.post(() -> {
            snackbarView.setTranslationY(-snackbarView.getHeight() - 100);
            
            // Slide in
            ObjectAnimator slideIn = ObjectAnimator.ofFloat(snackbarView, "translationY", 0f);
            slideIn.setDuration(400);
            slideIn.setInterpolator(new DecelerateInterpolator());
            slideIn.start();

            // Slide out after delay
            snackbarView.postDelayed(() -> {
                ObjectAnimator slideOut = ObjectAnimator.ofFloat(snackbarView, "translationY", -snackbarView.getHeight() - 100);
                slideOut.setDuration(400);
                slideOut.setInterpolator(new DecelerateInterpolator());
                slideOut.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        rootView.removeView(snackbarView);
                    }
                });
                slideOut.start();
            }, 2500);
        });
    }
}