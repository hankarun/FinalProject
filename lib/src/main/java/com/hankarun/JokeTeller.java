package com.hankarun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.parser.JSONParser;

public class JokeTeller {
    private String mJoke = "";

    public JokeTeller(){
        mJoke = "derp";
    }

    public String getmJoke(){
        try {
            //http://api.icndb.com/jokes/random?exclude=[nerdy,explicit]
            String url = "http://api.icndb.com/jokes/random?exclude=[nerdy,explicit]";

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
