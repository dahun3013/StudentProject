package com.parents.cbss.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.parents.cbss.Firebase.getFireBase;
import com.parents.cbss.R;
import com.parents.cbss.Variable.Var;

public class LoginActivity extends Activity {
    private EditText editText_ID; //아이디 입력필드
    private EditText editText_PW; //패스워드 입력필드
    private CheckBox checkBox_Save; //아이디,패스워드 저장 체크박스

    private Button button_Login; //로그인 버튼
    private Button button_Join; //계정신청 버튼

    private boolean save;
    private String id;
    private String pw;

    private Handler mHandler;
    private ProgressDialog mProgressDialog;
    private getFireBase gfb;
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


    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_button:
                gfb = new getFireBase();
                gfb.getUserID(editText_ID.getText().toString());//DB로부터 계정 읽기

                mHandler = new Handler();
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        mProgressDialog = ProgressDialog.show(LoginActivity.this,"","잠시만 기다려 주세요.",true);
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressDialog.dismiss();
                                if(Var.parentsInfo.getID().equals("")){
                                    Toast.makeText(LoginActivity.this, "아이디를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                                }else if(Var.parentsInfo.getPW().equals(editText_PW.getText().toString())==false){
                                    Toast.makeText(LoginActivity.this, "패스워드가 틀립니다.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                    local_db_save();

                                    runOnUiThread(new Runnable(){
                                        @Override
                                        public void run() {
                                            mProgressDialog = ProgressDialog.show(LoginActivity.this,"","잠시만 기다려 주세요.",true);
                                            mHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    gfb.getChildInfo();
                                                }
                                            },1500);
                                        }
                                    });

                                    runOnUiThread(new Runnable(){
                                        @Override
                                        public void run() {
                                            mHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    gfb.getVehicleInfo();
                                                }
                                            },1700);
                                        }
                                    });

                                    runOnUiThread(new Runnable(){
                                        @Override
                                        public void run() {
                                            mHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    gfb.getDriverInfo();
                                                }
                                            },1950);
                                        }
                                    });

                                    runOnUiThread(new Runnable(){
                                        @Override
                                        public void run() {
                                            mHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mProgressDialog.dismiss();
                                                    Intent user_intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(user_intent);
                                                    finish();
                                                }
                                            },2200);
                                        }
                                    });
                                }
                            }
                        },1300);
                    }
                });

                break;
            case R.id.kakao_button:
                String kakao_url ="https://open.kakao.com/o/slCibxKb";
                Intent kakao_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(kakao_url));
                startActivity(kakao_intent);
                break;
            case R.id.join_button:
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