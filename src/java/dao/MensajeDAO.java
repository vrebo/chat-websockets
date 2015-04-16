/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Mensaje;
import static modelos.Mensaje.mensaje;

/**
 *
 * @author vrebo
 */
public class MensajeDAO {

    public static final String ID;
    public static final String MENSAJE;
    private static final MensajeDAO dao;

    static {
        System.out.println("MensajeDAO creado");
        ID = "id";
        MENSAJE = "mensaje";
        dao = new MensajeDAO("root", "");
        Logger.getLogger(MensajeDAO.class.getName()).setLevel(Level.SEVERE);
        Logger.getLogger(MensajeDAO.class.getName()).addHandler(new ConsoleHandler());
    }

    private String usuario;
    private String password;

    public MensajeDAO(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    private Connection getConexion() {
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(url, usuario, password);
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void persisteMensaje(Mensaje mensaje) {
            Connection con = dao.getConexion();
        try {
            PreparedStatement ps = con.prepareStatement(String.format("INSERT INTO mensajes (%s, %s) values (?,?)", ID, MENSAJE));
            ps.setInt(1, mensaje.getId());
            ps.setString(2, mensaje.getMensaje());
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<Mensaje> consultaTodos(){
        List<Mensaje> mensajes = new ArrayList<>();
            Connection con = dao.getConexion();
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM mensajes;");
            while(rs.next()){
                int id = rs.getInt(ID);
                String mensaje = rs.getString(MENSAJE);
                mensajes.add(new Mensaje(id,mensaje));
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(MensajeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensajes;
    }
}
