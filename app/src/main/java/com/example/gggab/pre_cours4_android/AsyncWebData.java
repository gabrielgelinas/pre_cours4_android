package com.example.gggab.pre_cours4_android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

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

/**
 * Created by gggab(Zombietux) on 2018-01-30.
 */

class AsyncWebData extends AsyncTask<ListProducts, String, ListProducts[]> {
    private Context mContext;
    private ProgressDialog progress;
    private ListProducts p_List;
    private ListView mListView;
    private ProductAdapter adapter;

    AsyncWebData(Context context, ListProducts list, ListView listView) {
        mContext = context;
        p_List = list;
        mListView = listView;
    }

    @Override
    protected ListProducts[] doInBackground(ListProducts... params) {
        // this might take a while ...
        publishProgress("looping...");
        String resp;
        ListProducts temp;
        try {
            temp = ConnectToWebsite(p_List);

//            int time = 5000;
//            Thread.sleep(time);
//            resp = "Slept for " + params[0] + " seconds";
        } catch (Exception e) {
            e.printStackTrace();
            resp = e.getMessage();
        }
        return params;
    }

    private ListProducts ConnectToWebsite(ListProducts listProducts) {
        int page = 1;
        int maxpage = 0;

        for (int i = 0; i <= maxpage; i++) {
            URLBuilder url = new URLBuilder(page, "https://www.iga.net/en/search?page=", "&pageSize=60");

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

                    // Get max pages
                    if (maxpage==0) {
                        Pattern mPattern = Pattern.compile("<p>\\n\\s*(.+?)\\s+?match");
                        Matcher mMatcher = mPattern.matcher(stringBuffer);

                        while (mMatcher.find()) {
                            maxpage = (Integer.parseInt(mMatcher.group(1)) / 60);
                            System.out.println(maxpage);
                        }
                    }
                    /////////////////

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
        return listProducts;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(mContext,
                "Fetching Data...",
                "Wait for " + "X..." + " seconds");
    }

    @Override
    protected void onPostExecute(ListProducts[] result) {
        // execution of result of Long time consuming operation
        progress.dismiss();
        initArrayAdapter();
        mListView.setAdapter(adapter);

        System.out.println("Asyncdone");
    }

    private void initArrayAdapter() {
        adapter = new ProductAdapter(mContext, R.layout.adapter_listitems, p_List);
    }
}
