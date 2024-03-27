package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Pizzeria {

    ArrayList<Cliente> clientes;
    ArrayList<String> pizzaIngredients;

    public Pizzeria () {

        pizzaIngredients = new ArrayList<String>();

        try(BufferedReader reader = new BufferedReader(new FileReader("/Users/ivano/Desktop/USJ/Google_Hashcodes/HashCode_Solutions/2022/practice/app/src/main/resources/a_an_example.in.txt"))) {

            String linea = reader.readLine();
            clientes = new ArrayList<Cliente>(Integer.parseInt(linea));

            //Vuelvo a leer para entrar en los clientes
            linea = reader.readLine();
            while(linea != null) {

                //Ingredientes que le gustan al cliente
                String[] regex = linea.split(" ");
                Cliente aux = new Cliente();
                if(Integer.parseInt(regex[0]) > 0) {
                    for(int i = 1; i <= Integer.parseInt(regex[0]); i++) {
                        aux.likedIngredients.add(regex[i]);
                    }
                }

                //Ingredientes que no le gustan al cliente
                linea = reader.readLine();
                regex = linea.split(" ");
                if(Integer.parseInt(regex[0]) > 0) {
                    for(int i = 1; i <= Integer.parseInt(regex[0]); i++) {
                        aux.unlikedIngredients.add(regex[i]);
                    }
                }

                //Añadimos al cliente y pasamos a la siguiente linea
                clientes.add(aux);
                linea = reader.readLine();

            }
            

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void solve() {
        
        HashMap<String, Integer> mostLikedIngredients = new HashMap<String, Integer>();
        HashMap<String, Integer> mostUnlikedIngredients = new HashMap<String, Integer>();
        
        for(Cliente c : clientes) {
            for(String like : c.likedIngredients) {
                if(mostLikedIngredients.containsKey(like)) {
                    int auxNumLikes = mostLikedIngredients.get(like) + 1;
                    mostLikedIngredients.replace(like, auxNumLikes);
                } else {
                    mostLikedIngredients.put(like, 1);
                }
            }
            for(String unlike : c.unlikedIngredients) {
                if(mostUnlikedIngredients.containsKey(unlike)) {
                    int auxNumLikes = mostLikedIngredients.get(unlike) + 1;
                    mostUnlikedIngredients.replace(unlike, auxNumLikes);
                } else {
                    mostUnlikedIngredients.put(unlike, 1);
                }
            }
        }

        //Primero, haremos comprobaciones basicas, despues pasaremos a hacer mas detalladas
        for(Cliente c : clientes) {
            for(String like : c.likedIngredients) {
                boolean likeBool = true;
                for(Cliente cAux : clientes) {
                    if(cAux.unlikedIngredients.contains(like)) {
                        likeBool = false;
                    }
                }
                if(likeBool && !pizzaIngredients.contains(like)) {
                    pizzaIngredients.add(like);
                }
            }
        }

        //TODO: implementar algoritmo de comprobacion usando los hashmaps creados

    }

    public void createFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("solution.txt"))){
            String writeIntoFile = new String();
            writeIntoFile += pizzaIngredients.size() + " ";
            for(String ingredient : pizzaIngredients) {
                writeIntoFile += ingredient + " ";
            }
            writer.write(writeIntoFile);

        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
