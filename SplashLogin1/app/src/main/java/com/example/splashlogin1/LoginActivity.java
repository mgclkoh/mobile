package com.example.splashlogin1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;   //이메일, 비밀번호 문자넣을 곳 변수지정
    private TextView statusText;   //로그인 성공/실패 여부 출력할 변수
    private Button loginButton;   //로그인버튼 변수
    ActivityResultLauncher<Intent> launcher;   //launcher를 통해 시작
    //로그인 화면의 레이아웃 정의 후 필요함수 추가
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //변수마다 쓰일 xml에서 지정한 ID지정
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        statusText = findViewById(R.id.loginStatus);
        //아이디와 비밀번호 값 가져오기
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                //login 액티비티에 할당된 intnet 받아서 Sub액티비티로 전환하며 보내줌
                Intent intent = new Intent(LoginActivity.this, SubActivity.class);
                intent.putExtra("ID", email);
                intent.putExtra("Password", password);
                launcher.launch(intent);   //intent받고 전환되게 해줌
            }
        });
        //ActivityResultLauncher를 초기화하고 새 launcher 생성
        launcher = registerForActivityResult(new ActivityResultContracts.
                StartActivityForResult(),
                result -> {
            if(result.getResultCode() == Activity.RESULT_OK) {  //성공적으로 데이터 받을 경우
                Intent data = result.getData();  //다른 액티비에서 전달된 status문자열 가져와
                statusText.setText(data.getStringExtra("status")); //statusText에 설정해 화면표시
            }
                });
    }
}
