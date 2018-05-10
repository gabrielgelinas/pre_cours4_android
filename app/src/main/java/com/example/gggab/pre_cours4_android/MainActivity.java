package com.example.gggab.pre_cours4_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public ListProducts listProducts;
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


        txtName = findViewById(R.id.input_Name);
        txtPrice = findViewById(R.id.input_Price);

        list.setAdapter(adapter);

        FetchWebData(list);

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position != 0) {
//                    Toast.makeText(getApplicationContext(), "You clicked on: " + ((TextView) view.findViewById(R.id.name)).getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position == 0) {
//                    return false;
//                } else {
//                    //                        URL productURL = new URL("https://www.iga.net" + listProducts.get(position).getProductUrl());
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iga.net" + listProducts.get(position).getProductUrl()));
//
//                    startActivity(browserIntent);
//
//                    return false;
//                }
//            }
//        });
    }

    private void FetchWebData(ListView list) {
        ////////////////////
        // Test AsyncTask //
        ////////////////////
        try {
//            HttpGet httpGet = new HttpGet(new URI(TEST_URL));
            AsyncWebData task = new AsyncWebData(this, listProducts, list);
            task.execute(listProducts);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        AsyncWebData getData = new AsynWebData();

        ////////////////////
    }

    private void initArrayList() {
        listProducts = new ListProducts();

//        listProducts.add(new Product());
//        listProducts.add(new Product("        KitKat", 5.10));
//        listProducts.add(new Product("Aero", 5.10));
//        listProducts.add(new Product("Crunchy", 5.10));
//        listProducts.add(new Product("Mr.Big", 5.10));
//        listProducts.add(new Product("Reeses", 5.10));
//        listProducts.add(new Product("M&M's", 5.10));
    }

    public void AddProd(View view) {
        String name = txtName.getText().toString();
        String price = txtPrice.getText().toString();
        Product temp;
        try {
            temp = new Product(name, Double.parseDouble(price));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            temp = new Product(name, 0.0);
        }

//        listProducts.add(temp);
//
//
////        Runnable runnable = new Runnable() {
////            @Override
////            public void run() {
////                ConnectToWeb();
////            }
////        };
////        new Thread(runnable).start();
//
//        adapter.notifyDataSetChanged();

    }

    private void ConnectToWeb() {
        int page = 1;
        int maxpage;

        URLBuilder url = new URLBuilder("https://www.iga.net/en/search?page=", page, "&pageSize=60");

        try {
            URL obj = new URL(url.getURL());

            try {
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

                connection.setRequestMethod("GET");
                int response = connection.getResponseCode();

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                String inputLine;
                StringBuffer stringBuffer = new StringBuffer();

                while ((inputLine = bufferedReader.readLine()) != null) {
                    stringBuffer.append(inputLine).append("\n");
                }
                bufferedReader.close();

                Pattern pattern = Pattern.compile("<div class=\"js-product js-equalized js-addtolist-container js-ga\" data-product=\"(.+?)" + "\"");
                Matcher matcher = pattern.matcher(stringBuffer);
                int cnt = 0;

                while (matcher.find()) {
                    Gson gson = new Gson();
                    Product product = gson.fromJson(new JsonParser().parse(matcher.group(1)).getAsJsonObject(), Product.class);

                    listProducts.add(product);
                    cnt++;
                }

                System.out.println("job done with " + cnt + " products...........................................");
                //                System.out.println(stringBuffer.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void ProdsToConsole() {
        for (Product p :
                listProducts) {
            System.out.println(
                    "ID: " + p.getProductId() + " \t\t Name:" +
                            p.getName()
            );
        }
    }
}
