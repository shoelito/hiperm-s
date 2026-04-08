package com.example.application.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {
    private String fechaHora;
    private Long codigoProducto;
    private String tipo; // "ENTRADA", "SALIDA", o "AJUSTE"
    private double cantidad;
    private String motivo;

    public Movimiento() {
    }

    public Movimiento(Long codigoProducto, String tipo, double cantidad, String motivo) {
        this.codigoProducto = codigoProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getters y Setters
    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}