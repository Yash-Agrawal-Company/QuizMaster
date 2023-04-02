package com.yashagrawal.quizmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {
    Animation topAnim,rightAnimation;
    ImageView logoImage;
    TextView welcome,newAccount,forget;
    Button login;
    TextInputLayout user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        welcome = findViewById(R.id.welcome);
        logoImage = findViewById(R.id.logoImage);
        forget = findViewById(R.id.forget);
        login = findViewById(R.id.login);
        newAccount = findViewById(R.id.newAccount);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);

        topAnim = AnimationUtils.loadAnimation(MainActivity2.this,R.anim.top_animation);
        rightAnimation = AnimationUtils.loadAnimation(MainActivity2.this,R.anim.right_side_animation);


        logoImage.setAnimation(topAnim);
        welcome.setAnimation(rightAnimation);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUserName() || !validatePassword()){
                    return;
                }
                else{
                    final String enterredUserName = user.getEditText().getText().toString().trim();
                    final String enterredPassword = pass.getEditText().getText().toString().trim();

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                    Query checkUser = reference.orderByChild("user_Name").equalTo(enterredUserName);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                user.setError(null);
                                user.setErrorEnabled(false);
                                String passByDB = snapshot.child(enterredUserName).child("password").getValue(String.class);

                                if (passByDB.equals(enterredPassword)){
                                    pass.setError(null);
                                    pass.setErrorEnabled(false);
                                    Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    pass.setError("Password does not match");
                                }
                            }
                            else{
                                user.setError("No such user found");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
            }
        });

    }
    private boolean validateUserName(){
        String temp = user.getEditText().getText().toString();
        if(temp.isEmpty()){
            user.setError("Field cannot be empty");
            return false;
        }
        else{
            user.setError(null);
            user.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(){
        String temp = pass.getEditText().getText().toString();
        if(temp.isEmpty()){
            pass.setError("Field cannot be empty");
            return false;
        }
        else{
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        }
    }
    
}