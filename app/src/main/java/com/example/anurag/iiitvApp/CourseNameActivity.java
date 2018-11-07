package com.example.anurag.iiitvApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CourseNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursename);

        final String TAG = "CourseNameActivity";
        final String sem_no = getIntent().getStringExtra("semester");
        final String branch = getIntent().getStringExtra("branch");
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final List<String> coureseslist = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.lv_courses);


        db.collection("BTECH").document(branch).collection(sem_no)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                //String c = document.getId();
                                coureseslist.add(document.getId());
                            }
                            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.fragment_courses,coureseslist);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String course = adapter.getItem(i);
                                    Intent intent = new Intent(CourseNameActivity.this, CourseDataActivity.class);
                                    intent.putExtra("semester",sem_no);
                                    intent.putExtra("branch",branch);
                                    intent.putExtra("course",course);
                                    startActivity(intent);
                                }
                            });

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
