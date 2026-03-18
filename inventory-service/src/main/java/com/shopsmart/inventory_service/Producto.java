package com.shopsmart.inventory_service;

public class Producto {

    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Integer idAlmacen;

    // constructor con todos los campos
    public Producto(Long id, String nombre, Double precio, Integer stock, Integer idAlmacen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.idAlmacen = idAlmacen;
    }

    // constructor vacio que pide spring para el @RequestBody
    public Producto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
}