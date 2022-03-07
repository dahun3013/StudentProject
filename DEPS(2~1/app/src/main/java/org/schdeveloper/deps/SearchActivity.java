package org.schdeveloper.deps;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "음식번호";
    private static final String TAG_NAME = "음식이름";
    private static final String TAG_TAN = "탄수화물";
    private static final String TAG_DAN = "단백질";
    private static final String TAG_JI = "지방";
    private static final String TAG_KCAL = "Kcal";

    ArrayList<HashMap<String, String>> foodList;
    ListView list;
    ListAdapter adapter;
    boolean again = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        foodList = new ArrayList<HashMap<String, String>>();
        list = (ListView) findViewById(R.id.listView_search_list);
    }

    public void onRecordDietButtonClicked(View v){    //아래 기본버튼 4개 중 1개(기록 버튼)
        Intent intent = new Intent(getApplicationContext(), RecordDayDietActivity.class);
        startActivity(intent);
    }

    public void onRecommendDietButtonClicked(View v){    //아래 기본버튼 4개 중 1개(추천 버튼)
        Intent intent = new Intent(getApplicationContext(), RecommendDietActivity.class);
        startActivity(intent);
    }

    public void onSearchDietButtonClicked(View v){    //아래 기본버튼 4개 중 1개(검색 버튼)

    }

    public void onViewStatisticsButtonClicked(View v){    //아래 기본버튼 4개 중 1개(통계 버튼)
        Intent intent = new Intent(getApplicationContext(), statisticsActivity.class);
        startActivity(intent);
    }

    public void onSearchingButtonClicked(View V){    //검색 버튼(누르면 검색시작)
        EditText et = (EditText) findViewById(R.id.searchEditText);
        String text = "http://118.34.59.86//Food.php?Str=select * from 음식 where 음식번호 in (select 음식번호 from 식단구성 where 식단번호 = (select 식단번호 from 식단 where 식단이름 = '"+et.getText()+"'))&Table=음식";
        URLConnector task = new URLConnector(text);
        task.start();

        try {
            task.join();
            System.out.println("waiting... for result");
        } catch (InterruptedException e) {

        }
        String result = task.getResult();

        if(display(result))
        {
            System.out.println(result);
        }
        else
        {
            System.out.println("else");
            text = "http://118.34.59.86//Food.php?Str=select * from 음식 where 음식이름 = '"+et.getText()+"'&Table=음식";
            task = new URLConnector(text);

            task.start();

            try {
                task.join();
                System.out.println("waiting... for result");
            } catch (InterruptedException e) {

            }
            result = task.getResult();
            display(result);
            System.out.println(result);
        }

    }

    public boolean display(String result)
    {
        if(again)
        {
            foodList.clear();
        }
        try {
            JSONObject jsonObj = new JSONObject(result);
            JSONArray foods = jsonObj.getJSONArray(TAG_RESULTS);
            if(foods.length()==0)
            {
                return false;
            }
            for (int i = 0; i < foods.length(); i++) {
                JSONObject c = foods.getJSONObject(i);
                Food f = new Food(c.getString(TAG_NAME), "한식",new Nutrient(Double.parseDouble(c.getString(TAG_KCAL)),Double.parseDouble(c.getString(TAG_TAN)), Double.parseDouble(c.getString(TAG_DAN)), Double.parseDouble(c.getString(TAG_JI))));

                HashMap<String, String> foodmap = new HashMap<String, String>();

                foodmap.put(TAG_NAME, f.getName());
                foodmap.put(TAG_TAN, f.getNuts().getCarbo()+"");
                foodmap.put(TAG_DAN, f.getNuts().getProte()+"");
                foodmap.put(TAG_JI, f.getNuts().getFat()+"");
                foodmap.put(TAG_KCAL, f.getNuts().getKcal()+"");

                foodList.add(foodmap);
            }

            adapter = new SimpleAdapter(
                    SearchActivity.this, foodList, R.layout.list_item,
                    new String[]{TAG_NAME, TAG_TAN, TAG_DAN, TAG_JI, TAG_KCAL},
                    new int[]{R.id.list_name, R.id.list_tan, R.id.list_dan, R.id.list_ji, R.id.list_kcal}
            );
            list.setAdapter(adapter);
            again =true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

}
