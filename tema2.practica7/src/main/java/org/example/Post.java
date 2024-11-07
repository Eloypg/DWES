package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class Post {
    String title; // título del post
    String content; // contenido del post
    LocalDate publishedDate; // fecha de creación del post
    int likes; // cantidad de likes del post
    List<String> comments; // lista de comentarios del post

    @Override
    public String toString() {
        String string = title + "\n" + publishedDate + "\n" + likes + " likes\n" + content + "\n";
        for (String comment : comments) {
            string += " - " + comment + "\n";
        }
        return string;
    }

}