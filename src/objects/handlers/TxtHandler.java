package objects.handlers;

import objects.bytesService.BytesEstimator;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class TxtHandler extends Handler {

    @Override
    public boolean handle(String filePath) {

        if ((bytesFromFile = BytesEstimator.getBytesFromFile(filePath,
                getSizeOfFile(filePath))) == null) {
            //error case e.printStackTrace() printed
            return false;
        }
        if (checkIfFileIsUTF8(bytesFromFile)) {
            System.out.println(filePath + " file is a txt(utf-8).");
            return true;
        }
        return false;
    }

    private static boolean checkIfFileIsUTF8(byte[] barr) {
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
