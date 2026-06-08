package com.mycompany.sistemapersonalmunicipal;

public class Asistencia {

    private String fecha;
    private String horaIngreso;
    private String horaSalida;
    private boolean tardanza;

    public Asistencia() {
    }

    public Asistencia(String fecha, String horaIngreso) {
        setFecha(fecha);
        setHoraIngreso(horaIngreso);
        this.tardanza = calcularTardanza();
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacia.");
        }
        this.fecha = fecha;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        if (horaIngreso == null || horaIngreso.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de ingreso no puede estar vacia.");
        }
        this.horaIngreso = horaIngreso;
        this.tardanza = calcularTardanza();
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        if (horaSalida == null || horaSalida.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de salida no puede estar vacia.");
        }
        this.horaSalida = horaSalida;
    }

    public boolean isTardanza() {
        return tardanza;
    }

    public void setTardanza(boolean tardanza) {
        this.tardanza = tardanza;
    }

    public boolean calcularTardanza() {
        if (horaIngreso == null) {
            return false;
        }

        return horaIngreso.compareTo("08:00") > 0;
    }

    public String mostrarDatos() {
        String tardanzaTexto = tardanza ? "Si" : "No";

        return "Fecha: " + fecha
                + "\nHora ingreso: " + horaIngreso
                + "\nHora salida: " + horaSalida
                + "\nTardanza: " + tardanzaTexto;
    }
}