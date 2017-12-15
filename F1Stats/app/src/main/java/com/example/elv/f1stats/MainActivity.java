package com.example.elv.f1stats;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth authTest;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final String TAG = "Firebase_test";
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        mDatabase = FirebaseDatabase.getInstance().getReference();

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");

//        FragmentManager fm = getSupportFragmentManager();
//        MainList fragment = new MainList();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.fragment_container, fragment, "List");
//        ft.commit();
    }

//    public void addToDB(View view) {
//        EditText et3 = (EditText) findViewById(R.id.et3);
//
//        String name = et3.getText().toString();
//
//        Fruit aFruit = new Fruit(name);
//
//        mDatabase.child("fruitbasket").child("fruit1").setValue(aFruit);
//    }

    public void goToSecond (View view){
        Intent intent = new Intent(this, list.class);

        startActivity(intent);

    }

    public void goToTutorial (View view){
        Intent intent = new Intent(this, Register.class);

        startActivity(intent);

    }





//    @Override
}
