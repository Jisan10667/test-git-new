package com.example.khawa1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText editPhone, editPassword ;
    Button SignIn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editPassword=findViewById(R.id.editPassword) ;
        editPhone=findViewById(R.id.edit_phone) ;
        SignIn=findViewById(R.id.SignIn) ;

        FirebaseDatabase database=FirebaseDatabase.getInstance() ;
        DatabaseReference table_user=database.getReference("User") ;

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(editPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = snapshot.child(editPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(editPassword.getText().toString())) {
                                Intent homeIntent=new Intent(SignIn.this,  Home.class) ;
                                Common.currentUser=user ;
                                startActivity(homeIntent) ;
                                finish();
                                Toast.makeText(SignIn.this, "Sign In Successfully !", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(SignIn.this, "Sign In Failed !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User doesn't exist at all! !", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}