package org.example;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.time.LocalDate;

@JacksonXmlRootElement(localName = "protectoraDeAnimales")
public class Animal {
    enum AnimalSex {
        MALE, FEMALE
    }
    @JacksonXmlElementWrapper(localName = "animales")
    @JacksonXmlProperty(localName = "animal")
    private int id;
    private String name;
    private String species;
    private int age;
    private AnimalSex sex;
    private LocalDate entryDate;
    private boolean adopted;

    public Animal(int id, String name, String species, int age, AnimalSex sex, LocalDate entryDate, boolean adopted) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.sex = sex;
        this.entryDate = entryDate;
        this.adopted = adopted;
    }
    public Animal(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public AnimalSex getSex() {
        return sex;
    }
    public void setSex(AnimalSex sex) {
        this.sex = sex;
    }
    public LocalDate getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
    public boolean isAdopted() {
        return adopted;
    }
    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | NOMBRE: " + name +
                " | ESPECIE: " + species +
                " | EDAD: " + age +
                " | SEXO: " + sex +
                " | FECHA DE ENTRADA: " + entryDate +
                " | ESTA ADOPTADO: " + adopted;
    }
}
