package exercise.record;

public record Product(int id, String name, double price, String category) {
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
}
