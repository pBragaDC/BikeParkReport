package com.example.pdesktop.bikeparkreport;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import io.fabric.sdk.android.services.concurrency.AsyncTask;

/**
 * Created by Andres Acuna on 4/21/2016.
 */
public class RetrieveLocationTask extends AsyncTask<String, String, String> {

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public RetrieveLocationTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        String zip = params[0];

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://maps.googleapis.com/maps/api/geocode/json?address="+zip+"&sensor=true");

        String jsonResponse = null;

        try {
            HttpResponse response = httpClient.execute(httpPost);
            jsonResponse = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }

    @Override
    protected void onPostExecute(String result){
        delegate.processFinish(result);
    }
}
