package com.example.schollight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Entertainment extends AppCompatActivity {

    private Button btn2, btn3,btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        addListenerOnButton();
    }


    public void addListenerOnButton () {
        btn2 = (Button) findViewById(R.id.Internet1);
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Youtube");
                        startActivity(intent);

                    }
                }
        );


        btn4 = (Button) findViewById(R.id.radio1);
        btn4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Radio");
                        startActivity(intent);

                    }
                }
        );
    }
}
