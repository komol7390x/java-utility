package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import exercise.record.Book;
import exercise.record.City;
import exercise.record.Movie;
import exercise.record.Product;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

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
        productJson();

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
        String jsonString=Files.readString(path);
        List<Product> products=json.readValue(jsonString, new TypeReference<List<Product>>() {});
        System.out.println(products);
    }
}
