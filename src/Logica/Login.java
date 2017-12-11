package Logica;

import Interfaz.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
    
    public static Connection connection = null;
    public static ResultSet rs = null;
    public static Statement s = null;
    
    public static void Conexion() {
        if (connection != null) {
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/proyect";
        String password = "bryan2748245";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, "postgres", password);
            if (connection != null) {
                System.out.println("Connecting to database...");
            }
        } catch (Exception e) {
            System.out.println("Problem when connecting to the database: "+e);
        }
    }
    
    public boolean login(String usuario, String contraseña) {
        Conexion();
        try {
            Encriptacion en = new Encriptacion();
            String password = en.Encriptar(contraseña);
            String tipo;
            s = connection.createStatement();
            rs = s.executeQuery("SELECT cedula,nombre,telefono,direccion,foto,contraseña,tipo FROM usu_cliente WHERE nombre  = '" + usuario + "' and contraseña ='" + password + "'");
            while (rs.next()) {
                System.out.println("Usuario Existe");
                tipo = rs.getString("tipo");
                if (tipo.equals("admin")) {
                    Admin ven = new Admin();
                    ven.setVisible(true);
                    ven.setEnabled(true);
                    ven.setLocationRelativeTo(null);
                    return true;
                } else if (tipo.equals("user")) {
                    Tienda ven = new Tienda();
                    ven.setVisible(true);
                    ven.setEnabled(true);
                    ven.setLocationRelativeTo(null);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error de conexión" + e);
            return false;
        }
        return false;
    }
}
