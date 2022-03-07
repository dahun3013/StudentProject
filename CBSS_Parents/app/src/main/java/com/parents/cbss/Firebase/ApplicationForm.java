package com.parents.cbss.Firebase;

import java.util.ArrayList;

//가입신청서 클래스
public class ApplicationForm {
    private String ID;
    private String PW;
    private String phoneNumber;
    private ArrayList<String >UUIDList;
    private String childName;
    private String token;


    public ApplicationForm(String id, String pw, String phoneNumber, ArrayList<String> uuidList, String childName, String token) {
        ID = id;
        PW = pw;
        this.phoneNumber = phoneNumber;
        UUIDList = uuidList;
        this.childName = childName;
        this.token = token;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPW() {
        return PW;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<String> getUUIDList() {
        return UUIDList;
    }

    public void setUUIDList(ArrayList<String> UUIDList) {
        this.UUIDList = UUIDList;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
