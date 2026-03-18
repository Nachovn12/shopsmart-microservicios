package com.shopsmart.users_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    // cargamos usuarios de prueba
    public UsuarioController() {
        usuarios.add(new Usuario(1L, "Maria Gonzalez", "maria@gmail.com", "+56912345678", "VIP"));
        usuarios.add(new Usuario(2L, "Carlos Perez", "carlos@gmail.com", "+56987654321", "REGULAR"));
        usuarios.add(new Usuario(3L, "Ana Rodriguez", "ana@gmail.com", "+56911223344", "REGULAR"));
        usuarios.add(new Usuario(4L, "Luis Martinez", "luis@gmail.com", "+56944556677", "NUEVO"));
        usuarios.add(new Usuario(5L, "Sofia Torres", "sofia@gmail.com", "+56933445566", "VIP"));
    }

    // devuelve la lista completa de usuarios
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        System.out.println("listando todos los usuarios...");
        return usuarios;
    }

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario nuevoUsuario) {
        nuevoUsuario.setId((long) usuarios.size() + 1);
        // los usuarios nuevos siempre parten con categoria NUEVO
        nuevoUsuario.setCategoria("NUEVO");
        usuarios.add(nuevoUsuario);
        System.out.println("usuario registrado: " + nuevoUsuario.getNombre());
        return ResponseEntity.status(201).body(nuevoUsuario);
    }
}