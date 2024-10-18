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

    public static void showStudentGroupByHouse(House house){

    }
}
