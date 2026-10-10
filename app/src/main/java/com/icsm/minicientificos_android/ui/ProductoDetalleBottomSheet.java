package com.icsm.minicientificos_android.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.icsm.minicientificos_android.databinding.DialogDetalleProductoBinding;
import com.icsm.minicientificos_android.models.Producto;

import java.util.Locale;

public class ProductoDetalleBottomSheet extends BottomSheetDialogFragment {

    private DialogDetalleProductoBinding binding;
    private final Producto producto;
    private int cantidadActual = 1;

    public ProductoDetalleBottomSheet(Producto producto) {
        this.producto = producto;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogDetalleProductoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupData();
        setupListeners();
    }

    private void setupData() {
        if (producto == null) return;
        
        binding.ivProductoDetalle.setImageResource(producto.getDrawableResId());
        binding.tvNombreProducto.setText(producto.getNombre());
        binding.tvDescripcionProducto.setText(producto.getDescripcion());
        binding.tvPrecioProducto.setText(String.format(Locale.getDefault(), "S/ %.2f", producto.getPrecio()));
        binding.tvCategoria.setText(producto.getCategoria());

        if (producto.getBadgeChip() != null && !producto.getBadgeChip().isEmpty()) {
            binding.tvBadge.setVisibility(View.VISIBLE);
            binding.tvBadge.setText(producto.getBadgeChip());
        } else {
            binding.tvBadge.setVisibility(View.GONE);
        }

        binding.tvCantidad.setText(String.valueOf(cantidadActual));
    }

    private void setupListeners() {
        binding.btnMinus.setOnClickListener(v -> {
            if (cantidadActual > 1) {
                cantidadActual--;
                binding.tvCantidad.setText(String.valueOf(cantidadActual));
            }
        });

        binding.btnPlus.setOnClickListener(v -> {
            if (cantidadActual < producto.getStock()) {
                cantidadActual++;
                binding.tvCantidad.setText(String.valueOf(cantidadActual));
            } else {
                Toast.makeText(requireContext(), "Stock máximo alcanzado", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnAgregarCarritoModal.setOnClickListener(v -> {
            Toast.makeText(requireContext(), cantidadActual + " x " + producto.getNombre() + " añadidos al carrito", Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}