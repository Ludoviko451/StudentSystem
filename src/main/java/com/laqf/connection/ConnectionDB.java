package com.laqf.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionDB {

    public static Connection getConnectionBD(){

        //Objeto para guardar la conexion
        Connection connection = null;

        //Variables de conexion

        String db = "sena_db";
        String url = "jdbc:mysql://localhost:3306/" + db;
        String name= "root";
        String pass = "";

        try {
            //Sirve para comprobar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Intento conectarme ala base de datos
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (ClassNotFoundException | SQLException ex){

            JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            connection = null;
        }
        finally {
            JOptionPane.showMessageDialog(null, "Conexión Exitosa");
            return connection;
        }

    }

    public static void main(String[] args) {
        Connection c = getConnectionBD();

        if(c != null){
            System.out.println("Conexion exitosa" + c);
        }
        else{
            System.out.println("Error en la conexion" + c);
        }
    }
}











