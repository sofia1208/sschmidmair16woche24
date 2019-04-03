package com.example.sschmidmair16woche24;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
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




         un = findViewById(R.id.textName);
        un.getText().toString();
         pw = findViewById(R.id.textPassword);
        pw.getText().toString();



    }
    public void createUser(View v)
    {
        String s = un.getText().toString();
        String s1 = pw.getText().toString();



        mAuth.createUserWithEmailAndPassword(s, s1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d("", " createUserWithEmail:onComplete:" + task.isSuccessful());
                        // if task is successful  then AuthStateListener  will get notified you can get user details there.
                        // if task is not successful show error
                        if (!task.isSuccessful()) {

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException e) {
                                // log error here

                            } catch (FirebaseNetworkException e) {
                                // log error here
                            } catch (Exception e) {
                                // log error here
                            }

                        } else {

                            Log.d("", " createUserWithEmail:onComplete:" + task.isSuccessful());

                        }
                    }

                });


        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("name",s);
        i.putExtra("password", s1);

        startActivity(i);
    }
}
