package com.example.to_let.Activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to_let.Model.UserInformation;
import com.example.to_let.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {

    private Button signupButton2;
    private EditText signupFirstName,signupLastName,signupEmail,signupPhone,signupPassword,signupCity;

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    /*    getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/


        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("UserInformation");
        signupButton2 = (Button) findViewById(R.id.signupButtonId2);
        signupFirstName = (EditText) findViewById(R.id.signupFirstNameId);
        signupLastName = (EditText) findViewById(R.id.signupLastNameId);
        signupEmail = (EditText) findViewById(R.id.signupEmailId);
        signupPhone = (EditText) findViewById(R.id.signupPhoneId);
        signupPassword = (EditText) findViewById(R.id.signupPasswordId);
        signupCity = (EditText) findViewById(R.id.signupCityId);
        signupButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                Toast.makeText(SignupActivity.this,"Submitted",Toast.LENGTH_SHORT).show();
                final String FN = signupFirstName.getText().toString().trim();
                final String LN = signupLastName.getText().toString().trim();
                final String P = signupPhone.getText().toString().trim();
                final String C = signupCity.getText().toString().trim();

                //register
                final String Pw=signupPassword.getText().toString().trim();
                final String E=signupEmail.getText().toString().trim();
                if(E.isEmpty()){
                    signupEmail.setError("Enter an Email address");
                    signupEmail.requestFocus();
                    return;
                }if(!Patterns.EMAIL_ADDRESS.matcher(E).matches()){
                    signupEmail.setError("Enter a valid Email address");
                    signupEmail.requestFocus();
                    return;
                } if(Pw.isEmpty()) {
                    signupPassword.setError("Enter a PassWord");
                    signupPassword.requestFocus();
                    return;
                }if(Pw.length()<6){
                    signupPassword.setError("Minimum Length of a Password mustbe 6");
                    signupPassword.requestFocus();
                    return;
                }if(FN.isEmpty()) {
                    signupFirstName.setError("Enter a First Name");
                    signupFirstName.requestFocus();
                    return;
                }if(LN.isEmpty()){
                    signupLastName.setError("Enter a Last Name");
                    signupLastName.requestFocus();
                    return;
                }if( P.isEmpty()){
                    signupPhone.setError("Enter a PhoneNumber");
                    signupPhone.requestFocus();
                    return;
                }if(P.startsWith("01")){
                    signupPhone.setError("PhoneNumber must be start with 01 ");
                    signupPhone.requestFocus();
                    return;
                }
                if(P.length()<11){
                    signupPhone.setError("PhoneNumber must be 11 digits ");
                    signupPhone.requestFocus();
                    return;
                }
                if(C.isEmpty()){
                    signupCity.setError("Enter a City");
                    signupCity.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(E,Pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            //*//for save data in database
                            String key=databaseReference.push().getKey();
                            UserInformation newUser=new UserInformation(FN,LN,E,Pw,P,C);
                            databaseReference.child(key).setValue(newUser);

                            //*//for save data in database

                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        }else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(SignupActivity.this,"Email is already Registered",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignupActivity.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

            }
        });
    }
   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
    }*/







}

