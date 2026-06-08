package com.mycompany.sistemapersonalmunicipal;

public abstract class Persona {

    protected String tipoDocumento;
    protected String numeroDocumento;
    protected String nombres;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String celular;
    protected String correo;

    public Persona() {
    }

    public Persona(String tipoDocumento, String numeroDocumento, String nombres) 
    {
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setNombres(nombres);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        if (tipoDocumento == null || tipoDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede estar vacio.");
        }

        if (tipoDocumento.equalsIgnoreCase("DNI") || tipoDocumento.equalsIgnoreCase("CE")) {
            this.tipoDocumento = tipoDocumento;
        } else {
            throw new IllegalArgumentException("Tipo de documento invalido. Use DNI o CE.");
        }
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El numero de documento no puede estar vacio.");
        }

        if (tipoDocumento != null && tipoDocumento.equalsIgnoreCase("DNI") && numeroDocumento.length() != 8) {
            throw new IllegalArgumentException("El DNI debe tener 8 digitos.");
        }

        if (tipoDocumento != null && tipoDocumento.equalsIgnoreCase("CE") && numeroDocumento.length() < 9) {
            throw new IllegalArgumentException("El CE debe tener minimo 9 caracteres.");
        }

        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        if (nombres == null || nombres.trim().isEmpty()) {
            throw new IllegalArgumentException("Los nombres no pueden estar vacios.");
        }
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido paterno no puede estar vacio.");
        }
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        if (apellidoMaterno == null || apellidoMaterno.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido materno no puede estar vacio.");
        }
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        if (celular == null || celular.trim().isEmpty()) {
            throw new IllegalArgumentException("El celular no puede estar vacio.");
        }

        if (celular.length() != 9) {
            throw new IllegalArgumentException("El celular debe tener 9 digitos.");
        }

        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacio.");
        }

        if (!correo.contains("@")) {
            throw new IllegalArgumentException("El correo debe contener @.");
        }

        this.correo = correo;
    }

    public String mostrarDatos() {
        return "Documento: " + tipoDocumento + " - " + numeroDocumento
                + "\nNombres: " + nombres + " " + apellidoPaterno + " " + apellidoMaterno
                + "\nCelular: " + celular
                + "\nCorreo: " + correo;
    }
}