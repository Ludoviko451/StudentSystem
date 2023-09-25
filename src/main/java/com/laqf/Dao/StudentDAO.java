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

    void findStudentById() {
        Scanner entrada = new Scanner(System.in);
        PreparedStatement ps; // Encapsula la conexion
        ResultSet rs; // Guarda la conexion
        Connection con = ConnectionDB.getConnectionBD(); // Nos guarda la conexion
        System.out.println("Ingrese la id del estudiante que desea buscar");
        String id = entrada.nextLine();
        String sql = "SELECT * FROM student WHERE id_student = " + id;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Student idStudent = new Student();
                idStudent.setFirst_name(rs.getString("first_name"));
                idStudent.setLast_name(rs.getString("last_name"));
                idStudent.setPhone(rs.getString("phone"));
                idStudent.setEmail(rs.getString("email"));
                idStudent.setId_student(rs.getInt("id_student"));
                System.out.println(idStudent);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error en la consulta " + e.getMessage());
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion " + e.getMessage());
            }
        }
    }


    public List<Student> listStudent(){
        List<Student> students = new ArrayList<>();

        PreparedStatement ps; // Encapsula la conexion
        ResultSet rs; // Guarda la conexion
        Connection con = ConnectionDB.getConnectionBD(); // Nos guarda la conexion

        String sql = "SELECT * FROM student ORDER BY id_student";


        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
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
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Ocurrio un error al cerrar la conexion " + e.getMessage());
            }
        }



        return students;
    }

    void addStudent(){

    }

    void updateStudent(){

    }

    void deleteStudent(){

    }

    public static void main(String[] args) {
        StudentDAO studentdao = new StudentDAO();

        List<Student> students = studentdao.listStudent();

        // students.forEach(System.out::println);
        studentdao.findStudentById();
    }
}
