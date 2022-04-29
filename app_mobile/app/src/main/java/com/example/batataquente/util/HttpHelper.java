package com.example.batataquente.util;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {
    private static String   BASE_URL = "http://192.168.0.109/batata_quente_api/";

    public static String doGet(String url) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        URL u = new URL(BASE_URL + url);
        HttpURLConnection conn = null;
        String s = null;
        try {
            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            int status = conn.getResponseCode();

            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return responseContent.toString();
    }
}
