package com.driver.cbss.variable;

import android.bluetooth.BluetoothDevice;

import com.driver.cbss.bluetooth.BluetoothItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Var {
    public static String MainActivity_Driver_Name ="운전자 이름";
    public static String MainActivity_Car_Number = "0000";

    /** 주변 장치 스캔으로 얻은 결과에서 DB에 있는 것들만 간추린 비콘 목록 */
    public static ArrayList<BluetoothItem> Scanning_Child_List = new ArrayList<>();
    public static ArrayList<BluetoothDevice> Scanning_Child_Deivce = new ArrayList<>();
    public static ArrayList<BluetoothItem> Scanning_Door_List = new ArrayList<>();
    public static ArrayList<BluetoothDevice> Scanning_Door_Deivce = new ArrayList<>();
    public static ArrayList<BluetoothItem> Scanning_Key_List = new ArrayList<>();
    public static ArrayList<BluetoothDevice> Scanning_Key_Deivce = new ArrayList<>();

    /** DB에 들어있는 어린이비콘, 차량문비콘, 열쇠비콘 목록 */
    public static ArrayList<String> DB_Child_List = new ArrayList<>();
    public static ArrayList<String> DB_Door_List = new ArrayList<>();
    public static ArrayList<String> DB_Key_List = new ArrayList<>();

    /** 어린이 비콘과 매칭되는 부모 스마트폰 기기의 key 값 */
    public static HashMap<String,String> Match_Parents_Key_List = new HashMap<>();
    /** 어린이 비콘과 매칭되는 어린이 이름 */
    public static HashMap<String,String> Match_Child_Name_List = new HashMap<>();

    /** 현재 차량의 위도 경도 시속 */
    public static String Latitude; //위도
    public static String Longitude; //경도
    public static String Speed; //시속

}
