package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText eText1, eText2, eText3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eText1 = findViewById(R.id.edit1);
        eText2 = findViewById(R.id.edit2);
        eText3 = findViewById(R.id.edit3);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);

        btnSub.setOnClickListener(new View.OnClickListener(){   //익명의 클래스
            @Override
                public void onClick(View v){
                String s1 = eText1.getText().toString();
                String s2 = eText2.getText().toString();
                float result = Float.parseFloat(s1) - Float.parseFloat(s2);
                eText3.setText(""+result);
            }
        });

        btnMul.setOnClickListener(v ->{    //람다식
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();
            float result = Float.parseFloat(s1) * Float.parseFloat(s2);
            eText3.setText("" + result);
        });
        btnDiv.setOnClickListener(new MyClass());  //새로운 뷰클래스로 이벤트처리받기
    }
    public void cal_plus(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        float result = Float.parseFloat(s1) + Float.parseFloat(s2);
        eText3.setText("" + result);
    }
    public void cal_minus(View e) {
       /*
        String s1 = eText1.getText().toString();

        String s2 = eText2.getText().toString();
        float result = Float.parseFloat(s1) - Float.parseFloat(s2);
        eText3.setText("" + result);
        */
    }
    public void cal_mul(View e) {
        /*String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        float result = Float.parseFloat(s1) * Float.parseFloat(s2);
        eText3.setText("" + result);
        */
    }
    public void cal_divide(View e) {
        /*String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        float result = Float.parseFloat(s1) / Float.parseFloat(s2);
        eText3.setText("" + result);
        */
    }
    public class MyClass implements View.OnClickListener {       //새로운 뷰클래스로 이벤트처리받기

        @Override
        public void onClick(View v) {
            String s1 = eText1.getText().toString();
            String s2 = eText2.getText().toString();
            float result = Float.parseFloat(s1) / Float.parseFloat(s2);
            eText3.setText("" + result);
        }
    }

}