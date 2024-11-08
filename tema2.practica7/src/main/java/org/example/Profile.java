package org.example;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

@BsonDiscriminator(key = "_cls", value = "profile")
public class  Profile {
    @BsonId
    ObjectId myId;

    @BsonProperty(value = "name")
    String name; // nombre del perfil
    @BsonProperty(value = "status")
    String status; // estado del perfil
    @BsonProperty(value = "age")
    int age; // edad del perfil
    @BsonProperty(value = "since")
    LocalDate since; // fecha de creación del perfil
    @BsonProperty(value = "posts")
    List<Post> posts; // lista de amigos del perfil

    @BsonCreator
    public Profile(@BsonProperty(value = "name") String name, @BsonProperty(value = "status") String status, @BsonProperty(value = "age") int age, @BsonProperty(value = "since") LocalDate since, @BsonProperty(value = "posts") List<Post> posts){
        this.name = name;
        this.status = status;
        this.age = age;
        this.since = since;
        this.posts = posts;
    }

    @Override
    public String toString() {
        String string = "-".repeat(20) + "\n" + name + "\nUsuario desde: " + since + "\nEstado:" + status + "\nEdad: " + age + " años\n";
        if (posts != null) {
            string += "Publicaciones:\n";
            for (Post post : posts) {
                string += post + "\n";
            }
        } else {
            string += "No ha publicado nada todavía.\n";
        }
        string += "-".repeat(20);
        return string;
    }
}

