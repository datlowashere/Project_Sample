package com.edu.example.assignmentprojectsample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.example.assignmentprojectsample.Dao.ThuThuDao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {


    EditText edUsername, edPassword;
    CheckBox ckRemember;
    TextInputLayout tilUser,tilPass;
    ThuThuDao dao;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//        getSupportActionBar().hide();
        setContentView(R.layout.login);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        ckRemember = findViewById(R.id.ckRemember);
        tilUser=findViewById(R.id.tilUser);
        tilPass=findViewById(R.id.tilPass);


        String user = edUsername.getText().toString();
        String pass = edPassword.getText().toString();

        dao = new ThuThuDao(Login.this);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        restoringUser();

    }
    public void checkLogin(){
        String user = edUsername.getText().toString();
        String pass = edPassword.getText().toString();
        String error="";
        if(user.isEmpty()||pass.isEmpty()) {
            error+="Tên đăng nhập và mật khẩu không được để trống";

        }else {
            if (dao.checkLogin(user,pass)>0 ) {
                error+="Đăng nhập thành công";
                rememberUser(user,pass,ckRemember.isChecked());
                Intent ilogin = new Intent(getApplicationContext(), MainActivity.class);
                ilogin.putExtra("user",user);
                startActivity(ilogin);
                finish();
            } else {
                error+="Tên đăng nhập hoặc mật khẩu sai";

            }
        }
        Toast.makeText(Login.this,error,Toast.LENGTH_SHORT).show();


    }

    private void rememberUser(String u,String p,boolean status){
        SharedPreferences spf = getSharedPreferences("file", MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        String user = edUsername.getText().toString();
        String pass = edPassword.getText().toString();
        boolean ck = ckRemember.isChecked();
        if (!ck) {
            editor.clear();
        } else {
            editor.putString("username", user);
            editor.putString("password", pass);
            editor.putBoolean("checked", ck);
        }
        editor.commit();
    }
    private void restoringUser(){
        SharedPreferences spf=getSharedPreferences("file",MODE_PRIVATE);
        boolean ck=spf.getBoolean("checked",false);
        if(ck){
            String user=spf.getString("username","");
            String pass=spf.getString("password","");
            edUsername.setText(user);
            edPassword.setText(pass);
            ckRemember.setChecked(true);
        }
    }


}