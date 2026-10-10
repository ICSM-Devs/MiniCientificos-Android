package com.icsm.minicientificos_android.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.icsm.minicientificos_android.R;
import com.icsm.minicientificos_android.cart.CartManager;

public class CartBadgeHelper {

    private final BottomNavigationView bottomNavigationView;
    private final int menuItemId;
    private View badgeView;

    public CartBadgeHelper(BottomNavigationView bottomNavigationView, int menuItemId) {
        this.bottomNavigationView = bottomNavigationView;
        this.menuItemId = menuItemId;
    }

    public void updateBadge() {
        int count = CartManager.getInstance().obtenerConteoTotal();

        if (badgeView == null) {
            android.view.ViewGroup itemView = bottomNavigationView.findViewById(menuItemId);
            if (itemView != null) {
                badgeView = LayoutInflater.from(bottomNavigationView.getContext())
                        .inflate(R.layout.view_cart_badge, bottomNavigationView, false);
                itemView.addView(badgeView);
            }
        }

        if (badgeView != null) {
            TextView tvCount = badgeView.findViewById(R.id.tv_badge_count);
            if (count > 0) {
                tvCount.setText(String.valueOf(count));
                badgeView.setVisibility(View.VISIBLE);
            } else {
                badgeView.setVisibility(View.GONE);
            }
        }
    }
}