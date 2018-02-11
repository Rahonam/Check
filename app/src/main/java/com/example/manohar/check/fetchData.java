package com.example.manohar.check;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.crypto.NullCipher;
import javax.net.ssl.HttpsURLConnection;

/**
 * JASON data fetching
 */

public class fetchData extends AsyncTask<String, String, String> {
    String data="";
    String dataparsed="";
    String singleparsed="";

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection httpURLconnection=(HttpURLConnection)url.openConnection();
            InputStream inputstream=httpURLconnection.getInputStream();
            BufferedReader bufferedreader= new BufferedReader(new InputStreamReader(inputstream));
            String line="";
            while(line!=null){
                line=bufferedreader.readLine();
                data=data+line;
            }
            JSONArray JA=new JSONArray(data);
            for(int i=0;i<JA.length();i++){
                JSONObject JO=(JSONObject) JA.get(i);
                singleparsed="\nId : "+JO.get("id")+"\n"+"Date : "+JO.get("date")+"\n"+"Link : "+JO.get("link")+"\n"+"Title : "+JO.get("title")+"\n";
                dataparsed=dataparsed+singleparsed;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result!= null) {
            BranchActivity.book_title.setText(this.dataparsed);
        }
    }
}

