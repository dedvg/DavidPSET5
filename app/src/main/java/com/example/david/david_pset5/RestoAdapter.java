package com.example.david.david_pset5;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class RestoAdapter extends ResourceCursorAdapter {
    public RestoAdapter(Context context, Cursor cursor) {

        super(context, R.layout.row_order, cursor, 0);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // get the textviews
        TextView titleT = view.findViewById(R.id.titleText);
        TextView amountText = view.findViewById(R.id.amountText);
        TextView priceText = view.findViewById(R.id.priceText);


        // get the values
        Integer name_id = cursor.getColumnIndex("name");
        Integer amount_id = cursor.getColumnIndex("amount");
        Integer price_id = cursor.getColumnIndex("price");


        // set the right values
        String amount = cursor.getInt(amount_id) + " x";
        String name = cursor.getString(name_id);
        String price = cursor.getString(price_id);
        float total_price = cursor.getInt(amount_id) * cursor.getFloat(price_id);
        System.out.println(total_price);
        titleT.setText(name);
        amountText.setText(amount);
        priceText.setText("€" + price +" ");


    }
}