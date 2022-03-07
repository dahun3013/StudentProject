package org.schdeveloper.deps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    RecordDayDietActivity record;
    RecommendDietActivity recommend;
    SearchActivity search;
    statisticsActivity statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRecordDietButtonClicked(View v){
        Intent intent = new Intent(MainActivity.this, RecordDayDietActivity.class);
        startActivity(intent);
    }

    public void onRecommendDietButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), RecommendDietActivity.class);
        startActivity(intent);
    }

    public void onSearchDietButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void onViewStatisticsButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), statisticsActivity.class);
        startActivity(intent);
    }
}
