package com.driver.cbss.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.driver.cbss.R;
import com.driver.cbss.fragment.CarFragment;
import com.driver.cbss.variable.Var;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BluetoothListAdapter extends BaseAdapter {

    /** 모든 비콘목록 클리어 */
    public void BLE_Clear(){
        Var.Scanning_Child_List.clear();
        Var.Scanning_Door_List.clear();
        Var.Scanning_Key_List.clear();
    }

    /** 감지 비콘 추가 메소드 */
    public void addDevice(BluetoothDevice device){
        String check_adderss = device.getAddress();
        //어린이 비콘인 경우
        if(Var.DB_Child_List.contains(check_adderss)){
            addChildDevice(device);
        }
        //문 비콘인 경우
        else if(Var.DB_Door_List.contains(check_adderss)){
            addDoorDevice(device);
        }
        //열쇠 비콘인 경우
        else if(Var.DB_Key_List.contains(check_adderss)){
            addKeyDevice(device);
        }
        Device_List_Time();
        //키 감지 표시
        if(Var.Scanning_Key_List.size()==0){
            CarFragment.key_image.setImageResource(R.drawable.bluetooth_key_not);
        }else{
            CarFragment.key_image.setImageResource(R.drawable.bluetooth_key);
        }
        //문 열림닫힘 표시
        if(Var.Scanning_Door_List.size()==0){
            CarFragment.door_image.setImageResource(R.drawable.bus_open);
        }else{
            CarFragment.door_image.setImageResource(R.drawable.bus_close);
        }
    }
    /** 탐지된 비콘 생존주기 */
    private void Device_List_Time(){
        /** 어린이 비콘 */
        for(int i=0; i<Var.Scanning_Child_Deivce.size();i++){
            Var.Scanning_Child_List.get(i).setLifeCount();
            if(Var.Scanning_Child_List.get(i).getLifeCount() < 0){
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("비콘리스트").child(Var.Scanning_Child_List.get(i).getDeviceAddress());
                myRef.child("number").setValue("");
                Var.Scanning_Child_List.remove(i);
                Var.Scanning_Child_Deivce.remove(i);
            }
        }
        /** 문 비콘 */
        for(int i=0; i<Var.Scanning_Door_Deivce.size();i++){
            Var.Scanning_Door_List.get(i).setLifeCount();
            if(Var.Scanning_Door_List.get(i).getLifeCount() < 0){
                Var.Scanning_Door_List.remove(i);
                Var.Scanning_Door_Deivce.remove(i);
            }
        }
        /** 열쇠 비콘 */
        for(int i=0; i<Var.Scanning_Key_Deivce.size();i++){
            Var.Scanning_Key_List.get(i).setLifeCount();
            if(Var.Scanning_Key_List.get(i).getLifeCount() < 0){
                Var.Scanning_Key_List.remove(i);
                Var.Scanning_Key_Deivce.remove(i);
            }
        }
    }

    /** 어린이 비콘 추가 */
    private void addChildDevice(BluetoothDevice device){
        String device_name = device.getName();
        String device_addr = device.getAddress();
        //이미 추가가 되어있는 경우
        if(Var.Scanning_Child_Deivce.contains(device) == true){
            Var.Scanning_Child_List.set(
                    Var.Scanning_Child_Deivce.indexOf(device),
                    new BluetoothItem(
                            device_name,
                            device_addr,
                            Var.Match_Child_Name_List.get(device_addr),
                            Var.Match_Parents_Key_List.get(device_addr),
                            200)
            );
        }
        //새롭게 추가해야 하는 경우
        else{
            Var.Scanning_Child_List.add(
                    new BluetoothItem(
                            device_name,
                            device_addr,
                            Var.Match_Child_Name_List.get(device_addr),
                            Var.Match_Parents_Key_List.get(device_addr),
                            200)
            );
            Var.Scanning_Child_Deivce.add(device);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("비콘리스트").child(device_addr);
            myRef.child("number").setValue(Var.MainActivity_Car_Number);
        }
    }
    /** 문 비콘 추가 */
    private void addDoorDevice(BluetoothDevice device){
        String device_name = device.getName();
        String device_addr = device.getAddress();
        //이미 추가가 되어있는 경우
        if(Var.Scanning_Door_Deivce.contains(device)){
            Var.Scanning_Door_List.set(
                    Var.Scanning_Door_Deivce.indexOf(device),
                    new BluetoothItem(
                            device_name,
                            device_addr,
                            "문",
                            "문",
                            100)
            );
        }
        //새롭게 추가해야 하는 경우
        else{
            Var.Scanning_Door_List.add(
                    new BluetoothItem(
                            device_name,
                            device_addr,
                            "문",
                            "문",
                            100)
            );
            Var.Scanning_Door_Deivce.add(device);
        }
    }
    /** 열쇠 비콘 추가 */
    private void addKeyDevice(BluetoothDevice device){
        String device_name = device.getName();
        String device_addr = device.getAddress();
        //이미 추가가 되어있는 경우
        if(Var.Scanning_Key_Deivce.contains(device)){
            Var.Scanning_Key_List.set(
                    Var.Scanning_Key_Deivce.indexOf(device),
                    new BluetoothItem(
                            device_name,
                            device_addr,
                            "열쇠",
                            "열쇠",
                            200)
            );
        }
        //새롭게 추가해야 하는 경우
        else{
            Var.Scanning_Key_List.add(
                    new BluetoothItem(
                            device_name,
                            device_addr,
                            "열쇠",
                            "열쇠",
                            200)
            );
            Var.Scanning_Key_Deivce.add(device);
        }
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.bluetooth_listitem, viewGroup, false);

            TextView textView = view.findViewById(R.id.bluetooth_list_item_name);
            BluetoothItem bluetoothItem = Var.Scanning_Child_List.get(i);
            textView.setText(bluetoothItem.getChild_Name());

        }
        return view;
    }

    @Override
    public int getCount() {
        return Var.Scanning_Child_List.size();
    }

    @Override
    public Object getItem(int position) {
        return Var.Scanning_Child_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
