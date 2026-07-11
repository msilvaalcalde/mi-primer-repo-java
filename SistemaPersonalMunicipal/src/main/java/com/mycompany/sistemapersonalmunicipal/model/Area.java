package com.mycompany.sistemapersonalmunicipal.model;

public class Area {

    private String codigoArea;
    private String nombreArea;

    public Area() {
    }

    public Area(String codigoArea, String nombreArea) {
        setCodigoArea(codigoArea);
        setNombreArea(nombreArea);
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        if (codigoArea == null || codigoArea.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del area no puede estar vacio.");
        }
        this.codigoArea = codigoArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        if (nombreArea == null || nombreArea.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del area no puede estar vacio.");
        }
        this.nombreArea = nombreArea;
    }

    public String mostrarDatos() {
        return "Codigo area: " + codigoArea
                + "\nNombre area: " + nombreArea;
    }
}