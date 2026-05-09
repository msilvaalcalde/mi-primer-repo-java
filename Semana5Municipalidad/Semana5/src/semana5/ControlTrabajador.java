/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana5;

import java.util.ArrayList;

/**
 *
 * @author marantonio55
 */
public class ControlTrabajador {
 
    private ArrayList<Trabajador> listaTrabajadores;
    public ControlTrabajador() {
        listaTrabajadores = new ArrayList<>();
    }

    public void agregarTrabajador(Trabajador trabajador) {
        listaTrabajadores.add(trabajador);
    }

    public void listarTrabajadores() {
        for (Trabajador trabajador : listaTrabajadores) {
            trabajador.mostrarDatos();
            System.out.println("--------------------");
        }
    }
    
}
