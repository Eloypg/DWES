package org.example;

import java.util.List;

public class Main extends Service {
    public static void main(String[] args) {
        List<Pokemon> pokemons = readAPI();
        pokemons.forEach(System.out::println);
        for (Pokemon p : pokemons) {
            System.out.println(getPokemonData(p.getUrl()));
        }
    }
}