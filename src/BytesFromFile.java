import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BytesFromFile {

    static byte[] getBytesFromFile(String fileName, int maxLenghMagicNumber) {

        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        byte bytesReadFromFile[] = new byte[maxLenghMagicNumber];
        int len;
        int readBytes = 0;
        do {
            try {
                len = fileInputStream.read(bytesReadFromFile, 0, maxLenghMagicNumber);
                readBytes += len;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        while (len != -1 && readBytes < maxLenghMagicNumber);

        return bytesReadFromFile;
    }
}
