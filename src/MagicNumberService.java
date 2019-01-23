import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class MagicNumberService {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Brak plików.");
            return;
        }
        for (String filePath : args) {
            // main loop where we are checking each file
            byte[] bytesFromFile;

            File file = new File(filePath);
            //sanity checks
            if (!file.exists() || file.isDirectory()) {
                System.out.println("File " + filePath
                        + " is directory or it does not exist.");
                continue;
            }

            String givenFileExtension = BasicOperations.getExtension(filePath);
            // case when extension is txt
            if (checkIfTxtExtension(givenFileExtension)) {
                if ((bytesFromFile = BytesFromFile.getBytesFromFile(filePath,
                        BasicOperations.getSizeOfFile(filePath))) == null) {
                    //error case e.printStackTrace() printed
                    continue;
                }
                if (checkIfFileIsUTF8(bytesFromFile)) {
                    System.out.println(filePath + " file is txt.");
                    continue;
                }
                System.out.println(filePath + " has extension " + givenFileExtension
                        + " but magic number doesn't match to it.");
            }
        }
    }

    private static boolean checkIfTxtExtension(String filePath) {
        String extension;
        extension = BasicOperations.getExtension(filePath);
        if (extension.equals("txt")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkIfFileIsUTF8(byte[] barr) {

        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        ByteBuffer buf = ByteBuffer.wrap(barr);

        try {
            decoder.decode(buf);
        } catch (CharacterCodingException e) {
            return false;
        }
        return true;
    }
}
