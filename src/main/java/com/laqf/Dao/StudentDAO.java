package com.laqf.Dao;

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
        Connection con = ConnectionDB.getConnectionBD(); // Nos guarda la conexion
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
            System.out.println("Ocurrio un error en la consulta " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }


    public List<Student> listStudent() {
        List<Student> students = new ArrayList<>();

        PreparedStatement ps; // Encapsula la conexion
        ResultSet rs; // Guarda la conexion
        Connection con = ConnectionDB.getConnectionBD(); // Nos guarda la conexion

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
            System.out.println("Ocurrio un error en la consulta " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion " + e.getMessage());
            }
        }

        return students;
    }

    Boolean addStudent(Student student) {
        PreparedStatement ps; // Encapsula la conexion
        Connection con = ConnectionDB.getConnectionBD(); // Nos guarda la conexion
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
            System.out.println("Ocurrió un error en la consulta " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Ocurrió un error al cerrar la conexión " + e.getMessage());
            }
        }
        return false;
    }


     Boolean updateStudent(Student student) {
         PreparedStatement ps; // Encapsula la conexion
         Connection con = ConnectionDB.getConnectionBD(); // Nos guarda la conexion
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
             System.out.println("Ocurrió un error en la consulta " + e.getMessage());
         } finally {
             try {
                 con.close();
             } catch (SQLException e) {
                 System.out.println("Ocurrió un error al cerrar la conexión " + e.getMessage());
             }
         }
         return false;
    }

    Boolean deleteStudent(Student student) {
        PreparedStatement ps; // Encapsula la conexion
        ResultSet rs; // Guarda la conexion
        Connection con = ConnectionDB.getConnectionBD(); // Nos guarda la conexion
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
            System.out.println("Ocurrio un error en la consulta " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion " + e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        StudentDAO studentdao = new StudentDAO();
//
//        List<Student> students = studentdao.listStudent();
//
//        students.forEach(System.out::println);


//        Student student1 = new Student(1);
//        System.out.println("Estudiante antes de la busqueda" + student1);
//
//        boolean encontrado = studentdao.findStudentById(student1);
//        if (encontrado) {
//            System.out.println("Estudiante encontrado " + student1);
//        } else {
//            System.out.println("No se encontro el estudiante " + student1);
//        }


//        Student student = new Student("Luis", "Como", "23132", "luiiss@gmail.com");
//
//        boolean creado = studentdao.addStudent(student);
//        if (creado) {
//            System.out.println("Estudiante creado " + student);
//        } else {
//            System.out.println("No se creo el estudiante " + student);
//        }

//        Student student1 = new Student(1);
//
//        boolean encontrado = studentdao.deleteStudent(student1);
//        if (encontrado) {
//            System.out.println("Estudiante eliminado " + student1);
//        } else {
//            System.out.println("No se pudo eliminar el estudiante " + student1);
//        }


        Student student = new Student(3, "Luis", "Florez", "22222", "luis12@gmail.com");

        boolean encontrado = studentdao.updateStudent(student);
        if (encontrado) {
            System.out.println("Estudiante actualizado " + student);
        } else {
            System.out.println("No se pudo actualizar el estudiante " + student);
        }


    }
}
