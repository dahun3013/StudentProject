package com.parents.cbss.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.parents.cbss.Firebase.getFireBase;
import com.parents.cbss.R;
import com.parents.cbss.Fragment.CarFragment;
import com.parents.cbss.Variable.Var;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ImageView nav_header_image;//부모이미지
    private TextView nav_header_text;//부모 이름
    private TextView nav_header_sub_text;//어린이 이름

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Write a message to the database
        // 현제 기기의 토큰 값을 알아내기 위한 이벤트 리스너 재정의
        // 기기의 토큰 값이 NULL 이 아닌경우 호출됨
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,
                new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String newToken = instanceIdResult.getToken();
                        //학부모의 토큰을 Firebase에 저장
                        new getFireBase().setToken(newToken);
                    }
                });
        /** 풀 스크린 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        /** 네비게이션 메뉴 관리 */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        /** 네이베이션 헤드 관리 */
        View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        hView.setBackgroundColor(Color.WHITE);
        nav_header_image = hView.findViewById(R.id.nav_image);
        nav_header_image.setImageResource(R.drawable.ic_menu_people);
        nav_header_text = hView.findViewById(R.id.nav_header);
        nav_header_text.setText(Var.MainActivity_Parent_Name);
        nav_header_text.setTextColor(Color.BLACK);
        nav_header_sub_text = hView.findViewById(R.id.nav_head_sub);
        nav_header_sub_text.setText(Var.MainActivity_Child_Name);
        nav_header_sub_text.setTextColor(Color.BLACK);
        /** 네비게이션 애니메이션 적용 */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        /** 네비게이션 메뉴아이템 이벤트 */
        navigationView.setNavigationItemSelectedListener(this);
        /** 초기 Fragment 설정 */
        replaceFragment(new CarFragment());
    }

    /** Navigation 아이템 클릭 이벤트 */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_car:
                replaceFragment(new CarFragment());
                break;
            case R.id.nav_notify:
                Intent profile_intent = new Intent(this, ProfileActivity.class);
                startActivity(profile_intent);
                break;
            case R.id.nav_permission:
                Intent permission_intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null));
                permission_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(permission_intent);
                break;
            case R.id.nav_logout:
                Intent login_intent = new Intent(this, LoginActivity.class);
                startActivity(login_intent);
                finish();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Fragment 교체 */
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    public void onBackPressed(){
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료하시겠습니까?");

        // "예" 버튼을 누르면 실행되는 리스너
        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
                ActivityCompat.finishAffinity(MainActivity.this);
                finish();
            }
        });
        // "아니오" 버튼을 누르면 실행되는 리스너
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return; // 아무런 작업도 하지 않고 돌아간다
            }
        });
        alBuilder.setTitle("프로그램 종료");
        alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.

    }
}
