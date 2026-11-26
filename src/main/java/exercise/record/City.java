package exercise.record;

public record City(String name, String region, int population) {
    public City {
        if (population < 0) {
            throw new IllegalArgumentException("Population must be greater than 0");
        }
    }
}
