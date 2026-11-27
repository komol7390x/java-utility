package exercise.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BankCard(@JsonProperty("owner") String owner, @JsonProperty("number") String number,
                       @JsonIgnore String pin, @JsonProperty("balance") long balance) {
    public BankCard {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance must be greater than 0");
        }
    }
}
