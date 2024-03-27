package practice;

import java.util.ArrayList;

public class Cliente {

    ArrayList<String> likedIngredients;
    ArrayList<String> unlikedIngredients;

    public Cliente() {
        
        this.likedIngredients = new ArrayList<String>();
        this.unlikedIngredients = new ArrayList<String>();

    }

    @Override
    public String toString() {
        return likedIngredients.toString() + "\n" + unlikedIngredients.toString() + "\n";
    }

}
