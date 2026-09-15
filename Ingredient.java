public class Ingredient {
    private String name;
    private double quantity;
    private String unit;

    public Ingredient(String name, double quantity, String unit) {
        this.name = name.trim().toLowerCase();
        this.quantity = quantity;
        this.unit = unit.trim().toLowerCase();
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void addQuantity(double amount) {
        quantity += amount;
    }

    public void reduceQuantity(double amount) {
        quantity -= amount;
        if (quantity < 0) {
            quantity = 0;
        }
    }

    // used when writing back to pantry.txt
    public String toFileString() {
        return name + "," + quantity + "," + unit;
    }

    @Override
    public String toString() {
        return name + " - " + quantity + " " + unit;
    }
}
