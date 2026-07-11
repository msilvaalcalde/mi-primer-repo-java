package com.mycompany.sistemapersonalmunicipal.model;

public class Contrato {

    private String codigoContrato;
    private String fechaInicio;
    private String fechaFin;
    private boolean estadoContrato;

    public Contrato() {
        this.estadoContrato = true;
    }

    public Contrato(String codigoContrato, String fechaInicio, String fechaFin) {
        setCodigoContrato(codigoContrato);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        this.estadoContrato = true;
    }

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(String codigoContrato) {
        if (codigoContrato == null || codigoContrato.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del contrato no puede estar vacio.");
        }
        this.codigoContrato = codigoContrato;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        if (fechaInicio == null || fechaInicio.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de inicio no puede estar vacia.");
        }
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        if (fechaFin == null || fechaFin.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de fin no puede estar vacia.");
        }
        this.fechaFin = fechaFin;
    }

    public boolean isEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(boolean estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    public boolean validarVigencia() {
        return estadoContrato;
    }

    public String mostrarDatos() {
        String estadoTexto = estadoContrato ? "Vigente" : "No vigente";

        return "Codigo contrato: " + codigoContrato
                + "\nFecha inicio: " + fechaInicio
                + "\nFecha fin: " + fechaFin
                + "\nEstado contrato: " + estadoTexto;
    }
}