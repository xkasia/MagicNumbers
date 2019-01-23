import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

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

    static int getMaxSizeofSingleArray(int[][] multiArray) {
        int maxSize = -1;
        int newSize;
        for (int[] array : multiArray) {
            newSize = array.length;
            if (newSize > maxSize) {
                maxSize = newSize;
            }
        }
        return maxSize;
    }

    static boolean checkIfStringExistsInArray(String[] array, String toFound) {
        List<String> keyList = Arrays.asList(array);
        return keyList.contains(toFound);
    }

    static int[] convertByteArrayToIntArray(byte[] arr) {
        int len = arr.length;
        int convertedBytesToInts[] = new int[len];
        for (int i = 0; i < len; i++) {
            convertedBytesToInts[i] = arr[i] & 0xFF;
        }
        return convertedBytesToInts;
    }
}
