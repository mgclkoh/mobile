package com.example.mustgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    String id, password;
    private TextView statusText;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);

        statusText = findViewById(R.id.loginStatus);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("ID");
            password = intent.getStringExtra("Password");
        }

    launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

    result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                statusText.setText(data.getStringExtra("status"));
            }
        }
    });
}

        public void check(View view) {   //로그인 정보확인
            Intent intent = new Intent();
            if(isUserValid(id, password)) {
                intent.putExtra("status", "Welcome Admin!");
            } else {
                intent.putExtra("status", "Sorry");
            }
            setResult(RESULT_OK, intent);
            finish();
        }

        private boolean isUserValid(String username, String password) {   //미리 저장할 admin정보
            return username.equals("admin@must.com") && password.equals("8891");
        }
    }

