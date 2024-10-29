package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private Scanner in = new Scanner(System.in);
    private String url = "jdbc:postgresql://hogwartsdatabase.cvuxaffyzi1t.us-east-1.rds.amazonaws.com:5432/HogwartsDatabase";
    private String masterUser = "postgres";
    private String masterPassword = "12345678";

    public ResultSet connection(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(url, masterUser, masterPassword);
        PreparedStatement query = connection.prepareStatement(sql);
        return query.executeQuery();
    }
    public void DDLquery(String sql) throws SQLException {
        ResultSet resultSet = connection(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnAmount = metaData.getColumnCount();

        while (resultSet.next()){
            for (int i = 1; i <= columnAmount; i++) {
                System.out.println(resultSet.getString(i) + "\t");
            }
            System.out.println();
        }
    }
    public void DMLquery(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(url, masterUser, masterPassword);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        System.out.println("MODIFICACIÓN REALIZADA CORRECTAMENTE");
    }

    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        main.menu();
    }
    public void showMenu() {
            System.out.println("    -*-*- MENÚ -*-*-");
        System.out.println("1) Consulta de estudiantes y sus casas.");
        System.out.println("2) Consulta de estudiantes y sus mascotas (incluye estudiantes sin mascota).");
        System.out.println("3) Consulta de estudiantes y sus mascotas (solo estudiantes con mascota).");
        System.out.println("4) Consulta de estudiantes sin mascotas.");
        System.out.println("5) Notas promedio por estudiante.");
        System.out.println("6) Número de estudiantes en quinto año por casa.");
        System.out.println("7) Consulta de las mejores calificaciones en una asignatura específica (Defensa Contra las Artes Oscuras).");
        System.out.println("8) Promedio de calificaciones en una asignatura para una casa.");
        System.out.println("9) Actualización de calificaciones (incremento del 10% para alumnos del último año).");
        System.out.println("10) Desmatriculación de estudiantes con calificaciones inferiores a 5 en asignaturas optativas.");
        System.out.println("11) Salir.");
    }
    public void menu() throws SQLException {
        int option;
        do {
            showMenu();
            System.out.print("Que quieres hacer? ");
            option = in.nextInt();
            switch (option) {
                case 1 -> studentsWithHouse();
                case 2 -> studentsAndPets();
                case 3 -> studentsWithPet();
                case 4 -> studentsWithoutPet();
                case 5 -> studentAVGgrades();
                case 6 -> studentsIn5Year();
                case 7 -> bestGradesInSubject();
                case 8 -> avgGradesInSubjectForHouse();
                case 9 -> updateGrades();
                case 10 -> unenrollStudents();
                case 11 -> System.out.println("HAS SALIDO DEL PROGRAMA.");
                default -> System.out.println("SELECCIONA UNA OPCIÓN VÁLLIDA.");
            }
        } while (option != 11);
    }
    //MANAGE FUNCTIONS
    public void studentsWithHouse() throws SQLException {
        String sql = "SELECT e.nombre, e.apellido, casa.nombre_casa FROM estudiante e INNER JOIN casa c ON e.id_casa = c.id_casa";
        DDLquery(sql);
    }
    public void studentsAndPets() throws SQLException {
        String sql = "SELECT e.nombre, e.apellido, m.nombre_mascota FROM estudiante e LEFT JOIN mascota m ON e.id_estudiante = m.id_estudiante";
        DDLquery(sql);
    }
    public void studentsWithPet() throws SQLException {
        String sql = "SELECT e.nombre, e.apellido, m.nombre_mascota FROM estudiante e INNER JOIN mascota m ON e.id_estudiante = m.id_estudiante";
        DDLquery(sql);
    }
    public void studentsWithoutPet() throws SQLException {
        String sql = "SELECT e.nombre, e.apellido FROM estudiante e LEFT JOIN mascota m ON e.id_estudiante = m.id_estudiante WHERE m.id_estudiante ISNULL";
        DDLquery(sql);
    }
    public void studentAVGgrades() throws SQLException {
        String sql = "SELECT e.nombre, e.apellido, ROUND(AVG(ea.calificacion), 2) as AVG_Nota" +
                "FROM estudiante_asignatura ea" +
                "JOIN estudiante e" +
                "ON ea.id_estudiante = e.id_estudiante" +
                "GROUP BY e.nombre, e.apellido";
        DDLquery(sql);
    }
    public void studentsIn5Year() throws SQLException {
        String sql = "SELECT e.año_curso, c.nombre_casa, COUNT(e.id_estudiante) as Estudiantes" +
                "FROM estudiante e" +
                "JOIN casa c" +
                "ON e.id_casa = c.id_casa" +
                "GROUP BY e.año_curso, c.nombre_casa" +
                "HAVING e.año_curso = 5";
        DDLquery(sql);
    }
    public void bestGradesInSubject() throws SQLException {
        String subject = "Pociones"; //Podriamos poner que se introduciese, pero para asegurarme que no da erro la pongo yo
        String sql = "SELECT e.nombre, e.apellido, ea.calificacion" +
                "FROM estudiante_asignatura ea" +
                "JOIN estudiante e" +
                "ON ea.id_estudiante = e.id_estudiante" +
                "JOIN asignatura a" +
                "ON ea.id_asignatura = a.id_asignatura" +
                "WHERE a.nombre_asignatura = '" + subject + "'" +
                "ORDER BY ea.calificacion DESC" +
                "LIMIT 1";
        DDLquery(sql);
    }
    public void avgGradesInSubjectForHouse() throws SQLException {
        String subject = "Pociones";
        String sql = "SELECT c.nombre_casa, ROUND(AVG(ea.calificacion), 2) as AVG_Calificacion" +
                "FROM estudiante_asignatura ea" +
                "JOIN estudiante e" +
                "ON ea.id_estudiante = e.id_estudiante" +
                "JOIN casa c" +
                "ON e.id_casa = c.id_casa" +
                "JOIN asignatura a" +
                "ON ea.id_asignatura = a.id_asignatura" +
                "WHERE a.nombre_asignatura = '" + subject + "'" +
                "GROUP BY c.nombre_casa";
        DDLquery(sql);
    }
    public void updateGrades() throws SQLException {
        String sql = "UPDATE estudiante_asignatura" +
                "SET calificacion = LEAST(calificacion * 1.10, 10)" +
                "WHERE id_estudiante" +
                "IN (" +
                "SELECT id_estudiante" +
                "FROM estudiante" +
                "WHERE año_curso" +
                ");";
        DMLquery(sql);
    }
    public void unenrollStudents() throws  SQLException {
        String sql = "DELETE FROM estudiante" +
                "WHERE id_estudiante" +
                "IN (" +
                "SELECT e.id_estudiante" +
                "FROM estudiante e" +
                "JOIN estudiante_asignatura ea" +
                "ON e.id_estudiante = ea.id_estudiante" +
                "JOIN asignatura a" +
                "ON ea.id_asignatura = a.id_asignatura" +
                "WHERE ea.calificacion < 5" +
                "AND a.obligatoria = false" +
                ");";
        DMLquery(sql);
    }
}