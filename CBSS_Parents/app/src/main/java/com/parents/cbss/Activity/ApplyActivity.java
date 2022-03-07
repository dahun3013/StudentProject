package com.parents.cbss.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.parents.cbss.Firebase.AccountInfo;
import com.parents.cbss.Firebase.getFireBase;
import com.parents.cbss.R;
import com.parents.cbss.Variable.Var;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplyActivity extends Activity {
    EditText id;
    EditText pw;
    EditText pwcheck;
    EditText name1;
    EditText name2;
    EditText key;
    EditText phone;

    private String user_id="";
    private String user_pw="";
    private String user_name1="";
    private String user_name2="";
    private String user_key="";
    private String user_phone="";
    private String user_gender="";
    private String user_date="";
    private RadioButton radioButtonMan;
    private RadioButton radioButtonGirl;
    private RadioGroup radioGroup;
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        setContentView(R.layout.activity_apply);

        id = findViewById(R.id.apply_edit_ID);
        pw = findViewById(R.id.apply_edit_PW);
        pwcheck = findViewById(R.id.apply_edit_PW_RE);
        name1 = findViewById(R.id.apply_edit_ParName);
        name2 = findViewById(R.id.apply_edit_ChildName);
        key = findViewById(R.id.apply_edit_Key);
        phone = findViewById(R.id.apply_edit_phone);

        radioGroup = findViewById(R.id.apply_radio_group);
        radioButtonMan = findViewById(R.id.apply_radio_man);
        radioButtonGirl = findViewById(R.id.apply_radio_girl);
        radioButtonMan.setChecked(false);
        radioButtonGirl.setChecked(false);
        radioGroup.setOnCheckedChangeListener(mRadioCheck);
    }

    public boolean checkData(){
        if(id.getText().toString().length() == 0){
            Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pw.getText().toString().length() == 0){
            Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pwcheck.getText().toString().length() == 0){
            Toast.makeText(this, "비밀번호 확인을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pw.getText().toString().equals(pwcheck.getText().toString())==false){
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(name1.getText().toString().length() == 0){
            Toast.makeText(this, "부모 이름을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }if(name2.getText().toString().length() == 0){
            Toast.makeText(this, "아이 이름을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(key.getText().toString().length() == 0){
            Toast.makeText(this, "부모 나이를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(phone.getText().toString().length() == 0){
            Toast.makeText(this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(user_gender.length() == 0){
            Toast.makeText(this, "성별을 선택하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        user_id=id.getText().toString();
        user_pw=pw.getText().toString();
        user_name1=name1.getText().toString();
        user_name2=name2.getText().toString();
        user_key=key.getText().toString();
        user_phone=phone.getText().toString();

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user_date = sdf.format(date);

        return true;
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.apply_button_cancel:
                finish();
                break;
            case R.id.apply_button_ok:
                if(checkData() == false){
                    break;
                }else{
                    AccountInfo accountInfo = new AccountInfo(user_id,user_pw,user_name1,user_name2,user_key,user_phone,user_gender);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("가입신청서").child(user_id);
                    myRef.setValue(accountInfo);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    RadioGroup.OnCheckedChangeListener mRadioCheck =
            new RadioGroup.OnCheckedChangeListener(){
                public void onCheckedChanged(RadioGroup group, int checkedId){
                    if (group.getId() == R.id.apply_radio_group){
                        switch (checkedId){
                            case R.id.apply_radio_man:
                                user_gender="남자";
                                radioButtonMan.setChecked(radioButtonMan.isChecked());
                                break;
                            case R.id.apply_radio_girl:
                                user_gender="여자";
                                radioButtonGirl.setChecked(radioButtonGirl.isChecked());
                                break;
                        }
                    }
                }
            };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
