/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package semana5;

/**
 *
 * @author marantonio55
 */
public class Semana5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ControlTrabajador control = new ControlTrabajador();
        
        Trabajador t1 = new Trabajador();
        t1.setCelular("987654321");
        t1.setCorreo("trabajador1@gmail.com");
        
        Trabajador t2 = new Trabajador("DNI", "CAS");
        try

        {
            t2.setCelular("986783011");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            t2.setCorreo("juan@gmail.com");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
      
        control.agregarTrabajador(t1);
        control.agregarTrabajador(t2);

        control.listarTrabajadores();
        
    }
    
}
