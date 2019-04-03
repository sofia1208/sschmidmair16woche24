package com.example.sschmidmair16woche24;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
  private boolean login = false;
  List<Message> messages = new ArrayList<>();
  String username;
  String password;
    FirebaseFirestore db;
  ArrayAdapter<Message> mAdapter;
  String name = "messages";
 private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        password = intent.getStringExtra("password");

        db = FirebaseFirestore.getInstance();

        upadateDB();

        ListView listView = findViewById(R.id.list_View);
        bindAdapterToListView(listView, (ArrayList) messages);




    }

    public void onClicked(View view) {



            EditText et = findViewById(R.id.editText);
            String message = et.getText().toString();
            messages.add(new Message(username,message));
        ListView listView = findViewById(R.id.list_View);
        bindAdapterToListView(listView, (ArrayList) messages);
            writeInDB();

            et.clearComposingText();



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
    private void upadateDB()
    {  messages.clear();

        db.collection(name).addSnapshotListener(new EventListener<QuerySnapshot>() {


            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
               for(DocumentChange dc:  queryDocumentSnapshots.getDocumentChanges())
               {
                   switch (dc.getType()) {
                       case ADDED:
                           Message message = dc.getDocument().toObject(Message.class);
                           messages.add(message);
                           Log.d("firestoreDemo.set", "UPADTED!!!");
                           break;
                       case MODIFIED:

                           break;

                       default:
                           break;
                   }
               }
            }
        });

    }
}
