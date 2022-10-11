package com.example.topsegurosbrasilmobile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Conexao {

    private static String lerFluxo(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try{
            while((line = r.readLine()) != null){
                total.append(line).append('\n');
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return total.toString();
    }


    private static String request(String stringUrl){
        URL url = null;
        HttpsURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return lerFluxo(in);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return "";
    }

    public static String getParadas(String url){
        return request(url);
    }
}
