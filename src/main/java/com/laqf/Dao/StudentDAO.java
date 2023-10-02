package com.laqf.Dao;

import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.laqf.connection.ConnectionDB;
import com.laqf.domain.Student;

import java.util.ArrayList;

public class StudentDAO {

    public boolean findStudentById(Student student) {

        PreparedStatement ps; // Encapsula la conexion
        ResultSet rs; // Guarda la conexion
        ConnectionDB connectionDB = new ConnectionDB();
        Connection con = connectionDB.getConnection(); // Nos guarda la conexion
        String sql = "SELECT * FROM student WHERE id_student = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getId_student());
            rs = ps.executeQuery();

            while (rs.next()) {
                student.setFirst_name(rs.getString("first_name"));
                student.setLast_name(rs.getString("last_name"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                student.setId_student(rs.getInt("id_student"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the query" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the connection " + e.getMessage());
            }
        }
        return false;
    }


    public List<Student> listStudent() {
        List<Student> students = new ArrayList<>();

        PreparedStatement ps; // Encapsula la conexion
        ResultSet rs; // Guarda la conexion
        ConnectionDB connectionDB = new ConnectionDB();
        Connection con = connectionDB.getConnection();  // Nos guarda la conexion

        String sql = "SELECT * FROM student ORDER BY id_student";


        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setFirst_name(rs.getString("first_name"));
                student.setLast_name(rs.getString("last_name"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                student.setId_student(rs.getInt("id_student"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the query " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the connection " + e.getMessage());
            }
        }

        return students;
    }

    public Boolean addStudent(Student student) {
        PreparedStatement ps; // Encapsula la conexion
        ConnectionDB connectionDB = new ConnectionDB();
        Connection con = connectionDB.getConnection();  // Nos guarda la conexion
        String sql = "INSERT INTO student (first_name, last_name, phone, email) values (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirst_name());
            ps.setString(2, student.getLast_name());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getEmail());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // La inserción se realizó correctamente, así que devolvemos true
                return true;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the query " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the connection " + e.getMessage());
            }
        }
        return false;
    }


     public Boolean updateStudent(Student student) {
         PreparedStatement ps; // Encapsula la conexion
         ConnectionDB connectionDB = new ConnectionDB();
         Connection con = connectionDB.getConnection();  // Nos guarda la conexion
         String sql = "UPDATE student set first_name = ?, last_name = ?, phone = ?, email = ? WHERE id_student = ?";
         try {
             ps = con.prepareStatement(sql);
             ps.setInt(5, student.getId_student());
             ps.setString(1, student.getFirst_name());
             ps.setString(2, student.getLast_name());
             ps.setString(3, student.getPhone());
             ps.setString(4, student.getEmail());

             int rowsAffected = ps.executeUpdate();

             if (rowsAffected > 0) {
                 // La inserción se realizó correctamente, así que devolvemos true
                 return true;
             }
         } catch (SQLException e) {
             System.out.println("An error occurred in the query" + e.getMessage());
         } finally {
             try {
                 con.close();
             } catch (SQLException e) {
                 System.out.println("An error occurred while closing the connection" + e.getMessage());
             }
         }
         return false;
    }

    public Boolean deleteStudent(Student student) {
        PreparedStatement ps; // Encapsula la conexion
        ResultSet rs; // Guarda la conexion
        ConnectionDB connectionDB = new ConnectionDB();
        Connection con = connectionDB.getConnection();  // Nos guarda la conexion
        String sql = "DELETE FROM student WHERE id_student = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, student.getId_student());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // La inserción se realizó correctamente, así que devolvemos true
                return true;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in the query" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the connection " + e.getMessage());
            }
        }
        return false;
    }
}
