package apps.onkar.android.basiclogin;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManagerLogin extends AppCompatActivity {

    Button b1,b2,b3;
    EditText ed1,ed2;

    String email1,password1,email2,password2;

    TextView tx1;
    int counter = 3;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReferencemanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        b2 = (Button)findViewById(R.id.button2);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        b3 = (Button)findViewById(R.id.button3);


        Log.v("TAG",email1+"  "+password1);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReferencemanager = mFirebaseDatabase.getReference().child("manager");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email1 = ed1.getText().toString();
                password1 = ed2.getText().toString();
                mMessagesDatabaseReferencemanager.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot data : dataSnapshot.getChildren()){
                            email2 = data.child("email").getValue(String.class);
                            password2 = data.child("password").getValue(String.class);
                            Log.v("TAG",email1+"/"+email2+"  "+password1+"/"+password2);
                            if(email1.equals(email2) && password1.equals(password2)){
                                Toast.makeText(getApplicationContext(),
                                        "Redirecting...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ManagerLogin.this, ManagerLoginSuccess.class));
                            }
                            else{
                                tx1.setVisibility(View.VISIBLE);
                                tx1.setBackgroundColor(Color.RED);
                                counter--;
                                tx1.setText(Integer.toString(counter));

                                if (counter == 0) {
                                    b1.setEnabled(false);
                                }
                            }
                        }

                        Log.v("TAG",email1+"/"+email2+"  "+password1+"/"+password2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerLogin.this, ManagerRegister.class));
            }
        });
    }
}
