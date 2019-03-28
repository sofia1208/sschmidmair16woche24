package com.example.sschmidmair16woche24;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
  private boolean login = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();



    }

    public void onClicked(View view) {
        EditText et = findViewById(R.id.editText);
        String username = et.getText().toString();
        //TODO password angemelden
        et.setHint("ENTER PASSWORD");

        


    }
}
