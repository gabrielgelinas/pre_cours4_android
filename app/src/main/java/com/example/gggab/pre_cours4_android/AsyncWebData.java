package com.example.gggab.pre_cours4_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class AsyncWebData extends AsyncTask<ListProducts, Integer, ListProducts[]> {
    private Context mContext;
    private ProgressDialog progress;
    private ListProducts p_List;
    private ListView mListView;
    private ProductAdapter adapter;
    private int pagesLoading = 1;
    private int maxpage = 1;

    AsyncWebData(Context context, ListProducts list, ListView listView) {
        this.mContext = context;
        this.p_List = list;
        this.mListView = listView;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(mContext,
                "Fetching Data...",
                "Fetching data from page " + pagesLoading + " out of " + maxpage +".");
    }

    @Override
    protected ListProducts[] doInBackground(ListProducts... params) {
        // this might take a while ...
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

    @Override
    protected void onPostExecute(ListProducts[] result) {
        // execution of result of Long time consuming operation
        progress.dismiss();
        initArrayAdapter();
        mListView.setAdapter(adapter);

        System.out.println("Asyncdone");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progress.setProgress(values[0]);
    }

    private ListProducts ConnectToWebsite(ListProducts listProducts) throws IOException {
        Integer cnt;
        Pattern pattern = Pattern.compile("<div class=\"js-product js-equalized js-addtolist-container js-ga\" data-product=\"(.+?)" + "\"");

        for (pagesLoading = 1; pagesLoading <= maxpage; pagesLoading++) {
            publishProgress(pagesLoading);
            URLBuilder url = new URLBuilder(maxpage, "https://www.iga.net/en/search?page=", "&pageSize=60");
            if (maxpage == 1) {
                maxpage = GetMaxPages(url);

                System.out.println(maxpage);
                maxpage = 10;
            }
            ProductWebData productWebData = new ProductWebData(url);
//        try {
//            URL obj = new URL(url.getURL());
//
//            try {
//                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
//
//                connection.setRequestMethod("GET");
//                int response = connection.getResponseCode();
//
//                BufferedReader bufferedReader = new BufferedReader(
//                        new InputStreamReader(connection.getInputStream())
//                );
//
//                String inputLine;
//                StringBuffer stringBuffer = new StringBuffer();
//
//                while ((inputLine = bufferedReader.readLine()) != null) {
//                    stringBuffer.append(inputLine).append("\n");
//                }
//                bufferedReader.close();
            Matcher matcher = pattern.matcher(productWebData.getStringBuffer());

            cnt = 0;
            while (matcher.find()){
                listProducts.add(GetProductFromRgx((matcher.group(1))));
                cnt++;
            }
            System.out.println("Page " + maxpage + " done with " + cnt + " products...........................................");
        }
        //                System.out.println(stringBuffer.toString());

//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        int totalprod = listProducts.size();

        System.out.println("listProducts.size() = " + totalprod);
        return listProducts;
    }

    private Product GetProductFromRgx(String rgxJson) {
        Gson gson = new Gson();
        return gson.fromJson(new JsonParser().parse(rgxJson).getAsJsonObject(), Product.class);
    }

    private int GetMaxPages(URLBuilder url) throws IOException {
        Integer maxpage = 1;

        ProductWebData productWebData = new ProductWebData(url);
        Pattern mPattern = Pattern.compile("<p>\\n\\s*(.+?)\\s+?match");
        Matcher mMatcher = mPattern.matcher(productWebData.getStringBuffer());

        while (mMatcher.find()) {
            maxpage = (Integer.parseInt(mMatcher.group(1)) / 60);
            System.out.println(maxpage);
        }

        return maxpage;
    }

    private void initArrayAdapter() {
        adapter = new ProductAdapter(mContext, R.layout.adapter_listitems, p_List);
    }

}
