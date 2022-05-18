package com.example.anything;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button buttonLog;
    Button signUpButton;
    EditText usernameEditText;
    EditText passwordEditText;
    String username = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLog = (Button) findViewById(R.id.buttonLog);
        usernameEditText = findViewById(R.id.usernameSignUpText);
        passwordEditText = findViewById(R.id.emailSignUpText);

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if(username.equals("")||username == null){
                    Toast.makeText(getApplicationContext(), "fill in username box",Toast.LENGTH_SHORT).show();
                } else if(username.equals("") || username == null){
                    Toast.makeText(getApplicationContext(), "fill in password box",Toast.LENGTH_SHORT).show();
                } else {
                    executeRequest();
                }
            }
        });

        signUpButton = (Button) findViewById(R.id.buttonReg);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                URL url = null;
            }
        });
    }

    public void executeRequest() {

        String url = "https://anything-app.herokuapp.com/auth/login?username=" + username + "&password=" + password;
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                //loadingProgressBar.setVisibility(View.GONE);

                try {
                    String resp = response.getString("result");
                    String content = "";
                    String message = "";
                    try {
                        content = resp.substring(resp.indexOf(":"), resp.length());
                        message = resp.substring(0, resp.indexOf(":"));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured, please try again",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(message.trim().equals("successful")|| message.trim().equals("successful:")) {
                        startActivity(new Intent(MainActivity.this, ProductList_activity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), message + ", please try again",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Request error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObject);
    }
}