package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BufferedHeader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class network {


    private String path;

    public network(String path) {
        this.path = path;
    }

    public network() {

    }

    public InputStream getstream() {
        InputStream inputStream = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(path);
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("get链接成功>>>>>>>>");
                inputStream = response.getEntity().getContent();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public String getchengjj(InputStream in) {
        String s = "";
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gbk"));
            while ((line = reader.readLine()) != null) {
                s += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


    public InputStream getconnectionstream() {
        URL url;
        try {
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            if (conn.getResponseCode() == 200) {
                System.out.println("链接成功");
                InputStream stream = conn.getInputStream();
                return stream;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
