package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@JacksonXmlRootElement(localName = "animal")
public class Animal {
    enum AnimalSex {
        MALE, FEMALE
    }

    private int id;

    @JacksonXmlProperty(localName = "nombre")
    private String name;

    @JacksonXmlProperty(localName = "especie")
    private String species;

    @JacksonXmlProperty(localName = "edad")
    private int age;

    @JacksonXmlProperty(localName = "sexo")
    private AnimalSex sex;

    @JacksonXmlProperty(localName = "fechaIngreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate entryDate;

    @JacksonXmlProperty(localName = "adoptado")
    private boolean adopted;

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
