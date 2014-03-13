package library;
 
import java.io.BufferedReader;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
 
public class JSONParser extends AsyncTask<List<NameValuePair>, Void, JSONObject>{
 
    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static String json = "";
    String URL=null;
 
    // constructor
    public JSONParser(String url) {
    	URL = url;
    }

	@Override
	protected JSONObject doInBackground(List<NameValuePair>... params) {
		// Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(URL);
           
            
            
            httpPost.setEntity(new UrlEncodedFormEntity(params[0]));
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            System.out.println(URL);
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        catch (ClientProtocolException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            
            
            System.out.println("segment :" + sb+"--");
            is.close();
            
           
            
            
           json = sb.toString();
           
            System.out.println("segment12 :"  + json  + " ---json created");
            jObj = new JSONObject(json);  
            System.out.println("result1111-- : "+ jObj + "--json obj");
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result123 " + e.toString());
        }
 
       
        return jObj;
	}
}