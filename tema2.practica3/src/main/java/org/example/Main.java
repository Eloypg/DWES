package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main extends AnimalService {
    public static void main(String[] args) {
        menu();
    }
    public static void showMenu(){
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Cargar información de fichero XML");
        System.out.println("2. Guardar información en fichero XML");
        System.out.println("3. Añadir animal");
        System.out.println("4. Borrar animal");
        System.out.println("5. Consultar animal");
        System.out.println("6. Mostrar todos los animales");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }
    public static void menu(){
        Scanner in = new Scanner(System.in);
        int option;
        Protectora animalList = null;
        Path animalXml = Paths.get("tema2.practica3/src/main/resources/animales.json");
        do {
            showMenu();
            option = in.nextInt();
            switch (option) {
                case 1:
                    animalList = readJSONToList(animalXml);
                    break;
                case 2:
                    writeListToJSON(animalList, animalXml);
                    break;
                case 3:
                    addAnimal(animalList, in);
                    break;
                case 4:
                    removeAnimal(animalList, in);
                    break;
                case 5:
                    System.out.println(consultAnimal(animalList, in));
                    break;
                case 6:
                    showAllAnimals(animalList);
                    break;
                case 7:
                    System.out.println("\nHas salido del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 7.");
                    break;
            }
        } while (option != 7);
    }
}