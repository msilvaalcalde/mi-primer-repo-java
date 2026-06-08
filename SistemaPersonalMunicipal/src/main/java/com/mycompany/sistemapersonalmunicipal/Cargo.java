package com.mycompany.sistemapersonalmunicipal;

public class Cargo {

    private String codigoCargo;
    private String nombreCargo;
    private String descripcion;

    public Cargo() {
    }

    public Cargo(String codigoCargo, String nombreCargo) {
        setCodigoCargo(codigoCargo);
        setNombreCargo(nombreCargo);
    }

    public String getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(String codigoCargo) {
        if (codigoCargo == null || codigoCargo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del cargo no puede estar vacio.");
        }
        this.codigoCargo = codigoCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        if (nombreCargo == null || nombreCargo.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cargo no puede estar vacio.");
        }
        this.nombreCargo = nombreCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia.");
        }
        this.descripcion = descripcion;
    }

    public String mostrarDatos() {
        return "Codigo cargo: " + codigoCargo
                + "\nNombre cargo: " + nombreCargo
                + "\nDescripcion: " + descripcion;
    }
}