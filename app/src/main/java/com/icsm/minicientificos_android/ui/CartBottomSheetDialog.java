package com.icsm.minicientificos_android.ui;

import android.content.Intent;
import android.net.Uri;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        // Al escuchar cambios, repintamos el carrito
        actualizarUI();
    }

    private void enviarPedidoWhatsApp() {
        if (cartManager.obtenerConteoTotal() == 0) return;

        StringBuilder sb = new StringBuilder();
        sb.append("Hola *Mini Científicos*, me gustaría realizar el siguiente pedido de tienda:\n\n");

        List<CartItem> lista = cartManager.obtenerListaItems();
        for (CartItem item : lista) {
            sb.append("• ").append(item.getCantidad()).append("x ")
              .append(item.getProducto().getNombre())
              .append(" (").append(item.getSubtotalFormateado()).append(")\n");
        }

        sb.append("\n*Subtotal:* S/ ").append(String.format(Locale.US, "%.2f", cartManager.obtenerMontoTotal()));
        
        String notas = binding.etCartNotes.getText() != null ? binding.etCartNotes.getText().toString().trim() : "";
        if (!notas.isEmpty()) {
            sb.append("\n*Notas:* ").append(notas);
        }

        try {
            String url = "https://api.whatsapp.com/send?phone=51930754024&text=" + URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8.name());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            dismiss(); // Cierra el carrito tras enviar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cartManager.removeListener(this);
        binding = null;
    }
}