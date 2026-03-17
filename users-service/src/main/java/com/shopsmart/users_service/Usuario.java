package com.shopsmart.users_service;

public class Usuario {

    private Long   id;
    private String nombre;
    private String email;
    private String telefono;
    private String categoria;  // NUEVO, REGULAR o VIP

    public Usuario(Long id, String nombre, String email, String telefono, String categoria) {
        this.id        = id;
        this.nombre    = nombre;
        this.email     = email;
        this.telefono  = telefono;
        this.categoria = categoria;
    }

    public Usuario() {}

    public Long   getId()        { return id; }
    public String getNombre()    { return nombre; }
    public String getEmail()     { return email; }
    public String getTelefono()  { return telefono; }
    public String getCategoria() { return categoria; }

    public void setId(Long id)               { this.id = id; }
    public void setNombre(String nombre)     { this.nombre = nombre; }
    public void setEmail(String email)       { this.email = email; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCategoria(String cat)     { this.categoria = cat; }
}