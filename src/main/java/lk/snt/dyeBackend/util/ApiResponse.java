package lk.snt.dyeBackend.util;

public class ApiResponse {
    private String code;
    private String message;
    private Object content;

    // Getters and setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Object getContent() { return content; }
    public void setContent(Object content) { this.content = content; }
}

