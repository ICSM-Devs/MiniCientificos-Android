package com.icsm.minicientificos_android.cart;

import com.icsm.minicientificos_android.models.Producto;
import java.util.Locale;

public class CartItem {
    private Producto producto;
    private int cantidad;

    public CartItem(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    public String getSubtotalFormateado() {
        return String.format(Locale.US, "S/ %.2f", getSubtotal());
    }
}