package com.example.anurag.iiitvApp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class IIITVMainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final String TAG = "MainActivity";
    FirebaseAuth firebaseAuth;

    private DrawerLayout dl;
    private ActionBarDrawerToggle adt;
    private Button qabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iiitvmain);

        firebaseAuth = FirebaseAuth.getInstance();




            qabtn = (Button) findViewById(R.id.b_qa);
            qabtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (firebaseAuth.getCurrentUser() != null) {
                        String s[] = firebaseAuth.getCurrentUser().getEmail().split("@");
                        String username = s[0];
                        Intent intent = new Intent(IIITVMainActivity.this, QAActivity.class);
                        intent.putExtra("Username1", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(IIITVMainActivity.this, "Please login to continue", Toast.LENGTH_SHORT).show();
                    }
                }
            });




        dl = (DrawerLayout) findViewById(R.id.dl_main);
        adt = new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);
        adt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(adt);
        adt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_main);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.menu_about)
                {
                    Toast.makeText(IIITVMainActivity.this,"About",Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.menu_hec) {
                    Toast.makeText(IIITVMainActivity.this,"HEC Guilidelines",Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.menu_cal) {
                    Toast.makeText(IIITVMainActivity.this,"Academic Calendar",Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.menu_timetable){
                    Toast.makeText(IIITVMainActivity.this,"Time Table",Toast.LENGTH_SHORT).show();
                }

                else if (id == R.id.menu_logout)
                {
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(IIITVMainActivity.this,LoginActivity.class));
                }

                return true;
            }
        });

        Button courses = (Button) findViewById(R.id.b_courses);



        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IIITVMainActivity.this,BranchActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return adt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
