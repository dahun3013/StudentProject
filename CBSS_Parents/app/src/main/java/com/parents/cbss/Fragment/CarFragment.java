package com.parents.cbss.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parents.cbss.Activity.LoginActivity;
import com.parents.cbss.Activity.MainActivity;
import com.parents.cbss.Firebase.getFireBase;
import com.parents.cbss.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parents.cbss.Variable.Var;

public class CarFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap gmap;
    CameraUpdate cameraUpdate;
    LatLng xy;
    View rootView;
    MapView mapView;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    private DatabaseReference databaseReference;

    public void CarFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_car, container, false);
        mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        tv1 = (TextView) rootView.findViewById(R.id.textView);
        tv2 = (TextView) rootView.findViewById(R.id.textView3);
        tv3 = (TextView) rootView.findViewById(R.id.textView7);
        tv4 = (TextView) rootView.findViewById(R.id.text_home);
        tv1.setText("이름   : " + Var.driverInfo.getName());
        tv2.setText("연락처: " + Var.driverInfo.getPhoneNumber());
        tv3.setText("경력   : " + Var.driverInfo.getYears());
        tv4.setText(Var.vehicleInfo.getSpeed()+"Km/h");
        getVehicleInfo();
        return rootView;
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        MapsInitializer.initialize(this.getActivity());
        xy = new LatLng(35.141233, 126.925594);
        // Updates the location and zoom of the MapView
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(xy, 14);

        googleMap.animateCamera(cameraUpdate);

        googleMap.addMarker(new MarkerOptions()
                .position(xy)
                .title("루프리코리아"));

        System.out.println("구글맵 준비 호출");
    }

    //변경사항이 있는 경우 정보 업데이트
    public void getVehicleInfo(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("차량리스트").child(Var.childInfo.getNumber());
        /** 읽기 */
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //변경사항이 있는 경우 정보 업데이트
                    switch (i){
                        case 1:
                            if(!Var.vehicleInfo.get(i).equals(snapshot.getValue()+"")){
                                Var.vehicleInfo.init(i,snapshot.getValue()+"");
                                //경도 업데이트
                                Log.i("TEST","경도변화 감지...");
                                gmap.clear();
                                xy = new LatLng(xy.latitude,Double.parseDouble(snapshot.getValue()+""));
                                cameraUpdate = CameraUpdateFactory.newLatLngZoom(xy, 14);
                                gmap.animateCamera(cameraUpdate);
                                gmap.addMarker(new MarkerOptions()
                                        .position(xy)
                                        .title("현재위치"));
                            }
                            break;
                        case 2:
                            if(!Var.vehicleInfo.get(i).equals(snapshot.getValue()+"")) {
                                Var.vehicleInfo.init(i,snapshot.getValue()+"");
                                //시속 업데이트
                                Log.i("TEST", "시속변화 감지...");
                                tv4.setText(snapshot.getValue() + "Km/h");
                            }
                            break;
                        case 3:
                            //운전자 업데이트
                            if(!Var.vehicleInfo.get(i).equals(snapshot.getValue()+"")) {
                                Var.vehicleInfo.init(i,snapshot.getValue()+"");
                                new getFireBase().getDriverInfo();
                                Log.i("TEST", "운전자변화 감지...");
                                Handler mHandler = new Handler();
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv1.setText("이름   : " + Var.driverInfo.getName());
                                        tv2.setText("연락처: " + Var.driverInfo.getPhoneNumber());
                                        tv3.setText("경력   : " + Var.driverInfo.getYears());
                                    }
                                }, 500);
                            }
                            break;

                        case 4:
                            if(!Var.vehicleInfo.get(i).equals(snapshot.getValue()+"")) {
                                Var.vehicleInfo.init(i,snapshot.getValue()+"");
                                //위도 업데이트
                                Log.i("TEST", "위도변화 감지...");
                                gmap.clear();
                                xy = new LatLng(Double.parseDouble(snapshot.getValue() + ""), xy.longitude);
                                cameraUpdate = CameraUpdateFactory.newLatLngZoom(xy, 14);
                                gmap.animateCamera(cameraUpdate);
                                gmap.addMarker(new MarkerOptions()
                                        .position(xy)
                                        .title("현재위치"));
                            }
                            break;
                        default:
                            break;
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}