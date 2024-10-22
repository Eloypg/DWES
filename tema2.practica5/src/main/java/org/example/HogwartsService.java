package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HogwartsService {

    public static LocalDate parseStringToLocalDate(String dateString){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, format);
    }

    public static List<Student> getStudents(String url, String masterName, String masterPasswd){
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
    public static List<House> getHouses(String url, String masterName, String masterPasswd){
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
    public static List<Pet> getPets(String url, String masterName, String masterPasswd){
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
    public static List<Subject> getSubjects(String url, String masterName, String masterPasswd){
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



    public static void showStudentGroupByHouse(List<House> houseList, List<Student> studentList){
        for (House h : houseList){
            System.out.println("    - " + h.getName() + ": ");
            for (Student s : studentList){
                if (s.getId_house() == h.getId()){
                    System.out.println(s.toString());
                }
            }
        }
    }
}
