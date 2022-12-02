package solution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
    public Reader() {

    }

    public List<String> getItems(String fileName) {
        List<String> result = null;
        try (Stream<String> lines = Files.lines(Paths.get("src/main/java/solution/" + fileName))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
