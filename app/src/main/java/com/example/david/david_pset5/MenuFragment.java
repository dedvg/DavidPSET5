package com.example.david.david_pset5;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends ListFragment {

    // keep track of the titles and the prices with this list
    List<String> title_text = new ArrayList<String>();
    List<String> price_text = new ArrayList<String>();

    // have a list with dish_classes
    List<dish_class> list_text = new ArrayList<dish_class>();
    dish_adapter theAdapter;
    RestoDatabase theDatabase;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // do a volley request like CategoriesFragement
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://resto.mprog.nl/menu";


        theDatabase = RestoDatabase.getInstance(getActivity().getApplicationContext());

        Bundle arguments = this.getArguments();
        final String given_text = arguments.getString("category");
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (JsonObjectRequest.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("items");
                            list_text.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                if (jsonArray.getJSONObject(i).getString("category").equals(given_text)) {
                                    System.out.println("IN DE MENUFRAGMENT TRY");

                                    int id = jsonArray.getJSONObject(i).getInt("id");
                                    String title = (jsonArray.getJSONObject(i).getString("name"));
                                    float price = Float.valueOf(jsonArray.getJSONObject(i).getString("price"));
                                    String description = jsonArray.getJSONObject(i).getString("description");
                                    String image = (jsonArray.getJSONObject(i).getString("image_url"));
                                    fillList(id, title, description, price, image);
                                    
                                    title_text.add(title);
                                    price_text.add(jsonArray.getJSONObject(i).getString("price"));
                                }

                            }


                        } catch (JSONException e) {
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("a");
                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);

    }

    private void fillList(int id, String title, String description, float price, String image) {

        list_text.add(new dish_class(id, title, price, description, image));
        theAdapter = new dish_adapter(getActivity().getApplicationContext(),list_text);

    this.setListAdapter(theAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String name = title_text.get(position);
        Float price = Float.valueOf(price_text.get(position));
        Toast.makeText(getActivity(), "you added: " + name, Toast.LENGTH_SHORT).show();
        theDatabase.addItem(name, price);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
    }




