package com.mycompany.sistemapersonalmunicipal;

public abstract class Trabajador extends Persona {

    protected String codigoTrabajador;
    protected String fechaIngreso;
    protected String regimenLaboral;
    protected boolean estado;
    protected Area area;
    protected Cargo cargo;
    protected Contrato contrato;

    public Trabajador() {
        this.estado = true;
    }

    public Trabajador(String tipoDocumento, String numeroDocumento, String nombres,
            String codigoTrabajador, String regimenLaboral)
    {
        super(tipoDocumento, numeroDocumento, nombres);
        setCodigoTrabajador(codigoTrabajador);
        setRegimenLaboral(regimenLaboral);
        this.estado = true;
    }

    public String getCodigoTrabajador() {
        return codigoTrabajador;
    }

    public void setCodigoTrabajador(String codigoTrabajador) {
        if (codigoTrabajador == null || codigoTrabajador.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo del trabajador no puede estar vacio.");
        }
        this.codigoTrabajador = codigoTrabajador;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        if (fechaIngreso == null || fechaIngreso.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de ingreso no puede estar vacia.");
        }
        this.fechaIngreso = fechaIngreso;
    }

    public String getRegimenLaboral() {
        return regimenLaboral;
    }

    public void setRegimenLaboral(String regimenLaboral) {
        if (regimenLaboral == null || regimenLaboral.trim().isEmpty()) {
            throw new IllegalArgumentException("El regimen laboral no puede estar vacio.");
        }

        if (regimenLaboral.equalsIgnoreCase("CAS")
                || regimenLaboral.equals("276")
                || regimenLaboral.equals("728")) {
            this.regimenLaboral = regimenLaboral;
        } else {
            throw new IllegalArgumentException("Regimen laboral invalido. Use CAS, 276 o 728.");
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
            throw new IllegalArgumentException("El area no puede ser nula.");
        }
        this.area = area;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        if (cargo == null) {
            throw new IllegalArgumentException("El cargo no puede ser nulo.");
        }
        this.cargo = cargo;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        if (contrato == null) {
            throw new IllegalArgumentException("El contrato no puede ser nulo.");
        }
        this.contrato = contrato;
    }

    public int calcularTiempoServicio() {
        return 0;
    }

    public abstract double calcularSueldo();

    @Override
    public String mostrarDatos() {
        String estadoTexto = estado ? "Activo" : "Inactivo";

        return super.mostrarDatos()
                + "\nCodigo trabajador: " + codigoTrabajador
                + "\nFecha ingreso: " + fechaIngreso
                + "\nRegimen laboral: " + regimenLaboral
                + "\nEstado: " + estadoTexto
                + "\nArea: " + (area != null ? area.getNombreArea() : "Sin area")
                + "\nCargo: " + (cargo != null ? cargo.getNombreCargo() : "Sin cargo")
                + "\nContrato: " + (contrato != null ? contrato.getCodigoContrato() : "Sin contrato")
                + "\nSueldo calculado: " + calcularSueldo();
    }
}