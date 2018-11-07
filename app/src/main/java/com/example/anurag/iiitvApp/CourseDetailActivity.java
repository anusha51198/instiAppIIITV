package com.example.anurag.iiitvApp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetail);

        final TextView coursedetail = (TextView) findViewById(R.id.tv_coursedetail);
        coursedetail.setMovementMethod(new ScrollingMovementMethod());

        final String TAG = "CourseDetailActivity";
        final String sem_no = getIntent().getStringExtra("semester");
        final String branch = getIntent().getStringExtra("branch");
        final String course = getIntent().getStringExtra("course");
        final String field = getIntent().getStringExtra("field");

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("BTECH").document(branch).collection(sem_no).document(course)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            String detail = String.valueOf(task.getResult().get(field));
                            coursedetail.setText(detail);

                        }
                    }
                });


    }
}
