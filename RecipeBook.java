import java.util.ArrayList;
import java.util.List;

public class RecipeBook {
    private List<Recipe> recipes;

    public RecipeBook() {
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe r) {
        recipes.add(r);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void printAllRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("No recipes saved yet.");
            return;
        }
        System.out.println("\n--- Recipe Book ---");
        for (Recipe r : recipes) {
            System.out.println("  " + r);
        }
    }
}
