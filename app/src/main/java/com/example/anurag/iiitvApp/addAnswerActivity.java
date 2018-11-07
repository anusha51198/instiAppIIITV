package com.example.anurag.iiitvApp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addAnswerActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);


        firebaseAuth = FirebaseAuth.getInstance();

        String s[] = firebaseAuth.getCurrentUser().getEmail().split("@");
        final String username = s[0];

            final String ansColId = getIntent().getStringExtra("AnsColId");

           // final EditText editText7 = (EditText) findViewById(R.id.editText5);
            final EditText editText8 = (EditText) findViewById(R.id.editText6);

            Button button1 = (Button) findViewById(R.id.postButton1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Map<String, Object> data1 = new HashMap<>();
                    data1.put("Username", username);
                    data1.put("Answer", editText8.getText().toString());
                    data1.put("Timestamp", FieldValue.serverTimestamp());


                    FirebaseFirestore.getInstance().collection("QA Forum").document(ansColId)
                            .collection(ansColId)
                            .add(data1)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Error: ", e.toString());
                                    Toast toast = Toast.makeText(getApplicationContext(), "Check again", Toast.LENGTH_LONG);
                                    toast.setMargin(50, 50);
                                    toast.show();
                                }
                            });
                }
            });
        }
    }

