package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class MainActivity extends AppCompatActivity {
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bg_gif = (ImageView) findViewById(R.id.main_bg);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(bg_gif);
        Glide.with(this).load(R.drawable.background).into(gifImage);

        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
        Button register = (Button) findViewById(R.id.register_btn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "[회원 등록 페이지]준비중", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, BusInfoActivity.class);
        startActivity(intent);
    }
}