package com.vvit.ammu.shopowner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    EditText user,password;
    Button login;
    TextView register;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        user = (EditText) findViewById(R.id.id_name);
        password = (EditText) findViewById(R.id.id_password);

        login = (Button)findViewById(R.id.id_login_button);
        register = (TextView) findViewById(R.id.id_register_user);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("shopowners");
        mFirebaseInstance.getReference("app_title").setValue("ShopOwnerDatabase");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseInstance.getReference("shopowners").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        ShopOwner user = dataSnapshot.getValue(ShopOwner.class);

                        if (LoginActivity.this.user.getText().toString().equals(user.getShop_owner_login_name())
                                &&
                                LoginActivity.this.password.getText().toString().equals(user.getShop_onwer_password())) {

                                startActivity(new Intent(LoginActivity.this,OwnerHomeActivity.class));
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.e(TAG, "Failed to read app title value.", error.toException());
                    }
                });

            }
        });


    }


    /**
     * Creating new user node under 'users'
     */
    private void createUser(String name, String pwd) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        ShopOwner user = new ShopOwner(name, pwd,userId);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ShopOwner user = dataSnapshot.getValue(ShopOwner.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.getShop_owner_login_name() + ", " + user.getShop_onwer_password());



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    private void updateUser(String name, String pwd) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(userId).child("name").setValue(name);

        if (!TextUtils.isEmpty(pwd))
            mFirebaseDatabase.child(userId).child("email").setValue(pwd);
    }
    public void registerOwner(View view) {
        createUser(user.getText().toString(),password.getText().toString());
        Toast.makeText(this,"New Shop Owner Credetials were saved",Toast.LENGTH_SHORT).show();
    }
}
