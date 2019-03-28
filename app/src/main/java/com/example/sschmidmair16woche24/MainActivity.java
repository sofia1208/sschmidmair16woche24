package com.example.sschmidmair16woche24;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private boolean login = false;
  List<Message> messages = new ArrayList<>();
  String username;
    FirebaseFirestore db;
  ArrayAdapter<Message> mAdapter;
  String name = "messages";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         db = FirebaseFirestore.getInstance();



    }

    public void onClicked(View view) {
        if(!login)
        {
            EditText et = findViewById(R.id.editText);
             username = et.getText().toString();
            //TODO password angemelden
            login = true;
            et.setHint("ÉNTER MESSAGE");

        }


        else
        {
            EditText et = findViewById(R.id.editText);
            String message = et.getText().toString();
            messages.add(new Message(username,message));
            writeInDB();
        }


    }


    private void bindAdapterToListView (ListView lv, ArrayList list)
    {
        mAdapter = new MessageAdapter(this,list);
        lv.setAdapter(mAdapter);
    }
    private void writeInDB()
    {
        for (Message n : messages)
        {
            db.collection(name)
                    .document(String.valueOf(messages.indexOf(n)))
                    .set(n)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("firestoreDemo.set", "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("firestoreDemo.set", "Error writing document", e);
                        }
                    });
        }
    }
    private void readFromDB()
    {  db.collection(name)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Message note = document.toObject(Message.class);

                          Collections.sort(messages);
                            note.setId(messages.size()+1); ;
                            messages.add(note);

                        }

                    }
                    else {
                        Log.w("firestoreDemo.get", "Error getting documents.", task.getException());
                    }
                }
            });

        ListView v = findViewById(R.id.list_View);
        bindAdapterToListView(v, (ArrayList) messages);

    }
}
