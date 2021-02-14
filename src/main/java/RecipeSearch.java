
import java.util.Scanner;

public class RecipeSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RecipeManager rm = new RecipeManager();
        UserInterface ui = new UserInterface(scanner, rm);
        
        ui.start();
    }

}
