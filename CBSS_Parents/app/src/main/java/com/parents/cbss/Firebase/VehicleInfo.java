package com.parents.cbss.Firebase;

//차량 클래스
public class VehicleInfo {
    private String number;
    private String latitude;
    private String speed;
    private String driver;
    private String hardness;

    public VehicleInfo(){

    }

    public VehicleInfo(String number, String latitude, String speed, String driver, String hardness) {
        this.number = number;
        this.latitude = latitude;
        this.speed = speed;
        this.driver = driver;
        this.hardness = hardness;
    }

    public void init(int i, String s){
        switch (i){
            case 0:
                number = s;
                break;
            case 1:
                latitude = s;
                break;
            case 2:
                speed = s;
                break;
            case 3:
                driver =s;
                break;
            case 4:
                hardness = s;
                break;
        }
    }

    public String get(int i){
        switch (i){
            case 0:
                return number;
            case 1:
                return latitude;
            case 2:
                return speed;
            case 3:
                return driver;
            case 4:
                return hardness;
            default:
                return "";
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }
}
