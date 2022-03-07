package com.parents.cbss.Firebase;

//학부모 클래스
public class ParentsInfo {
    private String ID;
    private String PW;
    private String UUID;
    private String name;
    private String phoneNumber;

    public ParentsInfo(){
    }

    public ParentsInfo(String id, String name, String pw, String phoneNumber, String uuid) {
        ID = id;
        PW = pw;
        this.name = name;
        this.phoneNumber = phoneNumber;
        UUID = uuid;
    }

    public void init(int i, String s){
        switch (i){
            case 0:
                ID = s;
                break;
            case 1:
                PW = s;
                break;
            case 2:
                UUID = s;
                break;
            case 3:
                name = s;
                break;
            case 4:
                phoneNumber = s;
                break;
        }
    }

    public String get(int i){
        switch (i){
            case 0:
                return ID;
            case 1:
                return PW;
            case 2:
                return UUID;
            case 3:
                return name;
            case 4:
                return phoneNumber;
            default:
                return "";
        }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUIDList(String UUID) {
        this.UUID = UUID;
    }
}
