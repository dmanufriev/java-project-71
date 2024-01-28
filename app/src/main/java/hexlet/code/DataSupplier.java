package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSupplier {

    public static String getData(String filePath) throws Exception {
        return Files.readString(getPath(filePath));
    }

    public static Path getPath(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }

    public static String getFileExtension(String filePath) throws Exception {
        int index = filePath.lastIndexOf(".");
        if ((index < 0) || (index == filePath.length() - 1)) {
            throw new Exception("File '" + filePath + "' has wrong extension");
        }
        return filePath.substring(index + 1).toUpperCase();
    }
}
