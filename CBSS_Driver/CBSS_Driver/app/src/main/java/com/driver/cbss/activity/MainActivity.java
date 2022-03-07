package com.driver.cbss.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
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

import com.driver.cbss.R;
import com.driver.cbss.variable.Var;
import com.driver.cbss.fragment.CarFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ImageView nav_header_image;//운전자 이미지
    private TextView nav_header_text;//운전자 이름
    private TextView nav_header_sub_text;//차량 번호

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** DB 읽기 */
        //이곳에 DB 읽는 코드 호출이 필요함   ex) new Firebase().getKeyList() 등
/*
        Var.DB_Key_List.add("AC:23:3F:2A:FC:36");
        Var.DB_Door_List.add("00:13:AA:00:55:8A");
        Var.DB_Child_List.add("AC:23:3F:2A:FB:48");
        Var.Match_Child_Name_List.put("AC:23:3F:2A:FB:48","양영현");
        Var.Match_Parents_Key_List.put("AC:23:3F:2A:FB:48","c1Pzbe_fq1k:APA91bF6KLM1heeLuOCJ1gm0e5YzjWGhu91PKsfduBEsScewVEBSuvILfzUDL6MuEo3ejgpTiT21h6AR2UphJWCXm0BlxS-gMY_8ekt5AzwLpmtB8DRGK44mAsVZjfoQN5hLQtb7PtVR");
*/

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
        nav_header_text.setText(Var.MainActivity_Driver_Name);
        nav_header_text.setTextColor(Color.BLACK);
        nav_header_sub_text = hView.findViewById(R.id.nav_head_sub);
        nav_header_sub_text.setText(Var.MainActivity_Car_Number);
        nav_header_sub_text.setTextColor(Color.BLACK);
        /** 네비게이션 애니메이션 적용 */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        /** 네비게이션 메뉴아이템 이벤트 */
        navigationView.setNavigationItemSelectedListener(this);
        /** 초기 Fragment 설정 */
        replaceFragment(new CarFragment());


        final LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                100, // 통지사이의 최소 시간간격 (miliSecond)
                1, // 통지사이의 최소 변경거리 (m)
                mLocationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                100, // 통지사이의 최소 시간간격 (miliSecond)
                1, // 통지사이의 최소 변경거리 (m)
                mLocationListener);
    }

    /** GPS */
    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Log.d("test", "onLocationChanged, location:" + location);
            double longitude = location.getLongitude(); //경도
            double latitude = location.getLatitude();   //위도
            Var.Latitude = latitude+"";
            Var.Longitude = longitude+"";
            Var.Speed = location.getSpeed()+"";

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("차량리스트").child(Var.MainActivity_Car_Number);
            myRef.child("현재운전자").setValue(Var.MainActivity_Driver_Name);
            myRef.child("현재위도").setValue(Var.Latitude);
            myRef.child("현재경도").setValue(Var.Longitude);
            myRef.child("현재시속").setValue(Var.Speed);
        }
        public void onProviderDisabled(String provider) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }
        public void onProviderEnabled(String provider) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };



    /** Navigation 아이템 클릭 이벤트 */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_car:
                replaceFragment(new CarFragment());
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



    public void onClick(View view){
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
