package com.shopsmart.orders_service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();

    public PedidoController() {
        pedidos.add(new Pedido(1L, 101L, 1L, 2, "ENTREGADO", 179998.0));
        pedidos.add(new Pedido(2L, 102L, 2L, 1, "ENVIADO", 350000.0));
        pedidos.add(new Pedido(3L, 103L, 3L, 3, "PROCESANDO", 225000.0));
        pedidos.add(new Pedido(4L, 101L, 4L, 1, "PENDIENTE", 45990.0));
        pedidos.add(new Pedido(5L, 104L, 5L, 2, "PROCESANDO", 179980.0));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        System.out.println("GET /api/pedidos - total: " + pedidos.size());
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    @CircuitBreaker(name = "validacionServicios", fallbackMethod = "fallbackCrearPedido")
    public ResponseEntity<?> crearPedido(@RequestBody Pedido nuevoPedido) {
        RestTemplate restTemplate = new RestTemplate();

        // validar usuario en users-service (puerto 8083 corregido)
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> usuarios = restTemplate.getForObject(
                "http://localhost:8083/api/usuarios", List.class);
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
            return ResponseEntity.badRequest()
                    .body("Error: el cliente con ID " + nuevoPedido.getIdCliente() + " no existe.");
        }

        // validar producto en inventory-service (puerto 8081)
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> productos = restTemplate.getForObject(
                "http://localhost:8081/api/productos", List.class);
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
            return ResponseEntity.badRequest()
                    .body("Error: el producto con ID " + nuevoPedido.getIdProducto() + " no existe.");
        }

        nuevoPedido.setId((long) pedidos.size() + 1);
        nuevoPedido.setEstado("PENDIENTE");
        pedidos.add(nuevoPedido);
        System.out.println("Pedido creado con ID: " + nuevoPedido.getId());
        return ResponseEntity.status(201).body(nuevoPedido);
    }

    // este metodo se ejecuta automaticamente si los otros servicios no responden
    public ResponseEntity<?> fallbackCrearPedido(Pedido nuevoPedido, Throwable ex) {
        System.out.println("Circuit Breaker activado: " + ex.getMessage());
        return ResponseEntity.status(503).body(
                "Servicio temporalmente no disponible. El pedido no pudo procesarse porque " +
                        "inventory-service o users-service no están respondiendo. Intente más tarde."
        );
    }
}