package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Protectora {
    @JsonProperty(value = "animales")
    private List<Animal> animalList = new ArrayList<>();
}

