package com.laqf.presentation;

import com.laqf.Dao.StudentDAO;
import com.laqf.connection.ConnectionDB;
import com.laqf.domain.Student;
import java.util.List;
import java.util.Scanner;

public class SystemStudentApp {
    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while(!exit){
            showMenu();

            exit = executeOption(scanner, studentDAO);

        }
    }

    private static void showMenu(){
        System.out.println("""
            **** Student Menu ****
            [1] Show Students
            [2] Find Student by id
            [3] Add Student
            [4] Update Student
            [5] Delete Student
            [6] Exit
            Choose a option
            """);
    }

    private static boolean executeOption(Scanner scanner, StudentDAO studentdao){
        int option = Integer.parseInt(scanner.nextLine());
        boolean exit = false;

        switch (option){
            case 1 -> {
                System.out.println("Student List");
                List<Student> students = studentdao.listStudent();
                students.forEach(System.out::println);
            }

            case 2 -> {
                System.out.println("Enter the ID of the student you want to find");

                int id = Integer.parseInt(scanner.nextLine());
                Student student1 = new Student(id);

                boolean encontrado = studentdao.findStudentById(student1);
                if (encontrado) {
                    System.out.println("Student found " + student1);
                } else {
                    System.out.println("Student not found " + student1);
                }
            }

            case 3 -> {
                addStudent(scanner, studentdao);
            }

            case 4 -> {
                updateStudent(scanner, studentdao);
            }

            case 5 -> {
                System.out.println("Delete student");
                System.out.println("Enter an id");
                int id = Integer.parseInt(scanner.nextLine());
                Student student1 = new Student(id);

                boolean encontrado = studentdao.deleteStudent(student1);
                if (encontrado) {
                    System.out.println("Student deleted " + student1);
                } else {
                    System.out.println("Student could not be deleted" + student1);
                }
            }

            case 6 ->{
                System.out.println("Hasta la vista baby...");
                exit = true;

            }
        }
        return exit;
    }

    private static void addStudent(Scanner scanner, StudentDAO studentdao)
    {
        System.out.println("Add a new student");

        System.out.println("First name");
        String firstName = scanner.nextLine();
        System.out.println("Last name");
        String lastName = scanner.nextLine();
        System.out.println("Phone");
        String phone = scanner.nextLine();
        System.out.println("Email");
        String email = scanner.nextLine();

        Student student = new Student(firstName, lastName, phone, email);

        boolean creado = studentdao.addStudent(student);
        if (creado) {
            System.out.println("Student creaed " + student);
        } else {
            System.out.println("Student could not be created " + student);
        }
    }

    private static void updateStudent(Scanner scanner, StudentDAO studentdao) {
        System.out.println("Update a student");
        System.out.println("ID");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("First name");
        String firstName = scanner.nextLine();
        System.out.println("Last name");
        String lastName = scanner.nextLine();
        System.out.println("Phone");
        String phone = scanner.nextLine();
        System.out.println("Email");
        String email = scanner.nextLine();

        Student student = new Student(id, firstName, lastName, phone, email);

        boolean creado = studentdao.updateStudent(student);
        if (creado) {
            System.out.println("Updated student " + student);
        } else {
            System.out.println("Student could not be updated" + student);
        }
    }
}