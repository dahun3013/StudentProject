package com.driver.cbss.bluetooth;
/** 탐지된 주변 블루투스 기기의 결과를 가지고 리스트에 담을 아이템 클래스 */
public class BluetoothItem {
    private String DeviceName;
    private String DeviceAddress;
    private String Child_Name;
    private String Parents_Key;
    private int LifeCount;


    public BluetoothItem(String deviceName, String deviceAddress, String child_Name, String parents_Key, int lifeCount){
        this.DeviceName = deviceName;
        this.DeviceAddress = deviceAddress;
        this.Child_Name = child_Name;
        this.Parents_Key = parents_Key;
        this.LifeCount = lifeCount;
    }

    public String getChild_Name(){
        return Child_Name;
    }
    public String getParents_Key(){
        return Parents_Key;
    }
    public String getDeviceAddress(){return this.DeviceAddress;}
    public int getLifeCount(){
        return this.LifeCount;
    }
    public void setLifeCount(){
        this.LifeCount--;
    }
}
