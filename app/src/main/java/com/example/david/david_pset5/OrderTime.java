
package com.example.david.david_pset5;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class OrderTime extends DialogFragment {



@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                     Bundle savedInstanceState) {

View v = inflater.inflate(R.layout.fragment_order_time, container, false);

// do a post volley almost identical to the other volleys used in previous fragments
// and set the textview to the return time
final TextView timeV = v.findViewById(R.id.time_text);
RequestQueue queue = Volley.newRequestQueue(getActivity());
String url = "https://resto.mprog.nl/order";

JSONObject to_send = new JSONObject();

JsonObjectRequest jsObjRequest = new JsonObjectRequest
        (Request.Method.POST, url, to_send, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    timeV.setText("Your estimated waiting time is " + response.getString("preparation_time") + " minutes.");

                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("NOT ABLE TO GET WAITING TIME");
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                System.out.println("EROOR IN THE ON RESPONS OF REQUESTING THE TIME");

            }
        });

// Access the RequestQueue
queue.add(jsObjRequest);
// Inflate the layout for this fragment
return v;
}

}
