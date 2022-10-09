package com.edu.example.assignmentprojectsample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView img;
    TextView tvTitle,tvSub,tvName;
    Animation top,right,bot,left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


       img=findViewById(R.id.imgSplash);
       tvName=findViewById(R.id.tvName);
       tvSub=findViewById(R.id.tvSubject);
       tvTitle=findViewById(R.id.tvTitle);

       top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
       right=AnimationUtils.loadAnimation(this,R.anim.right);
       bot=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
       left=AnimationUtils.loadAnimation(this,R.anim.left);

       img.setAnimation(top);
       tvTitle.setAnimation(right);
       tvSub.setAnimation(left);
       tvName.setAnimation(bot);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iloginA=new Intent(SplashScreen.this,Login.class);
                startActivity(iloginA);
                finish();
            }
        },4000);




    }
}