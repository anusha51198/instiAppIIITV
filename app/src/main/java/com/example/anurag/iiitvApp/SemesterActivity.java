package com.example.anurag.iiitvApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SemesterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        Button sem1 = (Button) findViewById(R.id.b_sem1);
        Button sem2 = (Button) findViewById(R.id.b_sem2);
        Button sem3 = (Button) findViewById(R.id.b_sem3);
        Button sem4 = (Button) findViewById(R.id.b_sem4);
        Button sem5 = (Button) findViewById(R.id.b_sem5);
        Button sem6 = (Button) findViewById(R.id.b_sem6);
        Button sem7 = (Button) findViewById(R.id.b_sem7);
        Button sem8 = (Button) findViewById(R.id.b_sem8);

        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SemesterActivity.this,CourseNameActivity.class);
                i.putExtra("semester","Semester 1");
                i.putExtra("branch",getIntent().getStringExtra("branch"));
                startActivity(i);
            }
        });

        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SemesterActivity.this,CourseNameActivity.class);
                i.putExtra("semester","Semester 2");
                i.putExtra("branch",getIntent().getStringExtra("branch"));
                startActivity(i);
            }
        });

        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SemesterActivity.this,CourseNameActivity.class);
                i.putExtra("semester","Semester 3");
                i.putExtra("branch",getIntent().getStringExtra("branch"));
                startActivity(i);
            }
        });

        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SemesterActivity.this,CourseNameActivity.class);
                i.putExtra("semester","Semester 4");
                i.putExtra("branch",getIntent().getStringExtra("branch"));
                startActivity(i);
            }
        });

        sem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SemesterActivity.this,CourseNameActivity.class);
                i.putExtra("semester","Semester 5");
                i.putExtra("branch",getIntent().getStringExtra("branch"));
                startActivity(i);
            }
        });

        sem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SemesterActivity.this,CourseNameActivity.class);
                i.putExtra("semester","Semester 6");
                i.putExtra("branch",getIntent().getStringExtra("branch"));
                startActivity(i);
            }
        });

        sem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SemesterActivity.this,CourseNameActivity.class);
                i.putExtra("semester","Semester 7");
                i.putExtra("branch",getIntent().getStringExtra("branch"));
                startActivity(i);
            }
        });

        sem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SemesterActivity.this,CourseNameActivity.class);
                i.putExtra("semester","Semester 8");
                i.putExtra("branch",getIntent().getStringExtra("branch"));
                startActivity(i);
            }
        });
    }
}
