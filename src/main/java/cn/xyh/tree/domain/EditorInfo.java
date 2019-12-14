package cn.xyh.tree.domain;

public class EditorInfo {
    private int success;
    private String url;
    private String message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EditorInfo(int success, String url, String message) {
        this.success = success;
        this.url = url;
        this.message = message;
    }
}
