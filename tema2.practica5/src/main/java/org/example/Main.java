package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://hogwartsdatabase.cvuxaffyzi1t.us-east-1.rds.amazonaws.com:5432/HogwartsDatabase";
        String masterUser = "postgres";
        String masterPassword = "12345678";

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
    public static void menu(Scanner in){
        int option;
        do {
            showMenu();
            option = in.nextInt();
            switch (option) {
                case 1:
                    // Caso 1 vacio
                    break;
                case 2:
                    // Caso 2 vacío
                    break;
                case 3:
                    // Caso 3 vacío
                    break;
                case 4:
                    // Caso 4 vacío
                    break;
                case 5:
                    // Caso 5 vacío
                    break;
                case 6:
                    // Caso 6 vacío
                    break;
                case 7:
                    // Caso 7 vacío
                    break;
                case 8:
                    // Caso 8 vacío
                    break;
                case 9:
                    // Caso 9 vacío
                    break;
                case 10:
                    // Caso 10 vacío
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

}