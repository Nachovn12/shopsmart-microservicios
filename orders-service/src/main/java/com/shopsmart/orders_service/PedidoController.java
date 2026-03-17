package com.shopsmart.orders_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>(Arrays.asList(
        new Pedido(1L, 101L, 1L, 2, "ENTREGADO",  179998.0),
        new Pedido(2L, 102L, 2L, 1, "ENVIADO",    350000.0),
        new Pedido(3L, 103L, 3L, 3, "PROCESANDO", 225000.0),
        new Pedido(4L, 101L, 4L, 1, "PENDIENTE",   45990.0),
        new Pedido(5L, 104L, 5L, 2, "PROCESANDO", 179980.0)
    ));

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido nuevoPedido) {
        nuevoPedido.setId((long) (pedidos.size() + 1));
        nuevoPedido.setEstado("PENDIENTE");
        pedidos.add(nuevoPedido);
        return ResponseEntity.status(201).body(nuevoPedido);
    }
}
