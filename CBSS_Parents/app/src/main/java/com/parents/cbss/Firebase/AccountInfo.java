package com.parents.cbss.Firebase;

public class AccountInfo {
    private String id;
    private String pw;
    private String parents_name;
    private String child_name;
    private String uuid;
    private String phone_number;
    private String gender;

    public AccountInfo(){}

    public AccountInfo(String id, String pw, String parents_name, String child_name, String uuid, String phone_number, String gender){
        this.id = id;
        this.pw = pw;
        this.parents_name = parents_name;
        this.child_name = child_name;
        this.uuid = uuid;
        this.phone_number = phone_number;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getParents_name() {
        return parents_name;
    }

    public void setParents_name(String parents_name) {
        this.parents_name = parents_name;
    }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
