package com.parents.cbss.Firebase;

//운전자 클래스
public class DriverInfo {
    private String ID;
    private String PW;
    private String years;
    private String name;
    private String phoneNumber;

    public DriverInfo()
    {

    }

    public DriverInfo(String id, String pw, String name, String years, String phoneNumber) {
        ID = id;
        PW = pw;
        this.years = years;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void init(int i, String s){
        switch (i) {
            case 0:
                ID = s;
                break;
            case 1:
                PW = s;
                break;
            case 2:
                years = s;
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
                return years;
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

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
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
}
