package com.example.splashlogin1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    private TextView displayIdTextView, displayPasswordTextView; //login화면에 입력한 아이디,비번 보이는 변수
    String id, password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);   //Sub화면의 레이아웃 지정 및 함수 호출
        //xml에 쓴 해당 쓸 ID가져와 씀
        displayIdTextView = findViewById(R.id.displayIdTextView);
        displayPasswordTextView = findViewById(R.id.displayPasswordTextView);

        Intent intent = getIntent(); //자기자신 호출할때 썻던 메소드(아이디,비번 데이터) 다시가져오기
        if(intent != null) {   //빈공간 아닐경우   /null일 경우에 생기는 뻑가는 에러를 방지해줌
            id = intent.getStringExtra("ID");
            password = intent.getStringExtra("Password");
            displayIdTextView.setText("아이디: " + id);
            displayPasswordTextView.setText("비밀번호: " + password);
        }

    }
    public void check(View view) {   //로그인 성공/실패 여부 함수
        Intent intent = new Intent();
        if(isUserValid(id, password)) {  //함수에 미리 저장한 아이디 ,비번과 같은경우
            intent.putExtra("status", "로그인 성공!!!");
        } else {         //다른 경우
            intent.putExtra("status", "로그인 실패!!!");
        }
        setResult(RESULT_OK, intent);
        finish();
    }
    private boolean isUserValid(String username, String password) {   //미리 저장할 아디,비번 함수
        return username.equals("kim") && password.equals("1234");
    }
}
