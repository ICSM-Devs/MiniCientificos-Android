package com.icsm.minicientificos_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.icsm.minicientificos_android.databinding.ActivityMainBinding;
import com.icsm.minicientificos_android.ui.ContactFragment;
import com.icsm.minicientificos_android.ui.GalleryFragment;
import com.icsm.minicientificos_android.ui.HomeFragment;
import com.icsm.minicientificos_android.ui.ShopFragment;
import com.icsm.minicientificos_android.ui.WorkshopsFragment;

import com.icsm.minicientificos_android.cart.CartManager;
import com.icsm.minicientificos_android.ui.CartBadgeHelper;
import com.icsm.minicientificos_android.ui.CartBottomSheetDialog;

public class MainActivity extends AppCompatActivity implements CartManager.OnCartChangeListener {

    private ActivityMainBinding binding;
    private CartBadgeHelper cartBadgeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inicializar Gestor de Carrito
        CartManager.getInstance().addListener(this);

        // Inicializar Badge de Carrito en el FAB o BottomNav
        cartBadgeHelper = new CartBadgeHelper(binding.bottomNavigation, R.id.nav_shop);
        
        // Clic en FAB Carrito
        binding.fabCart.setOnClickListener(v -> {
            CartBottomSheetDialog bottomSheet = new CartBottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "CartBottomSheet");
        });

        // Fragment por defecto (HomeFragment)
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        // Listener para la navegación inferior (BottomNavigationView)
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Fragment selectedFragment = null;

            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_workshops) {
                selectedFragment = new WorkshopsFragment();
            } else if (itemId == R.id.nav_shop) {
                selectedFragment = new ShopFragment();
            } else if (itemId == R.id.nav_gallery) {
                selectedFragment = new GalleryFragment();
            } else if (itemId == R.id.nav_contact) {
                selectedFragment = new ContactFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (cartBadgeHelper != null) {
            cartBadgeHelper.updateBadge();
        }
    }

    @Override
    public void onCartChanged() {
        runOnUiThread(() -> {
            if (cartBadgeHelper != null) {
                cartBadgeHelper.updateBadge();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        com.icsm.minicientificos_android.cart.CartManager.getInstance().removeListener(this);
    }
}