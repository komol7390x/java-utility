package json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonTask {
    private final Path file = Path.of("tasks.json");
    private final JsonMapper mapper = JsonMapper.builder()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();
    public void save(List<Product> tasks) throws IOException {
        mapper.writeValue(file.toFile(), tasks);
    }
    public List<Product> load() throws IOException {
        if (!Files.exists(file)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(mapper.readValue(file.toFile(), new
                TypeReference<List<Product>>() {}));
    }
}

