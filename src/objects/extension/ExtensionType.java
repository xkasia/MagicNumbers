package objects.extension;

public enum ExtensionType {
    GIF(new String[]{"gif"}),
    TXT(new String[]{"txt"}),
    JPG(new String[]{"jpg, jpeg"});

    ExtensionType(String[] _ext) {
        ext = _ext;
    }

    String[] ext;

    boolean isInExtensions(String inExt) {
        for (String iterArr : ext) {
            if (inExt.equalsIgnoreCase(iterArr)) {
                return true;
            }
        }
        return false;
    }
}
