package objects.handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class Handler {

    byte[] bytesFromFile;
    int bytesToRead;

    public abstract boolean handle(String filePath);

    int getMaxSizeofSingleArray(int[][] multiArray) {
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

    int getSizeOfFile(String filePath) {
        try {
            return (int) Files.size(new File(filePath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    boolean checkIfReadBytesMatchHardcodedMagicNumbers(byte[] readFromFile, int[][] magicNumbers) {
        for (int[] magicNumber : magicNumbers) {
            if (readFromFile.length < magicNumber.length) {
                continue;
            }
            int lenOfActualMagicNumber = magicNumber.length;
            int count = 0;
            for (int readInt : magicNumber) {
                if (readFromFile[count] != readInt) {
                    count++;
                    continue;
                }
                count++;
                if (count == lenOfActualMagicNumber - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
