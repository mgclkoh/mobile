package com.example.databasetest1;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    EditText edit_name, edit_tel;
    TextView tvResult;
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
        //DBDATABASE생성하기
        helper = new DBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException ex) {
            db = helper.getReadableDatabase();
        }

        edit_name = findViewById(R.id.name);
        edit_tel = findViewById(R.id.tel);
        tvResult = findViewById(R.id.tvResult);
    }

    public void insert(View target) {
        String name = edit_name.getText().toString();
        String tel = edit_tel.getText().toString();
        //null은 _id의 defult값 insert에 테이블인 contacts추가
        db.execSQL("INSERT INTO contacts VALUES (null, '"+ name +"', '"+ tel +"');");
        Toast.makeText(getApplicationContext(), "성공적으로 추가되었음", Toast.LENGTH_LONG);
        //다시 지우기
        edit_name.setText("");
        edit_tel.setText("");
    }

    public void search(View view) {
        String name = edit_name.getText().toString();
        Cursor cursor;
        cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE name='"
            + name + "';", null);
        while (cursor.moveToNext()) {
            String tel = cursor.getString(1);
            edit_tel.setText(tel);
        }
    }

    public void search_all(View view) {
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM contacts", null);
        //  \r은 다시처음으로 돌아감, \n은 아랫줄의 처음으로 1번쨰 넣고 2,3번쨰 순으로 넣기
        String s = "ID \t NAME \t\t TEL \r\n";
        while (cursor.moveToNext()) {
            s += cursor.getString(0) + "\t";
            s += cursor.getString(1) + "\t\t";
            s += cursor.getString(2) + "\r\n";
        }
        tvResult.setText(s);   //문자열 추가
    }
}