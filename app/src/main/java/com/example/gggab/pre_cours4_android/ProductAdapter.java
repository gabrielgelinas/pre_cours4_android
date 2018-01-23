package com.example.gggab.pre_cours4_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gggab on 2018-01-23.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    int mResource;

    public ProductAdapter(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (position!=0) {
            String name = getItem(position).getName();
            Double price = getItem(position).getPrice();
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

            Product product = new Product(name, price);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView tvName = (TextView) convertView.findViewById(R.id.name);
            TextView tvPrice = (TextView) convertView.findViewById(R.id.price);

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
