package com.example.gggab.pre_cours4_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> listProducts;
    private ProductAdapter adapter;
    private EditText txtName;
    private EditText txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list;
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list_items);
        initArrayList();
        initArrayAdapter();

        txtName = findViewById(R.id.input_Name);
        txtPrice = findViewById(R.id.input_Price);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    Toast.makeText(getApplicationContext(), "You clicked on: " + ((TextView) view.findViewById(R.id.name)).getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    return false;
                }
                else {
                    listProducts.remove(position);
                    adapter.notifyDataSetChanged();
                    return false;
                }
            }
        });
    }

    private void initArrayList() {
        listProducts = new ArrayList<>();

        listProducts.add(new Product());
        listProducts.add(new Product("KitKat", 5.10));
        listProducts.add(new Product("Aero", 5.10));
        listProducts.add(new Product("Crunchy", 5.10));
        listProducts.add(new Product("Mr.Big", 5.10));
        listProducts.add(new Product("Reeses", 5.10));
        listProducts.add(new Product("M&M's", 5.10));
    }

    private void initArrayAdapter() {
        adapter = new ProductAdapter(this, R.layout.adapter_listitems, listProducts);
    }

    public void AddProd(View view) {
        String name = txtName.getText().toString();
        String price = txtPrice.getText().toString();
        Product temp;
        try {
            temp = new Product(name,Double.parseDouble(price));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            temp = new Product(name,0.0);
        }

        listProducts.add(temp);
        adapter.notifyDataSetChanged();
    }
}
