import java.io.File;

public class MagicNumberService {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Brak plików.");
            return;
        }
        for (String filePath : args) {
            // main loop where we are checking each file

            File file = new File(filePath);
            //sanity checks
            if (!file.exists() || file.isDirectory()) {
                System.out.println("File " + filePath
                        + " is directory or it does not exist.");
                continue;
            }
        }
    }
}
