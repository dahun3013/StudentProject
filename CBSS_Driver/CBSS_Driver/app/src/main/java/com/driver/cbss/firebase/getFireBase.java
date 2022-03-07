package com.driver.cbss.firebase;


import com.driver.cbss.variable.Var;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class getFireBase {

    public void getBeaconList(){
        /** 경로 */
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("비콘리스트");
        /** 읽기 */
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    /** 저장 */
                    BeaconInfo beaconInfo = snapshot.getValue(BeaconInfo.class);
                    if(beaconInfo.getSort().equals("어린이")){
                        Var.DB_Child_List.add(beaconInfo.getUUID());
                        Var.Match_Parents_Key_List.put(beaconInfo.getUUID().toString(),beaconInfo.getToken().toString());
                        Var.Match_Child_Name_List.put(beaconInfo.getUUID().toString(),beaconInfo.getName().toString());
                    }else if(beaconInfo.getSort().equals("키")){
                        Var.DB_Key_List.add(beaconInfo.getUUID());
                    }else if(beaconInfo.getSort().equals("문")){
                        Var.DB_Door_List.add(beaconInfo.getUUID());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    /** 차량리스트 - 차번호 - 현재경도, 현재위도, 현재운전자 */
    public void getWriteCarList(){

    }

    /** 비콘리스트 - 해당 비콘 - number */
    public void getWriteBeaconList(){

    }

}
