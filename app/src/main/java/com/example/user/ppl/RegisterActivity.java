package com.example.user.ppl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.email_register_button);
        editTextEmail = (EditText) findViewById(R.id.email_register);
        editTextPassword = (EditText) findViewById(R.id.password_register);

        buttonRegister.setOnClickListener(this);

    }

    private void registerUser(){
        String email= editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            return;
        }

        if (TextUtils.isEmpty(password)){
            return;
        }

        progressDialog.setMessage("Registering User ...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Register Sucessfully",Toast.LENGTH_LONG ).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this,"Could not register. Please try again",Toast.LENGTH_LONG ).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            registerUser();
                    }

    }
}
