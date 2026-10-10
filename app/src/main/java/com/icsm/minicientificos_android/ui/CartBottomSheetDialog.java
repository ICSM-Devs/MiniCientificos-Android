package com.icsm.minicientificos_android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.icsm.minicientificos_android.adapters.CartAdapter;
import com.icsm.minicientificos_android.cart.CartItem;
import com.icsm.minicientificos_android.cart.CartManager;
import com.icsm.minicientificos_android.databinding.BottomSheetCartBinding;
import com.icsm.minicientificos_android.utils.WhatsAppHelper;

import java.util.List;
import java.util.Locale;

public class CartBottomSheetDialog extends BottomSheetDialogFragment implements CartManager.OnCartChangeListener {

    private BottomSheetCartBinding binding;
    private CartManager cartManager;
    private CartAdapter adapter;

    public CartBottomSheetDialog() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = BottomSheetCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartManager = CartManager.getInstance();
        cartManager.addListener(this);

        setupRecyclerView();
        actualizarUI();

        binding.btnCloseCart.setOnClickListener(v -> dismiss());
        binding.btnCartCheckoutWhatsapp.setOnClickListener(v -> enviarPedidoWhatsApp());
    }

    private void setupRecyclerView() {
        List<CartItem> listaItems = cartManager.obtenerListaItems();
        adapter = new CartAdapter(listaItems);
        binding.rvCartItems.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvCartItems.setAdapter(adapter);
    }

    private void actualizarUI() {
        List<CartItem> listaActual = cartManager.obtenerListaItems();
        int totalItems = cartManager.obtenerConteoTotal();
        double totalMonto = cartManager.obtenerMontoTotal();

        // Actualizar datos del adaptador sin recrearlo
        binding.rvCartItems.setAdapter(new CartAdapter(listaActual));

        if (totalItems == 0) {
            binding.llCartEmptyState.setVisibility(View.VISIBLE);
            binding.rvCartItems.setVisibility(View.GONE);
            binding.llCartSummary.setVisibility(View.GONE);
            binding.tilCartNotes.setVisibility(View.GONE);
            binding.tvCartTotalItemsHeader.setText("0 artículos");
        } else {
            binding.llCartEmptyState.setVisibility(View.GONE);
            binding.rvCartItems.setVisibility(View.VISIBLE);
            binding.llCartSummary.setVisibility(View.VISIBLE);
            binding.tilCartNotes.setVisibility(View.VISIBLE);

            String pluralText = totalItems == 1 ? "1 artículo" : totalItems + " artículos";
            binding.tvCartTotalItemsHeader.setText(pluralText);

            String totalStr = String.format(Locale.US, "S/ %.2f", totalMonto);
            binding.tvCartSubtotal.setText(totalStr);
            binding.tvCartTotalFinal.setText(totalStr);
        }
    }

    @Override
    public void onCartChanged() {
        actualizarUI();
    }

    private void enviarPedidoWhatsApp() {
        if (cartManager.obtenerConteoTotal() == 0) return;

        List<CartItem> lista = cartManager.obtenerListaItems();
        double totalMonto = cartManager.obtenerMontoTotal();
        String notas = binding.etCartNotes.getText() != null ? binding.etCartNotes.getText().toString().trim() : "";

        WhatsAppHelper.abrirPedidoCarrito(requireContext(), lista, totalMonto, notas);
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cartManager.removeListener(this);
        binding = null;
    }
}