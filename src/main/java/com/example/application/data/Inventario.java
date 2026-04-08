package com.example.application.data;

public class Inventario {

    private Long Codigo;
    private String Nombre;
    private String Categoria;
    private int Stock;
    private int Precio;
    private int StockCritico;

    // Getters y Setters
    public Long getCodigo() {
        return Codigo;
    }

    public void setCodigo(Long Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public int getStockCritico() {
        return StockCritico;
    }

    public void setStockCritico(int StockCritico) {
        this.StockCritico = StockCritico;
    }
}