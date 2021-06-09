package com.example.firebase18_5_2021;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class RegisterActivity extends AppCompatActivity {
    private EditText emailedit, passedit, nameedit, passtypeedit;
    private Button btnregis;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        emailedit=findViewById(R.id.emailregiss);
        passedit= findViewById(R.id.passregiss);
        nameedit=findViewById(R.id.name);
        passtypeedit= findViewById(R.id.passtype);

        btnregis = findViewById(R.id.btnregiss);
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
            }
        });
    }

    private void register() {
        String email, pass, name, passtype;
        email=emailedit.getText().toString();
        pass=passedit.getText().toString();
        name= nameedit.getText().toString();
        passtype = passtypeedit.getText().toString();
        //Kiẻm tra ô nhập bị trống
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Vui lòng nhập name",Toast.LENGTH_SHORT).show();
            return;
        }        if(TextUtils.isEmpty(passtype)){
            Toast.makeText(this,"Vui lòng nhập password type",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui lòng nhập email !",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui lòng nhập password !",Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent (RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Tạo tài khoản không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

