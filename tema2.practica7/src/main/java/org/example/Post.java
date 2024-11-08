package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@BsonDiscriminator(key = "_cls", value = "profile")
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
