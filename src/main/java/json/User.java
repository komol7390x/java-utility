package json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record User(
        int id, @JsonProperty("fullName") String fullName,
        @JsonIgnore String password,
        @JsonInclude(JsonInclude.Include.NON_NULL) String phone
) { }
