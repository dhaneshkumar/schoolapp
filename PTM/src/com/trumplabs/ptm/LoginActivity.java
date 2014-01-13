package com.trumplabs.ptm;

import org.json.JSONException;
import org.json.JSONObject;

import library.DatabaseHandler;
import library.UserFunctions;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{
	EditText user;
	EditText pass;
	Button btn_login;
	Button btn_forgot;
	CheckBox chk_remember;
	TextView loginErrorMsg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//Defining all the related objects from xml
		user = (EditText)findViewById(R.id.username);
		pass = (EditText)findViewById(R.id.password);
		btn_login = (Button)findViewById(R.id.btn_login);
		btn_forgot = (Button)findViewById(R.id.btn_forgot);
		chk_remember = (CheckBox)findViewById(R.id.chk_remember);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);
		
		
		btn_login.setOnClickListener(this);
		chk_remember.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btn_login){
			String username = user.getText().toString();
            String password = pass.getText().toString();
           
            UserFunctions userFunction = new UserFunctions();
            
            
            
            if(username.equals("") || password.equals("") || !isOnline()){
            	return;
            }
         
            JSONObject json = userFunction.loginUser(username, password);
            
            // check for login response
            try {
                if (json.getString("success") != null) {
                    loginErrorMsg.setText("");
                    int res = json.getInt("success");
                    if(res == 1){
                        // user successfully logged in
                        // Store user details in SQLite Database
                        DatabaseHandler db = new DatabaseHandler(this);
                        
                        JSONObject json_user = json.getJSONObject("user");
                         
                        // Clear all previous data in database
                        userFunction.logoutUser(getApplicationContext());
                        db.addUser(
                        		json_user.getInt("id"),
                        		json_user.getString("table"));                       
                         
                        // Launch Dashboard Screen
                        Intent dashboard = new Intent("android.intent.action.PROFILE");
                         
                        // Close all views before launching Dashboard
                        dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(dashboard);
                         
                        // Close Login Screen
                        finish();
                    }else{
                        // Error in login
                        loginErrorMsg.setText("Incorrect username/password");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
		}
		else if(v.getId() == R.id.btn_forgot){
			
		}
		else{
			
		}
	}
	
	public boolean isOnline() {
	    ConnectivityManager connMgr = (ConnectivityManager) 
	            getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    return (networkInfo != null && networkInfo.isConnected());
	}  
}
