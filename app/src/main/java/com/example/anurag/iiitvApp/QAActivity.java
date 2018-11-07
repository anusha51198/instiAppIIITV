package com.example.anurag.iiitvApp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class QAActivity extends AppCompatActivity {

    public String QAForum_REF="QA Forum";
    private String text1,text2,text3;
    private long time;
    CollectionReference thoughtsCollectionRef = FirebaseFirestore.getInstance().collection(QAForum_REF);
    ArrayList<Thought> elements = new ArrayList<Thought>();

    ArrayList<String> idData = new ArrayList<String>();
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        firebaseAuth = FirebaseAuth.getInstance();
        String s[] = firebaseAuth.getCurrentUser().getEmail().split("@");
        final String username = s[0];

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QAActivity.this,addThoughtActivity.class);
                startActivity(intent);
            }
        });


        thoughtsCollectionRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {


                            for (final QueryDocumentSnapshot document : task.getResult())
                            {
                                //Object data1 = document.getData();
                                text1 =  (String) document.get("Username");
                                text2 = (String) document.get("addThoughtText");
                                text3 = (String) document.get("timestamp").toString();
                                Thought t = new Thought(text1,text2,text3);

                                elements.add(t);

                                idData.add(document.getId());

                            }

                            final ListView listView = (ListView) findViewById(R.id.list_View);
                            CustomAdapter customAdapter = new CustomAdapter(QAActivity.this,elements);

                            listView.setAdapter(customAdapter);

                            ListView listView2 = (ListView) findViewById(R.id.list_View);
                            listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    for (int i=0;i<idData.size();i++) {
                                        if (i==position) {
                                            Intent intent = new Intent(QAActivity.this, Answer.class);
                                            intent.putExtra("anscol", idData.get(i));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            });







                        }
                    }
                });


        thoughtsCollectionRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {


                for (final DocumentChange dc:queryDocumentSnapshots.getDocumentChanges())
                {
                    switch (dc.getType())
                    {
                        case MODIFIED:
                            text1 =  (String) dc.getDocument().get("Username");
                            text2 = (String) dc.getDocument().get("addThoughtText");
                            text3 = (String) dc.getDocument().get("timestamp").toString();
                            Thought tt = new Thought(text1,text2,text3);
                            elements.add(tt);
                            idData.add(dc.getDocument().getId());

                            ListView listView = (ListView) findViewById(R.id.list_View);
                            CustomAdapter customAdapter = new CustomAdapter(QAActivity.this,elements);
                            listView.setAdapter(customAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    for (int i = 0; i < idData.size(); i++) {
                                        if (i == position) {
                                            Intent intent = new Intent(QAActivity.this, Answer.class);
                                            intent.putExtra("anscol", idData.get(i));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            });



                    }

                }

            }
        });




    }



}
