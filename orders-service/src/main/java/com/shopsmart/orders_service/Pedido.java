package com.shopsmart.orders_service;

public class Pedido {

    private Long id;
    private Long idCliente;
    private Long idProducto;
    private Integer cantidad;
    private String estado; // puede ser PENDIENTE, PROCESANDO, ENVIADO o ENTREGADO
    private Double total;

    public Pedido(Long id, Long idCliente, Long idProducto, Integer cantidad, String estado, Double total) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.estado = estado;
        this.total = total;
    }

    public Pedido() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}