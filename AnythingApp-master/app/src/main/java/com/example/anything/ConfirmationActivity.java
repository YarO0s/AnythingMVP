package com.example.anything;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
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

public class ConfirmationActivity extends AppCompatActivity{

    Button dragFooterButton;
    Button confirmTokenButton;
    ConstraintLayout footer;
    //ConstraintLayout mainConstrLayout;
    ConstraintLayout.LayoutParams layoutParams;
    private boolean clicked = false;
    String token = "";
    EditText textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        dragFooterButton = (Button) findViewById(R.id.openFooterButton);
        confirmTokenButton = (Button) findViewById(R.id.confirmTokenButton);
        footer = (ConstraintLayout) findViewById(R.id.constraintLayout2);
        layoutParams = (ConstraintLayout.LayoutParams) footer.getLayoutParams();
        textBox = (EditText) findViewById(R.id.editTextConfirmation);
        Animation a = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                //if(!clicked) {
                    layoutParams.topMargin = 500;
                    footer.setLayoutParams(layoutParams);
                    clicked = true;
                    return;
                //} else {
                    //layoutParams.topMargin = 1800;
                    //footer.setLayoutParams(layoutParams);
                    //clicked = false;
                    //return;
                //}
            }
        };

        dragFooterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setDuration(500);
                footer.startAnimation(a);
            }
        });

        confirmTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeRequest();
            }
        });
    }



    public void executeRequest() {

        token = textBox.getText().toString();

        String url = "https://anything-app.herokuapp.com/auth/confirm?token=" + token;
        RequestQueue requestQueue = Volley.newRequestQueue(ConfirmationActivity.this);
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                //loadingProgressBar.setVisibility(View.GONE);

                try {
                    String resp = response.getString("result");
                    String content = "";
                    String message = "";
                    try {
                        Toast.makeText(getApplicationContext(), "" + resp.equals("successful"),
                                Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured, please try again",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(resp.trim().equals("successful")) {
                        Toast.makeText(getApplicationContext(), content,
                                Toast.LENGTH_SHORT).show();
                                finish();
                    } else {
                        Toast.makeText(getApplicationContext(), resp + ", please try again",
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