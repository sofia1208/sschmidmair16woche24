package com.example.sschmidmair16woche24;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIN extends AppCompatActivity {
   FirebaseAuth mAuth;
   EditText un,pw;
   FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);


        mAuth= FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


         un = findViewById(R.id.textName);
        un.getText().toString();
         pw = findViewById(R.id.textPassword);
        pw.getText().toString();

    }
    public void createUser(View v)
    {
        String s = un.getText().toString();
        String s1 = pw.getText().toString();

        mAuth.createUserWithEmailAndPassword(s,s1)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Toast.makeText(getApplicationContext(), "USER ADDED", Toast.LENGTH_LONG).show();
                }
            }
        });
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("name",s);

        startActivity(i);
    }
}
