package org.schdeveloper.deps;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class RecordDayDietActivity extends AppCompatActivity{
    TextView textView, yearView;
    EditText breakFastTextBox, lunchTextBox,dinnerTextBox, etcTextBox;
    Calendar cal;
    DatePickerDialog dpd;
    int year, month, day;
    String dayOfWeek;
    String toDay;
    String breakFast, lunch, dinner, etc; //기록들이 저장되는 문자열
    String[] recording;  //기록이 저장되는 문자 배열 위의 문자열들이 이곳에 차례로 저장
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_day_diet);

        init();
        dbHelper = new DBHelper(getApplicationContext(), "DAYRECORD.db", null, 1);
        // DB에 데이터 추가
        setRecording(toDay);
    }

    public void init(){
        try{
            breakFastTextBox = findViewById(R.id.breakfastEditText);
            lunchTextBox = findViewById(R.id.lunchEditText);
            dinnerTextBox = findViewById(R.id.dinnerEditText);
            etcTextBox = findViewById(R.id.etcEditText);
            textView = findViewById(R.id.dateView);
            yearView=findViewById(R.id.yearView);
            recording = new String[5];
            cal = Calendar.getInstance();

            setDateTextBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDate(){ //현재 설정된 날짜를 "yyyyMMdd"형식의 스트링으로 반환
        String setDate = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);

        return  setDate;
    }

    public void setDateTextBox() throws Exception{
        day = cal.get(Calendar.DAY_OF_MONTH);  //일 구하기
        month = cal.get(Calendar.MONTH); //월 구하기
        year = cal.get(Calendar.YEAR);  //연도 구하기

        dayOfWeek = getDay(year, month, day);  //요일 구하기

        yearView.setText(String.valueOf(year));
        textView.setText((month+1)+"월 " + day +"일 " + "(" +dayOfWeek + ")");
        toDay = String.valueOf(year) + String.valueOf(month) + String.valueOf(day); //오늘의 날짜
    }

    public void onRecordDietButtonClicked(View v) {

    }

    public void onRecommendDietButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), RecommendDietActivity.class);
        startActivity(intent);
    }

    public void onSearchDietButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void onViewStatisticsButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), statisticsActivity.class);
        startActivity(intent);
    }



    public void onSaveButtonClicked(View v) {  //저장 버튼
        getRecording(); //UI에서 기록을 get
        saveInDB(getDate());
    }
    public void saveInDB(String date){
        ArrayList<String[]> result = dbHelper.getResult(date);

        if(result.size()<1) {
            dbHelper.insert(recording);
            setRecording(getDate());
        }
        else {
            dbHelper.update(recording);
            setRecording(getDate());
        }

    }

    public void getRecording(){ //editText에 쓴 문자 문자열에 기록(저장)하기
        breakFast = breakFastTextBox.getText().toString();
        lunch =  lunchTextBox.getText().toString();
        dinner = dinnerTextBox.getText().toString();
        etc = etcTextBox.getText().toString();
        recording[1] = breakFast;
        recording[2] = lunch;
        recording[3] = dinner;
        recording[4] = etc;

        //문자열의 첫 번째 인덱스에는 기록이 처음 쓰여진 날짜가 저장된다.(각 기록에 알맞은 날짜가 저장됨)
        String currentDate = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        recording[0] =  currentDate;

    }

    public void setRecording(String date){ //이전에 저장된 문자열 editText에 기록하기
        ArrayList<String[]> result = dbHelper.getResult(date);

        if(result.size()>0) {
            breakFastTextBox.setText(result.get(0)[1]);
            lunchTextBox.setText(result.get(0)[2]);
            dinnerTextBox.setText(result.get(0)[3]);
            etcTextBox.setText(result.get(0)[4]);
        }
        else
        {
            breakFastTextBox.setText("");
            lunchTextBox.setText("");
            dinnerTextBox.setText("");
            etcTextBox.setText("");
        }
    }

    public void onSelectDateButtonClicked(View v){ //달력이미지 버튼

        dpd = new DatePickerDialog(RecordDayDietActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d) {
                try {
                    year = y;
                    month = m;
                    day = d;
                    dayOfWeek = getDay(year, month, day);
                    yearView.setText(String.valueOf(year));
                    textView.setText((month + 1) + "월 " + day + "일 " + "(" + dayOfWeek + ")");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },day, month, year);
        dpd.show();
    }

    public void onLeftArrowImageButtonClicked (View v) throws Exception{//왼쪽 화살표 이미지 버튼이 눌리면
        int dayOfMonth;    //한 달에 있는 일의 총 수(30 or 31)

        if(day > 1){
            day--;
        }
        else{
            if(month == 0) {
                year--;
                month = 11;
            }
            else {
                month--;
            }
            day = 1;
            Calendar c = new GregorianCalendar(year, month, day);
            dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

            if(dayOfMonth == 30){
                day = 30;
            }
            else if(dayOfMonth == 31){
                day = 31;
            }
        }

        dayOfWeek = getDay(year, month, day);
        yearView.setText(String.valueOf(year));
        textView.setText((month+1)+"월 " + day +"일 " + "(" +dayOfWeek + ")");

        //설정된 날짜에 맞는 정보 가져와서 쓰기
        String setDate = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        //설정된 날짜를 쿼리로 보내서 그것에 맞는 정보를 가져온 다음(이 때 반환되는 것은 문자배열이어야함)
        //setRecording()함수를 써서 기록해준다. 이 때, 함수 인자로 반환된 문자배열을 넣어주면 된다.
        setRecording(setDate);
    }

    public void onRightArrowImageButtonClicked(View v)throws Exception {//오른쪽 화살표 이미지 버튼이 눌리면
        int dayOfMonth; //한 달에 있는 일의 총 수(30 or 31)

        Calendar c = new GregorianCalendar(year, month, 1);
        dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(dayOfMonth>day){
            day++;
        }
        else{
            if(month < 11){
                month++;
            }
            else{
                year++;
                month = 0;
            }

            day=1;
        }
        dayOfWeek = getDay(year, month, day);
        yearView.setText(String.valueOf(year));
        textView.setText((month+1)+"월 " + day +"일 " + "(" +dayOfWeek + ")");

        //설정된 날짜에 맞는 정보 가져와서 쓰기
        String setDate = String.valueOf(year) + String.valueOf(month) + String.valueOf(day);
        //설정된 날짜를 쿼리로 보내서 그것에 맞는 정보를 가져온 다음(이 때 반환되는 것은 문자배열이어야함)
        //setRecording()함수를 써서 기록해준다. 이 때, 함수 인자로 반환된 문자배열을 넣어주면 된다.
        setRecording(setDate);
    }

    public String getDay(int y, int m, int d)throws Exception{ //요일 구하는 메소드
        String dayOfWeek = null, year, month, day;
        year = String.valueOf(y);
        month = String.valueOf(m);
        day = String.valueOf(d);
        String str = year + month + day;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = formatter.parse(str);

        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int dayNum = c.get(Calendar.DAY_OF_WEEK);
        switch(dayNum){
            case 1:
                dayOfWeek ="화";
                break;
            case 2:
                dayOfWeek ="수";
                break;
            case 3:
                dayOfWeek ="목";
                break;
            case 4:
                dayOfWeek ="금";
                break;
            case 5:
                dayOfWeek ="토";
                break;
            case 6:
                dayOfWeek ="일";
                break;
            case 7:
                dayOfWeek ="월";
                break;
       }


       return dayOfWeek;
    }
}


