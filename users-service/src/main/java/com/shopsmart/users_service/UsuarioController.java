package com.shopsmart.users_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // Base de datos simulada en memoria
    private List<Usuario> usuarios = new ArrayList<>(Arrays.asList(
        new Usuario(1L, "María González", "maria@gmail.com",  "+56912345678", "VIP"),
        new Usuario(2L, "Carlos Pérez",   "carlos@gmail.com", "+56987654321", "REGULAR"),
        new Usuario(3L, "Ana Rodríguez",  "ana@gmail.com",    "+56911223344", "REGULAR"),
        new Usuario(4L, "Luis Martínez",  "luis@gmail.com",   "+56944556677", "NUEVO"),
        new Usuario(5L, "Sofía Torres",   "sofia@gmail.com",  "+56933445566", "VIP")
    ));

    // GET /api/usuarios — devuelve todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarios);
    }

    // POST /api/usuarios — registra un usuario nuevo, siempre parte como NUEVO
    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario nuevoUsuario) {
        nuevoUsuario.setId((long) (usuarios.size() + 1));
        nuevoUsuario.setCategoria("NUEVO");
        usuarios.add(nuevoUsuario);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }
}