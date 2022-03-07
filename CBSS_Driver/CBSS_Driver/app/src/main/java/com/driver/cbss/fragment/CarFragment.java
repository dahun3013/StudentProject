package com.driver.cbss.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.driver.cbss.R;
import com.driver.cbss.bluetooth.BluetoothListAdapter;
import com.driver.cbss.variable.Var;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class CarFragment extends Fragment {
    View rootView;
    BluetoothAdapter bluetoothAdapter;
    BluetoothListAdapter bluetoothListAdapter;
    GridView listView;
    public static ImageView key_image;
    public static ImageView door_image;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_car, container, false);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        key_image = rootView.findViewById(R.id.bluetooth_key_image);
        door_image = rootView.findViewById(R.id.bluetooth_bus_image);



        /** 스캔결과를 담을 리스트뷰 초기화 및 어댑터 등록 */
        listView = (GridView)rootView.findViewById(R.id.beacon_list_view);
        bluetoothListAdapter = new BluetoothListAdapter();
        listView.setAdapter(bluetoothListAdapter); //리스트뷰에 리스트 어댑터 등록

        bluetoothListAdapter.BLE_Clear();
        bluetoothListAdapter.notifyDataSetChanged();

        return rootView;
    }


    public void onResume(){
        super.onResume();
        bluetoothAdapter.startLeScan(leScanCallback);
    }

    public void onStop(){
        super.onStop();
        bluetoothAdapter.stopLeScan(leScanCallback);
    }

    public static int send_count = 100;
    /** 주변 블루투스 기기 스캔 */
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
            if(bluetoothDevice != null){
                bluetoothListAdapter.addDevice(bluetoothDevice);
                bluetoothListAdapter.notifyDataSetChanged();
            }

            if(send_count < 0){
                send_count = 100;
                //탐지된 어린이가 1명 이상이고, 탐지된 문이 있으며 (문닫힘), 키가 탐지되지 않을 때
                if(Var.Scanning_Child_List.size()>0 && Var.Scanning_Door_List.size()>0 && Var.Scanning_Key_List.size()==0){
                    sendMessage();
                }
            }else{
                send_count--;
            }
        }
    };


    /** 위험 신호 전송 */
    public void sendMessage(){
        AsyncTask.execute(new Runnable(){
            @Override
            public void run(){
                for(int i=0; i<Var.Scanning_Child_List.size(); i++){
                    //메시지 가공
                    JsonObject jsonObj = new JsonObject();
                    //token
                    Gson gson = new Gson();
                    // 메세지를 보내고 싶은 특정 기기의 토큰값 설정
                    JsonElement jsonElement = gson.toJsonTree(Var.Match_Parents_Key_List.get(Var.Scanning_Child_List.get(i).getDeviceAddress()));
                    jsonObj.add("to", jsonElement);

                    //Notification 보내고 싶은 메세지 제목과 내용 기입
                    JsonObject notification = new JsonObject();
                    notification.addProperty("title", "❗ 위험알림 ❗");
                    notification.addProperty("body", Var.Match_Child_Name_List.get(Var.Scanning_Child_List.get(i).getDeviceAddress())+" 어린이가 차량에 혼자 있습니다.");
                    jsonObj.add("notification", notification);

                    /*발송*/
                    final MediaType mediaType = MediaType.parse("application/json");
                    OkHttpClient httpClient = new OkHttpClient();
                    try {
                        // Authorization의 키 값으로 서버 키 값을 할당해 주어야 함
                        Request request = new Request.Builder().url("https://fcm.googleapis.com/fcm/send")
                                .addHeader("Content-Type", "application/json; UTF-8")
                                .addHeader("Authorization", "key=" + "AAAAjc0VqEU:APA91bHtKm6EEA37hDZVWPkd_QfiLiAzupQ7JrR1oaOO4iSQPgm-2srVqMekVIqgz2NiNuLscyS6ExSO2HFIoTEA_6W9W7BtLZQRkS2O43aTsq_SbOt0_KJH-A0-IFKx3EmNf6FOc79l")
                                .post(RequestBody.create(mediaType, jsonObj.toString())).build();
                        Response response = httpClient.newCall(request).execute();
                        String res = response.body().string();
                        Log.i("TEST","notification response "+ res);
                    } catch (IOException e) {
                        Log.i("TEST","Error in sending message to FCM server "+e);
                    }
                }
            }
        });
    }
}