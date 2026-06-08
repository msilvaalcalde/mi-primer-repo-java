package com.mycompany.sistemapersonalmunicipal;

public class Usuario {

    private String usuario;
    private String clave;
    private String rol;
    private boolean activo;

    public Usuario() {
        this.activo = true;
    }

    public Usuario(String usuario, String clave, String rol) {
        setUsuario(usuario);
        setClave(clave);
        setRol(rol);
        this.activo = true;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El usuario no puede estar vacio.");
        }
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        if (clave == null || clave.trim().isEmpty()) {
            throw new IllegalArgumentException("La clave no puede estar vacia.");
        }

        if (clave.length() < 4) {
            throw new IllegalArgumentException("La clave debe tener minimo 4 caracteres.");
        }

        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        if (rol == null || rol.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacio.");
        }

        if (rol.equalsIgnoreCase("ADMIN")
                || rol.equalsIgnoreCase("RRHH")
                || rol.equalsIgnoreCase("OPERADOR")) {
            this.rol = rol;
        } else {
            throw new IllegalArgumentException("Rol invalido. Use ADMIN, RRHH u OPERADOR.");
        }
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean validarAcceso(String usuarioIngresado, String claveIngresada) {
        return activo
                && usuario.equals(usuarioIngresado)
                && clave.equals(claveIngresada);
    }

    public String mostrarDatos() {
        String estadoTexto = activo ? "Activo" : "Inactivo";

        return "Usuario: " + usuario
                + "\nRol: " + rol
                + "\nEstado: " + estadoTexto;
    }
}