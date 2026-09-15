import java.util.ArrayList;
import java.util.List;

public class RecipeMatcher {

    public static List<String> findMissingIngredients(Recipe recipe, PantryManager pantry) {
        List<String> missing = new ArrayList<>();
        for (String ing : recipe.getIngredients()) {
            if (!pantry.hasIngredient(ing)) {
                missing.add(ing);
            }
        }
        return missing;
    }

    public static void suggestRecipes(RecipeBook book, PantryManager pantry) {
        List<Recipe> recipes = book.getRecipes();
        if (recipes.isEmpty()) {
            System.out.println("No recipes to check against yet.");
            return;
        }

        List<Recipe> sorted = new ArrayList<>(recipes);
        // fewest missing ingredients first, so best matches show up on top
        sorted.sort((r1, r2) -> {
            int m1 = findMissingIngredients(r1, pantry).size();
            int m2 = findMissingIngredients(r2, pantry).size();
            return m1 - m2;
        });

        System.out.println("\n--- Recipe Suggestions (best match first) ---");
        for (Recipe r : sorted) {
            List<String> missing = findMissingIngredients(r, pantry);
            if (missing.isEmpty()) {
                System.out.println("  [READY] " + r.getName() + " - you have everything for this");
            } else {
                System.out.println("  [MISSING " + missing.size() + "] " + r.getName()
                        + " - need: " + String.join(", ", missing));
            }
        }
    }
}
