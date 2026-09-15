import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ShoppingListGenerator {

    public static void generateForRecipe(Recipe recipe, PantryManager pantry) {
        List<String> missing = RecipeMatcher.findMissingIngredients(recipe, pantry);

        if (missing.isEmpty()) {
            System.out.println("Good news - you already have everything for " + recipe.getName() + ".");
            return;
        }

        System.out.println("\nShopping list for " + recipe.getName() + ":");
        for (String item : missing) {
            System.out.println("  [ ] " + item);
        }

        try {
            new File("data").mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/shopping_list.txt", true));
            bw.write("For " + recipe.getName() + ":");
            bw.newLine();
            for (String item : missing) {
                bw.write("  - " + item);
                bw.newLine();
            }
            bw.newLine();
            bw.close();
            System.out.println("(also saved to data/shopping_list.txt)");
        } catch (IOException e) {
            System.out.println("Couldn't save shopping list: " + e.getMessage());
        }
    }
}
