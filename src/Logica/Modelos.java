package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Modelos {
    
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
            System.out.println("Problem when connecting to the database");
        }
    }
    
    public static void añadir(int id, String nombre, int marca) {
        Conexion();
        try {
            s = connection.createStatement();
            int z = s.executeUpdate("INSERT INTO modelo (id, nombre, id_marcas) VALUES ('" + id + "', '" + nombre + "', '" + marca + "')");
            if (z == 1) {
                System.out.println("Se agregó el registro de manera exitosa");
                JOptionPane.showMessageDialog(null, "Agregado Correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }
}
