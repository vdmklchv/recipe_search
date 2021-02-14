/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vdmclcv
 */
import java.util.ArrayList;

public class RecipeManager {

    private ArrayList<Recipe> recipeBook;

    public RecipeManager() {
        this.recipeBook = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        this.recipeBook.add(recipe);
    }

    public ArrayList<Recipe> getRecipes() {
        return this.recipeBook;
    }

    public ArrayList<Recipe> searchByName(String name) {
        ArrayList<Recipe> matched = new ArrayList<>();
        for (Recipe recipe : this.recipeBook) {
            if (recipe.getName().contains(name)) {
                matched.add(recipe);
            }
        }
        return matched;
    }

    public ArrayList<Recipe> searchByCookingTime(int maxTime) {
        ArrayList<Recipe> matched = new ArrayList<>();
        for (Recipe recipe : this.recipeBook) {
            if (recipe.getCookTime() <= maxTime) {
                matched.add(recipe);
            }
        }
        return matched;
    }

    public ArrayList<Recipe> searchByIngredient(String name) {
        ArrayList<Recipe> matched = new ArrayList<>();
        for (Recipe recipe : this.recipeBook) {
            for (String ingredient: recipe.getIngredients()) {
                if (ingredient.equals(name)) {
                    matched.add(recipe);
                }
            }
        }
        return matched;
    }

}
