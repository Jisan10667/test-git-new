package com.example.khawa1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    EditText editPhone,editName,editPassword ;
    Button SignUp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editName=findViewById(R.id.editName) ;
        editPhone=findViewById(R.id.edit_phone) ;
        editPassword=findViewById(R.id.editPassword) ;
        SignUp=findViewById(R.id.SignUp) ;

        FirebaseDatabase database=FirebaseDatabase.getInstance() ;
        DatabaseReference table_user=database.getReference("User") ;
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog=new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please waiting....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(editPhone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone number already registered", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            mDialog.dismiss();
                            User user=new User(editName.getText().toString(),editPassword.getText().toString()) ;
                            table_user.child(editPhone.getText().toString()).setValue(user) ;
                            Toast.makeText(SignUp.this, "Sign Up successfully done!", Toast.LENGTH_SHORT).show();
                            finish() ;
                            return;
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