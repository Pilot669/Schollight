package com.example.schollight;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    private static int MAX_MESSAGE_LENGTH = 100;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("messages");

    EditText mitext;
    Button mSendButoon;
    RecyclerView mMessageRecycler;

    ArrayList<String> messages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mSendButoon = findViewById(R.id.button2);
        mitext = findViewById(R.id.text3);
        mMessageRecycler = findViewById(R.id.message_recycler);

        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));

        final DataAdapter dataAdapter = new DataAdapter(this,messages);
        mMessageRecycler.setAdapter(dataAdapter);

        mSendButoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = mitext.getText().toString();
                if (msg.equals("")){
                    Toast.makeText(getApplicationContext(),"Введите сообщение!",Toast.LENGTH_SHORT).show();
                return;
                }

                if (msg.length() == MAX_MESSAGE_LENGTH) {
                    Toast.makeText(getApplicationContext(),"Слишком длинное сообщение!",Toast.LENGTH_SHORT).show();
                return;
                }
                myRef.push().setValue(msg);
                mitext.setText("");

            }

        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String msg = dataSnapshot.getValue(String.class);
                messages.add(msg);
                dataAdapter.notifyDataSetChanged();
                mMessageRecycler.smoothScrollToPosition(messages.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

