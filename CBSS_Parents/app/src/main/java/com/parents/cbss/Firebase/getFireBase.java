package com.parents.cbss.Firebase;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parents.cbss.Variable.Var;

public class getFireBase {
    private DatabaseReference databaseReference;

    //입력된 ID를 통한 학부모정보 가져오기
    public void getUserID(String ID){
        Var.parentsInfo.setID(ID);
        /** 경로 */
        databaseReference = FirebaseDatabase.getInstance().getReference().child("학부모리스트").child(Var.parentsInfo.getID());
        /** 읽기 */
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int i = 1;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        /** 저장 */
                        Var.parentsInfo.init(i,snapshot.getValue()+"");
                        Log.i("TEST","학부모리스트 Key: "+snapshot.getKey()+", Value = "+Var.parentsInfo.get(i)+"");
                        i++;
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void setToken(String token){
        /** 경로 */
        databaseReference = FirebaseDatabase.getInstance().getReference().child("비콘리스트").child(Var.childInfo.getUUID());
        /** 읽기 */
        Var.childInfo.setToken(token);
        databaseReference.setValue(Var.childInfo);
    }

    //학부모정보에 속한 UUID를 통한 비콘정보 가져오기
    public void getChildInfo(){
        Var.childInfo.setUUID(Var.parentsInfo.getUUID());
        databaseReference = FirebaseDatabase.getInstance().getReference().child("비콘리스트").child(Var.childInfo.getUUID());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int i = 1;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        /** 저장 */
                        Var.childInfo.init(i, snapshot.getValue() + "");
                        Log.i("TEST", "비콘리스트 Key: " + snapshot.getKey() + ", Value = " + Var.childInfo.get(i) + "");
                        i++;
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    //비콘정보에 속한 차량번호를 통한 차량정보 가져오기
    public void getVehicleInfo(){
        Var.vehicleInfo.setNumber(Var.childInfo.getNumber());
        databaseReference = FirebaseDatabase.getInstance().getReference().child("차량리스트").child(Var.vehicleInfo.getNumber());
        /** 읽기 */
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=1;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    /** 저장 */
                    Var.vehicleInfo.init(i,snapshot.getValue()+"");
                    Log.i("TEST","차량리스트 Key: "+snapshot.getKey()+", Value = "+snapshot.getValue()+"");
                    i++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    //차량정보에 속한 운전자명을 통한 운전자정보 가져오기
    public void getDriverInfo(){
        Var.driverInfo.init(0,Var.vehicleInfo.getDriver());
        databaseReference = FirebaseDatabase.getInstance().getReference().child("운전자리스트").child(Var.vehicleInfo.getDriver());
        /** 읽기 */
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int i = 1;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        /** 저장 */
                        Var.driverInfo.init(i, snapshot.getValue() + "");
                        Log.i("TEST", "운전자리스트 Key: " + snapshot.getKey() + ", Value = " + snapshot.getValue() + "");
                        i++;
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
