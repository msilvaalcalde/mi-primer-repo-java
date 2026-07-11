package com.mycompany.sistemapersonalmunicipal.model;

import com.mycompany.sistemapersonalmunicipal.model.Trabajador;

public class ObreroMunicipal extends Trabajador {

    private double jornalDiario;
    private int diasTrabajados;

    public ObreroMunicipal() {
    }

    public ObreroMunicipal(double jornalDiario, int diasTrabajados) {
        setJornalDiario(jornalDiario);
        setDiasTrabajados(diasTrabajados);
    }

    public double getJornalDiario() {
        return jornalDiario;
    }

    public void setJornalDiario(double jornalDiario) {
        if (jornalDiario <= 0) {
            throw new IllegalArgumentException("El jornal diario debe ser mayor a cero.");
        }
        this.jornalDiario = jornalDiario;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        if (diasTrabajados < 0) {
            throw new IllegalArgumentException("Los dias trabajados no pueden ser negativos.");
        }
        this.diasTrabajados = diasTrabajados;
    }

    @Override
    public double calcularSueldo() {
        return jornalDiario * diasTrabajados;
    }

    @Override
    public String mostrarDatos() {
        return "=== OBRERO MUNICIPAL ===\n"
                + super.mostrarDatos()
                + "\nJornal diario: " + jornalDiario
                + "\nDias trabajados: " + diasTrabajados;
    }
}