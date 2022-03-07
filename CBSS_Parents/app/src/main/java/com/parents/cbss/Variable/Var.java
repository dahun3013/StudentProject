package com.parents.cbss.Variable;

import com.parents.cbss.Firebase.BeaconInfo;
import com.parents.cbss.Firebase.DriverInfo;
import com.parents.cbss.Firebase.ParentsInfo;
import com.parents.cbss.Firebase.VehicleInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class Var {
    public static String MainActivity_Parent_Name ="부모 이름";
    public static String MainActivity_Child_Name = "어린이 이름";

    public static ParentsInfo parentsInfo = new ParentsInfo();
    public static BeaconInfo childInfo = new BeaconInfo();
    public static DriverInfo driverInfo = new DriverInfo();
    public static VehicleInfo vehicleInfo = new VehicleInfo();
}
