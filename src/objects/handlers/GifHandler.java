package objects.handlers;

import objects.bytesService.BytesEstimator;

public class GifHandler extends Handler {

    private int[][] magicNumbers = new int[][]{
            {0x47, 0x49, 0x46, 0x38, 0x37, 0x61},
            {0x47, 0x49, 0x46, 0x38, 0x39, 0x61}};

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
            System.out.println(filePath + " file is a gif.");
            return true;
        }
        return false;
    }
}
