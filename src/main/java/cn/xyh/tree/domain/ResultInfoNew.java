package cn.xyh.tree.domain;

import java.util.HashMap;
import java.util.Map;

public class ResultInfoNew {
    private String Message;
    private int status;
    private Map<String, Object> map = new HashMap<>();

    //成功
    public static ResultInfoNew success(String message) {
        ResultInfoNew resultInfoNew = new ResultInfoNew();
        resultInfoNew.status = 1;
        resultInfoNew.Message = message;
        return resultInfoNew;
    }

    //失败
    public static ResultInfoNew fail(String message) {
        ResultInfoNew resultInfoNew = new ResultInfoNew();
        resultInfoNew.status = 0;
        resultInfoNew.Message = message;
        return resultInfoNew;
    }

    //添加附加信息
    public ResultInfoNew addOthers(String s, Object o) {
        this.map.put(s, o);
        return this;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
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
}
