package org.schdeveloper.deps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class RecommendDietActivity extends AppCompatActivity {
    //최종적으로 선별한 추천 리스트
    ArrayList<Diet> sendDiets = new ArrayList<>();
    //추천할 식단을 담을 리스트
    ArrayList<Diet> diets = new ArrayList<>();
    //사용자가 선호한 식단들의 리스트
    ArrayList<Diet> userDiets = new ArrayList<>();
    //1. 같은 음식이름을 포함하는 식단들
    HashSet<Diet> sameNameDiets = new HashSet<>();
    //2. 같은 문화권인 식단들
    HashSet<Diet> sameCultureDiets = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_diet);

        recommand();
        filing();
    }

    //식단에 대한 정보 채워넣기
    private void filing() {
        String[] dietNames = new String[8];
        for (int i=0; i<sendDiets.size(); i++)
        {
            System.out.print("확인용 : " + sendDiets.get(i).getName());
            dietNames[i] = sendDiets.get(i).getName();
        }

        /*
        //식단 보내기 실험
        String text = "http://118.34.59.86//Food.php?Str=select * from 음식" +
                " where 음식번호 in (select 음식번호 from 식단구성 where 식단번호" +
                " = (select 식단번호 from 식단 where 식단이름 = '건강한균형식단1'))&Table=음식";
        Diet nowDiet = (Diet)tokenizerPHP(text, "음식");
        */

        sendDiets.clear();
        for (int i=0; i<8; i++)
        {
            String url = "http://118.34.59.86//Food.php?Str=select * from 음식 " +
                    "where 음식번호 in (select 음식번호 from 식단구성 where 식단번호 " +
                    "= (select 식단번호 from 식단 where 식단이름 = '" + dietNames[i] + "'))&Table=음식";
            Diet nowDiet = (Diet)tokenizerPHP(url, "음식");
            sendDiets.add(nowDiet);
        }
    }

    //식단 설명부분을 눌렀을 때 팝업을 띄어서 음식을 보여준다
    public void onDietButtonClicked(View v) {
        //받아온 식단 데이터들 변수로 받기
        String dietName;
        Button tmp;
        int nowId;
        Diet nowDiet;

        switch (v.getId())
        {
            case R.id.diet1:
                nowId = R.id.diet1;
                nowDiet = new Diet(sendDiets.get(0));
                break;
            case R.id.diet2:
                nowId = R.id.diet2;
                nowDiet = new Diet(sendDiets.get(1));
                break;
            case R.id.diet3:
                nowId = R.id.diet3;
                nowDiet = new Diet(sendDiets.get(2));
                break;
            case R.id.diet4:
                nowId = R.id.diet4;
                nowDiet = new Diet(sendDiets.get(3));
                break;
            case R.id.diet5:
                nowId = R.id.diet5;
                nowDiet = new Diet(sendDiets.get(4));
                break;
            case R.id.diet6:
                nowId = R.id.diet6;
                nowDiet = new Diet(sendDiets.get(5));
                break;
            case R.id.diet7:
                nowId = R.id.diet7;
                nowDiet = new Diet(sendDiets.get(6));
                break;
            case R.id.diet8:
                nowId = R.id.diet8;
                nowDiet = new Diet(sendDiets.get(7));
                break;
            default:
                return;
        }

        tmp = (Button)findViewById(nowId);
        dietName = tmp.getText().toString();

        Intent intent = new Intent(this, dietPopUpActivity.class);

        //팝업창에 데이터 전송 부분
        intent.putExtra("name", dietName);
        intent.putExtra("index", nowDiet.getFoods().size());
        for (int i=0; i<nowDiet.getFoods().size(); i++)
        {
            intent.putExtra("foodName" + (i+1), nowDiet.getFoods().get(i).getName());   //String
            intent.putExtra("foodCulture" + (i+1), nowDiet.getFoods().get(i).getCulture()); //String
            intent.putExtra("foodKcal" + (i+1), nowDiet.getFoods().get(i).getNuts().getKcal()); //double
            intent.putExtra("foodCarbo" + (i+1), nowDiet.getFoods().get(i).getNuts().getCarbo());  //double
            intent.putExtra("foodProte" + (i+1), nowDiet.getFoods().get(i).getNuts().getProte());  //double
            intent.putExtra("foodFat" + (i+1), nowDiet.getFoods().get(i).getNuts().getFat());    //double
        }

        startActivity(intent);
    }

    //PHP 데이터들을 필요한 토큰으로 저장
    private Object tokenizerPHP(String url, String tableName) {
        //반환할 식단들, 식단, 음식에 대한 참조변수
        ArrayList<Diet> newDiets = new ArrayList<>();
        Diet newDiet = new Diet();
        Food nowFood;

        //PHP url
        URLConnector task = new URLConnector(url);
        task.start();

        try
        {
            task.join();
            System.out.println("waiting... for result");
        }
        catch (InterruptedException e)
        {
            System.err.print(e.toString());
        }
        String resultText = task.getResult();

        //문자열 분리
        StringTokenizer tokens = new StringTokenizer(resultText);

        tokens.nextToken("[");
        while (tokens.hasMoreTokens())
        {
            //만약 결과가 null 이면, 비어있는 식단들 반환
            String tmpText;
            try
            {
                tmpText = tokens.nextToken("{");
            }
            catch (Exception e)
            {
                System.out.println("return : 비어있는 식단");
                return newDiets;
            }

            if (!tmpText.contains("["))
                if (!tmpText.contains(","))
                    break;

            //선택식단 테이블
            if (tableName.equals("선택식단"))
            {
                //선택 식단 하나에 대한 문자열 분리 : ID, 식단번호, 선호도 순
                tokens.nextToken(",");
                tokens.nextToken(":");
                tokens.nextToken("\"");

                String ID = tokens.nextToken("\"");

                tokens.nextToken(":");
                tokens.nextToken("\"");

                String dietNumber = tokens.nextToken("\"");

                tokens.nextToken(":");
                tokens.nextToken("\"");

                String like = tokens.nextToken("\"");

                newDiets.add(new Diet(dietNumber, like));
            }

            //식단 테이블
            if (tableName.equals("식단"))
            {
                //식단의 정보를 문자열 분리 : 식단번호, 식단이름 순
                tokens.nextToken(":");
                tokens.nextToken("\"");

                String dietNumber = tokens.nextToken("\"");

                tokens.nextToken(":");
                tokens.nextToken("\"");

                String dietName = tokens.nextToken("\"");

                newDiets.add(new Diet(dietNumber, dietName));
            }

            //음식 테이블
            if (tableName.equals("음식"))
            {
                //식단 하나에 속한 음식에 대한 문자열 분리 : 음식이름, 탄수화물, 단백질, 지방, 칼로리 순
                tokens.nextToken(",");
                tokens.nextToken(":");
                tokens.nextToken("\"");

                String name = tokens.nextToken("\"");

                tokens.nextToken(":");
                tokens.nextToken("\"");

                String carboText = tokens.nextToken("\"");
                double carbo = Double.valueOf(carboText);

                tokens.nextToken(":");
                tokens.nextToken("\"");

                String proteText = tokens.nextToken("\"");
                double prote = Double.valueOf(proteText);

                tokens.nextToken(":");
                tokens.nextToken("\"");

                String fatText = tokens.nextToken("\"");
                double fat = Double.valueOf(fatText);

                tokens.nextToken(":");
                tokens.nextToken("\"");

                String kcalText = tokens.nextToken("\"");
                double kcal = Double.valueOf(kcalText);

                nowFood = new Food(name, "한식", new Nutrient(kcal, carbo, prote, fat));
                newDiet.getFoods().add(nowFood);
            }
        }

        //테이블 이름에 맞는 반환값
        if (tableName.equals("음식"))
            return newDiet;
        else
            return newDiets;
    }

    //유저가 선호했던 모든 식단 찾기
    private ArrayList<Diet> searchUserDiets() {
        String urlSQL = "http://118.34.59.86//Food.php?Str=select * from 식단 where 식단번호 in (select 식단번호 from 선택식단)&Table=식단";
        ArrayList<Diet> tmpDiets = (ArrayList<Diet>)tokenizerPHP(urlSQL, "식단");

        String urlSQL2 = "http://118.34.59.86//Food.php?Str=select * from 선택식단&Table=선택식단";
        ArrayList<Diet> tmpDiets2 = (ArrayList<Diet>)tokenizerPHP(urlSQL2, "선택식단");

        for (int i=0; i<tmpDiets.size(); i++)
        {
            for (int j=0; j<tmpDiets2.size(); j++)
            {
                if (tmpDiets.get(i).getNumber().equals(tmpDiets2.get(j).getNumber()))
                {
                    tmpDiets.get(i).setLike(tmpDiets2.get(j).getLike());
                }
            }
        }



        return tmpDiets;
    }

    //원하는 음식 찾기(음식번호)
    private Diet searchFood(int dietNumber) {
        String urlSQL = "http://118.34.59.86//Food.php?Str=select * from 음식 where 음식번호 = " + dietNumber + "&Table=음식";
        Diet tmpFood = (Diet)tokenizerPHP(urlSQL, "음식");

        return tmpFood;
    }

    //원하는 식단 찾기(식단번호)
    private ArrayList<Diet> searchDiet(int dietNumber) {
        String urlSQL = "http://118.34.59.86//Food.php?Str=select * from 식단 where 식단번호 = " + dietNumber + "&Table=식단";
        ArrayList<Diet> tmpDiet = (ArrayList<Diet>)tokenizerPHP(urlSQL, "식단");

        return tmpDiet;
    }

    //원하는 음식을 갖는 식단 찾기(테이블이름, 음식이름, 선호도)
    private ArrayList<Diet> searchFoods(String tableName, String foodName, int like) {
        String urlSQL = "http://118.34.59.86//Food.php?Str=select * from " + tableName + " where 식단번호 in (select 식단번호 from 식단구성 where 음식번호" +
                " = (select 음식번호 from 음식 where 음식이름 = '" + foodName + "'))&Table=" + tableName;
        ArrayList<Diet> selectedFood = (ArrayList<Diet>)tokenizerPHP(urlSQL, tableName);

        //사용자의 선호도 적용
        if (like != 0)
            for (int i=0; i<selectedFood.size(); i++)
                selectedFood.get(i).setLike(like);

        return selectedFood;
    }

    //해당 테이블에 모든 식단 찾기(테이블이름)
    private ArrayList<Diet> searchDiets(String tableName) {
        String urlSQL = "http://118.34.59.86//Food.php?Str=select * from " + tableName + "&Table=" + tableName;
        tokenizerPHP(urlSQL, tableName);

        return null;
    }

    //해당 음식이 식단에 존재하는지
    private boolean isFood(String tableName, String foodName) {
        String urlSQL = "http://118.34.59.86//Food.php?Str=select * from " + tableName + " where 식단번호 in (select 식단번호 from 식단구성 where 음식번호" +
                " = (select 음식번호 from 음식 where 음식이름 = '" + foodName + "'))&Table=" + tableName;

        ArrayList<Diet> tmpDiets = (ArrayList<Diet>) tokenizerPHP(urlSQL, tableName);
        if (tmpDiets.size() == 0)
            return false;

        return true;
    }

    //유저의 테이블 식단 가져오기
    private void getUserDiets() {
        userDiets.clear();
        userDiets = searchUserDiets();
    }

    //선호 식단에 대한 추천
    private void recommand() {
        //현재 유저 테이블의 식단을 전부 가져오기
        getUserDiets();

        System.out.print("확인용1");
        System.out.println("유저식단들 사이즈 : " + userDiets.size());
        //유저가 선호한 식단이 하나도 없으면 (무작위 추천)
        if (userDiets.isEmpty())
        {
            System.out.print("확인용 내부");
            boolean check = false;
            for (int i=0; i<8; i++)
            {
                int randomNumber = (int)(Math.random() * 53) + 1;
                Diet tmp = searchDiet(randomNumber).get(0);

                //중복 검사
                for (int j=0; j<diets.size(); j++)
                {
                    if (diets.get(j).getNumber().equals(tmp.getNumber()))
                    {
                        i--;
                        check = true;
                        break;
                    }
                }

                if (check)
                    continue;

                diets.add(tmp);
            }
        }
        //유저가 선호한 식단이 하나라도 있으면
        else
        {
            //식단들
            for (int i=0; i<userDiets.size(); i++)
            {
                int nowLike = userDiets.get(i).getLike();

                System.out.print("확인용 내부4");
                //음식들
                for (int j=0; j<userDiets.get(i).foods.size(); j++)
                {
                    System.out.print("확인용 내부3");
                    //1. 선호 식단의 음식들을 검색한(서브스트링 포함) 결과 식단들
                    if (isFood("식단", userDiets.get(i).foods.get(j).getName()))
                    {
                        System.out.print("확인용 내부2");
                        //sameNameDiets.addAll(searchFoods("식단", userDiets.get(i).foods.get(j).getName(), nowLike));
                        ArrayList<Diet> tmpS = searchFoods("식단", userDiets.get(i).foods.get(j).getName(), nowLike);
                        for (Diet e: tmpS) {
                            System.out.println("식단이름" + e.getName());
                            sameNameDiets.add(e);
                        }
                    }
                }
            }
        }

        System.out.print("확인용2");
        System.out.println("같은이름식단들 사이즈 : " + sameNameDiets.size());

        //배열로 변환
        Diet[] tmpNameList = sameNameDiets.toArray(new Diet[0]);

        //1을 우선 추천
        for (int i=0; i<tmpNameList.length; i++)
        {
            Diet nowDiet = new Diet(tmpNameList[i], tmpNameList[i].getLike());
            diets.add(nowDiet);
        }

        Comparator<Diet> sortByLike = new Comparator<Diet>() {
            @Override
            public int compare(Diet o1, Diet o2) {
                return o1.getLike() > o2.getLike() ? 1 : -1;
            }
        };

        System.out.print("확인용3");
        //추천된 식단들을 평가 선호도 순으로 정렬
        Collections.sort(diets, sortByLike);
        Collections.reverse(diets);

        System.out.println("식단들 사이즈 : " + diets.size());

        //추천된 식단의 개수에 따라 최종적으로 결정
        if (diets.size() < 8)
        {
            for (int i=0; i<diets.size(); i++)
                sendDiets.add(diets.get(i));

            boolean check = false;
            for (int i=0; i<8 - diets.size(); i++)
            {
                int randomNumber = (int)(Math.random() * 53) + 1;
                Diet tmp = searchDiet(randomNumber).get(0);

                //중복 검사
                for (int j=0; j<sendDiets.size(); j++)
                {
                    if (sendDiets.get(j).getNumber().equals(tmp.getNumber()))
                    {
                        i--;
                        check = true;
                        break;
                    }
                }

                if (check)
                    continue;

                sendDiets.add(tmp);
            }
        }
        else
        {
            for (int i=0; i<8; i++)
                sendDiets.add(diets.get(i));
        }

        System.out.println("최종 식단 사이즈 : " + sendDiets.size());
    }

    //식단기록 버튼
    public void onRecordDietButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), RecordDayDietActivity.class);
        startActivity(intent);
    }

    //식단추천 버튼
    public void onRecommendDietButtonClicked(View v) {

    }

    //식단검색 버튼
    public void onSearchDietButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    //통계 버튼
    public void onViewStatisticsButtonClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), statisticsActivity.class);
        startActivity(intent);
    }
}