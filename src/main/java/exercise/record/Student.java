package exercise.record;

public record Student(String name, String group, int score) {
    public Student {
        if (score < 0) {
            throw new IllegalArgumentException("Score must be greater than 0");
        }
    }
}
