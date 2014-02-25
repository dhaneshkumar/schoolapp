package com.example.schoolapp;
import library.DatabaseHandler;
import library.Mail;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Leaveapp extends ActionBarActivity{

	
	TextView starting_date;
	TextView ending_date;
	TextView reason;
	String msg;
	String receiver_prof_email="pncooldude91@gmail.com";  // recepient email-id
	Mail m;
	String[] store;
	Spinner select;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leaveapp);
		starting_date=(TextView)findViewById(R.id.starting_date_txt);
		ending_date=(TextView)findViewById(R.id.ending_date_txt);
		reason=(TextView)findViewById(R.id.reason_txt);
		
		//Updating spinner dynamically
		DatabaseHandler db = new DatabaseHandler(this);
		String result1="";	    
		db.setPhoneList();
		result1 = db.getPhoneList();
		System.out.println("got result : " + result1);                 
		 store= result1.split("~");
		select=(Spinner) findViewById(R.id.spinner_select);
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		select.setAdapter(spinnerAdapter);
		
		for(int i=0;i<store.length;++i){
		final String[] parts = store[i].split(",");
		spinnerAdapter.add(parts[0]);
        System.out.println("parts : -- " + parts);
		}
		spinnerAdapter.notifyDataSetChanged();
		//System.out.println(select.getSelectedItem());
		select.setOnItemSelectedListener(new SpinnerSelectedListener()); 

	}
	
	public class SpinnerSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	        String selected = parent.getItemAtPosition(pos).toString();
	        for(int i=0; i<store.length;++i){
	        	final String[] parts = store[i].split(",");
	        	if(parts[0].equals(selected)){
	        		receiver_prof_email=parts[4];
	        		System.out.println(receiver_prof_email);
	        		break;
	        	}
	        }
	    }

	    public void onNothingSelected(AdapterView parent) {
	        // Do nothing.
	    }
	}
	
	
public void preview_leaveapp(View v){
	msg="<html>Sir/Mam," +"<br/>"+"  "+"This is to inform you that my child studying in 7th A will be unable to attend school due to"+reason.getText()+
			"<br/>"+"Please grant him/her leave from "+starting_date.getText()+" to "+ending_date.getText()+"<br/>"+"Thanking You,"+"<br/>"+"Yours Sincerely,"+"<br/>"+"XYZ"+"</html>";
	final AlertDialog.Builder previewDialog = new AlertDialog.Builder(Leaveapp.this);
	previewDialog.setTitle("Preview");
	 final TextView preview=new TextView(Leaveapp.this);
     preview.setText(Html.fromHtml(msg));
     previewDialog.setView(preview);
     previewDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface dialog1, int which1) {
			// TODO Auto-generated method stub
			dialog1.cancel();
		}
     });
     previewDialog.show();
}
public void submit_leaveapp(View v){
	msg="<html>Sir/Mam," +"<br/>"+"  "+"This is to inform you that my child studying in 7th A will be unable to attend school due to "+reason.getText()+
			"<br/>"+"Please grant him/her leave from "+starting_date.getText()+" to "+ending_date.getText()+"<br/>"+"Thanking You,"+"<br/>"+"Yours Sincerely,"+"<br/>"+"XYZ"+"</html>";
	//reason.setText(Html.fromHtml(msg));
	System.out.println(Html.fromHtml(msg));
	AlertDialog.Builder alertDialog = new AlertDialog.Builder(Leaveapp.this);

    // Setting Dialog Title
    alertDialog.setTitle("Account Details");

    // Setting Dialog Message
    //alertDialog.setMessage("Enter Password");
     final TextView email_id_text=new TextView(Leaveapp.this);
     email_id_text.setText("Email-id");
     
	 final EditText input_email = new EditText(Leaveapp.this);
     input_email.setHint("email-id");
     input_email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
     
     final TextView password_text=new TextView(Leaveapp.this);
     password_text.setText("Password");
       
     final EditText password = new EditText(Leaveapp.this);
     password.setHint("password");
     password.setTransformationMethod(PasswordTransformationMethod.getInstance());
    
	LinearLayout ll=new LinearLayout(this);
	ll.setOrientation(LinearLayout.VERTICAL);
	ll.addView(email_id_text);
	ll.addView(input_email);
	ll.addView(password_text);
	ll.addView(password);
	alertDialog.setView(ll);
	
	alertDialog.setPositiveButton("Send", new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			new send_email(input_email.getText().toString(),receiver_prof_email,password.getText().toString(),Html.fromHtml(msg).toString()).execute();
		}
		
	});
	
	alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			dialog.cancel();
		}
	});
	
	alertDialog.show();
}

public class send_email extends AsyncTask<Void, Void, Boolean> {
	
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		 if(result){
	           // show Toast Message here
			 Toast.makeText(Leaveapp.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
	          }else{
	           // show Toast Message here
	        	  Toast.makeText(Leaveapp.this, "Email was not sent.", Toast.LENGTH_LONG).show();
	           }
		//super.onPostExecute(result);
	}


	@Override
	protected Boolean doInBackground(Void ...params) {
		// TODO Auto-generated method stub
		 try { 
			 
                
		      
		      if(m.send()) { 
		         // Toast.makeText(MainActivity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show(); 
		          return true;   
		      } else { 
		         // Toast.makeText(MainActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show(); 
		    	  return false;   
		      } 
		      
		      
		      
		         
            } catch (Exception e) {   
                Log.e("SendMail", e.getMessage(), e);   
               // Toast.makeText(MainActivity.this, "in_catch! ! " + e.toString(), Toast.LENGTH_LONG).show();
                return false;
            }
		
	}

	public send_email(String sender_email, String receiver_email, String sender_password, String message){
		
		// TODO Auto-generated constructor stub
	     String ur_email=sender_email;                   
	     String ur_email_pass=sender_password;//
		 String emer_email=receiver_email;
		  m = new Mail(ur_email, ur_email_pass); 
	      String[] toArr = { emer_email}; 
	      m.setTo(toArr); 
	      m.setFrom(ur_email); 
	      m.setSubject("Leave application"); 
	      m.setBody(message);    
	}	
}
}
