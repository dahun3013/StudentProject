package org.schdeveloper.deps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class statisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public void onRecordDietButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(), RecordDayDietActivity.class);
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

    }

    public void onCalcStatisticsButtonClicked(View v){
        //spinner에 있는 값을 읽어와서 그것에 맞는 계산을 함
    }

    public void onSelectDateImageButtonClicked(View v){

    }

}
