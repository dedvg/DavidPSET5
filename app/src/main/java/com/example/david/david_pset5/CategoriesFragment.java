package com.example.david.david_pset5;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


// this function will fill the list and in the next fragment the same volley request will be used.
/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends ListFragment {
    //    initialize the adapter and list
    List<String> list_text = new ArrayList<String>();
    ArrayAdapter theAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // start a new volley with the following url
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://resto.mprog.nl/categories";
        // set an adapter
        theAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list_text);
        this.setListAdapter(theAdapter);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (JsonObjectRequest.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray group = response.getJSONArray("categories");
                            list_text.clear();
                            for (int i = 0; i < group.length(); i++) {
                                list_text.add(group.getString(i));

                            }
                            theAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("ERROR IN CATOGORIESFRAGMENT");
                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);



    }
    // takes the clicked list item and gives its text to the new fragment
    // it allows the user to navigate with the back button
    // this is code to replace a fragment
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MenuFragment menuFragment = new MenuFragment();

        Bundle args = new Bundle();
        args.putString("category", l.getItemAtPosition(position).toString());
        menuFragment.setArguments(args);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, menuFragment)
                .addToBackStack(null)
                .commit();

    }


}
