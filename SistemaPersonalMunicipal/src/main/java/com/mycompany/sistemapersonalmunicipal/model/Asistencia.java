package com.mycompany.sistemapersonalmunicipal.model;

public class Asistencia {

    private String fecha;
    private String horaIngreso;
    private String horaSalida;
    private boolean tardanza;

    public Asistencia() {
    }

    public Asistencia(String fecha, String horaIngreso, String horaSalida) {
        setFecha(fecha);
        setHoraIngreso(horaIngreso);
        setHoraSalida(horaSalida);
        calcularTardanza();
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La fecha no puede estar vacía."
            );
        }

        this.fecha = fecha;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        if (horaIngreso == null || horaIngreso.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La hora de ingreso no puede estar vacía."
            );
        }

        this.horaIngreso = horaIngreso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public boolean isTardanza() {
        return tardanza;
    }

    public boolean calcularTardanza() {

        if (horaIngreso == null || horaIngreso.trim().isEmpty()) {
            tardanza = false;
            return false;
        }

        tardanza = horaIngreso.compareTo("08:00") > 0;

        return tardanza;
    }

    public String mostrarDatos() {

        return "Fecha: " + fecha
                + "\nHora ingreso: " + horaIngreso
                + "\nHora salida: "
                + (horaSalida != null ? horaSalida : "Sin registrar")
                + "\nTardanza: "
                + (tardanza ? "Sí" : "No");
    }
}