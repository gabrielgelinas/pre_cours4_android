package com.example.gggab.pre_cours4_android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Zombietux on 2018-01-30.
 */

class ProductWebData {
    private URLBuilder urlString;

    public ProductWebData(URLBuilder url) {
        urlString = url;
    }

    public StringBuffer getStringBuffer() throws IOException {
        URL obj = new URL(urlString.getURL());

        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");
        int response = connection.getResponseCode();

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        String inputLine;
        StringBuffer stringBuffer = new StringBuffer();

        while ((inputLine = bufferedReader.readLine()) != null)

        {
            stringBuffer.append(inputLine).append("\n");
        }
        bufferedReader.close();


        return stringBuffer;
    }
}
