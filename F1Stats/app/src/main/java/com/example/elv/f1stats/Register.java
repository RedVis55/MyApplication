package com.example.elv.f1stats;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ELV on 11-Dec-17.
 */

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String email;
    String password;
    Button registerButton;
    Button backButton;
    EditText emailad;
    EditText passtex;

    private static final String TAG = "Firebase_test";
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        registerButton = (Button) findViewById(R.id.button2);
        emailad = (EditText) findViewById(R.id.emailad);
        passtex = (EditText) findViewById(R.id.passtex);



        //Initialize the FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("signed in", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("signed out", "onAuthStateChanged:signed_out");
                }
            }
        };
        createUser();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    //Create a user and store the user in the database
    public void createUser() {

        final String email = emailad.getText().toString();
        String password = passtex.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password)
         .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("create user", "createUserWithEmail:onComplete:" + task.isSuccessful());
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "User created.",
                            Toast.LENGTH_SHORT).show();
                }
                // ...
                else {
                        Toast.makeText(Register.this, "Authentication failed." + email,
                                Toast.LENGTH_SHORT).show();
                    }
            }

        });

    }
    // Use this to log in the app
    public void logIn (View view) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("signIn", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w("email", "signInWithEmail", task.getException());
                            Toast.makeText(Register.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Register.this, "Logged in " + email,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void addToDBs(View view) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        EditText et3 = (EditText) findViewById(R.id.et3);
//
//        String name = et3.getText().toString();
//
//        Fruit aFruit = new Fruit(name);
//
//        mDatabase.child("fruitbasket").child("fruit1").setValue(aFruit);
//        Log.d("signed out", "fout");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        FirebaseUser user = mAuth.getCurrentUser();


        myRef.child("Users").child(user.getUid()).setValue("X");
    }


    public void setDatabase(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    public void Tolis (View view){
        Intent intent = new Intent(this, MainList.class);

        startActivity(intent);

    }
}

