import java.util.HashMap;
import java.util.Map;

public class HardCodedNumbers {

    public static Map<String[], int[][]> hardCodedMap;

    static {
        hardCodedMap = new HashMap<String[], int[][]>();
        // treat ?? as -1 int value
        hardCodedMap.put(new String[]{"jpg", "jpeg"}, new int[][]{
                {0xFF, 0xD8, 0xFF, 0xDB},
                {0xFF, 0xD8, 0xFF, 0xE0, 0x00, 0x10, 0x4A, 0x46, 0x49, 0x46, 0x00, 0x01},
                {0xFF, 0xD8, 0xFF, 0xE1, -1, -1, 0x45, 0x78, 0x69, 0x66, 0x00, 0x00}});
        hardCodedMap.put(new String[]{"gif"}, new int[][]{
                {0x47, 0x49, 0x46, 0x38, 0x37, 0x61},
                {0x47, 0x49, 0x46, 0x38, 0x39, 0x61}});
    }

    static int[][] getMagicNumbersByExtension(String extension) {
        for (String[] extensions : hardCodedMap.keySet()) {
            if (BasicOperations.checkIfStringExistsInArray(extensions, extension)) {
                return hardCodedMap.get(extensions);
            }
        }
        return null;
    }

    static int getBytesToReadByExtension(String extension) {
        for (String[] key : hardCodedMap.keySet()) {
            if (BasicOperations.checkIfStringExistsInArray(key, extension)) {
                return BasicOperations.getMaxSizeofSingleArray(hardCodedMap.get(key));
            }
        }
        // if not found in hardcoded database return -1
        return -1;
    }

}
