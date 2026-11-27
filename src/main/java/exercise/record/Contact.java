package exercise.record;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Contact(
        String name,
        String phone,
        String email,
        String telegram
) {}
