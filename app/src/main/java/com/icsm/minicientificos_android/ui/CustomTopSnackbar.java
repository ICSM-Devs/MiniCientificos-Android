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
        android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        snackbarView.setLayoutParams(params);
        rootView.addView(snackbarView);
        
        binding.tvVerCarrito.setOnClickListener(v -> {
            if (activity instanceof androidx.fragment.app.FragmentActivity) {
                CartBottomSheetDialog bottomSheet = new CartBottomSheetDialog();
                bottomSheet.show(((androidx.fragment.app.FragmentActivity) activity).getSupportFragmentManager(), "CartBottomSheet");
            }
            rootView.removeView(snackbarView);
        });

        // Position above screen initially
        snackbarView.post(() -> {
            // El margen superior (marginTop="32dp" en el XML) puede hacer que la altura sola
            // no lo oculte si no bajamos el root view lo suficiente o lo sumamos.
            float outPosition = -snackbarView.getHeight() - 150f;
            snackbarView.setTranslationY(outPosition);
            
            // Slide in (baja hasta un ligero margen en Y, digamos 16px si quisiéramos)
            ObjectAnimator slideIn = ObjectAnimator.ofFloat(snackbarView, "translationY", 0f);
            slideIn.setDuration(400);
            slideIn.setInterpolator(new DecelerateInterpolator());
            slideIn.start();

            // Slide out after delay
            snackbarView.postDelayed(() -> {
                if (snackbarView.getParent() != null) {
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
                }
            }, 2500);
        });
    }
}