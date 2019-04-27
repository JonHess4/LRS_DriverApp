package com.cs3370.android.lrs_driverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mPassword;
    private TextView mErrorMessage;
    private Button mSignInButton;
    private RequestQueue mRequestQueue;

    private Driver mDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mUserName = (EditText) findViewById(R.id.userName);
        mPassword = (EditText) findViewById(R.id.password);
        mErrorMessage = (TextView) findViewById(R.id.errorMessage);
        mSignInButton = (Button) findViewById(R.id.signInButton);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(mUserName.getText().toString(), mPassword.getText().toString());
            }
        });
    }

    //checks if they are in the database, identifies if they are a driver or a passenger, directs them to the proper screen
    private void validate(String userName, String userPassword) {
        mRequestQueue = Volley.newRequestQueue(this);
        String url = "https://apps.ericvillnow.com/rideshare/api/login?email=" + userName + "&password=" + userPassword;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject user = response.getJSONObject("user");

                    boolean authorized = response.getBoolean("authorized");
                    String role = response.getString("role");

                    if (authorized && role.equals("driver")) {
                        String id = user.getString("id");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String phone_number = user.getString("phone_number");
                        String rating = user.getString("rating");
                        String history = user.getString("history");
                        String created_at = user.getString("created_at");
                        String updated_at = user.getString("updated_at");
                        boolean online = user.getBoolean("online");
                        mDriver = Driver.getInstance();
                        mDriver.set("authorized", authorized);
                        mDriver.set("role", role);
                        mDriver.set("id", id);
                        mDriver.set("name", name);
                        mDriver.set("email", email);
                        mDriver.set("phoneNumber", phone_number);
                        mDriver.set("rating", rating);
                        mDriver.set("history", history);
                        mDriver.set("createdAt", created_at);
                        mDriver.set("updatedAt", updated_at);
                        mDriver.set("online", online);

                        Intent intent = new Intent(SignInActivity.this, RecyclerViewActivity.class);
                        startActivity(intent);

                    } else {
                        mErrorMessage.setText("Invalid Username or Password");
                    }
                } catch (JSONException e1) {
                    mErrorMessage.setText("Invalid Username or Password");
                    e1.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}
