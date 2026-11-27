package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
    static JsonMapper json = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).build();
    static Path jsonPath = Path.of("src", "main", "java", "exercise", "json").toAbsolutePath();

    public static void main(String[] args) throws IOException {
//        bookToJson();
//        movieToJson();
//        cityToJson();
//        productJson();
//        cartItem();
//        readJson();
//        bankCard();
//        contact();
//        order();
//        populationToRead();
        student();

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
        double sum = cart.items().stream().mapToDouble(item -> item.price() * item.qty()).sum();
        System.out.println("Umumiy summa: " + sum);
        int count = cart.items().stream().mapToInt(CartItem::qty).sum();
        System.out.println("Jami dona: " + count);
        String mostExpensiveName = cart.items().stream().max(Comparator.comparingDouble(item -> item.price() * item.qty())).map(CartItem::product).orElse("Topilmadi");
        System.out.println("Eng katta qator: " + mostExpensiveName);
        System.out.println("Chegirma bilan: " + (sum > 100_000 ? sum * 0.95 : sum));
    }

    private static void readJson() throws IOException {
        Path path = Path.of(String.valueOf(jsonPath), "person.json");
        String jsonString = Files.readString(path);
        Person person = json.readValue(jsonString, Person.class);
        System.out.println(person.firstName() + " " + person.lastName() + ", " + (2026 - person.birthYear()) + " yosh");
        System.out.println(jsonString);
    }

    private static void bankCard() throws JsonProcessingException {
        BankCard bankCard = new BankCard("Sevara", "8600 **** **** 4512", "1234", 1500000);
        String jsonString = json.writeValueAsString(bankCard);
        System.out.println(jsonString);
    }

    private static void contact() throws JsonProcessingException {
        List<Contact> contacts = List.of(new Contact("Anvar", "+998901112233", "anvar@mail.uz", null), new Contact("Barno", "+998935556677", null, "@barno_uz"), new Contact("Sanjar", null, null, null));

        String jsonString = json.writeValueAsString(contacts);
        System.out.println(jsonString);

    }

    private static void order() throws IOException {
        Path path = Path.of(String.valueOf(jsonPath), "order.json");
        String jsonString = Files.readString(path);
        Order order = json.readValue(jsonString, Order.class);
        System.out.println("Buyurtma: " + order.orderId());
        System.out.println("Mijoz shahri: " + order.customer().city());
        System.out.println("Mahsulot turlari: " + order.items().size());
        System.out.println("Jami dona: " + order.items().stream().mapToInt(OrderItem::qty).sum());
        System.out.println("Jami summa: " + order.items().stream().mapToDouble(item -> item.price() * item.qty()).sum());
        System.out.println("Holati: " + (order.paid() ? "To'langan" : "To'lanmagan"));
    }

    private static void populationToRead() throws IOException {
        Path path = Path.of(String.valueOf(jsonPath), "population.json");
        String jsonString = Files.readString(path);
        Map<String, Long> population = json.readValue(jsonString, new TypeReference<Map<String, Long>>() {
        });
        Map.Entry<String, Long> maxEntry = population.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();
        long allPopulation = population.values().stream()
                .mapToLong(Long::longValue)
                .sum();
        Map<String, Long> over400k = population.entrySet().stream()
                .filter(ent -> ent.getValue() > 400_000)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Eng katta: " + maxEntry.getKey() + " (" + maxEntry.getValue() + ") ");
        System.out.println("Jami: " + allPopulation);
        System.out.println(400_000 + " mingdan ko'p: " + over400k.values());
    }

    private static void student() throws IOException {
        Path pathStudent = Path.of(String.valueOf(jsonPath), "students.json");
        String jsonString = Files.readString(pathStudent);
        List<Student> students = json.readValue(jsonString, new TypeReference<List<Student>>() {
        });
        Map<String, List<String>> groups = students.stream()
                .collect(Collectors.groupingBy(
                        Student::group,
                        Collectors.mapping(Student::name, Collectors.toList())
                ));
        Path pathGroup = Path.of(String.valueOf(jsonPath), "group.json");
        Files.createDirectories(pathGroup.getParent());
        String jsonOutput = json.writeValueAsString(groups);
        Files.writeString(pathGroup, jsonOutput);
        System.out.println("Success");
    }
}
