package org.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBManager {
    MongoCollection<Profile> profiles; // Colección de perfiles
    Profile myProfile; // Mi perfil

    public MongoDBManager(String uri, String databaseName, String collectionName) {
        MongoClient mongoClient = MongoClients.create(uri);
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
        profiles = database.getCollection(collectionName, Profile.class);
    }

    public void createProfile(String name, String status, int age) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); //Coger fecha actual
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"); //Formateador del timeStamp
        LocalDate creationDate = LocalDateTime.parse(timeStamp, originalFormatter).toLocalDate(); //Convertimos en local date con horas incluidas, y lo cambiamos a LocalDate

        List<Post> posts = new ArrayList<>();

        myProfile = new Profile(name, status, age, creationDate, posts);

        profiles.insertOne(myProfile);
    }

    public void publishPost(String title, String content) {
        if (myProfile != null) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            LocalDate publishedDate = LocalDateTime.parse(timeStamp, originalFormatter).toLocalDate();

            List<String> comments = new ArrayList<>();

            myProfile.getPosts().add(new Post(title, content, publishedDate, 0, comments));
            profiles.replaceOne(Filters.eq("name", myProfile.getName()), myProfile);
        } else {
            System.out.println("No existe el perfil.");
        }
    }

    public void updateStatus(String status) {
        if (myProfile != null) {
            myProfile.setStatus(status);
            profiles.replaceOne(Filters.eq("name", myProfile.getName()), myProfile);
        } else {
            System.out.println("No existe el perfil.");
        }
    }

    public void deleteProfile() {
        if (myProfile != null) {
            profiles.deleteOne(Filters.eq("name", myProfile.getName()));
        } else {
            System.out.println("No existe el perfil.");
        }
    }

    public void showProfiles() {
        profiles.find().forEach(System.out::println);
    }

    public void showPosts(String profileName) {
        if (myProfile.getName().equalsIgnoreCase(profileName)) {
            myProfile.getPosts().forEach(System.out::println);
        } else {
            System.out.println("No hay ningún perfil con ese nombre.");
        }
    }

    public Profile findProfile(String profileName){
        Profile findedProfile = profiles.find(Filters.eq("name", profileName)).first();
        return findedProfile;
    }

    public void likePost(String profileName, String title) {
        Profile profile = findProfile(profileName);
        for (Post p : profile.getPosts()) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                int likes = p.getLikes();
                likes++;
                p.setLikes(likes);
            }
        }
        profiles.replaceOne(Filters.eq("name", profile.getName()), profile);
    }

    public void commentPost(String profileName, String title, String comment) {
        Profile profile = findProfile(profileName);
        for (Post p : profile.getPosts()) {
            if (p.getTitle().equalsIgnoreCase(title)) {
                p.getComments().add(comment);
            } else {
                System.out.println("Este post no existe.");
            }
        }
        profiles.replaceOne(Filters.eq("name", profile.getName()), profile);
    }

    public void showProfileStats() {
        if (myProfile != null) {
            int numPosts = myProfile.getPosts().size();
            int totalLikes = 0;
            int totalComments = 0;
            for (Post p : myProfile.getPosts()) {
                totalLikes += p.getLikes();
                totalComments += p.getComments().size();
            }
            System.out.println("    - MI PERFIL: ");
            System.out.println("- Num. Publicaciones: " + numPosts);
            System.out.println("- Total likes: " + totalLikes);
            System.out.println("- Total comentarios: " + totalComments);
        }
    }

    public void showAllStats() {
        System.out.println("- Num. total perfiles: ");
        System.out.println("- Num. total de publicaciones: ");
        System.out.println("- Num. total de likes: ");
        System.out.println("- Num. total de comentarios: ");
        System.out.println("- Usiarios mayores de edad: ");
        System.out.println("- Perfiles con más publicaciones (top 3): ");
    }
}
