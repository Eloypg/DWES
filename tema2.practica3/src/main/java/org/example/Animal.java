package org.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@JsonRootName(value = "animal")
public class Animal {
    enum AnimalSex {
        Macho, Hembra
    }
    private int id;

    //@JacksonXmlProperty(localName = "nombre")
    private String nombre;

    //@JacksonXmlProperty(localName = "especie")
    private String especie;

    //@JacksonXmlProperty(localName = "edad")
    private int edad;

    //@JacksonXmlProperty(localName = "sexo")
    private AnimalSex sexo;

    //@JacksonXmlProperty(localName = "fechaIngreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaIngreso;

    //@JacksonXmlProperty(localName = "adoptado")
    private boolean adoptado;

    @Override
    public String toString() {
        return "ID: " + id +
                " | NOMBRE: " + nombre +
                " | ESPECIE: " + especie +
                " | EDAD: " + edad +
                " | SEXO: " + sexo +
                " | FECHA DE ENTRADA: " + fechaIngreso +
                " | ESTA ADOPTADO: " + adoptado;
    }
}
