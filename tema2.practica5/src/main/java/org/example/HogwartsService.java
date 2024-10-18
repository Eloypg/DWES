package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class HogwartsService {
    public static List<Student> getStudents(){
        List<Student> studentList = new ArrayList<>();

        try (Connection conection = DriverManager.getConnection())
        String SQLquery = "SELECT id_estudiante, nombre, apellido, id_casa, año_curso, fecha_nacimiento FROM Estudiante;";
        PreparedStatement query = conec

        return studentList;
    }

    public static void showStudentGroupByHouse(House house){

    }
}
