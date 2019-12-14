package cn.xyh.tree.domain;

import cn.xyh.tree.util.toolImpl.Md5Util;

import java.io.Serializable;

import static cn.xyh.tree.util.toolImpl.Md5Util.encodeByMd5;

/**
 * 用户类
 */
public class User implements Serializable {
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_tel;
    private String user_sex;
    private String user_email;
    private int user_authority;
    private String user_idnumber;
    private String user_statecode;
    private String user_code;

    private String user_imgs;
    private String user_city;
    private String user_nickname;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = encodeByMd5(user_password);
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public int getUser_authority() {
        return user_authority;
    }

    public void setUser_authority(int user_authority) {
        this.user_authority = user_authority;
    }

    public String getUser_idnumber() {
        return user_idnumber;
    }

    public void setUser_idnumber(String user_idnumber) {
        this.user_idnumber = user_idnumber;
    }

    public String getUser_statecode() {
        return user_statecode;
    }

    public void setUser_statecode(String user_statecode) {
        this.user_statecode = user_statecode;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_imgs() {
        return user_imgs;
    }

    public void setUser_imgs(String user_imgs) {
        this.user_imgs = user_imgs;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_tel='" + user_tel + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_authority=" + user_authority +
                ", user_idnumber='" + user_idnumber + '\'' +
                ", user_statecode='" + user_statecode + '\'' +
                ", user_code='" + user_code + '\'' +
                ", user_imgs='" + user_imgs + '\'' +
                ", user_city='" + user_city + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                '}';
    }

    public User(int user_id) {
        this.user_id = user_id;
    }

    public User() {}
}
