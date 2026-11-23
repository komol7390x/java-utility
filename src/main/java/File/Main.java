package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("src", "File", "file", "text.txt");
        Path log = Path.of("src", "File", "file", "app.log");
        Path file = Path.of("tasks.txt");
        Path abs = Path.of("C:/projects/todo/tasks.txt");
        Path nested = Path.of("data", "2026", "tasks.txt");
//        System.out.println(nested.getFileName()); // tasks.txt
//        System.out.println(nested.getParent()); // data/2026
//        System.out.println(file.toAbsolutePath());
//        System.out.println(path.toAbsolutePath());

        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        Path parentLog = log.getParent();
        if (parent != null) {
            Files.createDirectories(parentLog);
        }
//        Files.writeString(path,
//                "Non olish kere" + System.lineSeparator(),
//                StandardOpenOption.CREATE,
//                StandardOpenOption.APPEND);

        String content = Files.readString(path);
//        System.out.println(content);

        List<String> lines = Files.readAllLines(path);
        for (int i = 0; i < lines.size(); i++) {
//            System.out.println((i + 1) + ". " + lines.get(i));
        }
//        System.out.println(Files.exists(path));
//        System.out.println(Files.isDirectory(Path.of("src")));
//        System.out.println(Files.size(path));

        try (BufferedWriter writer = Files.newBufferedWriter(log)) {
            writer.write("Hisobot");
            writer.newLine();
            writer.write("Bajarilgan vazifalar: 3");
        }

        try {
            String text = Files.readString(path);
//            System.out.println(text);
        } catch (NoSuchFileException e) {
            System.out.println("Fayl topilmadi: " + e.getFile());
        } catch (IOException e) {
            System.out.println("Faylni o‘qib bo‘lmadi: " + e.getMessage());
        }


//        try (Stream<String> lines2 = Files.lines(log)) {
//            long errors = lines2.filter(line -> line.contains("ERROR")).count();
//            System.out.println("Xatolar soni: " + errors);
//        } catch (NoSuchFileException e) {
//            System.out.println("File topilmadi: " + e.getFile());
//        } catch (IOException e) {
//            System.out.println("Faylni o‘qib bo‘lmadi: " + e.getMessage());
//        }


        try (BufferedReader reader = Files.newBufferedReader(log)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (NoSuchFileException e) {
            System.out.println("File topilmadi: " + e.getFile());
        } catch (IOException e) {
            System.out.println("Faylni o‘qib bo‘lmadi: " + e.getMessage());
        }
    }
}