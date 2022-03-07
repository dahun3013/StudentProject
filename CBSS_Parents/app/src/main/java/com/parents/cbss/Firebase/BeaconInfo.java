package com.parents.cbss.Firebase;

//비콘 클래스
public class BeaconInfo {
    private String UUID;
    private String sort;
    private String name;
    private String number;
    private String token;

    public BeaconInfo(){

    }

    public BeaconInfo(String uuid, String number, String sort, String name, String token) {
        UUID = uuid;
        this.number = number;
        this.sort = sort;
        this.name = name;
        this.token = token;
    }

    public void init(int i, String s){
        switch (i) {
            case 0:
                UUID = s;
                break;
            case 1:
                sort = s;
                break;
            case 2:
                name = s;
                break;
            case 3:
                number = s;
                break;
            case 4:
                token = s;
                break;
        }
    }

    public String get(int i){
        switch (i){
            case 0:
                return UUID;
            case 1:
                return number;
            case 2:
                return sort;
            case 3:
                return name;
            case 4:
                return token;
            default:
                return "";
        }
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
