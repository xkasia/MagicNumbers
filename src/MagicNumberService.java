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
            int bytesToRead;
            File file = new File(filePath);
            byte[] bytesFromFile;

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
            // case where extension is not txt, but it is in our DB
            else if ((bytesToRead = HardCodedNumbers.getBytesToReadByExtension(givenFileExtension)) != -1) {
                if ((bytesFromFile = BytesFromFile.getBytesFromFile(filePath, bytesToRead)) == null) {
                    //error case e.printStackTrace() printed
                    continue;
                }
                int[][] magicNumbers = HardCodedNumbers.getMagicNumbersByExtension(givenFileExtension);

                if (checkIfReadBytesMatchHardcodedMagicNumbers(bytesFromFile, magicNumbers)) {
                    System.out.println(filePath + " file is " + givenFileExtension + ".");
                    continue;
                }
                System.out.println(filePath + " has extension "
                        + givenFileExtension + " but magic number doesn't match to it.");
            }

            // case where we haven't got such extension in our db or extension doesn't match magic number
            // now we are checking if we have such magic number in our DB
            int maxLengthOfKnownMagicNumbers = HardCodedNumbers.getMaxLengthOfKnownMagicNumbers();
            if ((bytesFromFile = BytesFromFile.getBytesFromFile(filePath, maxLengthOfKnownMagicNumbers)) == null) {
                //error case e.printStackTrace() printed
                continue;
            }

            String[] keysByMagicNumber;
            // case where we found extension via read bytes in our DB
            if ((keysByMagicNumber = HardCodedNumbers.getKeysByMagicNumber(bytesFromFile)) != null) {
                String possibleExtensions = "";
                for (String key : keysByMagicNumber) {
                    possibleExtensions += key + ", ";
                }
                System.out.println("Given file " + filePath + " has " + possibleExtensions + "format.");
                continue;
            }
            // case where file is utf-8
            if ((bytesFromFile = BytesFromFile.getAllBytesFromFile(filePath)) == null) {
                //error case e.printStackTrace() printed
                continue;
            }
            if (checkIfFileIsUTF8(bytesFromFile)) {
                System.out.println(filePath + " file is txt (utf-8).");
                continue;
            }

            System.out.println("We haven't got such magic number for file "
                    + filePath + " in our DB, so we don't know what type of file it really is.");
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

    private static boolean checkIfTxtExtension(String filePath) {
        String extension;
        extension = BasicOperations.getExtension(filePath);
        if (extension.equals("txt")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean checkIfReadBytesMatchHardcodedMagicNumbers(byte[] readFromFile, int[][] magicNumbers) {
        for (int[] magicNumber : magicNumbers) {
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
