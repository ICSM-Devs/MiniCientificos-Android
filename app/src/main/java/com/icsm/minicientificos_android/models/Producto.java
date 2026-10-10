package com.icsm.minicientificos_android.models;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;
    private int stock;
    private int drawableResId;
    private String topTag; // ej: "Eco", "Hecho a mano", "Nuevo"
    private String badgeChip; // ej: "Coleccionable", "Ideal para regalar"

    public Producto(String id, String nombre, String descripcion, double precio, String categoria, int stock, int drawableResId, String topTag, String badgeChip) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.drawableResId = drawableResId;
        this.topTag = topTag;
        this.badgeChip = badgeChip;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public int getStock() { return stock; }
    public int getDrawableResId() { return drawableResId; }
    public String getTopTag() { return topTag; }
    public String getBadgeChip() { return badgeChip; }
}