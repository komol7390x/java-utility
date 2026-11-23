package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

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
        System.out.println(newProduct.getName());
    }
}
