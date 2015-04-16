/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author vrebo
 */
public class Mensaje {
    private int id;
    private String mensaje;

    public Mensaje(int id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public static String mensaje(String mensaje){
        return new StringBuilder(mensaje).reverse().toString();
    }
}
