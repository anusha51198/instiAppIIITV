package com.example.anurag.iiitvApp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail,editTextPassword;
    private Button signUpButton;
    private TextView textViewSignIn;
    private TextView textViewguest;
    private ProgressDialog progressDialog;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null)
        {
           // finish();
            startActivity(new Intent(MainActivity.this,IIITVMainActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignIn = (TextView) findViewById(R.id.textViewSignin);
        textViewguest = (TextView) findViewById(R.id.guestsession);

        signUpButton = (Button) findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (password.length()<8)
                {
                    Toast.makeText(MainActivity.this,"Password must be at least 8 characters long",Toast.LENGTH_SHORT).show();
                    return;
                }

                String s[] = new String[2];
                s = email.split("@");

                final boolean flag = s[1].equals("iiitvadodara.ac.in");

                if (TextUtils.isEmpty(email) && flag==false)
                {
                    Toast.makeText(MainActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(MainActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Registering User...");
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful())
                                {
                                    finish();
                                    Intent intent = new Intent(MainActivity.this,IIITVMainActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this,"Could not Register.. Please try again",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        textViewguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,IIITVMainActivity.class));
            }
        });
    }
}
