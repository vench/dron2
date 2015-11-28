/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dron;

import android.util.Log;
import java.io.IOException;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author vench
 */
public class SenderState implements Runnable {

    private final IDronState dronState;

    private final boolean running = true;

    private final int timeDelay = 3000;

    private SenderState(IDronState dronState) {
        this.dronState = dronState;
    }

    public static void init(IDronState dronState) {
        new Thread(new SenderState(dronState)).start();
    }

    public void run() {
        while (running) {
            try {
                TimeUnit.MILLISECONDS.sleep(timeDelay);
            } catch (InterruptedException ex) {
            }
            sendState();
        }
    }

    public void sendState() {
          Map<String, String> m = new HashMap(); 
        m.put("base", "xxx");
            dronState.setParams(m);
        
        /*
        String ip = dronState.getIP().trim();
        if (ip.length() == 0) {
            return;
        }

        HttpClient client = new DefaultHttpClient();

        List<NameValuePair> parameters = new ArrayList();
        Map<String, String> params = dronState.getParams();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        
        String action = "api/position/add";
        String paramString = URLEncodedUtils.format(parameters, "utf-8");
        HttpGet request = new HttpGet(ip + action +  "?" + paramString);

        HttpResponse response;
        Map<String, String> m = new HashMap(); 
        try {
            response = client.execute(request); 
            HttpEntity entity = response.getEntity();
            String resp = EntityUtils.toString(entity);             
           JSONObject json = getJson(resp);
            
            
            
            List<String> moves = new ArrayList<String>();
            moves.add("mover1");
            moves.add("mover2");
            moves.add("mover3");
            moves.add("mover4");
            for(String keyName : moves) {
                if(json.has(keyName)) {
                   try {
                     m.put(keyName, json.getString(keyName));
                   } catch (JSONException ex) { } 
                }    
            } 
            
            m.put("base", resp);
            dronState.setParams(m);
           
        } catch (ClientProtocolException e) {
           // e.printStackTrace();
            m.put("base", e.getMessage());
        } catch (IOException e) {
           // e.printStackTrace();
             m.put("base", e.getMessage());
        } 
       // Log.d("Response of GET request", ip); 
         
         dronState.setParams(m);*/
    }

    private JSONObject getJson(String jsonStr) {
        try {
            JSONObject json = new JSONObject(jsonStr);
            return json;
        } catch (JSONException ex) {
           
        }
        return new JSONObject();
    }
}
