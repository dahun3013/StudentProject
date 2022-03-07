package com.driver.cbss.firebase;

public class BeaconInfo {
    private String uuid;
    private String sort;
    private String name;
    private String number;
    private String token;

    public BeaconInfo(){}

    public BeaconInfo(String uuid, String number, String sort, String name, String token) {
        this.uuid = uuid;
        this.number = number;
        this.sort = sort;
        this.name = name;
        this.token = token;
    }

    public String getUUID() {
        return uuid;
    }
    public String getSort() {
        return sort;
    }
    public String getName() {
        return name;
    }
    public String getNumber() {
        return number;
    }
    public String getToken() {
        return token;
    }
}
