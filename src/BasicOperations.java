import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class BasicOperations {

    static String getExtension(String filePath) {
        int lastIndex = filePath.lastIndexOf(".");
        int length = filePath.length();

        String format = filePath.substring(lastIndex + 1, length);
        return format;
    }

    static int getSizeOfFile(String filePath) {
        try {
            return (int) Files.size(new File(filePath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
