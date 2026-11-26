package exercise.record;

import java.util.List;

public record Movie(String title, int year, double rating, List<String> genres) {
    public Movie{
        if(rating<0) {
            throw new IllegalArgumentException("Rating must be greater than 0");
        }
    }
}
