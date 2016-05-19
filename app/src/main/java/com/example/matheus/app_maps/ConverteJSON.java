package com.example.matheus.app_maps;

import android.util.Log;
import android.util.Pair;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Matheus on 19/05/2016.
 */
public class ConverteJSON {

    JSONObject jObj = null;
    InputStream is = null;
    String json;

    public ConverteJSON() {

    }

    public JSONObject mandaRequisicaoHTTP(String url, String method, List<NameValuePair> params) {


        try {
            if (method == "POST") {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                post.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            } else if (method == "GET") {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramsString = URLEncodedUtils.format(params, "UTF-8");
                url += "?" + paramsString;
                HttpGet get = new HttpGet(url);
                HttpResponse response = httpClient.execute(get);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);

            StringBuilder stringBuilder = new StringBuilder();
            String linha = null;
            while ((linha = reader.readLine()) != null) {
                stringBuilder.append(linha + "\n");
            }
            is.close();
            json = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("ConverteJSON", "Erro ao converter o dado: " + e.toString());
        }

        return jObj;
    }
}
