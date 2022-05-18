package com.example.anything;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    Button signUpButton;
    Button signInButton;
    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    private ProgressBar loadingProgressBar;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEditText = (EditText) findViewById(R.id.usernameSignUpText);
        emailEditText = (EditText) findViewById(R.id.emailSignUpText3);
        passwordEditText = (EditText) findViewById(R.id.passwordSignUpText);

        signInButton = (Button) findViewById(R.id.buttonSignIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

        signUpButton = (Button) findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar = v.findViewById(R.id.requestLoading);
                executeRequest();
            }

        });

    }

    public void executeRequest() {

        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(username == null || username == ""){
            Toast.makeText(getApplicationContext(),"Fill in username",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(username == null || username == ""){
            Toast.makeText(getApplicationContext(),"Fill in username",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(username == null || username == ""){
            Toast.makeText(getApplicationContext(),"Fill in username",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://anything-app.herokuapp.com/auth/new?name=" + username + "&email=" + email + "&password=" + password;
        RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
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
                    if(message.trim().equals("successful")) {
                        Toast.makeText(getApplicationContext(), content,
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, ConfirmationActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), content + ", please try again",
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