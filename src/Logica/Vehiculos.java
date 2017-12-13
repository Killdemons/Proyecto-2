package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Vehiculos {

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

    public static void añadir(String placa, int marca, int modelo, int estilo, String transmi, String año, String precio, String estado, String foto) {
        Conexion();
        try {
            s = connection.createStatement();
            int z = s.executeUpdate("INSERT INTO vehiculo (placa,marcaid,modeloid,estiloid,transmision,año,precio,estado,foto) VALUES ('" + placa + "', '" + marca + "', '" + modelo + "', '" + estilo + "', '" + transmi + "', '" + Integer.parseInt(año) + "', '" + Integer.parseInt(precio) + "', '" + estado + "', '" + foto + "')");
            if (z == 1) {
                System.out.println("Se agregó el registro de manera exitosa");
                JOptionPane.showMessageDialog(null, "Agregado Correctamente");
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }
    
    public static void marcas() {
        Conexion();
        try {
            s = connection.createStatement();
            rs = s.executeQuery("SELECT id, nombre FROM marcas");
            while (rs.next()) {
                Interfaz.Vehiculos.ids.add(rs.getString("id"));
                Interfaz.Vehiculos.marcas.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }
    
    public static void modelos(int id) {
        Conexion();
        try {
            s = connection.createStatement();
            rs = s.executeQuery("SELECT id, nombre, id_marcas FROM modelo WHERE id_marcas = '" + id + "'");
            while (rs.next()) {
                Interfaz.Vehiculos.modelos.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }
    
    public static void estilos() {
        Conexion();
        try {
            s = connection.createStatement();
            rs = s.executeQuery("SELECT id, nombre FROM estilo");
            while (rs.next()) {
                Interfaz.Vehiculos.estilos.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }
    
    public static void ids(String marca, String modelo, String estilo){
        Conexion();
        try {
            s = connection.createStatement();
            rs = s.executeQuery("SELECT id, nombre FROM marcas WHERE nombre = '" + marca + "'");
            while (rs.next()) {
                Interfaz.Vehiculos.idmarca = Integer.parseInt(rs.getString("id"));
            }
            rs = s.executeQuery("SELECT id, nombre, id_marcas FROM modelo WHERE nombre = '" + modelo + "'");
            while (rs.next()) {
                Interfaz.Vehiculos.idmodelo = Integer.parseInt(rs.getString("id"));
            }
            rs = s.executeQuery("SELECT id, nombre FROM estilo WHERE nombre = '" + estilo + "'");
            while (rs.next()) {
                Interfaz.Vehiculos.idestilo = Integer.parseInt(rs.getString("id"));
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }
    
    public static void buscar(String placa) {
        Conexion();
        try {
            s = connection.createStatement();
            rs = s.executeQuery("SELECT placa, marcaid, modeloid, estiloid, transmision, año, precio, estado, foto FROM vehiculo WHERE placa = '" + placa + "'");
            while (rs.next()) {
                Interfaz.Vehiculos.mod.add(rs.getString("placa"));
                Interfaz.Vehiculos.mod.add(rs.getString("marcaid"));
                Interfaz.Vehiculos.mod.add(rs.getString("modeloid"));
                Interfaz.Vehiculos.mod.add(rs.getString("estiloid"));
                Interfaz.Vehiculos.mod.add(rs.getString("transmision"));
                Interfaz.Vehiculos.mod.add(rs.getString("año"));
                Interfaz.Vehiculos.mod.add(rs.getString("precio"));
                Interfaz.Vehiculos.mod.add(rs.getString("estado"));
                Interfaz.Vehiculos.mod.add(rs.getString("foto"));
            }
            rs = s.executeQuery("SELECT id, nombre FROM marcas WHERE id = '" + Interfaz.Vehiculos.mod.get(1).toString() + "'");
            while (rs.next()) {
                Interfaz.Vehiculos.mod.set(1, rs.getString("nombre"));
            }
            rs = s.executeQuery("SELECT id, nombre, id_marcas FROM modelo WHERE id = '" + Interfaz.Vehiculos.mod.get(2).toString() + "'");
            while (rs.next()) {
                Interfaz.Vehiculos.mod.set(2, rs.getString("nombre"));
            }
            rs = s.executeQuery("SELECT id, nombre FROM estilo WHERE id = '" + Interfaz.Vehiculos.mod.get(3).toString() + "'");
            while (rs.next()) {
                Interfaz.Vehiculos.mod.set(3, rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }

    public static void eliminar(String placa) {
        Conexion();
        try {
            s = connection.createStatement();
            int z = s.executeUpdate("DELETE FROM vehiculo WHERE placa = '" + placa + "'");
            if (z == 1) {
                System.out.println("Se elimino el registro de manera exitosa");
            } else {
                System.out.println("Error al eliminar el registro");
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e);
        }
    }
}
