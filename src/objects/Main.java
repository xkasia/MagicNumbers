package objects;

import objects.extension.ExtensionsManager;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No files.");
            return;
        }
        for (String filePath : args) {
            File file = new File(filePath);
            //sanity checks
            if (!file.exists() || file.isDirectory()) {
                System.out.println("File " + filePath
                        + " is directory or it does not exist.");
                continue;
            }
            ExtensionsManager extensionsManager = new ExtensionsManager();
            String extension = extensionsManager.getExtension(filePath);
            extensionsManager.triggerHandler(extension, filePath);
        }
    }
}
