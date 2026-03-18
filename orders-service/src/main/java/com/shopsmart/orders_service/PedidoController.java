package com.shopsmart.orders_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    // simulamos la bd con una lista en memoria
    private List<Pedido> pedidos = new ArrayList<>();

    public PedidoController() {
        // datos de prueba
        pedidos.add(new Pedido(1L, 101L, 1L, 2, "ENTREGADO", 179998.0));
        pedidos.add(new Pedido(2L, 102L, 2L, 1, "ENVIADO", 350000.0));
        pedidos.add(new Pedido(3L, 103L, 3L, 3, "PROCESANDO", 225000.0));
        pedidos.add(new Pedido(4L, 101L, 4L, 1, "PENDIENTE", 45990.0));
        pedidos.add(new Pedido(5L, 104L, 5L, 2, "PROCESANDO", 179980.0));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        System.out.println("se pidieron todos los pedidos, hay: " + pedidos.size());
        return ResponseEntity.ok(pedidos);
    }

    // validamos que el idProducto y el idCliente existan comunicandonos con los otros ms
    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Pedido nuevoPedido) {
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            
            // 1. Validar si existe el usuario consultando al microservicio de usuarios (8083)
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> usuarios = restTemplate.getForObject("http://localhost:8083/api/usuarios", List.class);
            boolean clienteExiste = false;
            if (usuarios != null) {
                for (Map<String, Object> u : usuarios) {
                    if (u.get("id").toString().equals(String.valueOf(nuevoPedido.getIdCliente()))) {
                        clienteExiste = true;
                        break;
                    }
                }
            }
            if (!clienteExiste) {
                System.out.println("error: cliente no existe");
                return ResponseEntity.badRequest().body("Error: El cliente con ID " + nuevoPedido.getIdCliente() + " no existe.");
            }

            // 2. Validar si existe el producto consultando al microservicio de inventario (8081)
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> productos = restTemplate.getForObject("http://localhost:8081/api/productos", List.class);
            boolean productoExiste = false;
            if (productos != null) {
                for (Map<String, Object> p : productos) {
                    if (p.get("id").toString().equals(String.valueOf(nuevoPedido.getIdProducto()))) {
                        productoExiste = true;
                        break;
                    }
                }
            }
            if (!productoExiste) {
                System.out.println("error: producto no existe");
                return ResponseEntity.badRequest().body("Error: El producto con ID " + nuevoPedido.getIdProducto() + " no existe.");
            }

        } catch (Exception e) {
            System.out.println("error al conectar con otros servicios: " + e.getMessage());
            // retornamos 500 porque es un problema de conexion entre servicios
            return ResponseEntity.status(500).body("Error interno: no se pudo validar con los otros servicios.");
        }

        nuevoPedido.setId((long) pedidos.size() + 1);
        // todo pedido nuevo parte como PENDIENTE
        nuevoPedido.setEstado("PENDIENTE");
        pedidos.add(nuevoPedido);
        System.out.println("nuevo pedido creado con id: " + nuevoPedido.getId());
        return ResponseEntity.status(201).body(nuevoPedido);
    }
}
