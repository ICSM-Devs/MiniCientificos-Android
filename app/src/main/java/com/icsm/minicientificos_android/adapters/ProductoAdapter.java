package com.icsm.minicientificos_android.adapters;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.icsm.minicientificos_android.R;
import com.icsm.minicientificos_android.databinding.ItemProductoBinding;
import com.icsm.minicientificos_android.models.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    public interface OnProductClickListener {
        void onProductClick(Producto producto);
        void onAddToCart(Producto producto, MaterialButton btn);
    }

    private List<Producto> items = new ArrayList<>();
    private final OnProductClickListener listener;

    public ProductoAdapter(OnProductClickListener listener) {
        this.listener = listener;
    }

    public void updateList(List<Producto> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductoBinding binding = ItemProductoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.bind(items.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    static class ProductoViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductoBinding binding;

        public ProductoViewHolder(@NonNull ItemProductoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Producto item, OnProductClickListener listener) {
            binding.tvNombre.setText(item.getNombre());
            binding.tvDescripcion.setText(item.getDescripcion());
            binding.tvPrecio.setText(String.format(Locale.getDefault(), "S/ %.2f", item.getPrecio()));
            binding.ivProducto.setImageResource(item.getDrawableResId());

            // Top Tag (Eco, Hecho a mano, Nuevo)
            if (item.getTopTag() != null && !item.getTopTag().isEmpty()) {
                binding.tvTopTag.setVisibility(View.VISIBLE);
                binding.tvTopTag.setText(item.getTopTag());
                // Configurar color según el tag
                switch (item.getTopTag().toLowerCase()) {
                    case "eco":
                        binding.tvTopTag.setTextColor(Color.WHITE);
                        binding.tvTopTag.setBackgroundResource(R.drawable.bg_badge_tag); // Se podría crear un fondo verde
                        binding.tvTopTag.setBackgroundColor(Color.parseColor("#2E7D32"));
                        break;
                    case "hecho a mano":
                        binding.tvTopTag.setTextColor(Color.WHITE);
                        binding.tvTopTag.setBackgroundColor(Color.parseColor("#E65100"));
                        break;
                    case "nuevo":
                        binding.tvTopTag.setTextColor(Color.WHITE);
                        binding.tvTopTag.setBackgroundColor(Color.parseColor("#00838F"));
                        break;
                    default:
                        binding.tvTopTag.setBackgroundResource(R.drawable.bg_washi_tape);
                }
            } else {
                binding.tvTopTag.setVisibility(View.GONE);
            }

            // Badge Chip Inferior
            if (item.getBadgeChip() != null && !item.getBadgeChip().isEmpty()) {
                binding.tvBadgeChip.setVisibility(View.VISIBLE);
                binding.tvBadgeChip.setText(item.getBadgeChip());
            } else {
                binding.tvBadgeChip.setVisibility(View.GONE);
            }

            // Clics
            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) listener.onProductClick(item);
            });

            binding.btnAgregar.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAddToCart(item, binding.btnAgregar);
                }
            });
        }
    }
}