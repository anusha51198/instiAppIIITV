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

public class addThoughtActivity extends AppCompatActivity {

    public String QAForum_REF = "QA Forum";
    EditText editText2;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thought);

        firebaseAuth = FirebaseAuth.getInstance();
       // String s[] = firebaseAuth.getCurrentUser().getEmail().split("@");
       // final String username = s[0];

        final String username = firebaseAuth.getCurrentUser().getDisplayName().toString();


        editText2 = (EditText) findViewById(R.id.editText2);

        Button button = (Button) findViewById(R.id.postButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,Object> data = new HashMap<>();
                data.put("addThoughtText",editText2.getText().toString());
                data.put("timestamp",FieldValue.serverTimestamp());
                data.put("Username",username);


                FirebaseFirestore.getInstance().collection(QAForum_REF)
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("Error: " , e.toString());
                                Toast toast =Toast.makeText(getApplicationContext(),"Check again",Toast.LENGTH_LONG);
                                toast.setMargin(50,50);
                                toast.show();
                            }
                        });

            }
        });
    }
}
