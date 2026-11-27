package exercise.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Person(@JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
                     @JsonProperty("birth_year") int birthYear) {
    public Person {
        if (birthYear < 1900) {
            throw new IllegalArgumentException("Population must be greater than 1900");
        }
    }
}
