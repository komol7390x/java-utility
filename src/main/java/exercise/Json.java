package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import exercise.record.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Json {
    static JsonMapper json = JsonMapper
            .builder().enable(SerializationFeature.INDENT_OUTPUT)
            .disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS)
            .build();
    static Path jsonPath = Path.of("src", "main", "java", "exercise", "json").toAbsolutePath();

    public static void main(String[] args) throws IOException {
//        bookToJson();
//        movieToJson();
//        cityToJson();
//        productJson();
        cartItem();

    }

    private static void bookToJson() throws JsonProcessingException {

        Book book = new Book("O‘tkan kunlar", "Abdulla Qodiriy", 1926, 360);
        String jsonMapper = json.writeValueAsString(book);
        System.out.println(jsonMapper);
    }

    private static void movieToJson() throws JsonProcessingException {
        String jsonString = """
                {
                  "title": "Inception",
                  "year": 2010,
                  "rating": 8.8,
                  "genres": [ "Sci-Fi", "Thriller"]
                }
                """;
        Movie jsonMapper = json.readValue(jsonString, Movie.class);
        System.out.println(jsonMapper.title() + " (" + jsonMapper.year() + ")" + " - reyting: " + jsonMapper.rating());
        System.out.println("Janrlar soni: " + jsonMapper.genres().size());
        System.out.println("Birinchi janri: " + jsonMapper.genres().getFirst());
    }

    private static void cityToJson() throws IOException {
        Path path = Path.of(String.valueOf(jsonPath), "cities.json");
        String jsonString = Files.readString(path);
        List<City> city = json.readValue(jsonString, new TypeReference<List<City>>() {
        });
        System.out.println("Shaharlar soni: " + city.size());
        String bigCity = city.stream().max(Comparator.comparingInt(City::population)).orElseThrow().region();
        System.out.println("Eng katta shahar: " + bigCity);
        int allPopulation = city.stream().mapToInt(City::population).sum();
        System.out.println("Jami aholi: " + allPopulation);
    }

    private static void productJson() throws IOException {
        Path path = Path.of(String.valueOf(jsonPath), "product.json");
        String jsonString = Files.readString(path);
        List<Product> products = json.readValue(jsonString, new TypeReference<List<Product>>() {
        });
        int size = products.size();
        List<String> expensiveProduct = products.stream().filter(p -> p.price() > 50_000).map(Product::name).toList();
        System.out.println("Jami:" + size);
        System.out.println("Qimmat mahsulotlar: " + expensiveProduct);
        Map<String, Long> counts = products.stream().collect(Collectors.groupingBy(Product::category, Collectors.counting()));
        counts.forEach((category, count) -> System.out.println(category + " : " + count));
    }

    private static void cartItem() throws IOException {
        Path path = Path.of(String.valueOf(jsonPath), "cart.json");
        String jsonString = Files.readString(path);
        Cart cart = json.readValue(jsonString, Cart.class);
        System.out.println("Mijoz: " + cart.customer());
        double sum = cart.items().stream()
                .mapToDouble(item -> item.price() * item.qty())
                .sum();
        System.out.println("Umumiy summa: " + sum);
        int count = cart.items().stream().mapToInt(CartItem::qty).sum();
        System.out.println("Jami dona: " + count);
        String mostExpensiveName = cart.items().stream()
                .max(Comparator.comparingDouble(item -> item.price() * item.qty()))
                .map(CartItem::product)
                .orElse("Topilmadi");
        System.out.println("Eng katta qator: " + mostExpensiveName);
        System.out.println("Chegirma bilan: " + (sum > 100_000 ? sum * 0.95 : sum));
    }
}
