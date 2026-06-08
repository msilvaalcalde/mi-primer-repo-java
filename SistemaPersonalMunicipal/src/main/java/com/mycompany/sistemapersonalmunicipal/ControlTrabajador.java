package com.mycompany.sistemapersonalmunicipal;

import java.util.ArrayList;

public class ControlTrabajador {

    private ArrayList<Trabajador> listaTrabajadores;

    public ControlTrabajador() {
        listaTrabajadores = new ArrayList<>();
    }

    public void agregarTrabajador(Trabajador trabajador) {
        if (trabajador == null) {
            throw new IllegalArgumentException("El trabajador no puede ser nulo.");
        }

        listaTrabajadores.add(trabajador);
    }

    public void listarTrabajadores() {
        if (listaTrabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
        } else {
            for (Trabajador trabajador : listaTrabajadores) {
                System.out.println(trabajador.mostrarDatos());
                System.out.println("------------------------------");
            }
        }
    }

    public Trabajador buscarPorDocumento(String numeroDocumento) {
        for (Trabajador trabajador : listaTrabajadores) {
            if (trabajador.getNumeroDocumento().equals(numeroDocumento)) {
                return trabajador;
            }
        }

        return null;
    }

    public Trabajador buscarPorNombre(String nombre) {
        for (Trabajador trabajador : listaTrabajadores) {
            if (trabajador.getNombres().equalsIgnoreCase(nombre)) {
                return trabajador;
            }
        }

        return null;
    }

    public void generarReporteGeneral() {
        System.out.println("===== REPORTE GENERAL DE TRABAJADORES =====");
        System.out.println("Total de trabajadores registrados: " + listaTrabajadores.size());

        for (Trabajador trabajador : listaTrabajadores) {
            System.out.println(trabajador.getCodigoTrabajador() + " - "
                    + trabajador.getNombres() + " "
                    + trabajador.getApellidoPaterno() + " - "
                    + (trabajador.isEstado() ? "Activo" : "Inactivo"));
        }
    }

    public void generarReportePorTipo() {
        System.out.println("===== REPORTE POR TIPO DE TRABAJADOR =====");

        if (listaTrabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
        } else {
            for (Trabajador trabajador : listaTrabajadores) {

                if (trabajador instanceof EmpleadoMunicipal) {
                    EmpleadoMunicipal empleado = (EmpleadoMunicipal) trabajador;

                    System.out.println("Tipo: Empleado Municipal");
                    System.out.println("Nombre: " + empleado.getNombres());
                    System.out.println("Sueldo mensual: " + empleado.getSueldoMensual());
                    System.out.println("Bonificacion: " + empleado.getBonificacion());
                    System.out.println("Sueldo total: " + empleado.calcularSueldo());
                }

                if (trabajador instanceof ObreroMunicipal) {
                    ObreroMunicipal obrero = (ObreroMunicipal) trabajador;

                    System.out.println("Tipo: Obrero Municipal");
                    System.out.println("Nombre: " + obrero.getNombres());
                    System.out.println("Jornal diario: " + obrero.getJornalDiario());
                    System.out.println("Dias trabajados: " + obrero.getDiasTrabajados());
                    System.out.println("Sueldo total: " + obrero.calcularSueldo());
                }

                System.out.println("------------------------------");
            }
        }
    }

    public ArrayList<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }
}