package com.icsm.minicientificos_android.models;

import java.io.Serializable;

public class Taller implements Serializable {
    private int id;
    private String titulo;
    private String categoria;
    private String modalidad;
    private String descripcionCorta;
    private String objetivo;
    private String[] temario;
    private String[] materialesIncluidos;
    private String horario;
    private String vacantes;
    private double precio;
    private int bannerDrawable;

    public Taller(int id, String titulo, String categoria, String modalidad, String descripcionCorta, String objetivo, String[] temario, String[] materialesIncluidos, String horario, String vacantes, double precio, int bannerDrawable) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.modalidad = modalidad;
        this.descripcionCorta = descripcionCorta;
        this.objetivo = objetivo;
        this.temario = temario;
        this.materialesIncluidos = materialesIncluidos;
        this.horario = horario;
        this.vacantes = vacantes;
        this.precio = precio;
        this.bannerDrawable = bannerDrawable;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getCategoria() { return categoria; }
    public String getModalidad() { return modalidad; }
    public String getDescripcionCorta() { return descripcionCorta; }
    public String getObjetivo() { return objetivo; }
    public String[] getTemario() { return temario; }
    public String[] getMaterialesIncluidos() { return materialesIncluidos; }
    public String getHorario() { return horario; }
    public String getVacantes() { return vacantes; }
    public double getPrecio() { return precio; }
    public int getBannerDrawable() { return bannerDrawable; }
}