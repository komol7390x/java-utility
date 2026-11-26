package exercise.record;

import java.util.List;

public record Cart(String customer, List<CartItem> items) {}
