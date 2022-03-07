package com.parents.cbss.Firebase;

import java.util.HashMap;

//탑승목록 클래스(학부모용에서는 안쓰일 듯)
public class BoardingList {
    private HashMap<String,BeaconInfo> list;

    public BoardingList(HashMap<String, BeaconInfo> list) {
        reset();
    }

    public void reset(){
        list = new HashMap<String,BeaconInfo>();
    }

    public HashMap<String, BeaconInfo> getList() {
        return list;
    }

    public void setList(HashMap<String, BeaconInfo> list) {
        this.list = list;
    }
}
