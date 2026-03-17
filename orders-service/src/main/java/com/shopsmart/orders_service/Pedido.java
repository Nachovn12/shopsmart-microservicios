package com.shopsmart.orders_service;

public class Pedido {

    private Long    id;
    private Long    idCliente;   // qué cliente hizo la compra
    private Long    idProducto;  // qué producto compró
    private Integer cantidad;    // cuántas unidades
    private String  estado;      // PENDIENTE, PROCESANDO, ENVIADO, ENTREGADO
    private Double  total;       // valor total en pesos

    public Pedido(Long id, Long idCliente, Long idProducto, Integer cantidad, String estado, Double total) {
        this.id         = id;
        this.idCliente  = idCliente;
        this.idProducto = idProducto;
        this.cantidad   = cantidad;
        this.estado     = estado;
        this.total      = total;
    }

    public Pedido() {}
    public Long    getId()         { return id; }
    public Long    getIdCliente()  { return idCliente; }
    public Long    getIdProducto() { return idProducto; }
    public Integer getCantidad()   { return cantidad; }
    public String  getEstado()     { return estado; }
    public Double  getTotal()      { return total; }

    public void setId(Long id)                  { this.id = id; }
    public void setIdCliente(Long idCliente)    { this.idCliente = idCliente; }
    public void setIdProducto(Long idProducto)  { this.idProducto = idProducto; }
    public void setCantidad(Integer cantidad)   { this.cantidad = cantidad; }
    public void setEstado(String estado)        { this.estado = estado; }
    public void setTotal(Double total)          { this.total = total; }
}