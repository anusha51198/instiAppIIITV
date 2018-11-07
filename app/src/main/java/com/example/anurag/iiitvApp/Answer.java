package com.example.anurag.iiitvApp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class Answer extends AppCompatActivity {

    private String text1,text2,text3;
    ArrayList<Thought> answers = new ArrayList<Thought>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);



        final String AnsColId = getIntent().getStringExtra("anscol");
        CollectionReference answersCollectionRef = FirebaseFirestore.getInstance().collection("QA Forum")
                .document(AnsColId).collection(AnsColId);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Answer.this,addAnswerActivity.class);
                intent.putExtra("AnsColId",AnsColId);
                startActivity(intent);
            }
        });

        answersCollectionRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            for (final QueryDocumentSnapshot document : task.getResult())
                            {
                                text1 =  (String) document.get("Username");
                                text2 = (String) document.get("Answer");
                                text3 = (String) document.get("Timestamp").toString();
                                Thought t = new Thought(text1,text2,text3);

                                answers.add(t);


                                ListView listView1 = (ListView) findViewById(R.id.list_View1);
                                CustomAdapter customAdapter = new CustomAdapter(Answer.this,answers);
                                listView1.setAdapter(customAdapter);
                            }
                        }
                    }
                });


        answersCollectionRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                for (final DocumentChange dc:queryDocumentSnapshots.getDocumentChanges())
                {
                    switch (dc.getType())
                    {
                        case MODIFIED:
                            text1 = (String) dc.getDocument().get("Username");
                            text2 = (String) dc.getDocument().get("Answer");
                            text3 = (String) dc.getDocument().get("Timestamp").toString();
                            Thought tt = new Thought(text1,text2,text3);
                            answers.add(tt);

                            ListView listView1 = (ListView) findViewById(R.id.list_View1);
                            CustomAdapter customAdapter = new CustomAdapter(Answer.this,answers);
                            listView1.setAdapter(customAdapter);



                    }

                }

            }
        });



    }
}
