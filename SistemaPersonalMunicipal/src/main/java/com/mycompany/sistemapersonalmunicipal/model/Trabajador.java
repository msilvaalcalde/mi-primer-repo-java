package com.mycompany.sistemapersonalmunicipal.model;
import com.mycompany.sistemapersonalmunicipal.model.Area;
import java.util.ArrayList;

public abstract class Trabajador {

    // ================= DATOS PERSONALES =================
    protected String tipoDocumento;
    protected String numeroDocumento;
    protected String nombres;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String celular;
    protected String correo;

    // ================= DATOS LABORALES =================
    protected String codigoTrabajador;
    protected String fechaIngreso;
    protected String regimenLaboral;
    protected boolean estado;

    protected Area area;
    protected Cargo cargo;
    protected Contrato contrato;
    protected ArrayList<Asistencia> asistencias;

    // ================= CONSTRUCTOR =================
    public Trabajador() {
        this.estado = true;
        this.asistencias = new ArrayList<>();
    }

    public Trabajador(
            String tipoDocumento,
            String numeroDocumento,
            String nombres,
            String codigoTrabajador,
            String regimenLaboral) {

        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setNombres(nombres);
        setCodigoTrabajador(codigoTrabajador);
        setRegimenLaboral(regimenLaboral);

        this.estado = true;
        this.asistencias = new ArrayList<>();
    }

    // ================= GETTERS Y SETTERS PERSONALES =================

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {

        if (tipoDocumento == null || tipoDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El tipo de documento no puede estar vacío."
            );
        }

        if (tipoDocumento.equalsIgnoreCase("DNI")
                || tipoDocumento.equalsIgnoreCase("CE")) {

            this.tipoDocumento = tipoDocumento;

        } else {
            throw new IllegalArgumentException(
                    "Tipo de documento inválido. Use DNI o CE."
            );
        }
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {

        if (numeroDocumento == null
                || numeroDocumento.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El número de documento no puede estar vacío."
            );
        }

        if (tipoDocumento != null
                && tipoDocumento.equalsIgnoreCase("DNI")) {

            if (!numeroDocumento.matches("\\d{8}")) {
                throw new IllegalArgumentException(
                        "El DNI debe contener exactamente 8 dígitos numéricos."
                );
            }
        }

        if (tipoDocumento != null
                && tipoDocumento.equalsIgnoreCase("CE")) {

            if (numeroDocumento.length() < 9) {
                throw new IllegalArgumentException(
                        "El CE debe tener mínimo 9 caracteres."
                );
            }
        }

        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {

        if (nombres == null || nombres.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Los nombres no pueden estar vacíos."
            );
        }

        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {

        if (apellidoPaterno == null
                || apellidoPaterno.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El apellido paterno no puede estar vacío."
            );
        }

        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {

        if (apellidoMaterno == null
                || apellidoMaterno.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El apellido materno no puede estar vacío."
            );
        }

        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {

        if (celular == null || celular.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El celular no puede estar vacío."
            );
        }

        if (!celular.matches("\\d{9}")) {
            throw new IllegalArgumentException(
                    "El celular debe contener exactamente 9 dígitos numéricos."
            );
        }

        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {

        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El correo no puede estar vacío."
            );
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException(
                    "El correo electrónico no tiene un formato válido."
            );
        }

        this.correo = correo;
    }

    // ================= GETTERS Y SETTERS LABORALES =================

    public String getCodigoTrabajador() {
        return codigoTrabajador;
    }

    public void setCodigoTrabajador(String codigoTrabajador) {

        if (codigoTrabajador == null
                || codigoTrabajador.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El código del trabajador no puede estar vacío."
            );
        }

        this.codigoTrabajador = codigoTrabajador;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {

        if (fechaIngreso == null
                || fechaIngreso.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "La fecha de ingreso no puede estar vacía."
            );
        }

        this.fechaIngreso = fechaIngreso;
    }

    public String getRegimenLaboral() {
        return regimenLaboral;
    }

    public void setRegimenLaboral(String regimenLaboral) {

        if (regimenLaboral == null
                || regimenLaboral.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El régimen laboral no puede estar vacío."
            );
        }

        if (regimenLaboral.equalsIgnoreCase("CAS")
                || regimenLaboral.equals("276")
                || regimenLaboral.equals("728")) {

            this.regimenLaboral = regimenLaboral;

        } else {
            throw new IllegalArgumentException(
                    "Régimen laboral inválido. Use CAS, 276 o 728."
            );
        }
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {

        if (area == null) {
            throw new IllegalArgumentException(
                    "El área no puede ser nula."
            );
        }

        this.area = area;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {

        if (cargo == null) {
            throw new IllegalArgumentException(
                    "El cargo no puede ser nulo."
            );
        }

        this.cargo = cargo;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {

        if (contrato == null) {
            throw new IllegalArgumentException(
                    "El contrato no puede ser nulo."
            );
        }

        this.contrato = contrato;
    }
    public ArrayList<Asistencia> getAsistencias() {
    return asistencias;
    }

    public void agregarAsistencia(Asistencia asistencia) {

    if (asistencia == null) {
        throw new IllegalArgumentException(
                "La asistencia no puede ser nula."
        );
    }

    asistencias.add(asistencia);
    }           

    // ================= MÉTODOS =================

    public int calcularTiempoServicio() {
        return 0;
    }

    public abstract double calcularSueldo();

    public String mostrarDatos() {

        String estadoTexto = estado ? "Activo" : "Inactivo";

        return "Documento: " + tipoDocumento + " - " + numeroDocumento
                + "\nNombres: " + nombres + " "
                + apellidoPaterno + " "
                + apellidoMaterno
                + "\nCelular: " + celular
                + "\nCorreo: " + correo
                + "\nCódigo trabajador: " + codigoTrabajador
                + "\nFecha ingreso: " + fechaIngreso
                + "\nRégimen laboral: " + regimenLaboral
                + "\nEstado: " + estadoTexto
                + "\nÁrea: "
                + (area != null ? area.getNombreArea() : "Sin área")
                + "\nCargo: "
                + (cargo != null ? cargo.getNombreCargo() : "Sin cargo")
                + "\nContrato: "
                + (contrato != null
                        ? contrato.getCodigoContrato()
                        : "Sin contrato")
                + "\nSueldo calculado: " + calcularSueldo();
    }
}