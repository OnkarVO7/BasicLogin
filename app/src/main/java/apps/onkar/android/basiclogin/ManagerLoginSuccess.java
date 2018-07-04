package apps.onkar.android.basiclogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ManagerLoginSuccess extends AppCompatActivity {

    private ListView mPersonListView;
    Button signoutbutton;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReferenceuser;
    private ChildEventListener mChildEventListener;

    private PersonAdapter mPersonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login_success);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReferenceuser = mFirebaseDatabase.getReference().child("user");
        mPersonListView = (ListView) findViewById(R.id.personListView);


        List<Person> personlist= new ArrayList<>();
        mPersonAdapter = new PersonAdapter(this, R.layout.item_person, personlist);
        mPersonListView.setAdapter(mPersonAdapter);


        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Person person = dataSnapshot.getValue(Person.class);
                    mPersonAdapter.add(person);
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            mMessagesDatabaseReferenceuser.addChildEventListener(mChildEventListener);
        }


        signoutbutton = (Button) findViewById(R.id.signoutbutton);

        signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
