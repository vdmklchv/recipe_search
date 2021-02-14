/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vdmclcv
 */
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private RecipeManager rm;

    public UserInterface(Scanner scan, RecipeManager recipeManager) {
        this.scanner = scan;
        this.rm = recipeManager;
    }

    public void start() {
        System.out.print("File to read: ");
        String fileName = this.scanner.nextLine();

        try (Scanner fileReader = new Scanner(Paths.get(fileName))) {

            String resultingString = "";

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                resultingString += line + "\n";
            }

            String[] importedRecipes = resultingString.split("\n\n");

            for (String recipe : importedRecipes) {
                ArrayList<String> ingredients = new ArrayList<>();
                String[] components = recipe.split("\n");
                String name = components[0];
                int cookingTime = Integer.valueOf(components[1]);
                for (int i = 2; i < components.length; i++) {
                    ingredients.add(components[i]);
                }
                rm.addRecipe(new Recipe(name, cookingTime, ingredients));
            }

            while (true) {
                printCommands();
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                if (command.equals("stop")) {
                    break;
                }
                if (command.equals("list")) {
                    System.out.println("Recipes:");
                    for (Recipe recipe : rm.getRecipes()) {
                        System.out.println(recipe.toString());
                    }
                }
                if (command.equals("find name")) {
                    System.out.print("Searched word: ");
                    String searchedWord = scanner.nextLine();
                    ArrayList<Recipe> foundRecipes = rm.searchByName(searchedWord);
                    if (foundRecipes.isEmpty()) {
                        System.out.println("Nothing found.");
                    } else {
                        System.out.println("Recipes:");
                        for (Recipe recipe : foundRecipes) {
                            System.out.println(recipe.toString());
                        }
                    }
                }
                if (command.equals("find cooking time")) {
                    System.out.print("Max cooking time: ");
                    int maxCookingTime = Integer.valueOf(scanner.nextLine());
                    ArrayList<Recipe> foundRecipes = rm.searchByCookingTime(maxCookingTime);

                    if (foundRecipes.isEmpty()) {
                        System.out.println("Nothing found.");
                    } else {
                        System.out.println("Recipes:");
                        for (Recipe recipe : foundRecipes) {
                            System.out.println(recipe.toString());
                        }
                    }
                }
                if (command.equals("find ingredient")) {
                    System.out.print("Ingredient: ");
                    String ingredient = scanner.nextLine();
                    ArrayList<Recipe> foundRecipes = rm.searchByIngredient(ingredient);
                    if (foundRecipes.isEmpty()) {
                        System.out.println("Nothing found.");
                    } else {
                        System.out.println("Recipes:");
                        for (Recipe recipe : foundRecipes) {
                            System.out.println(recipe.toString());
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public static String printCommands() {
        String outputString = "Commands:\nlist - lists the recipes\nstop - stops the program\nfind name - searches recipes by name\nfind cooking time - searches recipes by cooking time\nfind ingredient - searches recipes by ingredient";
        return outputString;
    }
}
