package webserver.controller;

public enum ContentType {
    HTML("text/html", "charset=utf-8", ""),
    CSS("text/css", "", ""),
    JAVASCRIPT("application/javascript", "", ""),
    TEXT("text/plain", "", "");

    private final String mediaType;
    private final String charset;
    private final String boundary;

    ContentType(String mediaType, String charset, String boundary) {
        this.mediaType = mediaType;
        this.charset = charset;
        this.boundary = boundary;
    }

    public String value() {
        String charsetToAppend = (charset == "") ? "" : (";" + charset);
        String boundaryToAppend = (boundary == "") ? "" : ( ";" + boundary);

        String value = mediaType + charsetToAppend + boundaryToAppend;

        return value;
    }
}