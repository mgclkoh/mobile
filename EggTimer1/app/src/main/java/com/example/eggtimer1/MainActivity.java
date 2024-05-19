package com.example.eggtimer1;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText; //m은 member의 단축말 보기좋게
    String NOTIFICCATION_CHANNEL_ID = "my_channel_id_01";
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
        mEditText = findViewById(R.id.edit);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICCATION_CHANNEL_ID,
            "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Channel description");
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
    //알림 보내기
    @SuppressLint("NotificationPermission")
    public void sendNotification(){
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,
                NOTIFICCATION_CHANNEL_ID);
        //액션 첨부
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        //알림 붙이기
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE);

        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Egg Timer")
                .setContentText("계란 삻기가 완료되었습니다")
                .setContentIntent(pendingIntent);
        //관리 설정
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());//알림을 알려라[nofity]
    }
    //알림 실행
    public void startTimer(View view) {
        String s = mEditText.getText().toString();
        int min = Integer.parseInt(s.substring(0,2));  //2포함 안함
        int sec = Integer.parseInt(s.substring(3,5));  //5포함 안함
        //타이머
        new CountDownTimer(min+60*1000 +  sec*1000, 1000){
            public void onTick(long millsUntilFinishesd) {
                mEditText.setText(""+ (int)(millsUntilFinishesd/1000) + "초");
            }
            public void onFinish() {
                mEditText.setText("done");
                sendNotification();
            }
        }.start();   // 1000/1초 단위
    }
}