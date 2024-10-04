package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AnimalService {

    public static Protectora readXmlToList(Path path) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(path.toFile(), new TypeReference<>() { });
        } catch (IOException e) {
            System.out.println("\nNO SE HA PODIDO LEER EL XML (readXmlToList)\n");
            throw new RuntimeException(e);
        }
    }
    public static Protectora writeListToXml(Protectora animalList, Path path) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            // Serializar la lista de personas a formato XML
            xmlMapper.writeValue(path.toFile(), animalList);

        } catch (IOException e) {
            System.out.println("\nNO SE HA PODIDO ESCRIBIR EN EL XML (writeListToXml)\n");
            e.printStackTrace();
        }
        return animalList;
    }

    public static Animal createAnimal(Scanner in){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println("\n--- INTRODUCE LOS DATOS DE TU ANIMAL ---");
        System.out.print("· ID: ");
        int id = in.nextInt();

        System.out.print("· NOMBRE: ");
        String name = in.next();

        System.out.print("· ESPECIE: ");
        String species = in.next();

        System.out.print("· EDAD: ");
        int age = in.nextInt();

        //Animal.AnimalSex animalSex = sexSelector(in);
        String animalSex = sexSelector(in);

        System.out.print("· FECHA DE INGRESO (yyyy-MM-dd): ");
        String entryDateString = in.next();
        LocalDate entryDate = LocalDate.parse(entryDateString, formatter);

        boolean adopted = isAdopted(in);

        return new Animal(id, name, species, age, animalSex, entryDate, adopted);
    }
    public static boolean isAdopted(Scanner in) {
        boolean isAdopted = false;
        int option;
        do {
            System.out.println("\n· ESTA ADOPTADO: ");
            System.out.print("1) Sí.\n" +
                    "2) No.\n" +
                    "Elige una opción: ");
            option = in.nextInt();
            switch (option) {
                case 1 -> isAdopted = true;
                case 2 -> isAdopted = false;
                default -> System.out.println("Selecciona una opción válida.");
            }
        } while (option != 1 && option != 2);
        return isAdopted;
    }
    public static String sexSelector(Scanner in){
        Animal.AnimalSex animalSex = null;
        int option;
        do {
            System.out.println("\n· SEXO: ");
            System.out.print("1) Macho.\n" +
                    "2) Hembra.\n" +
                    "Elige una opción: ");
            option = in.nextInt();
            switch (option) {
                case 1 -> animalSex = Animal.AnimalSex.MALE;
                case 2 -> animalSex = Animal.AnimalSex.FEMALE;
                default -> System.out.println("Selecciona una opción válida.");
            }
        } while (option != 1 && option != 2);
        return new ObjectMapper().writeValueAsString(animalSex);;
    }
    /*public static String sexSelector(Scanner in){
        String animalSex = "";
        int option;
        do {
            System.out.println("\n· SEXO: ");
            System.out.print("1) Macho.\n" +
                    "2) Hembra.\n" +
                    "Elige una opción: ");
            option = in.nextInt();
            switch (option) {
                case 1 -> animalSex = "Macho";
                case 2 -> animalSex = "Hembra";
                default -> System.out.println("Selecciona una opción válida.");
            }
        } while (option != 1 && option != 2);
        return animalSex;
    }
*/
    public static Animal findAnimal(Protectora animalList, Scanner in) {
        Animal animal = new Animal();
        System.out.println("\nDime el nombre de el animal a buscar: ");
        String name = in.next();
        for (Animal a : animalList.getAnimalList()) {
            if (a.getName().equalsIgnoreCase(name)) animal = a;
        }
        return animal;
    }

    public static void addAnimal(Protectora animalList, Scanner in){
        Animal animal = createAnimal(in);
        animalList.getAnimalList().add(animal);
    }
    public static void removeAnimal(Protectora animalList, Scanner in){
        Animal animal = findAnimal(animalList, in);
        animalList.getAnimalList().remove(animal);
    }
    public static String consultAnimal(Protectora animalList, Scanner in){
        Animal animal = findAnimal(animalList, in);
        return animal.toString();
    }
    public static void showAllAnimals(Protectora animalList){
        animalList.getAnimalList().forEach(a -> System.out.println(a.toString()));
    }
}
