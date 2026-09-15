Pantry & Recipe Assistant
A command-line Java application that keeps track of what's in your kitchen pantry, stores your recipes, and tells you which recipes you can cook right now with what you have (and what's still missing for the rest).
Overview
Most people forget what they have at home and end up buying duplicates, or they plan to cook something only to realize halfway through that they're missing an ingredient. This project solves that by letting you maintain a digital pantry and recipe book, and automatically matching the two.
Features
Add, view, and remove pantry ingredients (with quantity and unit)
Add recipes with a list of required ingredients
View all saved recipes
Get recipe suggestions ranked by how close you are to being able to cook them (fewest missing ingredients first)
Auto-generate a shopping list for any recipe, showing only what you don't already have
Everything is saved to text files automatically, so your pantry and recipes persist between runs
Technologies Used
Java (JDK 17 or newer recommended)
Plain text file storage (no external database needed)
Core Java only — no external libraries required
Project Structure
```
pantry-recipe-assistant/
├── src/
│   ├── Main.java                 # CLI menu and program entry point
│   ├── Ingredient.java           # Represents one pantry ingredient
│   ├── Recipe.java               # Represents one recipe
│   ├── PantryManager.java        # Add/remove/check pantry stock
│   ├── RecipeBook.java           # Stores and lists all recipes
│   ├── RecipeMatcher.java        # Matches recipes against the pantry
│   ├── FileStorage.java          # Saves/loads pantry & recipes to disk
│   └── ShoppingListGenerator.java# Builds a shopping list for a recipe
├── data/                          # Created automatically at runtime
│   ├── pantry.txt
│   ├── recipes.txt
│   └── shopping_list.txt
├── statement.md
└── README.md
```
Setup & Installation
1. Install Java
Make sure you have a JDK (Java Development Kit) installed, version 17 or later.
Check if you already have it:
```bash
java -version
```
If it's not installed:
Windows/Mac: Download from Adoptium and install.
Linux (Debian/Ubuntu):
```bash
  sudo apt-get update
  sudo apt-get install default-jdk
  ```
2. Clone the repository
```bash
git clone https://github.com/{your-username}/{your-repo-name}.git
cd {your-repo-name}
```
3. Compile the project
From the project root folder:
```bash
javac -d out src/*.java
```
This compiles all the `.java` files and puts the class files in an `out` folder.
4. Run the project
```bash
java -cp out Main
```
You'll see a menu like this:
```
What do you want to do?
1. Add ingredient to pantry
2. View pantry
3. Remove ingredient from pantry
4. Add a recipe
5. View all recipes
6. Suggest recipes based on pantry
7. Generate shopping list for a recipe
0. Save and exit
>
```
Just type the number of what you want to do and follow the prompts.
How to Test It
A simple manual test flow:
Run the program.
Choose `1` and add a few ingredients (e.g., rice, onion, tomato).
Choose `4` and add a recipe that uses ingredients you have, plus one recipe that needs something you don't have.
Choose `6` to see the suggestions — the recipe you can fully make should show up as `[READY]`.
Choose `7`, type the name of the recipe you're missing ingredients for, and check that the shopping list only lists what's missing.
Choose `0` to save and exit, then run the program again — your pantry and recipes should still be there.
Notes
All data is stored as plain text under the `data/` folder, which is created automatically the first time you save.
Ingredient and recipe names are matched case-insensitively (e.g., "Onion" and "onion" are treated as the same thing).
