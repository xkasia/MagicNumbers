package objects.extension;

import objects.handlers.GifHandler;
import objects.handlers.Handler;
import objects.handlers.JpgAndJpegHandler;
import objects.handlers.TxtHandler;

import java.util.ArrayList;
import java.util.List;

public class ExtensionsManager {
    private boolean checkIfProper;

    public String getExtension(String filePath) {
        int lastIndex = filePath.lastIndexOf(".");
        int length = filePath.length();
        String format = filePath.substring(lastIndex + 1, length);
        return format;
    }

    public void triggerHandler(String format, String filePath) {
        List<Handler> handTab;
        if ((handTab = checkIfExtensionMatch(format, filePath)) == null) {
            return;
        }

        for (Handler handler : handTab) {
            if (handler.handle(filePath)) {
                return;
            }
        }

        if (checkIfProper == false) {
            System.out.println("We haven't got such magic number for file "
                    + filePath + " in our DB, so we don't know what type of file it really is.");
        }
    }

    private List<Handler> checkIfExtensionMatch(String format, String filePath) {
        List<Handler> handTab = new ArrayList<Handler>();

        if (ExtensionType.GIF.isInExtensions(format)) {
            checkIfProper = new GifHandler().handle(filePath);
            if (checkIfProper) {
                return null;
            }
        } else {
            handTab.add(new GifHandler());
        }

        if (ExtensionType.JPG.isInExtensions(format)) {
            checkIfProper = new JpgAndJpegHandler().handle(filePath);
            if (checkIfProper) {
                return null;
            }
        } else {
            handTab.add(new JpgAndJpegHandler());
        }

        if (ExtensionType.TXT.isInExtensions(format)) {
            checkIfProper = new TxtHandler().handle(filePath);
            if (checkIfProper) {
                return null;
            }
        } else {
            handTab.add(new TxtHandler());
        }
        return handTab;
    }



}
