package com.example.lifeassistant.Dictionary;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// parameter => URL, response => list of Strings containing responses
public class RequestHandler extends AsyncTask<URL, Void, List<String>> {


    protected void onPreExecute() {
    }

    protected List<String> doInBackground(URL... urls) {
        List<String> definitions = new ArrayList<>();
        try {
            for (URL passedUrl : urls) {
                HttpURLConnection urlConnection = (HttpURLConnection) passedUrl.openConnection();
                urlConnection.setRequestProperty("app_id", "d63a4429");
                urlConnection.setRequestProperty("app_key", "0f0e1943eaa446c1f63c86c67cb0196c");
                urlConnection.setRequestMethod("GET");

                if (urlConnection.getResponseCode() < 300) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.contains("definitions")) {
                            inputLine = in.readLine();
                            String trimmed = inputLine.trim().replaceAll("\"", "");
                            definitions.add(trimmed);
                        }
                    }
                    in.close();
                    urlConnection.disconnect();
                    return definitions;
                }
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return definitions;
    }

    protected void onPostExecute(List<String> definitions) {


    }
}
