package cn.xyh.tree.domain;

import java.util.Map;

/**
 * 封装返回信息
 */
public class ResultInfo {
    private String message;
    private int status;
    private Map<String, Object> map;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public ResultInfo(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public ResultInfo(String message, int status, Map<String, Object> map) {
        this.message = message;
        this.status = status;
        this.map = map;
    }
}
