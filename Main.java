import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static PantryManager pantry = new PantryManager();
    static RecipeBook recipeBook = new RecipeBook();

    public static void main(String[] args) {
        FileStorage.loadPantry(pantry);
        FileStorage.loadRecipes(recipeBook);

        System.out.println("=====================================");
        System.out.println(" Pantry & Recipe Assistant");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addIngredientFlow();
                    break;
                case "2":
                    pantry.printPantry();
                    break;
                case "3":
                    removeIngredientFlow();
                    break;
                case "4":
                    addRecipeFlow();
                    break;
                case "5":
                    recipeBook.printAllRecipes();
                    break;
                case "6":
                    RecipeMatcher.suggestRecipes(recipeBook, pantry);
                    break;
                case "7":
                    shoppingListFlow();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("That's not a valid option, try again.");
            }
        }

        FileStorage.savePantry(pantry);
        FileStorage.saveRecipes(recipeBook);
        System.out.println("Saved everything. Bye!");
    }

    static void printMenu() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("1. Add ingredient to pantry");
        System.out.println("2. View pantry");
        System.out.println("3. Remove ingredient from pantry");
        System.out.println("4. Add a recipe");
        System.out.println("5. View all recipes");
        System.out.println("6. Suggest recipes based on pantry");
        System.out.println("7. Generate shopping list for a recipe");
        System.out.println("0. Save and exit");
        System.out.print("> ");
    }

    static void addIngredientFlow() {
        System.out.print("Ingredient name: ");
        String name = sc.nextLine();

        double qty;
        while (true) {
            System.out.print("Quantity: ");
            try {
                qty = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a number please.");
            }
        }

        System.out.print("Unit (e.g. kg, litre, pcs): ");
        String unit = sc.nextLine();

        pantry.addIngredient(name, qty, unit);
        System.out.println("Added " + name + " to pantry.");
    }

    static void removeIngredientFlow() {
        System.out.print("Which ingredient do you want to remove? ");
        String name = sc.nextLine();
        if (pantry.removeIngredient(name)) {
            System.out.println("Removed " + name + ".");
        } else {
            System.out.println("Couldn't find that in your pantry.");
        }
    }

    static void addRecipeFlow() {
        System.out.print("Recipe name: ");
        String name = sc.nextLine();
        Recipe r = new Recipe(name);

        System.out.println("Enter ingredients needed, one at a time. Type 'done' when finished.");
        while (true) {
            System.out.print("  ingredient: ");
            String ing = sc.nextLine();
            if (ing.equalsIgnoreCase("done")) {
                break;
            }
            if (!ing.trim().isEmpty()) {
                r.addIngredient(ing);
            }
        }

        recipeBook.addRecipe(r);
        System.out.println("Saved recipe: " + name);
    }

    static void shoppingListFlow() {
        if (recipeBook.getRecipes().isEmpty()) {
            System.out.println("You don't have any recipes saved yet.");
            return;
        }
        System.out.print("Which recipe do you want to cook? ");
        String name = sc.nextLine();

        Recipe found = null;
        for (Recipe r : recipeBook.getRecipes()) {
            if (r.getName().equalsIgnoreCase(name)) {
                found = r;
                break;
            }
        }

        if (found == null) {
            System.out.println("Couldn't find a recipe with that name.");
            return;
        }

        ShoppingListGenerator.generateForRecipe(found, pantry);
    }
}
