package exercise.record;

public record Book(
        String title,
        String author,
        int year,
        int pages
) {
    public Book {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be greater than 0");
        }
    }
}
