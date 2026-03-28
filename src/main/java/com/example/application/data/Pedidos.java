package com.example.application.data;

import java.util.HashMap;
import java.util.Map;

public class Pedidos {

    private Long id;
    private String cliente;

    // Articulo: cantidad
    private Map<String, Integer> articulos = new HashMap<>();
    private String prioridad;
    private String estado;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Map<String, Integer> getArticulos() {
        return articulos;
    }

    public void setArticulos(Map<String, Integer> articulos) {
        this.articulos = articulos;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}