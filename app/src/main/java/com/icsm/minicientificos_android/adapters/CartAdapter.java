package com.icsm.minicientificos_android.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icsm.minicientificos_android.cart.CartItem;
import com.icsm.minicientificos_android.cart.CartManager;
import com.icsm.minicientificos_android.databinding.ItemCartProductBinding;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final List<CartItem> items;
    private final CartManager cartManager;

    public CartAdapter(List<CartItem> items) {
        this.items = items;
        this.cartManager = CartManager.getInstance();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartProductBinding binding = ItemCartProductBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {

        private final ItemCartProductBinding binding;

        public CartViewHolder(@NonNull ItemCartProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CartItem item) {
            binding.tvCartProductoNombre.setText(item.getProducto().getNombre());
            binding.tvCartProductoPrecio.setText(item.getSubtotalFormateado());
            binding.tvCartCantidad.setText(String.valueOf(item.getCantidad()));
            binding.ivCartProductoImagen.setImageResource(item.getProducto().getDrawableResId());

            // Aumentar cantidad
            binding.btnCartPlus.setOnClickListener(v -> {
                cartManager.actualizarCantidad(
                        String.valueOf(item.getProducto().getId()), 
                        item.getCantidad() + 1
                );
            });

            // Disminuir cantidad
            binding.btnCartMinus.setOnClickListener(v -> {
                cartManager.actualizarCantidad(
                        String.valueOf(item.getProducto().getId()), 
                        item.getCantidad() - 1
                );
            });

            // Eliminar producto
            binding.btnCartRemoveItem.setOnClickListener(v -> {
                cartManager.quitarProducto(String.valueOf(item.getProducto().getId()));
            });
        }
    }
}