package com.shopsmart.inventory_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> productos = new ArrayList<>(Arrays.asList(
        new Producto(1L, "Laptop Gamer X500",    899990.0, 15,  1),
        new Producto(2L, "Monitor 4K UltraWide", 350000.0, 50,  2),
        new Producto(3L, "Teclado Mecánico RGB",  75000.0, 120, 1),
        new Producto(4L, "Mouse Inalámbrico Pro",  45990.0, 80,  1),
        new Producto(5L, "Audífonos Bluetooth",    89990.0, 60,  2)
    ));

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos() {
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto nuevoProducto) {
        nuevoProducto.setId((long) (productos.size() + 1));
        productos.add(nuevoProducto);
        return ResponseEntity.status(201).body(nuevoProducto);
    }
}