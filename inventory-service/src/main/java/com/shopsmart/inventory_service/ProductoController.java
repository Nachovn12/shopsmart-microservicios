package com.shopsmart.inventory_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // lista que simula la base de datos
    private List<Producto> productos = new ArrayList<>();

    // agregamos algunos productos de prueba al inicio
    public ProductoController() {
        productos.add(new Producto(1L, "Laptop Gamer X500", 899990.0, 15, 1));
        productos.add(new Producto(2L, "Monitor 4K UltraWide", 350000.0, 50, 2));
        productos.add(new Producto(3L, "Teclado Mecanico RGB", 75000.0, 120, 1));
        productos.add(new Producto(4L, "Mouse Inalambrico Pro", 45990.0, 80, 1));
        productos.add(new Producto(5L, "Audifonos Bluetooth", 89990.0, 60, 2));
    }

    // retorna todos los productos
    @GetMapping
    public List<Producto> obtenerProductos() {
        System.out.println("GET /api/productos - retornando " + productos.size() + " productos");
        return productos;
    }

    // agrega un nuevo producto a la lista
    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto nuevoProducto) {
        // le asignamos un id basado en el tamanio de la lista
        nuevoProducto.setId((long) productos.size() + 1);
        productos.add(nuevoProducto);
        System.out.println("Producto agregado: " + nuevoProducto.getNombre());
        return ResponseEntity.status(201).body(nuevoProducto);
    }
}