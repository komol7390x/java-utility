package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("title")
    public String getName() {
        return name;
    }

    @JsonProperty("done")
    public boolean isAvailable() {
        return isAvailable;
    }

    private final int id;
    private final String name;
    private final boolean isAvailable;

//    public Product(int id, String name, boolean isAvailable) {
//        this.id = id;
//        this.name = name;
//        this.isAvailable = isAvailable;
//    }

    @JsonCreator
    Product(
    @JsonProperty("id") int id,
    @JsonProperty("title") String name,
    @JsonProperty("done") boolean isAvailable)
    {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
    }
}
