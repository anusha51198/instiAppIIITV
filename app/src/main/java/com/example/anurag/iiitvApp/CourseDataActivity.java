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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursename);

        final String TAG = "CourseDataActivity";
        final String sem_no = getIntent().getStringExtra("semester");
        final String branch = getIntent().getStringExtra("branch");
        final String course = getIntent().getStringExtra("course");

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final List<String> courseDataList = new ArrayList<>();
        final ListView listView = (ListView) findViewById(R.id.lv_courses);

        db.collection("BTECH").document(branch).collection(sem_no).document(course)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            Map<String, Object> map = task.getResult().getData();
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                courseDataList.add(entry.getKey());
                                Log.d("TAG", entry.getKey());
                            }
                            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.fragment_courses,courseDataList);
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String field = adapter.getItem(i);
                                    Intent intent = new Intent(CourseDataActivity.this, CourseDetailActivity.class);
                                    intent.putExtra("branch",branch);
                                    intent.putExtra("semester",sem_no);
                                    intent.putExtra("course",course);
                                    intent.putExtra("field",field);
                                    startActivity(intent);
                                }
                            });
                        }
                        else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
