import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;

    public Recipe(String name) {
        this.name = name.trim();
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(String ingredientName) {
        ingredients.add(ingredientName.trim().toLowerCase());
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("|");
        for (int i = 0; i < ingredients.size(); i++) {
            sb.append(ingredients.get(i));
            if (i != ingredients.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return name + " (needs: " + String.join(", ", ingredients) + ")";
    }
}
