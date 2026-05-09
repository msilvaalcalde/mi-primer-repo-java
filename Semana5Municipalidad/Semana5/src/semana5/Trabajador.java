/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana5;

/**
 *
 * @author marantonio55
 */
public class Trabajador {
    
    
    private static int ultimoCorrelativo = 0;
    private String codigoInterno;
    private String tipoDocumento;
    private String regimenLaboral;
    private String celular;
    private String correo;

    public Trabajador() {

        this.codigoInterno = generarCodigoInterno();

    }

    public Trabajador(String tipoDocumento, String regimenLaboral) {
        this.codigoInterno = generarCodigoInterno();
        this.tipoDocumento = tipoDocumento;
        this.regimenLaboral = regimenLaboral;
    }
    
    public void setCelular(String celular) {
    if(celular.length() == 9)
    {
        this.celular = celular;
    }
    else
    {
        throw new IllegalArgumentException("Celular invalido");
    }

    }

    public void setCorreo(String correo) {
        if(correo.contains("@"))
        {
            this.correo = correo;
        }
        else
        {
            throw new IllegalArgumentException("Correo invalido");
        }
    }
    
    
    
    
    public static String generarCodigoInterno() {
        ultimoCorrelativo++;
        return "T" + ultimoCorrelativo;
    }

    public void mostrarDatos() {
        System.out.println("Codigo: " + codigoInterno);
        System.out.println("Tipo Documento: " + tipoDocumento);
        System.out.println("Regimen Laboral: " + regimenLaboral);
        System.out.println("Celular: " + celular);
        System.out.println("Correo: " + correo);
    }
}
