package com.icsm.minicientificos_android.cart;

import com.icsm.minicientificos_android.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private final List<CartItem> items;
    private final List<OnCartChangeListener> listeners;

    public interface OnCartChangeListener {
        void onCartChanged();
    }

    private CartManager() {
        items = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addListener(OnCartChangeListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(OnCartChangeListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

    private void notifyListeners() {
        for (OnCartChangeListener listener : listeners) {
            listener.onCartChanged();
        }
    }

    public void agregarProducto(Producto p) {
        for (CartItem item : items) {
            if (item.getProducto().getId().equals(p.getId())) {
                item.setCantidad(item.getCantidad() + 1);
                notifyListeners();
                return;
            }
        }
        items.add(new CartItem(p, 1));
        notifyListeners();
    }

    public void quitarProducto(String productoId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProducto().getId().equals(productoId)) {
                items.remove(i);
                notifyListeners();
                break;
            }
        }
    }

    public void actualizarCantidad(String productoId, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            quitarProducto(productoId);
            return;
        }

        for (CartItem item : items) {
            if (item.getProducto().getId().equals(productoId)) {
                item.setCantidad(nuevaCantidad);
                notifyListeners();
                break;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
        notifyListeners();
    }

    public int obtenerConteoTotal() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getCantidad();
        }
        return total;
    }

    public double obtenerMontoTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<CartItem> obtenerListaItems() {
        return new ArrayList<>(items); // Retornar copia inmutable superficial
    }
}