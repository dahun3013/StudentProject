package com.driver.cbss.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.driver.cbss.R;
import com.driver.cbss.firebase.getFireBase;
import com.driver.cbss.variable.Var;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends Activity {

    private EditText editText_ID; //아이디 입력필드
    private EditText editText_PW; //패스워드 입력필드
    private CheckBox checkBox_Save; //아이디,패스워드 저장 체크박스

    private boolean save;
    private String id;
    private String pw;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_ID = findViewById(R.id.login_ID);
        editText_PW = findViewById(R.id.login_PW);
        checkBox_Save = findViewById(R.id.login_check);

        local_db_load();


        checkBox_Save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                save = isChecked;
            }
        });

        /** 블루투스 권한 확인 및 on */
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        /** 블루투스를 지원하지 않는 경우 */
        if(btAdapter == null){
            Toast.makeText(this, "이 기기는 블루투스를 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            /** 블루투스가 꺼져있는 경우 */
            if (!btAdapter.isEnabled()) {
                //Ble 설정화면으로 이동
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(intent,2);
            }
        }
        /** GPS 권한 확인 및 on */
        String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,REQUIRED_PERMISSIONS[0]);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(this,REQUIRED_PERMISSIONS[1]);
        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,100);
            } else {
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,100);
            }
        }
    }
    ProgressDialog mProgressDialog;
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_button:
                //local_db_save();
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        mProgressDialog = ProgressDialog.show(LoginActivity.this,"","잠시만 기다려 주세요.",true);
                        /** 경로 */
                        try {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("운전자리스트").child(editText_ID.getText().toString());
                            /** 읽기 */
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        /** 저장 */
                                        password = (String) snapshot.getValue();
                                        if(password.equals(editText_PW.getText().toString())){
                                            new getFireBase().getBeaconList();
                                            mProgressDialog.dismiss();
                                            Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                            Var.MainActivity_Driver_Name = id;
                                            local_db_save();
                                            Intent user_intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(user_intent);
                                            finish();
                                            return;
                                        }
                                    }
                                    if(password == null){
                                        mProgressDialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                                    }else if(password != editText_PW.getText().toString()){
                                        mProgressDialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "패스워드가 틀립니다."+password, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }catch (Exception e){
                            mProgressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "아이디 또는 패스워드가 틀립니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //Intent user_intent = new Intent(this, MainActivity.class);
                //startActivity(user_intent);
                //finish();
                break;
            case R.id.kakao_button:
                String kakao_url ="https://open.kakao.com/o/slCibxKb";
                Intent kakao_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(kakao_url));
                startActivity(kakao_intent);
                break;
            case R.id.carnumber_button:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);

                final EditText input = new EditText(this);
                input.setText(Var.MainActivity_Car_Number);
                builder.setTitle("차량 번호 변경");
                builder.setView(input);
                builder.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Var.MainActivity_Car_Number = input.getText().toString();
                                SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("CARNUMBER",Var.MainActivity_Car_Number);
                                editor.commit();
                            }
                        });
                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                break;
        }
    }

    /** SharedPreference 사용 */
    private void local_db_load(){
        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);
        //text라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        id = sf.getString("ID","");
        pw = sf.getString("PW","");
        save = sf.getBoolean("SAVE",false);
        Var.MainActivity_Car_Number = sf.getString("CARNUMBER","0000");
        if(save == true){
            editText_ID.setText(id);
            editText_PW.setText(pw);
            checkBox_Save.setChecked(save);
        }else{
            editText_ID.setText("");
            editText_PW.setText("");
        }
    }
    private void local_db_save(){
        SharedPreferences sharedPreferences = getSharedPreferences("sFile",MODE_PRIVATE);
        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("SAVE",save);
        editor.putString("ID",editText_ID.getText()+"");
        editor.putString("PW",editText_PW.getText()+"");
        editor.commit();
    }
}