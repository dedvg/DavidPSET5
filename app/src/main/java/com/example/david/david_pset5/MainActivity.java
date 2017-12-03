package com.example.david.david_pset5;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OrderFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // set up the fragment manager
        FragmentManager fm = getSupportFragmentManager();
        CategoriesFragment fragment = new CategoriesFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment, "categories");
        ft.commit();
    }
    // use actions that will open a popup
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ORDER:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                OrderFragment fragment = new OrderFragment();
                fragment.show(ft, "dialog");

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onFragmentInteraction() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        OrderTime fragment = new OrderTime();
        fragment.show(ft, "dialog");
    }

}
