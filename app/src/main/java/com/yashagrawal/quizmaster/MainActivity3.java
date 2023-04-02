package com.yashagrawal.quizmaster;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextInputLayout fullName,userName,email,contactNumber,password;
    Animation topAnim,leftAnim;
    ImageView logo;
    TextView title;
    Button submit;
    private boolean noSpace(String temp) {
        boolean validate = false;
        for (int i = 0; i < temp.length(); i++) {
            if(temp.charAt(i) == ' '){
               return false;
            }
            else {
                validate = true;
            }
        }
        return validate;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        logo = findViewById(R.id.logo_im);
        title = findViewById(R.id.title);
        submit = findViewById(R.id.submit);
        fullName = findViewById(R.id.fullName);
        userName = findViewById(R.id.uname);
        email = findViewById(R.id.email);
        contactNumber = findViewById(R.id.phone);
        password = findViewById(R.id.pass);


        topAnim = AnimationUtils.loadAnimation(MainActivity3.this,R.anim.top_animation);
        leftAnim = AnimationUtils.loadAnimation(MainActivity3.this,R.anim.left_side_animation);

        logo.setAnimation(topAnim);
        title.setAnimation(leftAnim);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validatefname() || !validateuserName() || !validateemail() || !validatecontactNumber() || !validatepassword()){
                    return;
                }

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String Full_Name = fullName.getEditText().getText().toString();
                String User_Name = userName.getEditText().getText().toString();
                String Email_id = email.getEditText().getText().toString();
                String Phone_no = contactNumber.getEditText().getText().toString();
                String Password = password.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(Full_Name,User_Name,Email_id,Phone_no,Password);
                reference.child(User_Name).setValue(helperClass);
                Toast.makeText(MainActivity3.this, "Your account has been created successfully\nKindly go back to the sign page to continue", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private boolean validatefname(){
        String temp = fullName.getEditText().getText().toString();
        if(temp.isEmpty()){
            fullName.setError("Field cannot be empty");
            return false;
        }
        else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateuserName(){
        int i = 0;
        String temp = userName.getEditText().getText().toString();

        if(temp.isEmpty()){
            userName.setError("Field cannot be empty");
            return false;
        }
        else if(!noSpace(temp)){
            userName.setError("Username cannot contain spaces");
            return false;
        }
        else if(temp.length()>=30){
            userName.setError("Username is too long");
            return false;
        }

        else{
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateemail(){
        String temp = email.getEditText().getText().toString();
        int i = 0;
        boolean validate = false;
        while(i<temp.length()){
            if (temp.charAt(i) != '@') {
                validate = false;
            }
            else {
                validate = true;
                break;

            }
            i++;
        }
        if(temp.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }

        else if (!noSpace(temp)) {
            email.setError("Email must not contain spaces");
            return false;
        }
        else if(!validate){
            email.setError("Email should contain @");
            return false;
        }
        else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatecontactNumber(){
        String temp = contactNumber.getEditText().getText().toString();
        if(temp.isEmpty()){
            contactNumber.setError("Field cannot be empty");
            return false;
        }
        else if(temp.length() != 10){
            contactNumber.setError("Field should contain only 10 digits");
            return false;
        }
        else if (!noSpace(temp)) {
            contactNumber.setError("Field must not contain spaces");
            return false;
        }
        else{
            contactNumber.setError(null);
            contactNumber.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatepassword(){
        String temp = password.getEditText().getText().toString();
        if(temp.isEmpty()){
            password.setError("Password cannot be empty");
            return false;
        }
        else if (!noSpace(temp)) {
            password.setError("Password must not contain spaces");
            return false;
        } else if (temp.length() < 8 || temp.length() > 16)
        {
            password.setError("password range must be from 8 - 16 characters");
            return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    // Below method takes you back to the previous page of login
    public void loginPage(View view){
        Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
        startActivity(intent);
        finish();
    }
}