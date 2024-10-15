package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Service {
    public static List<Pokemon> readAPI(){
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        List<Pokemon> pokemons;
        try {
            JsonNode rootNode = objectMapper.readTree(new URL("https://pokeapi.co/api/v2/pokemon"));
            pokemons = objectMapper.readValue(rootNode.get("results").traverse(), new TypeReference<>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pokemons;
    }
    /*public static List<String> getPokemonData(String url) {
        ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());
        List<String> pokemonData;
        try {
            JsonNode rootNode = om.readTree(new URL(url));
            pokemonData = om.readValue(rootNode.get("results").traverse(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pokemonData;
    }*/
}