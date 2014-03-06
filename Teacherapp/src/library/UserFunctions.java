package library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import library.JSONParser;
import android.content.Context;
import android.os.AsyncTask;
 
public class UserFunctions {
     
    private static String loginURL = "http://10.0.2.2/schoolapp/index.php";
    private static String login_tag = "login";
    private static String details_tag = "details";
    private static String children_tag = "children";
    private static JSONObject json = null;
     
    // constructor
    public UserFunctions(){
    	
    }
     
    public JSONObject loginUser(String username, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(loginURL).execute(params);
        
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
        
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(loginURL).execute(params);
        
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
        
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(loginURL).execute(params);
        
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
        
        
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(loginURL).execute(params);
        
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
      
        AsyncTask<List<NameValuePair>, Void, JSONObject> json_parse = new JSONParser(loginURL).execute(params);
        
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
    
     
}