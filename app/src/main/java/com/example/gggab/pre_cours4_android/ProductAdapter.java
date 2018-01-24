package com.example.gggab.pre_cours4_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    private int mResource;

    ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        if (position!=0) {
            String name = getItem(position).getName();
            Double price = getItem(position).getPrice();
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView tvName = convertView.findViewById(R.id.name);
            TextView tvPrice = convertView.findViewById(R.id.price);

            try {
                tvName.setText(name);
                tvPrice.setText(numberFormat.format(price));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.adapter_headerlistitems, parent, false);
        }

        return convertView;
    }
}
