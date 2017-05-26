package com.example.raghavendrkolisetty.schedulemywork;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class HomeActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
    }

    @Override
    protected void onStart(){
        super.onStart();
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://52.32.123.205:5000/test", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("in JSON response"+response.toString());
                SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
                System.out.println("checking prefs"+prefs.getString("token",null));
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if(networkResponse!=null){
                    if(networkResponse.statusCode==400){
                        System.out.println("user already exists");
                    }
                }
                System.out.println("in error listener response"+error);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
                System.out.println("accessing token from homeactivity"+prefs.getString("access_token",null));
                String access_token = prefs.getString("access_token",null);
                headers.put("Authorization", "JWT "+access_token);
                return headers;
            }
        };



        RequestQueue queue = Volley.newRequestQueue(HomeActivity2.this);
        queue.add(jsonObjectRequest);
    }


}
