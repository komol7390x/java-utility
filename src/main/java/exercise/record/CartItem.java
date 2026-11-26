package exercise.record;

public record CartItem(String product, double price, int qty) {
    public CartItem {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        if (qty < 0) {
            throw new IllegalArgumentException("Qty must be greater than 0");
        }
    }
}
