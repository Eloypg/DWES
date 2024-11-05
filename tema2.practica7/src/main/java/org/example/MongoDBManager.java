package org.example;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

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
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void publishPost(String title, String content) {

        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void updateStatus(String status) {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void deleteProfile() {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void showProfiles() {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void showPosts(String profileName) {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void likePost(String profileName, String title) {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void commentPost(String profileName, String title, String comment) {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void showProfileStats() {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }

    public void showAllStats() {
        throw new UnsupportedOperationException("Funcionalidad no implementada");
    }
}
