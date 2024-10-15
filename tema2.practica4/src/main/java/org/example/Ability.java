package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Ability {
    private String name;
    private String url;
    //private boolean is_hidden;
   // private int slot;

    @Override
    public String toString() {
        return "Habilidad { " +
                "NOMBRE: " + name + " | " +
                "URL: " + url + " | " +
               // "ES OCULTA? " + is_hidden + " | " +
              //  "SLOT: " + slot +
                " }";
    }
}
