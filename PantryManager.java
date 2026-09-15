import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PantryManager {
    private Map<String, Ingredient> pantry;

    public PantryManager() {
        pantry = new HashMap<>();
    }

    public void addIngredient(String name, double qty, String unit) {
        String key = name.trim().toLowerCase();
        if (pantry.containsKey(key)) {
            // already have some, just top it up
            pantry.get(key).addQuantity(qty);
        } else {
            pantry.put(key, new Ingredient(name, qty, unit));
        }
    }

    public boolean removeIngredient(String name) {
        String key = name.trim().toLowerCase();
        return pantry.remove(key) != null;
    }

    public boolean hasIngredient(String name) {
        return pantry.containsKey(name.trim().toLowerCase());
    }

    public Collection<Ingredient> getAllIngredients() {
        return pantry.values();
    }

    public void printPantry() {
        if (pantry.isEmpty()) {
            System.out.println("Pantry is empty right now.");
            return;
        }
        System.out.println("\n--- Current Pantry ---");
        for (Ingredient ing : pantry.values()) {
            System.out.println("  " + ing);
        }
    }
}
