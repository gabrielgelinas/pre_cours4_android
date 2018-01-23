package com.example.gggab.pre_cours4_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private ArrayList<Product> listProduits;
    private ProductAdapter adapter;
    private EditText txtName;
    private EditText txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list_items);
        initArrayList();
        initArrayAdapter();

        txtName = (EditText) findViewById(R.id.input_Name);
        txtPrice = (EditText) findViewById(R.id.input_Price);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0) {
                    Toast.makeText(getApplicationContext(), "Vous avez clické sur: " + ((TextView) view.findViewById(R.id.name)).getText().toString(), Toast.LENGTH_SHORT).show();
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
                    listProduits.remove(position);
                    adapter.notifyDataSetChanged();
                    return false;
                }
            }
        });
    }

    private void initArrayList() {
        listProduits = new ArrayList<Product>();

        listProduits.add(new Product());
        listProduits.add(new Product("KitKat",5.10));
        listProduits.add(new Product("Aero",5.10));
        listProduits.add(new Product("Crunchy",5.10));
        listProduits.add(new Product("Mr.Big",5.10));
        listProduits.add(new Product("Reeses",5.10));
        listProduits.add(new Product("M&M's",5.10));
    }

    private void initArrayAdapter() {
        adapter = new ProductAdapter(this, R.layout.adapter_listitems, listProduits);
    }

    public void AddProd(View view) {
        String name = txtName.getText().toString();
        String price = txtPrice.getText().toString();
        Product temp = null;
        try {
            temp = new Product(name,Double.parseDouble(price));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            temp = new Product(name,0.0);
        }

        listProduits.add(temp);
        adapter.notifyDataSetChanged();
    }
}
