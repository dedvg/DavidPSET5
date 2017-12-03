package com.example.david.david_pset5;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by dedvg on 2-12-2017.
 * with use of https://www.youtube.com/watch?v=HIm-T0NCEqY
 */
// this java file is the adapter used for to order stuff
public class dish_adapter extends BaseAdapter
{
    // initialize the list and context
    private Context context;
    private List<dish_class> dishes;

    public dish_adapter(Context context, List<dish_class> dishes){
        this.context = context;
        this.dishes = dishes;

}

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int position) {
            return dishes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // dish_row is used to set the textviews and imageview with the right information
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view.inflate(context, R.layout.dish_row, null);

        TextView titleT = v.findViewById(R.id.TitleV);
        TextView priceT = v.findViewById(R.id.PriceV);
        TextView descT = v.findViewById(R.id.DescriptioV);
        final ImageView imageV = v.findViewById(R.id.ImageV);

        titleT.setText(dishes.get(position).getName());
        priceT.setText("€ " + dishes.get(position).getPrice() );
        descT.setText(dishes.get(position).getDescription());

        // new volley request that uses the given link to get the image in the app
        RequestQueue queue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(dishes.get(position).getImage(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                // here the imageview will take the right image
                imageV.setImageBitmap(bitmap);
            }

        },
                0,
                0,
                null,
                Bitmap.Config.ALPHA_8,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("NOT ABLE TO LOAD IMAGE");
                    }
                }
        );

        queue.add(imageRequest);

        return v;
    }
}
