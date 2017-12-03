package com.example.david.david_pset5;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends DialogFragment implements View.OnClickListener {
    ListView listView;
    RestoAdapter theAdapter;
    RestoDatabase theDatabase;
    Cursor data;
    TextView total;
    Button Cancel_btn, Order_btn;
    ArrayList<String> listData = new ArrayList<String>();
    private OnFragmentInteractionListener mListener;

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        update_list();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // get the view and use it
        View v =inflater.inflate(R.layout.fragment_order, container, false);

        listView = v.findViewById(R.id.order_list);
        Cancel_btn = v.findViewById(R.id.cancel_btn);
        Order_btn = v.findViewById(R.id.order_btn);
        theDatabase = RestoDatabase.getInstance(getActivity().getApplicationContext());
        total = v.findViewById(R.id.totalText);

        // set onClickListeners
        Cancel_btn.setOnClickListener(this);
        Order_btn.setOnClickListener(this);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // delete an item and update the list
                theDatabase.deleteItem((int)id);
                update_list();
                return true;
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
    // check which button is clicked and act accordingly
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_btn:
                getDialog().dismiss();
                break;
            case R.id.order_btn:
                // clear the database if the order is ordered
                theDatabase.clear();
                mListener.onFragmentInteraction();

                getDialog().dismiss();
                break;
        }
    }


    // update the list
    public void update_list(){
        theDatabase = RestoDatabase.getInstance(getActivity().getApplicationContext());

        data = theDatabase.getData();

        while (data.moveToNext()){
            listData.add(data.getString(1));
        }
        theAdapter = new RestoAdapter(getActivity(), data);
        total.setText("TOTAL PRICE: " + theDatabase.totalPrice());

        listView.setAdapter(theAdapter);
    }
    // on attack, detach are OnFragmentInteractionListener are used to use a function in the
    // mainactivity when the order button is clicked
    //
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else {
            System.out.println("Error");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }
}


