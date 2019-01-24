package objects.handlers;

import objects.bytesService.BytesEstimator;

public class JpgAndJpegHandler extends Handler {

    private int[][] magicNumbers = new int[][]{
            {0xFF, 0xD8, 0xFF, 0xDB},
            {0xFF, 0xD8, 0xFF, 0xE0, 0x00, 0x10, 0x4A, 0x46, 0x49, 0x46, 0x00, 0x01},
            {0xFF, 0xD8, 0xFF, 0xE1, -1, -1, 0x45, 0x78, 0x69, 0x66, 0x00, 0x00}};

    @Override
    public boolean handle(String filePath) {

        if ((bytesFromFile = BytesEstimator.getBytesFromFile(filePath,
                getSizeOfFile(filePath))) == null) {
            //error case e.printStackTrace() printed
            return false;
        }

        if ((bytesToRead = getMaxSizeofSingleArray(magicNumbers)) == -1) {
            return false;
        } else if (checkIfReadBytesMatchHardcodedMagicNumbers(bytesFromFile, magicNumbers)) {
            System.out.println(filePath + " file is a jpeg or jpg.");
            return true;
        }
        return false;
    }
}
