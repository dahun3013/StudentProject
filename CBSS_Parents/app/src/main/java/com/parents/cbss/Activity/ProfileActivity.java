package com.parents.cbss.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.parents.cbss.R;

public class ProfileActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** 풀 스크린 */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
    }
}
