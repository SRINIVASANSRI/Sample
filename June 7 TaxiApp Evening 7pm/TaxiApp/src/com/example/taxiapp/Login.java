package com.example.taxiapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode;
import android.util.Log;
public class Login extends Activity {
	Button submit;
	CheckBox chkbox;
	EditText e_mail, mobile_number ,user_name;
	SharedPreferences sharedpreferences;
	Editor editor;

	String sharedpreferences_mobModel = "", sharedpreferences_andVersion = "";
    String sharedpreferences_mobnumber = "";
    Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try
		{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		context = getApplicationContext();
		submit = (Button) findViewById(R.id.submit);
		chkbox = (CheckBox) findViewById(R.id.chkAndroid);
		
		e_mail = (EditText) findViewById(R.id.email_id);
		mobile_number = (EditText) findViewById(R.id.mobile_edittext);
		user_name = (EditText) findViewById(R.id.name_edittext);
		
		
		final String MyPREFERENCES = "MyPrefs";
		final String emailKey = "Select_Email_Cab";

		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		editor = sharedpreferences.edit();

	    sharedpreferences_mobnumber = sharedpreferences.getString("mobile", "");
	    sharedpreferences_andVersion = sharedpreferences.getString("version_shared", "");
	    sharedpreferences_mobModel = sharedpreferences.getString("model_shared", "");
	    
		String Email = sharedpreferences.getString(emailKey, "");
		e_mail.setText(Email);
		login_buton();
		notification_checkbok_Action();
	}
		catch(Exception e)
		{
			    Home obj = new Home();
			    SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "onCreate";
		        History obj1 = new History();
		        obj1.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	 }
	 

	public void login_buton() {
        System.out.println("submit is : "+submit);
		
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try
				{
				// TODO Auto-generated method stub
					System.out.println("chkbox is : "+chkbox);
					System.out.println("e_mail is : "+e_mail);
					System.out.println("mobile_number is : "+mobile_number);
					System.out.println("user_name is : "+user_name);
				if (e_mail.getText() != null && !String.valueOf(e_mail.getText()).equals("") && mobile_number.getText() != null && !String.valueOf(mobile_number.getText()).equals("") && user_name.getText() != null && !String.valueOf(user_name.getText()).equals("")){
					System.out.println("chkbox.isChecked() is : "+chkbox.isChecked());
					if(chkbox.isChecked())
					{	
				    String loginURL1 = getResources().getString(R.string.login_url);
				    System.out.println("loginURL is : ===== "+loginURL1);
				    System.out.println("History is : ===== ");
					History obj = new History();
					obj.testTimingTable("Start Action - User Create",context);
					 System.out.println("History is : ===1== ");
					//insert query for Database
					final String MyPREFERENCES = "MyPrefs";	
				     /* one time loging */
					sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
					editor = sharedpreferences.edit();
					editor.putString("email", e_mail.getText().toString());
					editor.putString("mobile", mobile_number.getText().toString());
					editor.putString("uname", user_name.getText().toString());
					editor.commit();
					//finish();
					
					
					boolean isNetworkAvailable = false;
					System.out.println("isNetworkAvailable is : "+isNetworkAvailable);
				    isNetworkAvailable =  isNetworkAvailable();
				    System.out.println("isNetworkAvailable is : 9999 "+isNetworkAvailable);
				    System.out.println("isNetworkAvailable is : "+isNetworkAvailable);
					if(isNetworkAvailable)
					{
						
						String mobileInfo = getmobileInformation();
						String mobileInfoArr[] = mobileInfo.split("_:_",-1);
						String mobile_model = mobileInfoArr[0] + mobileInfoArr[1];
						String android_version = mobileInfoArr[2];
						StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
					    StrictMode.setThreadPolicy(policy);
					    String e_mailValue = "";
					    String user_nameValue = "";
					    String mobilenoValue = "";
					    if(e_mail.getText() == null)
					    {
					    	e_mailValue = "";
					    }
					    else
					    {
					    	e_mailValue = String.valueOf(e_mail.getText());
					    }
					    if(user_name.getText() == null)
					    {
					    	user_nameValue = "";
					    }
					    else
					    {
					    	user_nameValue = String.valueOf(user_name.getText());
					    }
					    if(mobile_number.getText() == null)
					    {
					    	mobilenoValue = "";
					    }
					    else
					    {
					    	mobilenoValue = String.valueOf(mobile_number.getText());
					    }
					    obj.testTimingTable("start RestFul - User Create",context);
					    JSONObject jsonObject = new JSONObject("{\"emailid\":\""+e_mailValue+"\",\"name\":\""+user_nameValue+"\",\"mobileno\":\""+mobilenoValue+"\",\"androidVersion\":\""+android_version+"\",\"mobileModel\":\""+mobile_model+"\"}");
					    System.out.println(jsonObject);
		                //Toast.makeText(getApplicationContext(),"isNetworkAvailable "+jsonObject, Toast.LENGTH_SHORT).show();
					    String loginURL = getResources().getString(R.string.login_url);
					    
		                URL url = new URL(loginURL);
		                //System.out.println("url is : "+url);
		                URLConnection connection = url.openConnection();
		                System.out.println("connection is : "+connection);
		                connection.setDoOutput(true);
		                connection.setRequestProperty("Content-Type", "application/json");
		                connection.setConnectTimeout(5000);
		                connection.setReadTimeout(5000);
		                //System.out.println("connection 1 is : "+connection);
		                System.out.println("connection.getOutputStream() is : "+connection.getOutputStream());
		                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		                //System.out.println("out is : "+out);
		                out.write(jsonObject.toString());
		                out.close();
		                //System.out.println("I am in close");
		                System.out.println("connection.getInputStream() is : "+connection.getInputStream());
		                BufferedReader in = new BufferedReader(new InputStreamReader(
		                        connection.getInputStream()));
		 
		                String line;
		                while ((line = in.readLine()) != null) {
		                System.out.println("!!!!-"+line+"\n");
		                }
		                //System.out.println("\nREST Service Invoked Successfully..");
		                in.close();
		                obj.testTimingTable("Finished RestFul - User Create",context);
		                /*
		                url = new URL("http://localhost:8080/AdminApp/webresources/cab/getRating/KA 05 HZ 5378,9035720890");
		                URLConnection connection1 = url.openConnection();
		                BufferedReader in1 = new BufferedReader(new InputStreamReader(
		                        connection1.getInputStream()));
		 
		                String line1;
		                while ((line1 = in1.readLine()) != null) {
		                System.out.println("!!!!-"+line1+"\n");
		                }*/
					}
					else
					{
						Toast.makeText(getApplicationContext(),"Please check the internet connection.", Toast.LENGTH_SHORT).show();
					}

					obj.testTimingTable("End Action - User Create",context);	
				    Intent intent = new Intent(Login.this, Home.class);
					startActivity(intent);				
					}
					else
					{
					Toast.makeText(getApplicationContext(),"Please check the checkbox.", Toast.LENGTH_SHORT).show();
					}
				}	
			    else{
					
					Toast.makeText(getApplicationContext(), "Please Enter the Fields", Toast.LENGTH_SHORT)
					.show();
				}
			}
				catch(Exception e)
				{
					    Home obj = new Home();
					    SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
				    	System.out.println(e);
				    	String service_name =  "onCreate";
				        History obj1 = new History();
				        obj1.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
				}

			}
			
		});
	}
	public void notification_checkbok_Action() {
    try
	{
		 final CheckBox chkbox;

		chkbox = (CheckBox) findViewById(R.id.chkAndroid);
		chkbox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(chkbox.isChecked()) {
					alertbox();
					
				}				
			}
		});
	}
    catch(Exception e)
	{
		    Home obj = new Home();
		    SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
	    	System.out.println(e);
	    	String service_name =  "onCreate";
	        History obj1 = new History();
	        obj1.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
	}

	}

	public void alertbox() {
		try
		{
		AlertDialog ad = new AlertDialog.Builder(this)
				.setMessage(Html.fromHtml("<b>Summary of licensing terms.\n</b>By downloading a free Android application and document available on this website, you accept and agree to our terms and conditions."))
				.setIcon(R.drawable.ic_launcher).setTitle("Terms and Conditions.").create();
		        ad.getWindow().setGravity(Gravity.CENTER);  
               // to hide a keyboard when a setting menu display  
		       ad.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
               //Dialog no disappear when click outside  
		      ad.setCanceledOnTouchOutside(false);  
              InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
              ad.show();
				
				 View focus = getCurrentFocus();    
                 if (focus != null) {    
                     im.hideSoftInputFromWindow(focus.getWindowToken(), 0);    
                 }
		}
		catch(Exception e)
		{
			    Home obj = new Home();
			    SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "onCreate";
		        History obj1 = new History();
		        obj1.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}

	}

	public boolean isNetworkAvailable() {
 		System.out.println("isNetworkAvailable ------------  inside method ");
 		try
         {
         ConnectivityManager connectivityManager 
               = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
         System.out.println("isNetworkAvailable ------------  inside method " +activeNetworkInfo.isConnected());
         return activeNetworkInfo != null && activeNetworkInfo.isConnected();
         }
 		catch(Exception e)
		{
			    Home obj = new Home();
			    SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "onCreate";
		        History obj1 = new History();
		        obj1.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}

 		return false;
     }
	
	public String getmobileInformation()
	{
		try
        {
		String version = android.os.Build.VERSION.RELEASE;
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		return manufacturer+"_:_"+model+"_:_"+version;
        }
		catch(Exception e)
		{
			    Home obj = new Home();
			    SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "onCreate";
		        History obj1 = new History();
		        obj1.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
		return "";
	}
 	
}