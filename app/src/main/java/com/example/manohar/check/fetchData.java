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

import javax.net.ssl.HttpsURLConnection;

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data="";
    String dataparsed="";
    String singleparsed="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url=new URL("http://xoombook.com/wp-json/wp/v2/posts/?filter[category_name]=android&per_page=100&fields=id,date,link,title");
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
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.text.setText(this.dataparsed);

    }
}

