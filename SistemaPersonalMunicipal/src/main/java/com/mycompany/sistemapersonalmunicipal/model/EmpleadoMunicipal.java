package com.mycompany.sistemapersonalmunicipal.model;

import com.mycompany.sistemapersonalmunicipal.model.Trabajador;

public class EmpleadoMunicipal extends Trabajador {

    private double sueldoMensual;
    private double bonificacion;

    public EmpleadoMunicipal() {
    }

    public EmpleadoMunicipal(double sueldoMensual, double bonificacion) {
        setSueldoMensual(sueldoMensual);
        setBonificacion(bonificacion);
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(double sueldoMensual) {
        if (sueldoMensual <= 0) {
            throw new IllegalArgumentException("El sueldo mensual debe ser mayor a cero.");
        }
        this.sueldoMensual = sueldoMensual;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(double bonificacion) {
        if (bonificacion < 0) {
            throw new IllegalArgumentException("La bonificacion no puede ser negativa.");
        }
        this.bonificacion = bonificacion;
    }

    @Override
    public double calcularSueldo() {
        return sueldoMensual + bonificacion;
    }

    @Override
    public String mostrarDatos() {
        return "=== EMPLEADO MUNICIPAL ===\n"
                + super.mostrarDatos()
                + "\nSueldo mensual: " + sueldoMensual
                + "\nBonificacion: " + bonificacion;
    }
}