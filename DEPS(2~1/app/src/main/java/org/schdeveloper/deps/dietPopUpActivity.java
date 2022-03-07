package org.schdeveloper.deps;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class dietPopUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_diet_pop_up);

        TextView text1 = (TextView)findViewById(R.id.textView1);
        TextView texts = (TextView)findViewById(R.id.textView2);

        Intent intent = getIntent();

        String dietName = intent.getExtras().getString("name");
        text1.setText(dietName);

        texts.setText("");

        int index = intent.getExtras().getInt("index");
        for (int i=0; i<index; i++)
        {
            String foodName = intent.getExtras().getString("foodName" + (i+1));
            texts.append(foodName + ", ");

            String foodCulture = intent.getExtras().getString("foodCulture" + (i+1));
            texts.append(foodCulture+ ", ");

            double foodKcal = intent.getExtras().getDouble("foodKcal" + (i+1));
            texts.append(foodKcal+ ", ");

            double foodCarbo = intent.getExtras().getDouble("foodCarbo" + (i+1));
            texts.append(foodCarbo + ", ");

            double foodProte = intent.getExtras().getDouble("foodProte" + (i+1));
            texts.append(foodProte + ", ");

            double foodFat = intent.getExtras().getDouble("foodFat" + (i+1));
            texts.append(foodFat + "\n");
        }
    }
}
