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

import com.example.to_let.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity  {
    private FirebaseAuth mAuth;
    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private Button createButton;
    private int counter = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar

      /*  LoginActivity.this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        LoginActivity.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
*/
        //set content view AFTER ABOVE sequence (to avoid crash)
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        loginEmail = (EditText) findViewById(R.id.loginEmailId);
        //userName.setShowSoftInputOnFocus(false);
        // password.setShowSoftInputOnFocus(false);
        //or in manifest.xml//android:windowSoftInputMode="stateHidden"

        loginPassword = (EditText) findViewById(R.id.loginPasswordId);
        createButton = (Button) findViewById(R.id.createButtonId);
        loginButton = (Button) findViewById(R.id.loginButtonId);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                                startActivity(intent);

                            }
                        });
                    }

                }).start();



            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
              new Thread(new Runnable() {
                    @Override
                    public void run() {
                        view.post(new Runnable() {
                            @Override
                            public void run() {

                                String E = loginEmail.getText().toString().trim();
                                String Pw = loginPassword.getText().toString().trim();

                                if(E.isEmpty()){
                                    loginEmail.setError("Enter an Email address");
                                    loginEmail.requestFocus();
                                    return;
                                }if(!Patterns.EMAIL_ADDRESS.matcher(E).matches()){
                                    loginEmail.setError("Enter a valid Email address");
                                    loginEmail.requestFocus();
                                    return;
                                } if(Pw.isEmpty()) {
                                    loginPassword.setError("Enter a PassWord");
                                    loginPassword.requestFocus();
                                    return;
                                }if(Pw.length()<6){
                                    loginPassword.setError("Minimum Length of a Password mustbe 6");
                                    loginPassword.requestFocus();
                                    return;

                                }


                                mAuth.signInWithEmailAndPassword(E,Pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(LoginActivity.this,"Submitted",Toast.LENGTH_SHORT).show();
                                             finish();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);

                                        }else{
                                            Toast.makeText(LoginActivity.this,"Login UnSuccessfull",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        });
                    }
                }).start();


            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (mAuth == null) {
            finish();
        }

        if (currentUser != null) {
            //loginUserAndUpdateInstanceId(currentUser.getUid());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }


  /*  private void loginUserAndUpdateInstanceId(String uid) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("UserInformation").child(uid);
        String token = FirebaseInstanceId.getInstance().getToken();
        ref.child("instanceid").setValue(token);
    }*/

}








