package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Subject {
    private int id;
    private String name;
    private String classroom;
    private boolean isMandatory;

    @Override
    public String toString() {
        return  "ID: " + id +
                "NOMBRE: " + name +
                "AULA: " + classroom + '\'' +
                "OBLIGATORIA: " + isMandatory;
    }
}
