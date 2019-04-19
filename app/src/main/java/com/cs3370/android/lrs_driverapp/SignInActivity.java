package com.cs3370.android.lrs_driverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPassword;
    private TextView mMessage;
    private Button mSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mUserName = (EditText) findViewById(R.id.userName);
        mPassword = (EditText) findViewById(R.id.password);
        mMessage = (TextView) findViewById(R.id.message);
        mSignIn = (Button) findViewById(R.id.signInButton);

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(mUserName.getText().toString(), mPassword.getText().toString());
            }
        });
    }

    //checks if they are in the database, identifies if they are a driver or a passenger, directs them to the proper screen
    private void validate(String userName, String userPassword) {
        if (userName.equals("Driver") && userPassword.equals("1234")) {
            Intent intent = new Intent(SignInActivity.this, RecyclerViewActivity.class);
            startActivity(intent);
        }else {
            mMessage.setText("Incorrect Username or Password");
        }
    }
}
