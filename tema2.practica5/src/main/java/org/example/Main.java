package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {

    static HogwartsService hs = new HogwartsService();
    static Scanner in = new Scanner(System.in);
    static String url = "jdbc:postgresql://hogwartsdatabase.cvuxaffyzi1t.us-east-1.rds.amazonaws.com:5432/HogwartsDatabase";
    static String masterUser = "postgres";
    static String masterPassword = "12345678";

    public static void main(String[] args) {
        List<House> houseList = hs.getHouses(url, masterUser, masterPassword);
        List<Student> studentList = hs.getStudents(url, masterUser, masterPassword);
        List<Subject> subjectList = hs.getSubjects(url, masterUser, masterPassword);
        List<Pet> petList = hs.getPets(url, masterUser, masterPassword);
        List<StudentSubject> relations = hs.getStudentSubject(url, masterUser, masterPassword);
        menu(in, houseList, studentList, subjectList, petList, relations);
    }
    public static void showMenu(){
        System.out.print("\n    *** MENÚ ***\n" +
                "1. Lista los estudiantes agrupados por casa.\n" +
                "2. Lista de las asignaturas obligatorias.\n" +
                "3. Ver mascota de estudiante.\n" +
                "4. Lista de estudiantes sin mascotas.\n" +
                "5. Promedio de notas de un estudiante.\n" +
                "6. Número de estudiantes por casa.\n" +
                "7. Listado de estudiantes matriculados en una asignatura.\n" +
                "8. Insertar nuevo estudiante.\n" +
                "9. Modificar aula de una asignatura.\n" +
                "10. Desmatricular estudiante de una asignatura.\n" +
                "0. Salir.\n" +
                "Que quieres hacer: ");
    }
    public static void menu(Scanner in, List<House> houseList, List<Student> studentList, List<Subject> subjectList, List<Pet> petList, List<StudentSubject> relations){
        int option;
        do {
            showMenu();
            option = in.nextInt();
            switch (option) {
                case 1:
                    hs.showStudentGroupByHouse(houseList, studentList);
                    break;
                case 2:
                    hs.mandatorySubjects(subjectList).forEach(System.out::println);
                    break;
                case 3:
                    showStudentPet(studentList, petList);
                    break;
                case 4:
                    hs.studentsWithoutPet(studentList, petList).forEach(System.out::println);
                    break;
                case 5:
                    showAverageGrade(relations, studentList);
                    break;
                case 6:
                    hs.studentsAmountByHouse(studentList, houseList);
                    break;
                case 7:
                    showEnrolledStudents(relations, studentList, subjectList);
                    break;
                case 8:
                    hs.insertNewStudent(in, studentList, url, masterUser, masterUser);
                    break;
                case 9:
                    hs.alterClassroomInSubject(in, url, masterUser, masterPassword);
                    break;
                case 10:
                    hs.unenrollStudentFromSubject(subjectList, studentList, in, url, masterUser, masterPassword);
                    break;
                case 0:
                    System.out.println("HAS SALIDO DEL PROGRAMA");
                default:
                    System.out.println("SELECCIONA UNA OPCIÓN CORRECTA POR FAVOR.");
                    break;
            }
        } while (option != 0);
    }

    //GESTION DE FUNCIONES
    public static void showStudentPet(List<Student> studentList, List<Pet> petList){
        System.out.println("Nombre del estudiante: ");
        Student student = hs.findStudent(in.next(), studentList);
        Pet pet = hs.specificStudentPet(student, petList);
        System.out.println(pet.toString());
    }
    public static void showAverageGrade(List<StudentSubject> relations, List<Student> studentList){
        System.out.println("Nombre del estudiante: ");
        Student student = hs.findStudent(in.next(), studentList);
        float avgGrade = hs.averageGrades(student, relations);
        System.out.println("\nNota promedio de " + student.getName() + ": " + avgGrade);
    }
    public static void showEnrolledStudents(List<StudentSubject> relations, List<Student> studentList, List<Subject> subjectList){
        System.out.println("Nombre de la asignatura a mostrar: ");
        Subject subject = hs.findSubject(in.next(), subjectList);
        hs.enrolledStudentsInSubject(subject, studentList, relations).forEach(System.out::println);
    }

}