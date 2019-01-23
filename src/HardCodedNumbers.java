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

    static int getMaxLengthOfKnownMagicNumbers() {
        int maxSize = -1;
        int newSize;
        for (String[] key : hardCodedMap.keySet()) {
            for (int[] array : hardCodedMap.get(key)) {
                newSize = array.length;
                if (newSize > maxSize) {
                    maxSize = newSize;
                }
            }
        }
        return maxSize;
    }

    static String[] getKeysByMagicNumber(byte[] inByteArray) {
        int[] intArr = BasicOperations.convertByteArrayToIntArray(inByteArray);
        //intArr always will be greater or equal to every magicNumber,
        // because we read earlier maximum amount of bytes for the longest magic number

        for (String[] keySet : hardCodedMap.keySet()) {
            int[][] magicNumbers = hardCodedMap.get(keySet);

            for (int[] magicNumber : magicNumbers) {
                for (int i = 0; i < magicNumber.length; i++) {
                    if (magicNumber[i] == -1) {
                        continue;
                    }
                    if (intArr[i] == magicNumber[i]) {
                        if (i == magicNumber.length - 1) {
                            // we found proper magic number
                            return keySet;
                        }
                        continue;
                    } else {
                        break;
                    }
                }
            }
        }
        return null;
    }




}
