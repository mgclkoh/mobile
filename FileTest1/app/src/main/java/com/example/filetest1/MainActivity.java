package com.example.filetest1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String FILENAME = "test.txt";
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edit = findViewById(R.id.editText);
        Button readButton = findViewById(R.id.read);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //파일오류날시 예외처리하기위한 try catch구문
                try {
                    FileInputStream fis = openFileInput(FILENAME);
                    byte[] butter = new byte[fis.available()];//한번에 file에서 읽을수있는 size 배정.
                    fis.read(butter);
                    edit.setText(new String(butter));  //butter라는 걸로 일거 문자열로 바꿔 editText에 저장.
                    fis.close();
                } catch (IOException e) {
                }
            }
        });
        Button writeButton = findViewById(R.id.write);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);//모드지정 Context.MOEE-PRIVATE나
                    fos.write(edit.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e) {
                }
            }
        });
    }
}