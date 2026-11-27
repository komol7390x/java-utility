package exercise.record;

import java.util.List;

public record Order(String orderId, Customer customer, List<OrderItem> items, boolean paid) {
}
