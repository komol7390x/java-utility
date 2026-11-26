package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        JsonMapper mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
        Product product = new Product(1, "Non", true);
        String json = mapper.writeValueAsString(product);
//        System.out.println(json);

        String jsonString = """
                {"id": 2, "title": "Java mashq qilish", "done": true}
                """;

        Product newProduct = mapper.readValue(jsonString, Product.class);
//        System.out.println(newProduct.getName());


        List<Product> tasks = List.of(
                new Product(1, "Non olish", false),
                new Product(2, "Java mashq qilish", true));
        
        String json2 = mapper.writeValueAsString(tasks);
        List<Product> loaded = mapper.readValue(json2, new TypeReference<List<Product>>() {});
        System.out.println(loaded.size());

        User user=new User(7,"Ali Valiyev","password1234",null);
        System.out.println(mapper.writeValueAsString(user));
    }
}
