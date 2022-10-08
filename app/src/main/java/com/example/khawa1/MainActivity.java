package com.example.khawa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtSlogan ;
    Button SignIn, SignUp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignIn=findViewById(R.id.SignIn) ;
        SignUp=findViewById(R.id.SignUp) ;
        txtSlogan=findViewById(R.id.txtSlogan) ;

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF") ;
        txtSlogan.setTypeface(face);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignIn=new Intent(MainActivity.this, SignIn.class) ;
                startActivity(SignIn) ;
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUp=new Intent(MainActivity.this, SignUp.class) ;
                startActivity(SignUp) ;
            }
        });

    }
}