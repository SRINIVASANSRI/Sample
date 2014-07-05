package com.example.taxiapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Email extends Activity {

	private RadioGroup radioSexGroup;
	private RadioButton radioSexButton;
	private Button btnDisplay;
	SharedPreferences sharedpreferences;
	Editor editor;
    Context context;

	String sharedpreferences_mobModel = "", sharedpreferences_andVersion = "";
    String sharedpreferences_mobnumber = "";
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try
		{
		super.onCreate(savedInstanceState);
		
		final String MyPREFERENCES = "MyPrefs";
		context = getApplicationContext();
     /* one time loging */
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		editor = sharedpreferences.edit();

        sharedpreferences_mobnumber = sharedpreferences.getString("mobile", "");
	    sharedpreferences_andVersion = sharedpreferences.getString("version_shared", "");
	    sharedpreferences_mobModel = sharedpreferences.getString("model_shared", "");
	    
		String email = sharedpreferences.getString("email", "");
		if(!email.equalsIgnoreCase(""))
		{
			finish();
			Intent intent = new Intent(Email.this, Home.class);
			startActivity(intent);
		}
		
		setContentView(R.layout.email);
		addListenerOnButton();
		check_Android_Version();

		RelativeLayout mLinearLayout = (RelativeLayout) findViewById(R.id.email_linear);
		System.out.println("mLinearLayout is : " + mLinearLayout);
		// create text button
		TextView title = new TextView(this);
	    //title.setText("Please Select one below mail id");
		
		System.out.println("title is : " + title);
		title.setTextColor(Color.BLUE);
		mLinearLayout.addView(title);
		Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");
		System.out.println("accounts is : " + accounts.length);
		// create radio button
		final RadioButton[] rb = new RadioButton[accounts.length];
		RadioGroup rg = new RadioGroup(this);
		rg.setId(R.id.email_radio_group);
		rg.setOrientation(RadioGroup.VERTICAL);
		int i = 0;
		for (Account account : accounts) {
			System.out.println("account.name is : " + account.name);
			final String acc = account.name;
			rb[i] = new RadioButton(this);
			rb[i].setId(i);
			rb[i].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// setRadioButtonSelect(v);

					Toast.makeText(getApplicationContext(),"Mail Id is : " + acc, Toast.LENGTH_SHORT).show();
				}
			});
			rg.addView(rb[i]);
			rb[i].setText(acc);
			i++;
		}
		mLinearLayout.addView(rg);
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

	public void addListenerOnButton() {
		try
		{

		btnDisplay = (Button) findViewById(R.id.btnDisplay);

		System.out.println("btnDisplay is : " + btnDisplay);
		btnDisplay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				radioSexGroup = (RadioGroup) findViewById(R.id.email_radio_group);
				System.out.println("radioSexButton istart " + radioSexGroup);
				// get selected radio button from radioGroup
				int selectedId = radioSexGroup.getCheckedRadioButtonId();
				System.out.println("selectedId is : " + selectedId);
				// Toast.makeText(Email.this,
				// selectedId, Toast.LENGTH_SHORT).show();

				if (selectedId != -1) {
					// find the radiobutton by returned id
					radioSexButton = (RadioButton) findViewById(selectedId);
					final String MyPREFERENCES = "MyPrefs";
					// System.out.println("radioSexButton is : "+radioSexButton);
					// Toast.makeText(Email.this,
					// String.valueOf(radioSexButton.getText()),
					// Toast.LENGTH_SHORT).show();
					final String emailKey = "Select_Email_Cab";

					SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
					Editor edit = sharedpreferences.edit();
					edit.putString(emailKey,String.valueOf(radioSexButton.getText()));
					

					edit.putString("version_shared", android.os.Build.VERSION.RELEASE);
					edit.putString("model_shared", Build.MANUFACTURER + " " + Build.MODEL);
					
					edit.commit();
					String.valueOf(radioSexButton.getText());					
					finish();
					Intent intent = new Intent(Email.this, Login.class);
					startActivity(intent);
					
					// Toast.makeText(Email.this,
					// radioSexButton.getText(), Toast.LENGTH_SHORT).show();
				}
				 Intent intent = new Intent(Email.this, Login.class);// need to comment
				 startActivity(intent);//need to comment
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
	
	public void check_Android_Version(){
		
	}
	
	
	
	
	
}