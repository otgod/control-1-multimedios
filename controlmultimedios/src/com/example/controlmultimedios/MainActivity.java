package com.example.controlmultimedios;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	ListView listView1;
	  TextView title;
	  ImageView image;
	  TextView points;
	  List<String> titulos = new ArrayList<String>();
	  List<String> fotos = new ArrayList<String>();
	  List<String> puntos = new ArrayList<String>();
	private ConnectivityManager connManager;
	private static final String LOGTAG = "LogsAndroid";
	private NetworkInfo netInfo;
	public JSONObject retorno;
	JSONArray titulo ;
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        retorno= getJsonObj(); 
        
      
        	try {
				titulo = retorno.getJSONArray("");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        
        
        for (int i=0; i<titulo.length(); i++) {
            JSONObject posteo;
			try {
				posteo = titulo.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String titl;
			try {
				titl = posteo.getString("title");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String foto;
			try {
				foto = posteo.getString("image");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String ptos;
			try {
				ptos = posteo.getString("points");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            titulos.add(titl);fotos.add(foto);puntos.add(ptos);
        }
        
    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static JSONObject getJsonObj() {
    	HttpGet httpGet = new HttpGet("http://www.mocky.io/v2/5440667984d353f103f697c0");
    	Log.d(LOGTAG, "1");

    	HttpClient client = new DefaultHttpClient();
    	Log.d(LOGTAG, "1");

    	HttpResponse response;
    	Log.d(LOGTAG, "1");
    	StringBuilder stringBuilder = new StringBuilder();
    	Log.d(LOGTAG, "1");
    	try {
    		response = client.execute(httpGet);
    		Log.d(LOGTAG, "1");
    		HttpEntity entity = response.getEntity();
    		Log.d(LOGTAG, "1");
    		InputStream stream = entity.getContent();
    		Log.d(LOGTAG, "1");
    		int b;
    		while ((b = stream.read()) != -1) {
    			stringBuilder.append((char) b);
    		}
    	} catch (ClientProtocolException e) {
    	} catch (IOException e) {
    	}
    	JSONObject jsonObject = new JSONObject();
    	try {
    		jsonObject = new JSONObject(stringBuilder.toString());
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    	return jsonObject;
    }

	
    

}

