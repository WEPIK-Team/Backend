package wepik.backend.module.file.dao;

public enum FileType {
    JPEG("image/jpeg", "jpeg"),
    PNG("image/png", "png"),
    GIF("image/gif", "gif");

    private final String mimeType;
    private final String fileExtension;

    FileType(String mimeType, String fileExtension) {
        this.mimeType = mimeType;
        this.fileExtension = fileExtension;
    }

    public String getMimeType() {
        return mimeType;
    }
}