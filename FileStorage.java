import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage {

    private static final String PANTRY_FILE = "data/pantry.txt";
    private static final String RECIPES_FILE = "data/recipes.txt";

    public static void loadPantry(PantryManager pantry) {
        File file = new File(PANTRY_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    continue;
                }
                try {
                    String name = parts[0];
                    double qty = Double.parseDouble(parts[1]);
                    String unit = parts[2];
                    pantry.addIngredient(name, qty, unit);
                } catch (NumberFormatException e) {
                    System.out.println("Skipped a bad line in pantry file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't read pantry file: " + e.getMessage());
        }
    }

    public static void savePantry(PantryManager pantry) {
        try {
            new File("data").mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(PANTRY_FILE));
            for (Ingredient ing : pantry.getAllIngredients()) {
                bw.write(ing.toFileString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Couldn't save pantry: " + e.getMessage());
        }
    }

    public static void loadRecipes(RecipeBook book) {
        File file = new File(RECIPES_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if (parts.length < 1) {
                    continue;
                }
                Recipe r = new Recipe(parts[0]);
                if (parts.length == 2) {
                    for (String ing : parts[1].split(",")) {
                        if (!ing.trim().isEmpty()) {
                            r.addIngredient(ing);
                        }
                    }
                }
                book.addRecipe(r);
            }
        } catch (IOException e) {
            System.out.println("Couldn't read recipes file: " + e.getMessage());
        }
    }

    public static void saveRecipes(RecipeBook book) {
        try {
            new File("data").mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(RECIPES_FILE));
            for (Recipe r : book.getRecipes()) {
                bw.write(r.toFileString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Couldn't save recipes: " + e.getMessage());
        }
    }
}
