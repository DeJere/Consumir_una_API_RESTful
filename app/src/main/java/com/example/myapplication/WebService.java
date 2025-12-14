package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WebService extends AsyncTask<String, Void, String> {

    public interface Asynchtask {
        void processFinish(String result);
    }

    private Context context;
    private String url;
    private Asynchtask delegate;

    public WebService(String url, Map<String, String> data,
                      Context context, Asynchtask delegate) {
        this.url = url;
        this.context = context;
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(params[0]);
            conn.connect();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
