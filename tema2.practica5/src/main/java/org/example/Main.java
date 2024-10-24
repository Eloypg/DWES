package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://hogwartsdatabase.cvuxaffyzi1t.us-east-1.rds.amazonaws.com:5432/HogwartsDatabase";
        String masterUser = "postgres";
        String masterPassword = "12345678";

        HogwartsService hs = new HogwartsService();

        List<StudentSubject> relationsList = hs.getStudentSubject(url, masterUser, masterPassword);
        List<Student> studentList = hs.getStudents(url, masterUser, masterPassword);
        List<House> houseList = hs.getHouses(url, masterUser, masterPassword);
        //HogwartsService.studentsAmountByHouse(studentList, houseList);
        houseList.forEach(System.out::println);
        try (Connection conection = DriverManager.getConnection(url, masterUser, masterPassword)) {
            System.out.println("TE HAS CONECTADO A LA BASE DE DATOS");
        } catch (SQLException ex) {
            System.out.println("\nNO SE HA PODIDO CONECTAR A LA BASE DE DATOS");
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}