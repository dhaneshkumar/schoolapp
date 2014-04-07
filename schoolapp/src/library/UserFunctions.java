package library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import library.JSONParser;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
 
public class UserFunctions {
     
   // private final static String URL = "http://10.0.2.2/schoolapp/index.php";
    private final static String URL = "http://www.trumplab.com/schoolapp/index.php";
    private static String login_tag = "login";
    private static String details_tag = "details";
    private static String children_tag = "children";
    private static JSONObject json = null;
    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static String json1 = "";
    
     
    // constructor
    public UserFunctions(){
    	
    }
     
    public JSONObject loginUser(String username, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(URL).execute(params);
        
		try {
			json = json_parse.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return json;
    }
    
   
    public JSONObject getChildren(int id){
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	
        params.add(new BasicNameValuePair("tag", children_tag));
        params.add(new BasicNameValuePair("id", id+""));
        
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(URL).execute(params);
        
		try {
			json = json_parse.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return json;
    }
    
    public JSONObject getDetails(int id, String table){    
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	
        params.add(new BasicNameValuePair("tag", details_tag));
        params.add(new BasicNameValuePair("id", id+""));
        params.add(new BasicNameValuePair("table", table));
        
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(URL).execute(params);
        
		try {
			json = json_parse.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return json;
    }
     
    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount("User");
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }
     
    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }
    
   
    
    
    public JSONObject getTtFromServer(String table, String class_name, String section_name){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
        params.add(new BasicNameValuePair("tag", "timeTableDetails"));
        params.add(new BasicNameValuePair("table", table));
        params.add(new BasicNameValuePair("class", class_name));
        params.add(new BasicNameValuePair("section", section_name));
        
        
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(URL).execute(params);
        
		try {
			json = json_parse.get();
			
		//	System.out.println("json---" + json);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return json;
    }
    
    
    //**********************************< phone list>**********************************
    
    public JSONObject getPhoneListFromServer(String table){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
        params.add(new BasicNameValuePair("tag", "phoneListDetails"));
        //params.add(new BasicNameValuePair("tag", "phoneListDetails"));
        params.add(new BasicNameValuePair("table", table));
      
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(URL).execute(params);
        
		try {
			json = json_parse.get();
			
			System.out.println("json---" + json);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return json;
    }
    
    
    
    //**************************************< table details>******************************
    
    public JSONObject getTables(String table)
    {
    	// Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
        params.add(new BasicNameValuePair("tag", "details"));
        params.add(new BasicNameValuePair("table", table));
        
        System.out.println(table +"-------- table name ");
        
        System.out.println(DatabaseHandler.pid +"-------- pid ");
        params.add(new BasicNameValuePair("pid", DatabaseHandler.pid));
       
        System.out.println(table +"--------" + URL);
        
        //JSONObject json_parse = datafetch(params);
        
        json =datafetch(params);
        //AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(loginURL).execute(params);
        
        System.out.println(table +"-------- getting data from server ");
		
       /* try {
        	System.out.println("json---111000" );
			json = json_parse.get();
			System.out.println("json---1110000000000000000" );
		System.out.println("json---" + json);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("json---111111" );
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			System.out.println("json---1213");
			e.printStackTrace();
		}*/
		
        return json;
    }
    
    
    
    /***************************************************************************************************/
    
    public JSONObject datafetch(List<NameValuePair>... params) {
		// Making HTTP request
		
		 System.out.println("background under background starting :-------------");
		
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
            
            
          //  System.out.println("segment :" + sb+"--");
            is.close();
            
           
            
            
           json1 = sb.toString();
           
            System.out.println("segment12 :"  + json1  + " ---json created");
            jObj = new JSONObject(json1);  
           System.out.println("result1111-- : "+ jObj + "--json obj");
            Log.e("JSON", json1);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result123 " + e.toString());
        }
 
       
        return jObj;
	}
    
    
    
     
}