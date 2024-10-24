package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HogwartsService {

    //FUNCIONES EXTRA
    public  LocalDate parseStringToLocalDate(String dateString){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, format);
    }
    public  Student createStudent(Scanner in, List<Student> studentList){
        int id = studentList.size();
        System.out.print("Nombre: ");
        String name = in.next();
        System.out.print("Apellido: ");
        String surname = in.next();
        System.out.print("ID de la casa: ");
        int houseID = in.nextInt();
        System.out.print("Curso (año): ");
        int year = in.nextInt();
        System.out.print("Fecha nacimiento (yyyy-MM-dd): ");
        LocalDate birthDate = parseStringToLocalDate(in.next());

        return new Student(id, name, surname, houseID, year, birthDate);
    }
    public  Student findStudent(String name, List<Student> studentList){
        Student student = new Student();
        for (Student s : studentList){
            if (s.getName().equalsIgnoreCase(name)) student = s;
        }
        return student;
    }

    //FUNCIONES PARA LEER DE LA BBDD
    public  List<Student> getStudents(String url, String masterName, String masterPasswd){
        List<Student> studentList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, masterName, masterPasswd)) {
            String SQLquery = "SELECT id_estudiante, nombre, apellido, id_casa, año_curso, fecha_nacimiento FROM Estudiante";
            PreparedStatement query = connection.prepareStatement(SQLquery);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                int id_student = result.getInt("id_estudiante");
                String name = result.getString("nombre");
                String surname = result.getString("apellido");
                int id_house = result.getInt("id_casa");
                int courseYear = result.getInt("año_curso");
                LocalDate birthDate = parseStringToLocalDate(result.getString("fecha_nacimiento"));
                Student student = new Student(id_student, name, surname, id_house, courseYear, birthDate);
                studentList.add(student);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA SIGUIENTE FUNCIÓN: getStudents()");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
        return studentList;
    }
    public  List<House> getHouses(String url, String masterName, String masterPasswd){
        List<House> housesList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, masterName, masterPasswd)) {
            String SQLquery = "SELECT id_casa, nombre_casa, fundador, jefe_casa, fantasma FROM Casa";
            PreparedStatement query = connection.prepareStatement(SQLquery);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                House house = new House(
                        result.getInt("id_casa"),
                        result.getString("nombre_casa"),
                        result.getString("fundador"),
                        result.getString("jefe_casa"),
                        result.getString("fantasma"));
                housesList.add(house);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA SIGUIENTE FUNCIÓN: getHouses()");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
        return housesList;
    }
    public  List<Pet> getPets(String url, String masterName, String masterPasswd){
        List<Pet> petsList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, masterName, masterPasswd)) {
            String SQLquery = "SELECT id_mascota, nombre_mascota, especie, id_estudiante FROM Mascota";
            PreparedStatement query = connection.prepareStatement(SQLquery);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                Pet pet = new Pet(
                        result.getInt("id_mascota"),
                        result.getString("nombre_mascota"),
                        result.getString("especie"),
                        result.getInt("id_estudiante"));
                petsList.add(pet);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA SIGUIENTE FUNCIÓN: getPets()");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
        return petsList;
    }
    public  List<Subject> getSubjects(String url, String masterName, String masterPasswd){
        List<Subject> subjectList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, masterName, masterPasswd)) {
            String SQLquery = "SELECT id_asignatura, nombre_asignatura, aula, obligatoria FROM Asignatura";
            PreparedStatement query = connection.prepareStatement(SQLquery);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                Subject subject = new Subject(
                        result.getInt("id_asignatura"),
                        result.getString("nombre_asignatura"),
                        result.getString("aula"),
                        result.getBoolean("obligatoria"));
                subjectList.add(subject);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA SIGUIENTE FUNCIÓN: getSubjects()");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
        return subjectList;
    }
    public List<StudentSubject> getStudentSubject(String url, String masterName, String masterPasswd){
        List<StudentSubject> studentSubject = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, masterName, masterPasswd)) {
            String SQLquery = "SELECT id_estudiante, id_asignatura, calificacion FROM Estudiante_Asignatura";
            PreparedStatement query = connection.prepareStatement(SQLquery);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                StudentSubject relation= new StudentSubject(
                        result.getInt("id_estudiante"),
                        result.getInt("id_asignatura"),
                        result.getFloat("calificacion"));
                studentSubject.add(relation);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA SIGUIENTE FUNCIÓN: getStudentSubject()");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
        return studentSubject;
    }

    //FUNCONES PRINCIPALES
    public void showStudentGroupByHouse(List<House> houseList, List<Student> studentList){
        for (House h : houseList){
            System.out.println("    - " + h.getName() + ": ");
            for (Student s : studentList){
                if (s.getId_house() == h.getId()){
                    System.out.println(s.toString());
                }
            }
        }
    }
    public List<Subject> mandatorySubjects(List<Subject> subjects){
        List<Subject> mandatorySubjects = new ArrayList<>();
        for (Subject s : subjects) {
            if (s.isMandatory()) mandatorySubjects.add(s);
        }
        if (mandatorySubjects.isEmpty()) System.out.println("\nNo hay ninguna asignatura obligatoria, el listado se devolvera vacío.");
        return mandatorySubjects;
    }
    public Pet specificStudentPet(Student student, List<Pet> petList){
        Pet pet = new Pet();
        for (Pet p : petList){
            if (p.getId_student() == student.getId()) pet = p;
        }
        return pet;
    }
    public List<Student> studentsWithoutPet(List<Student> studentList, List<Pet> petList){
        List<Student> studentsWithoutPet = new ArrayList<>();
        for (Student s : studentList){
            int counter = 0;
            for (Pet p : petList) {
                if (p.getId_student() == s.getId()) counter++;
            }
            if (counter == 0) studentsWithoutPet.add(s);
        }
        return studentsWithoutPet;
    }
    //no funcions
    public float averageGrades(Student student, List<StudentSubject> relations) {
        float totalGrade = 0;
        int subjectCounter = 0;
        for (StudentSubject r : relations) {
            if (r.getId_student() == student.getId()){
                totalGrade += r.getCalification();
                subjectCounter++;
            }
        }
        if (subjectCounter == 0) {
            return 0;
        }
        return (totalGrade / subjectCounter);
    }
    public void studentsAmountByHouse(List<Student> studentList, List<House> houseList){
        for (House h : houseList){
            int counter = 0;
            for (Student s : studentList){
                if (s.getId_house() == h.getId()) counter++;
            }
            System.out.println("    - " + h.getName().toUpperCase() + ": " + counter);
        }
    }
    //estudiantes matriculados en una asignatura especifica
    /*public static List<Student> enrolledStudentsInSubject(Subject subject, List<Student> studentList, List<Subject> subjectList, List<StudentSubject> relation){

    }*/
    public void insertNewStudent(Scanner in, List<Student> studentList,String url, String masterName, String masterPasswd){
        try (Connection connection = DriverManager.getConnection(url, masterName, masterPasswd)) {
            String SQLquery = "INSERT INTO Estudiante (nombre, apellido, id_casa, año_curso, fecha_nacimiento) VALUES" +
                    "(?, ?, ?, ?, ?)";
            PreparedStatement query = connection.prepareStatement(SQLquery);
            System.out.println("    - Dime los datos del estudiante a insertar: ");
            Student student = createStudent(in, studentList);
            query.setString(1, student.getName());
            query.setString(2, student.getSurname());
            query.setInt(3, student.getId_house());
            query.setInt(4, student.getCourseYear());
            query.setDate(5, Date.valueOf(student.getBirthDate()));

        System.out.println("ESTUDIANTE INSERTADO CON EXITO;");
        } catch (SQLException ex) {
            System.out.println("ERROR EN LA SIGUIENTE FUNCIÓN: getStudentSubject()");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
    public void alterClassroomInSubject(Scanner in, String url, String masterName, String masterPassword){
        try (Connection connection = DriverManager.getConnection(url, masterName, masterPassword)){
            String sql = "UPDATE Asignaturas SET aula = ? WHERE name = ?";
            PreparedStatement query = connection.prepareStatement(sql);
            System.out.println("Dime el nuevo número del aula: ");
            query.setInt(1, in.nextInt());
            System.out.println("Dime el nombre de la asignatura a la que le vas a cambiar el aula: ");
            query.setString(2, in.next());
        } catch (SQLException e) {
            System.out.println("ERROR EN LA SIGUIENTE FUNCIÓN: alterClassroomInSubject()");
            throw new RuntimeException(e);
        }
    }


}
