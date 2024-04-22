package com.example.advancedwidget1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView, bulbImageView;
    private Switch switchButton;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;   //이구아나 업그레이드시 뜸, 화면 확장시켜 연결 할 수있게 default로 만들어줌
        });
        linearLayout = findViewById(R.id.linearLayout);   //색깔 바꾸기 위해 instance 생성
        imageView = findViewById(R.id.imageView);
        bulbImageView = findViewById(R.id.bulbImageView);
        switchButton = findViewById(R.id.switchButton);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    bulbImageView.setImageResource(R.drawable.on);
                } else {
                    bulbImageView.setImageResource(R.drawable.off);
                }
            }
        });
    }
    public void onToggleClicked(View view) {  //view 타입을 toggleButton으로 바꿔 체크
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            imageView.setImageResource(R.drawable.pic3);   //on일시 이미지3 출력
        } else {
            imageView.setImageResource(0);   //off일시 이미지 꺼버림
        }
    }


    /*
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch ((view.getId()) {
            case R.id.radio_red:   //자동으로 상수가 추가가 안되어 오류남
                if(checked)
                    linearLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.radio_green:
                if(checked)
                    linearLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.radio_blue:
                if(checked)
                    linearLayout.setBackgroundColor(Color.BLUE);
                break;
        }
    }
*/
}