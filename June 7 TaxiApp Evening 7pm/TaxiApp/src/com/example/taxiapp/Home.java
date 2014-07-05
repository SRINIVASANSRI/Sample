package com.example.taxiapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;



public class Home extends Activity {

	
	String sharedpreferences_cabnumber ="";	
	String sharedpreferences_emailid = "";
	NotificationManager mNotificationManager;
	int notificationID = 0;
	int numMessages = 0;
	int drivervalue = 0;
	int vehiclevalue = 0;
	int farevalue = 0;
    String sharedpreferences_mobnumber = "";
	Button reddrive, yellowdrive, greendrive;
	Button redvehicle, yellowvehicle, greenvehicle;
	Button redfare, yellowfare, greenfare;
	Button rate_now, get_rating, submit_all_rating, clear_rating, rate_now_result, get_rating_result;
	Button whitedriver, whitevehicle, whitefare;
	RelativeLayout layout_ratenow, layout_get_rating, layout_one;
	Button getRating_driver, getRating_vehicle,getRating_fare ;
	SharedPreferences sharedpreferences;
	String sharedpreferences_mobModel = "", sharedpreferences_andVersion = "";
	Editor editor;
	EditText cab_number ;
	boolean flagRate = true, flagGetRating = true;

	/* This method() for menu */
	MenuItem item1;
	Context context;
	public void historycall(MenuItem item) {
		try
		{
		if (item.getItemId() == R.id.menu_history) {
			Intent intent = new Intent(Home.this, History.class);
			startActivity(intent);
		}
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "historycall";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}

	public void aboutus(MenuItem item) {
		try
		{
		if (item.getItemId() == R.id.menu_aboutus) {
			AlertDialog ad = new AlertDialog.Builder(this)
					.setMessage(Html.fromHtml("<b>WhoWeAre:</b>Ivalei Technologies is an IT services,consulting and business solutions organization that delivers real results to global businesses,ensuring a level of certainty that no other firm can match.<b>www.ivalei.com</b>"))
					.setIcon(R.drawable.ic_launcher)
					.setTitle("About Ivalei Tech.").create();
			        ad.show();
			        
		//String cab = null;
		//cab.charAt(0);
		}
		}
		catch(Exception e)
		{

			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "aboutus";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}

	public void logout(MenuItem item) {
		try
		{
		if (item.getItemId() == R.id.menu_logout) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Do you want to logout?");
			// alert.setMessage("Message");

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Home.this.finish();
						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
						}
					});

			alert.show();

		}
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "logout";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}

	/* This method() for menu */
	/* This method() for back button */
	@Override
	public void onBackPressed() {
		try
		{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Do you want to logout?");
		// alert.setMessage("Message");

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Home.this.finish();
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});

		alert.show();
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "onBackPressed";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		final String MyPREFERENCES = "MyPrefs";
		SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		editor = sharedpreferences.edit();
	    context = getApplicationContext();
		SQLiteDatabase mydatabase = createMobileDataBase(context);
		emptyCreateTable(mydatabase);
		final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
		String cab = sharedpreferences.getString(sharedpreferences_cabnumber, "");
		cab_number.setText(cab);
		
		Editor edit = sharedpreferences.edit();
		edit.putString(sharedpreferences_cabnumber,"");
		edit.commit();
		URL url = null;
	    sharedpreferences_mobnumber = sharedpreferences.getString("mobile", "");
	    sharedpreferences_andVersion = sharedpreferences.getString("version_shared", "");
	    sharedpreferences_mobModel = sharedpreferences.getString("model_shared", "");
	    sharedpreferences_emailid = sharedpreferences.getString("Select_Email_Cab", "");
	    if(sharedpreferences_andVersion != null && !sharedpreferences_andVersion.equals(android.os.Build.VERSION.RELEASE))
	    {
	    	HttpURLConnection connection = null;
	    	String urlValue =  getResources().getString(R.string.cabUserUpdate_url);
			String urlValue1 = urlValue + sharedpreferences_mobnumber + "," + sharedpreferences_emailid + "," + sharedpreferences_andVersion + "," + sharedpreferences_mobModel;
			
			try {
				url = new URL(urlValue1);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //System.out.println("url is : "+url);
	        
			try {
				connection = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("connection is --------- : "+connection);
	        connection.setDoOutput(false);
	        connection.setConnectTimeout(5000);
	        connection.setReadTimeout(5000);
	    }
	    
	    System.out.println("sharedpreferences_mobnumber is : "+sharedpreferences_mobnumber);
		Log.d("neeraj", "email : "+sharedpreferences.getString("email", ""));
		Log.d("neeraj", "mobile : "+sharedpreferences.getString("mobile", ""));
		Log.d("neeraj", "uname : "+sharedpreferences.getString("uname", ""));
		
		reddrive = (Button) findViewById(R.id.red_driver);
		yellowdrive = (Button) findViewById(R.id.yellow_driver);
		greendrive = (Button) findViewById(R.id.green_driver);

		redvehicle = (Button) findViewById(R.id.red_vehicle);
		yellowvehicle = (Button) findViewById(R.id.yellow_vehicle);
		greenvehicle = (Button) findViewById(R.id.green_vehicle);

		redfare = (Button) findViewById(R.id.red_fare);
		yellowfare = (Button) findViewById(R.id.yellow_fare);
		greenfare = (Button) findViewById(R.id.green_fare);

		layout_get_rating = (RelativeLayout) findViewById(R.id.layout_get_rating);
		layout_ratenow = (RelativeLayout) findViewById(R.id.layout_ratenow);
		layout_one = (RelativeLayout) findViewById(R.id.layout_one);

		rate_now = (Button) findViewById(R.id.rate_now);
		get_rating = (Button) findViewById(R.id.get_rating);

		submit_all_rating = (Button) findViewById(R.id.rating_btnsubmit);
		clear_rating = (Button) findViewById(R.id.rating_btnclear);

		whitedriver = (Button) findViewById(R.id.driver);
		whitevehicle = (Button) findViewById(R.id.vehicle);
		whitefare = (Button) findViewById(R.id.fare);
		
		getRating_driver = (Button) findViewById(R.id.getRating_driver);
		getRating_vehicle = (Button) findViewById(R.id.getRating_vehicle);
		getRating_fare = (Button) findViewById(R.id.getRating_fare);
		
		rate_now_result = (Button) findViewById(R.id.rate_now_result);
		get_rating_result = (Button) findViewById(R.id.get_rating_result);
		
		
		
		
		submit_all_rating.setEnabled(false);
		clear_rating.setEnabled(false);
		
		
		layoutChange_on_BtnClick();
		change_DriverColor();
		change_VehicleColor();
		change_FareColor();
		//save_All_Cab_Rating(); This method calling onclick fun()
		clear_All_Rating();
		serch_Again();
		hired_for_notification();
	    //get_Rating_Details();  This method calling onclick fun()
		ACTION_SEARCH_BUTTON();//for custom search button in softkey pad.
     	isNetworkAvailable(context);
		convertStringToDocument("",context);
		History obj = new History();
        obj.errorInsert(String.valueOf(""),null,"",mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
        obj.errorlogTablemobileToServer(mydatabase,context);
        System.out.println("cabRatingTableDataToServer before ");
        obj.cabRatingTableDataToServer(mydatabase);
      
	}
/*Due to this getting Error*/
 	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
 		try
 		{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
 		}
 		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "onCreateOptionsMenu";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
 		return true;
	}

	private void layoutChange_on_BtnClick() {
		try
		{
        rate_now.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
				submit_all_rating.setVisibility(View.VISIBLE);
				if (cab_number.getText() != null
						&& !String.valueOf(cab_number.getText()).equals("")) {
					if (flagRate) {
						hideSoftKey(cab_number);
						layout_get_rating.setVisibility(View.GONE);
						layout_ratenow.setVisibility(View.VISIBLE);
						rate_now.setVisibility(View.GONE); // Hiding  You Rate now button
						get_rating.setVisibility(View.GONE); // Hiding  get Rating button
						rate_now_result.setVisibility(View.VISIBLE);
						save_All_Cab_Rating();
						//flagRate = false;
						//flagGetRating = true;
						//Toast.makeText(getApplicationContext(),"Please Enter the Taxi number.", Toast.LENGTH_LONG).show();
					} else {
						layout_ratenow.setVisibility(View.GONE);
						//flagRate = true;
					}
				} else {
					hideSoftKey(cab_number);
					Toast.makeText(getApplicationContext(),
							"Please Enter the Taxi number.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		get_rating.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
				if (cab_number.getText() != null
						&& !String.valueOf(cab_number.getText()).equals("")) {
					if (flagGetRating) {
						hideSoftKey(cab_number);
						layout_get_rating.setVisibility(View.VISIBLE);
						rate_now.setVisibility(View.GONE);
						get_rating.setVisibility(View.GONE);
						get_rating_result.setVisibility(View.VISIBLE);
						get_Rating_Details();
						layout_ratenow.setVisibility(View.GONE);
						//flagGetRating = false;
						//flagRate = true;
					} else {
						layout_get_rating.setVisibility(View.GONE);
						//flagGetRating = true;
					}
				} else {
					hideSoftKey(cab_number);
					Toast.makeText(getApplicationContext(),
							"Please Enter the Taxi number.", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		layout_one.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final EditText cab_number = (EditText) findViewById(R.id.taxi_number);				
				hideSoftKey(cab_number);
			}
		});
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "layoutChange_on_BtnClick";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}
	
	public void hideSoftKey(EditText editText)
	{
		try
		{
		 submit_all_rating.setVisibility(View.VISIBLE);
		 InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
         imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "hideSoftKey";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}

	public void change_DriverColor() {
		try
		{

		reddrive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				reddrive.getBackground().setColorFilter(Color.parseColor("#CC0001"),Mode.SRC_ATOP);		
				yellowdrive.getBackground().setColorFilter(Color.parseColor("#ff000000"),Mode.SRC_ATOP);
				greendrive.getBackground().setColorFilter(Color.parseColor("#ff000000"),Mode.SRC_ATOP);
				submit_all_rating.setEnabled(true);
				clear_rating.setEnabled(true);
				drivervalue = 1;
				EditText text1 = (EditText) findViewById(R.id.rating_driver_value);
				text1.setText(String.valueOf(drivervalue));

			}
		});
		yellowdrive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				yellowdrive.getBackground().setColorFilter(Color.parseColor("#E7C14E"), Mode.SRC_ATOP);
				reddrive.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				greendrive.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				submit_all_rating.setEnabled(true);
				clear_rating.setEnabled(true);
				drivervalue = 2;
				EditText text1 = (EditText) findViewById(R.id.rating_driver_value);
				text1.setText(String.valueOf(drivervalue));

			}
		});
		greendrive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				greendrive.getBackground().setColorFilter(Color.parseColor("#07CB5F"), Mode.SRC_ATOP);
				yellowdrive.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				reddrive.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				submit_all_rating.setEnabled(true);
				clear_rating.setEnabled(true);
				drivervalue = 3;
				EditText text1 = (EditText) findViewById(R.id.rating_driver_value);
				text1.setText(String.valueOf(drivervalue));

			}
		});
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "change_DriverColor";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}

	public void change_VehicleColor() {

		try
		{
		redvehicle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				redvehicle.getBackground().setColorFilter(Color.parseColor("#CC0001"), Mode.SRC_ATOP);
				yellowvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				greenvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				vehiclevalue = 1;
				EditText text2 = (EditText) findViewById(R.id.rating_vehicle_value);
				text2.setText(String.valueOf(vehiclevalue));
			}
		});
		yellowvehicle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				yellowvehicle.getBackground().setColorFilter(Color.parseColor("#E7C14E"), Mode.SRC_ATOP);
				redvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				greenvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				vehiclevalue = 2;
				EditText text2 = (EditText) findViewById(R.id.rating_vehicle_value);
				text2.setText(String.valueOf(vehiclevalue));
			}
		});
		greenvehicle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				greenvehicle.getBackground().setColorFilter(Color.parseColor("#07CB5F"), Mode.SRC_ATOP);
				yellowvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				redvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				vehiclevalue = 3;
				EditText text2 = (EditText) findViewById(R.id.rating_vehicle_value);
				text2.setText(String.valueOf(vehiclevalue));
			}
		});
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "change_VehicleColor";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}

	public void change_FareColor() {

		try
		{
		redfare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				redfare.getBackground().setColorFilter(Color.parseColor("#CC0001"), Mode.SRC_ATOP);
				yellowfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				greenfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				farevalue = 1;
				EditText text3 = (EditText) findViewById(R.id.rating_fare_value);
				text3.setText(String.valueOf(farevalue));
			}
		});
		yellowfare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				yellowfare.getBackground().setColorFilter(Color.parseColor("#E7C14E"), Mode.SRC_ATOP);
				redfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				redfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				farevalue = 2;
				EditText text3 = (EditText) findViewById(R.id.rating_fare_value);
				text3.setText(String.valueOf(farevalue));

			}
		});
		greenfare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				greenfare.getBackground().setColorFilter(Color.parseColor("#07CB5F"), Mode.SRC_ATOP);
				redfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				yellowfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
				farevalue = 3;
				EditText text3 = (EditText) findViewById(R.id.rating_fare_value);
				text3.setText(String.valueOf(farevalue));

			}
		});
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "change_VehicleColor";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}

	private void save_All_Cab_Rating() {

		submit_all_rating.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				History objk = new History();
				objk.testTimingTable("Start Action - Save Rating",context);
				
				String today = "";
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				today = dateFormat.format(date);
				
				final EditText cab_number = (EditText) findViewById(R.id.taxi_number);	
				System.out.println("String.valueOf(cab_number.getText()) is : "+String.valueOf(cab_number.getText()));
				String vehicle_no = String.valueOf(cab_number.getText());
				final EditText drivervalue = (EditText) findViewById(R.id.rating_driver_value);
				final EditText vehiclevalue = (EditText) findViewById(R.id.rating_vehicle_value);
				final EditText farevalue = (EditText) findViewById(R.id.rating_fare_value);  
				SQLiteDatabase mydatabase = createMobileDataBase(context);
				try
				{
				// TODO Auto-generated method stub				
				//sathish added...
				boolean isNetworkAvailable = false;
				
				isNetworkAvailable =  isNetworkAvailable(context);  
				System.out.println("isNetworkAvailable is : "+isNetworkAvailable);
			    
				int vehicle_int = 0;
				int rating_int = 0;
				boolean dataflag = false;
				
				System.out.println(" save_All_Cab_Rating today is :"+today);
				//sathish added...

				float iflt = 0;
				float jflt = 0;
				float kflt = 0;
				//String today = "";
				boolean flag_today_msg = true;
				if (String.valueOf(drivervalue.getText()) != null
						&& !String.valueOf(drivervalue.getText()).equals("")) {
					iflt = Float.parseFloat(String.valueOf(drivervalue
							.getText()));
				}
				if (String.valueOf(vehiclevalue.getText()) != null
						&& !String.valueOf(vehiclevalue.getText()).equals("")) {
					jflt = Float.parseFloat(String.valueOf(vehiclevalue
							.getText()));
				}
				if (String.valueOf(farevalue.getText()) != null
						&& !String.valueOf(farevalue.getText()).equals("")) {
					kflt = Float.parseFloat(String.valueOf(farevalue.getText()));
				}
				if (String.valueOf(cab_number.getText()) != null
						&& !String.valueOf(cab_number.getText()).equals("")
						&& iflt != 0 && jflt != 0 && kflt != 0) {
					if(!isNetworkAvailable)
					{
					System.out.println(" save_All_Cab_Rating today1 is :"+today);
					submit_all_rating.setVisibility(View.GONE);
					
					int vehicle_id = 0;
					
					System.out.println(" save_All_Cab_Rating today2 is :"+today);
					//String DELETE_CONTACTS_TABLE = "drop table cab_information";
					//mydatabase.execSQL(DELETE_CONTACTS_TABLE);
					
					String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS cab_information(vehicle_id INTEGER PRIMARY KEY AUTOINCREMENT,vehicle_no VARCHAR(20),date_time DATETIME DEFAULT CURRENT_TIMESTAMP,UNIQUE (vehicle_no))";
					mydatabase.execSQL(CREATE_CONTACTS_TABLE);
					System.out.println(" save_All_Cab_Rating today3 is :"+today);
	            	//History sathish added...
					History obj = new History();
					System.out.println(" History today4 is :"+today);
					obj.history_getRating(String.valueOf(cab_number.getText()),"","Rated",today,mydatabase);
					//History sathish added...
				    System.out.println("cab_number.getText() is : "+cab_number.getText());
				    
				    String sql1 = "select max(vehicle_id) from cab_information";
					Cursor mCursor1 = mydatabase.rawQuery(sql1, null);
					System.out.println("mCursor Count is : "+mCursor1.getCount());
					if (mCursor1.moveToFirst()) {
						do {
							vehicle_int = mCursor1.getInt(0);
						} while (mCursor1.moveToNext());
					}
					
					
					String sql = "select vehicle_id from cab_information where vehicle_no='"+cab_number.getText()+"'";
					Cursor mCursor = mydatabase.rawQuery(sql, null);
					System.out.println("mCursor Count is : "+mCursor.getCount());
					if (mCursor.moveToFirst()) {
						do {
							vehicle_id = mCursor.getInt(0);
							dataflag = true;
							System.out.println("dataflag -------- is : " + dataflag);
							break;
						} while (mCursor.moveToNext());
					}
					if (!dataflag) {
						System.out.println("dataflag is : " + dataflag);
						vehicle_int = vehicle_int + 1;
				mydatabase.execSQL("INSERT INTO cab_information VALUES ('"+vehicle_int+"','"+ cab_number.getText() + "','"+today+"')");
				}
				System.out.println("Sucessfully inserted cab_information ");
				String CREATE_CONTACTS_TABLE1 = "CREATE TABLE IF NOT EXISTS cab_rating(rating_id INTEGER PRIMARY KEY AUTOINCREMENT,vehicle_id Integer,rating_driver Integer,rating_vehicle Integer,rating_fare Integer,mobile_no VARCHAR(20),date_time DATETIME DEFAULT CURRENT_TIMESTAMP)";
				mydatabase.execSQL(CREATE_CONTACTS_TABLE1);

					System.out.println("cab_details created successfully");

					String sql7 = "select vehicle_id from cab_information where vehicle_no='"
							+ cab_number.getText() + "' ";
					Cursor mCursor7 = mydatabase.rawQuery(sql7, null);
					if (mCursor7.moveToFirst()) {
						do {
							vehicle_id = mCursor7.getInt(0);
						} while (mCursor7.moveToNext());
					}

					String todayArr[] = null;
					String todayDate = "";
					System.out.println("vehicle_id is  : " + vehicle_id);
				
					todayArr = today.split(" ", -1);
					todayDate = todayArr[0];
					int today_insert_count = 0;
					String date_time_for_delete = "";
					

				    String sql2 = "select max(rating_id) from cab_rating";
					Cursor mCursor2 = mydatabase.rawQuery(sql2, null);
					System.out.println("mCursor Count is : "+mCursor2.getCount());
					if (mCursor2.moveToFirst()) {
						do {
							rating_int = mCursor2.getInt(0);
						} while (mCursor2.moveToNext());
					}
					rating_int = rating_int + 1;
					
				    String sql5 = "select count(*) from cab_rating where vehicle_id='"+ vehicle_id + "' and '" + todayDate + "' =SUBSTR('"	+ today	+ "',1,10) and mobile_no='' ";
							
					// above query we need to add mobile no field
					Cursor mCursor5 = mydatabase.rawQuery(sql5, null);
					System.out
							.println("condition for checking user can not insert rate morethen 2 hr");
					System.out.println("Query for checking user status :"
							+ sql5);

					if (mCursor5.moveToFirst()) {
						do {
							today_insert_count = mCursor5.getInt(0);
						} while (mCursor5.moveToNext());
					}
					System.out.println("today_insert_count :"
							+ today_insert_count);
					if (today_insert_count < 5) {  //change 2 to 5 by Rahul
						saveCab_Details(mydatabase, vehicle_id,
								String.valueOf(drivervalue.getText()),
								String.valueOf(vehiclevalue.getText()),
								String.valueOf(farevalue.getText()), today,rating_int);
					} else {

						if (today_insert_count > 5) {
							flag_today_msg = false;
						String sql6 = "select date_time from cab_rating where vehicle_id='"
								+ vehicle_id
								+ "' and mobile_no='' order by date_time";
							// above query we need to add mobile no field
							Cursor mCursor6 = mydatabase.rawQuery(sql6, null);
							if (mCursor6.moveToFirst()) {
								do {
									date_time_for_delete = mCursor6
											.getString(0);
								mydatabase
										.execSQL("delete from cab_rating where date_time='"
												+ date_time_for_delete
												+ "' ");
									break;
								} while (mCursor6.moveToNext());
							}

							saveCab_Details(mydatabase, vehicle_id,
									String.valueOf(drivervalue.getText()),
									String.valueOf(vehiclevalue.getText()),
									String.valueOf(farevalue.getText()), today,rating_int);
						}
						if (flag_today_msg)
							Toast.makeText(
									getApplicationContext(),
									"Exceeded Rating Limitations,You Can Try After 24 Hours.",
									Toast.LENGTH_LONG).show();
					}
					Toast.makeText(getApplicationContext(),"Network having some problem, We will take care of your details & insert once internet get connected", Toast.LENGTH_SHORT).show();
					/*Intent intent = new Intent(Home.this, Home.class);//adding for calling Home page
					startActivity(intent);*/
					rate_now.setVisibility(View.VISIBLE); // Hiding  You Rate now button
					get_rating.setVisibility(View.VISIBLE); // Hiding  get Rating button
					get_rating_result .setVisibility(View.GONE);
					rate_now_result.setVisibility(View.GONE);
					objk.testTimingTable("End Action - Save Rating",context);
			
					}
					else {
					        objk.testTimingTable("Start RestFul - Save Rating",context);
					    	submit_all_rating.setVisibility(View.GONE);
					    	System.out.println("I am in else----------------");
					    	saveRatingRestFulService(cab_number,drivervalue,vehiclevalue,farevalue);
					    	History obj = new History();
					    	obj.history_getRating(String.valueOf(cab_number.getText()),"","Rated",today,mydatabase);
					    	clearAllRating();
							
			    	        objk.testTimingTable("End RestFul - Save Rating",context);
					    	/*Intent intent = new Intent(Home.this, Home.class);//adding for calling Home page
							startActivity(intent);*/
					    	rate_now.setVisibility(View.VISIBLE); // Hiding  You Rate now button
							get_rating.setVisibility(View.VISIBLE); // Hiding  get Rating button
							get_rating_result .setVisibility(View.GONE);
							rate_now_result.setVisibility(View.GONE);
					 }
					  objk.testTimingTable("End Action - Save Rating",context);
				} else {
					boolean flag = true;
					if (String.valueOf(cab_number.getText()) == null
							|| String.valueOf(cab_number.getText()).equals("")) {
						flag = false;
						Toast.makeText(getApplicationContext(),
								"Please Enter the Cab Number..",
								Toast.LENGTH_SHORT).show();
					}

					if (iflt == 0 && flag) {
						flag = false;
						Toast.makeText(getApplicationContext(),
								"Please select Driver Rating..!!",
								Toast.LENGTH_SHORT).show();

					}
					if (jflt == 0 && flag) {
						flag = false;
						Toast.makeText(getApplicationContext(),
								"Please select Vechile Rating..!!",
								Toast.LENGTH_SHORT).show();
					}
					if (kflt == 0 && flag) {
						flag = false;
						Toast.makeText(getApplicationContext(),
								"Please select Fare Rating..!!",
								Toast.LENGTH_SHORT).show();
					}
					flag = true;
				}
			
			}
		    catch(Exception e)
		    {
		    	System.out.println(e);
		    	final String MyPREFERENCES = "MyPrefs";
		    	System.out.println("Context.MODE_PRIVATE is :  "+Context.MODE_PRIVATE);
			    SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
			    System.out.println("sharedpreferences is : "+sharedpreferences);
			    System.out.println("sharedpreferences_mobnumber is : "+sharedpreferences_mobnumber);
		    	String service_name =  "save_All_Cab_Rating";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(vehicle_no),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		    }
			}
			
		});

	}
	
	public synchronized void saveRatingRestFulService(EditText cab_number,EditText drivervalue,EditText vehiclevalue,EditText farevalue) throws IOException
	{
		try
		{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    String cab_numberValue = "";
	    if(cab_number.getText() == null)
	    {
	    	cab_numberValue = "";
	    }
	    else
	    {
	    	cab_numberValue = String.valueOf(cab_number.getText());
	    }
	    String drivervalue1 = "";
        if(drivervalue.getText() == null)
        {
        	drivervalue1 = "";
        }
        else
        {
        	drivervalue1 = String.valueOf(drivervalue.getText());
        }
	    
        String farevalue1 = "";
        if(farevalue.getText() == null)
        {
        	farevalue1 = "";
        }
        else
        {
        	farevalue1 = String.valueOf(farevalue.getText());
        }
        
        String vehiclevalue1 = "";
        if(vehiclevalue.getText() == null)
        {
        	vehiclevalue1 = "";
        }
        else
        {
        	vehiclevalue1 = String.valueOf(vehiclevalue.getText());
        }
        String mobileNo = sharedpreferences_mobnumber;
        JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject("{\"vehicleNo\":\""+cab_numberValue+"\",\"mobileNo\":\""+mobileNo+"\",\"driverRating\":\""+drivervalue1+"\",\"fareRating\":\""+farevalue1+"\",\"vehicleRating\":\""+vehiclevalue1+"\"}");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(jsonObject);
        //Toast.makeText(getApplicationContext(),"isNetworkAvailable "+jsonObject, Toast.LENGTH_SHORT).show();
	    String saveRatingURL = getResources().getString(R.string.saveRating_url);
        URL url = new URL(saveRatingURL);
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
        //System.out.println("connection.getInputStream() is : "+connection.getInputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        String line = "";
        while ((line = in.readLine()) != null) {
        line = in.readLine();
        }
        System.out.println("!!!!-"+line+"\n");
        Toast.makeText(getApplicationContext(),line, Toast.LENGTH_SHORT).show();
		//System.out.println("\nREST Service Invoked Successfully..");
        in.close();
        }
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "saveRatingRestFulService";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}

	public synchronized void saveCab_Details(SQLiteDatabase mydatabase, int vehicle_id,String iValue, String jValue, String kValue, String today,int rating_int) {
		try
		{
		String cab_details_sql = "insert into cab_rating values('"+rating_int+"','"
				+ vehicle_id + "','" + iValue + "','" + jValue + "','" + kValue
				+ "','','" + today + "')";
		mydatabase.execSQL(cab_details_sql);
		System.out.println("cab_details insserted Successfully.......");
		Toast.makeText(getApplicationContext(),	"Thank you for using this Application...!!", Toast.LENGTH_SHORT).show();
		clearAllRating();		
		}
		catch(Exception e)
		{
		    	System.out.println(e);
		    	String service_name =  "saveCab_Details";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}
	
	
	public void clear_All_Rating() {
		try
		{
		clear_rating.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearAllRating();
			}
		});
		}
		catch(Exception e)
		{
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "clear_All_Rating";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}
	
	

	 public void get_Rating_Details(){
		 
		 		final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
		        try
		        {
		        History objk = new History();
		        objk.testTimingTable("Start Action - get Rating",context);
		        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    	StrictMode.setThreadPolicy(policy);
				boolean resultFlag = true;
				System.out.println("query0");
				ResultSet rs = null;
				Statement stmt = null;
				Connection con = null;
				int vehicle_id = 0;
				if (String.valueOf(cab_number.getText()) != null && !String.valueOf(cab_number.getText()).equals("")) {


					hideSoftKey(cab_number);
	                boolean isNetworkAvailable = false;
					
					isNetworkAvailable =  isNetworkAvailable(context);
					if(isNetworkAvailable)
					{
						objk.testTimingTable("Start RestFul - get Rating",context);
						String urlValue =  getResources().getString(R.string.getRating_url);
						String urlValue1 = urlValue + String.valueOf(cab_number.getText()) + "," + sharedpreferences_mobnumber;
						URL url = new URL(urlValue1);
				        //System.out.println("url is : "+url);
				        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				        System.out.println("connection is --------- : "+connection);
				        connection.setDoOutput(false);
				        connection.setConnectTimeout(5000);
				        connection.setReadTimeout(5000);
				        
				        //OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				        //System.out.println("out is : "+out);
				        //String outputText = cab_number.getText() + "," + mobileNo;
				        //out.write(URLEncoder.encode(outputText));
				        //out.close();
				        
				        //System.out.println("connection.getOutputStream() is --------- : "+connection.getOutputStream());
				        
				        System.out.println("connection.getURL() is : "+connection.getURL());
				        System.out.println("connection error code is : "+connection.getResponseCode());
				        //System.out.println("I am in close");
				        
				        System.out.println("connection.getInputStream() is : "+connection.getInputStream());
				        System.out.println("connection erro stream is "+connection.getInputStream());
				        BufferedReader in = new BufferedReader(new InputStreamReader(
				                connection.getInputStream()));
	                    
				        String line;
				        String XMLData = "";
				        while ((line = in.readLine()) != null) {
				        	XMLData = line;
				        }
				        
				        System.out.println("XMLData is : "+XMLData);
				        
	                    int green_count_rating_driver = 0;
	                    int yellow_count_rating_driver = 0;
	                    int red_count_rating_driver = 0;
	                    int green_count_rating_vehicle = 0;
	                    int yellow_count_rating_vehicle = 0;
	                    int red_count_rating_vehicle = 0;
	                    int green_count_rating_fare = 0;
	                    int yellow_count_rating_fare = 0;
	                    int red_count_rating_fare = 0;
	                    int total_count_rating_driver = 0;
	                    int total_count_rating_vehicle = 0;
	                    int total_count_rating_fare = 0;
	                    String viewRatingText = "";
				        Document doc = convertStringToDocument(XMLData,context);
				        //System.out.println("doc is : "+doc);
				        //System.out.println("1 is : "+doc.getElementsByTagName("driverRatingGreenCnt"));
				        //System.out.println("2 is : "+doc.getElementsByTagName("driverRatingGreenCnt").item(0).getFirstChild());
				        String driverRatingGreenCnt =  doc.getElementsByTagName("driverRatingGreenCnt").item(0).getFirstChild().getNodeValue();
				        //System.out.println("driverRatingGreenCnt is : "+driverRatingGreenCnt);
				        if(driverRatingGreenCnt != null && !driverRatingGreenCnt.equals(""))
				        {
				        	green_count_rating_driver = Integer.parseInt(driverRatingGreenCnt);
				        }
				        //System.out.println("driverRatingGreenCnt is : "+driverRatingGreenCnt);
				       
				        String driverRatingYellowCnt = doc.getElementsByTagName("driverRatingYellowCnt").item(0).getFirstChild().getNodeValue();
				        if(driverRatingYellowCnt != null && !driverRatingYellowCnt.equals(""))
				        {
				        	yellow_count_rating_driver = Integer.parseInt(driverRatingYellowCnt);
				        }
				        String driverRatingRedCnt = doc.getElementsByTagName("driverRatingRedCnt").item(0).getFirstChild().getNodeValue();
				        if(driverRatingRedCnt != null && !driverRatingRedCnt.equals(""))
				        {
				        	red_count_rating_driver = Integer.parseInt(driverRatingRedCnt);
				        }
				        
				        total_count_rating_driver = green_count_rating_driver + yellow_count_rating_driver + red_count_rating_driver;
				        		
				        String vehicleRatingGreenCnt = doc.getElementsByTagName("vehicleRatingGreenCnt").item(0).getFirstChild().getNodeValue();
				        if(vehicleRatingGreenCnt != null && !vehicleRatingGreenCnt.equals(""))
				        {
				        	green_count_rating_vehicle = Integer.parseInt(vehicleRatingGreenCnt);
				        }
				        String vehicleRatingYellowCnt = doc.getElementsByTagName("vehicleRatingYellowCnt").item(0).getFirstChild().getNodeValue();
				        if(vehicleRatingYellowCnt != null && !vehicleRatingYellowCnt.equals(""))
				        {
				        	yellow_count_rating_vehicle = Integer.parseInt(vehicleRatingYellowCnt);
				        }
				        String vehicleRatingRedCnt = doc.getElementsByTagName("vehicleRatingRedCnt").item(0).getFirstChild().getNodeValue();
				        if(vehicleRatingRedCnt != null && !vehicleRatingRedCnt.equals(""))
				        {
				        	red_count_rating_vehicle = Integer.parseInt(vehicleRatingRedCnt);
				        }
				        
				        total_count_rating_vehicle = green_count_rating_vehicle + yellow_count_rating_vehicle + red_count_rating_vehicle;
				        		
				        String fareRatingGreenCnt = doc.getElementsByTagName("fareRatingGreenCnt").item(0).getFirstChild().getNodeValue();
				        if(fareRatingGreenCnt != null && !fareRatingGreenCnt.equals(""))
				        {
				        	green_count_rating_fare = Integer.parseInt(fareRatingGreenCnt);
				        }
				        String fareRatingYellowCnt = doc.getElementsByTagName("fareRatingYellowCnt").item(0).getFirstChild().getNodeValue();
				        if(fareRatingYellowCnt != null && !fareRatingYellowCnt.equals(""))
				        {
				        	yellow_count_rating_fare = Integer.parseInt(fareRatingYellowCnt);
				        }
				        String fareRatingRedCnt = doc.getElementsByTagName("fareRatingRedCnt").item(0).getFirstChild().getNodeValue();
				        if(fareRatingRedCnt != null && !fareRatingRedCnt.equals(""))
				        {
				        	red_count_rating_fare = Integer.parseInt(fareRatingRedCnt);
				        }
				        total_count_rating_fare = green_count_rating_fare + yellow_count_rating_fare + red_count_rating_fare;
				        
				        
				    	if (red_count_rating_fare >= green_count_rating_fare
								&& red_count_rating_fare >= yellow_count_rating_fare) {
							//getRating_fare.setBackgroundResource(R.drawable.brfare);
							getRating_fare.getBackground().setColorFilter(Color.parseColor("#FF2C2C"), Mode.MULTIPLY);
							   viewRatingText = red_count_rating_fare + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_fare;
							getRating_fare.setText(viewRatingText);
							if (red_count_rating_fare == green_count_rating_fare) {
								//getRating_fare.setBackgroundResource(R.drawable.byfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
							    viewRatingText = yellow_count_rating_fare + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
							if (red_count_rating_fare == yellow_count_rating_fare) {
								//getRating_fare.setBackgroundResource(R.drawable.brfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#FF2C2C"), Mode.MULTIPLY);
								viewRatingText = red_count_rating_fare + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
						
							if((red_count_rating_fare == green_count_rating_fare)&& (red_count_rating_fare == yellow_count_rating_fare ))
							{
								//getRating_fare.setBackgroundResource(R.drawable.byfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_fare + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
							
							
						} else if (green_count_rating_fare >= red_count_rating_fare
								&& green_count_rating_fare >= yellow_count_rating_fare) {
								//getRating_fare.setBackgroundResource(R.drawable.bgfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
								viewRatingText = green_count_rating_fare + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							if (green_count_rating_fare == red_count_rating_fare) {
								//getRating_fare.setBackgroundResource(R.drawable.byfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_fare + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
							if (green_count_rating_fare == yellow_count_rating_fare) {
								//getRating_fare.setBackgroundResource(R.drawable.bgfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
								viewRatingText = green_count_rating_fare + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
							
							if((green_count_rating_fare == red_count_rating_fare)&& (green_count_rating_fare == yellow_count_rating_fare ))
							{
								//getRating_fare.setBackgroundResource(R.drawable.byfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_fare + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
							} else if (yellow_count_rating_fare >= red_count_rating_fare
								&& yellow_count_rating_fare >= green_count_rating_fare) {
								//getRating_fare.setBackgroundResource(R.drawable.byfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_fare + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							
							if (yellow_count_rating_fare == red_count_rating_fare) {
								//getRating_fare.setBackgroundResource(R.drawable.brfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#FF2C2C"), Mode.MULTIPLY);
								viewRatingText = red_count_rating_fare + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
							if (yellow_count_rating_fare == green_count_rating_fare) {
								//getRating_fare.setBackgroundResource(R.drawable.bgfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
								viewRatingText = green_count_rating_fare + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
							
							if((yellow_count_rating_fare == red_count_rating_fare)&& (yellow_count_rating_fare == green_count_rating_fare ))
							{
								//getRating_fare.setBackgroundResource(R.drawable.byfare);
								getRating_fare.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_fare + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_fare;
								getRating_fare.setText(viewRatingText);
							}
						}

				    		if (red_count_rating_vehicle >= green_count_rating_vehicle
								&& red_count_rating_vehicle >= yellow_count_rating_vehicle) {
								//getRating_vehicle.setBackgroundResource(R.drawable.brvehicle);
				    			getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FF2C2C"), Mode.MULTIPLY);
				    			viewRatingText = red_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							if (red_count_rating_vehicle == green_count_rating_vehicle) {
								//getRating_vehicle.setBackgroundResource(R.drawable.byvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
							if (red_count_rating_vehicle == yellow_count_rating_vehicle) {
								//getRating_vehicle.setBackgroundResource(R.drawable.brvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FF2C2C"), Mode.MULTIPLY);
								viewRatingText = red_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
							
							if((red_count_rating_vehicle == green_count_rating_vehicle)&& (red_count_rating_vehicle == yellow_count_rating_vehicle ))
							{
								//getRating_vehicle.setBackgroundResource(R.drawable.byvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
							 
							} else if (green_count_rating_vehicle >= red_count_rating_vehicle
								&& green_count_rating_vehicle >= yellow_count_rating_vehicle) {
								//getRating_vehicle.setBackgroundResource(R.drawable.bgvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
								viewRatingText = green_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
								
							if (green_count_rating_vehicle == red_count_rating_vehicle) {
								//getRating_vehicle.setBackgroundResource(R.drawable.byvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
							if (green_count_rating_vehicle == yellow_count_rating_vehicle) {
								//getRating_vehicle.setBackgroundResource(R.drawable.bgvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
								viewRatingText = green_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
							if((green_count_rating_vehicle == red_count_rating_vehicle)&& (green_count_rating_vehicle == yellow_count_rating_vehicle ))
							{
								//getRating_vehicle.setBackgroundResource(R.drawable.byvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
						} else if (yellow_count_rating_vehicle >= red_count_rating_vehicle
								&& yellow_count_rating_vehicle >= green_count_rating_vehicle) {
							//getRating_vehicle.setBackgroundResource(R.drawable.byvehicle);
							getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
							viewRatingText = yellow_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_vehicle;
			    			getRating_vehicle.setText(viewRatingText);
							
							if (yellow_count_rating_vehicle == red_count_rating_vehicle) {
								//getRating_vehicle.setBackgroundResource(R.drawable.brvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = red_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
							if (yellow_count_rating_vehicle == green_count_rating_vehicle) {
								//getRating_vehicle.setBackgroundResource(R.drawable.bgvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
								viewRatingText = green_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
							if((yellow_count_rating_vehicle == red_count_rating_vehicle)&& (yellow_count_rating_vehicle == green_count_rating_vehicle ))
							{
								//getRating_vehicle.setBackgroundResource(R.drawable.byvehicle);
								getRating_vehicle.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
								viewRatingText = yellow_count_rating_vehicle + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_vehicle;
				    			getRating_vehicle.setText(viewRatingText);
							}
						}

				    	
				    	if (red_count_rating_driver >= green_count_rating_driver && red_count_rating_driver >= yellow_count_rating_driver) {
				    		//getRating_driver.setBackgroundResource(R.drawable.brdriver);
				    		getRating_driver.getBackground().setColorFilter(Color.parseColor("#FF2C2C"), Mode.MULTIPLY);
				    		viewRatingText = red_count_rating_driver + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
				    	
				        if (red_count_rating_driver == green_count_rating_driver) {
				        	//getRating_driver.setBackgroundResource(R.drawable.bydriver);
				        	getRating_driver.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
				        	viewRatingText = yellow_count_rating_driver + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
				    	}
				    	if (red_count_rating_driver == yellow_count_rating_driver) {
				    		//getRating_driver.setBackgroundResource(R.drawable.brdriver);
				    		getRating_driver.getBackground().setColorFilter(Color.parseColor("#FF2C2C"), Mode.MULTIPLY);
				    		viewRatingText = red_count_rating_driver + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
				    	}
	                    if((red_count_rating_driver == green_count_rating_driver)&& (red_count_rating_driver == yellow_count_rating_driver ))
						{
	                    	//getRating_driver.setBackgroundResource(R.drawable.bydriver);
	                    	getRating_driver.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
	                    	viewRatingText = yellow_count_rating_driver + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
						}
						} else if (green_count_rating_driver >= red_count_rating_driver
							&& green_count_rating_driver >= yellow_count_rating_driver) {
							//getRating_driver.setBackgroundResource(R.drawable.bgdriver);
							getRating_driver.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
							viewRatingText = green_count_rating_driver + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
							
						if (green_count_rating_driver == red_count_rating_driver) {
							//getRating_driver.setBackgroundResource(R.drawable.bydriver);
							getRating_driver.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
							viewRatingText = yellow_count_rating_driver + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
						}
						if (green_count_rating_driver == yellow_count_rating_driver) {
							//getRating_driver.setBackgroundResource(R.drawable.bgdriver);
							getRating_driver.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
							viewRatingText = green_count_rating_driver + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
						}
						if((green_count_rating_driver == red_count_rating_driver)&& (green_count_rating_driver == yellow_count_rating_driver ))
						{
							//getRating_driver.setBackgroundResource(R.drawable.bydriver);
							getRating_driver.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
							viewRatingText = yellow_count_rating_driver + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
						}
						} else if (yellow_count_rating_driver >= red_count_rating_driver
							&& yellow_count_rating_driver >= green_count_rating_driver) {
							//getRating_driver.setBackgroundResource(R.drawable.bydriver);
							getRating_driver.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
							viewRatingText = yellow_count_rating_driver + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
						if (yellow_count_rating_driver == red_count_rating_driver) {
							//getRating_driver.setBackgroundResource(R.drawable.brdriver);
							getRating_driver.getBackground().setColorFilter(Color.parseColor("#FF2C2C"), Mode.MULTIPLY);
							viewRatingText = red_count_rating_driver + " " + "Rated" + " "  + "\n" + "Bad" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
						}
						if (yellow_count_rating_driver == green_count_rating_driver) {
							//getRating_driver.setBackgroundResource(R.drawable.bgdriver);
							getRating_driver.getBackground().setColorFilter(Color.parseColor("#92D050"), Mode.MULTIPLY);
							viewRatingText = green_count_rating_driver + " " + "Rated" + " "  + "\n" + "Good" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
						}
						if((yellow_count_rating_driver == red_count_rating_driver)&& (yellow_count_rating_driver == green_count_rating_driver ))
						{
							//getRating_driver.setBackgroundResource(R.drawable.bydriver);
							getRating_driver.getBackground().setColorFilter(Color.parseColor("#FFC000"), Mode.MULTIPLY);
							viewRatingText = yellow_count_rating_driver + " " + "Rated" + " "  + "\n" + "Average" + " on " + total_count_rating_driver;
				    		getRating_driver.setText(viewRatingText);
						}
					 }
				    	History obj = new History();
				    	String today = "";
						SQLiteDatabase mydatabase = createMobileDataBase(context);
				    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = new Date();
						today = dateFormat.format(date);
				    	obj.history_getRating(String.valueOf(cab_number.getText()),"","Checked",today,mydatabase);
				    	objk.testTimingTable("End RestFul - get Rating",context);
					} 
					else {
						Toast.makeText(
								getApplicationContext(),
								"Please Check Your Network Connection...",
								Toast.LENGTH_LONG).show();
					}
						
				}	
			   else {

					Toast.makeText(getApplicationContext(),
							"Please enter the cab number...!!",
							Toast.LENGTH_SHORT).show();
				}
				objk.testTimingTable("End Action - get Rating",context);
		        }
		        catch(Exception e)
				{
					    SQLiteDatabase mydatabase = createMobileDataBase(context);
				    	System.out.println(e);
				    	String service_name =  "get_Rating_Details";
				        History obj = new History();
				        obj.errorInsert(String.valueOf(cab_number.getText()),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
				}	
		        }


  public void serch_Again() {
	  try
	  {
		Button searchagain;
		searchagain = (Button) findViewById(R.id.search_again);
		searchagain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
				Home.this.finish();
				Intent intent = new Intent(Home.this, Home.class);
				startActivity(intent);
				//cab_number.setText("");				
				/*getRating_driver.setBackgroundResource(R.drawable.bdriver);
				getRating_vehicle.setBackgroundResource(R.drawable.bvehicle);
				getRating_fare.setBackgroundResource(R.drawable.bfare);*/
				cab_number.requestFocus();
			}
		});
	  }
	  catch(Exception e)
	  {
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "serch_Again";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
	 }	
	}

	public void hired_for_notification() {
		try
		{
		Button hirednotification;
		hirednotification = (Button) findViewById(R.id.hired);
		hirednotification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
				if (cab_number != null
						&& !String.valueOf(cab_number.getText()).equals("")) {
					if (notificationID < 5) {
						displayNotification();
						
					} else {
						Toast.makeText(getApplicationContext(),
								"Sorry..,You can't Hired...!!",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Please Enter the Taxi number.", Toast.LENGTH_LONG)
							.show();

				}
			}
		});
		}
		catch(Exception e)
		  {
				    SQLiteDatabase mydatabase = createMobileDataBase(context);
			    	System.out.println(e);
			    	String service_name =  "hired_for_notification";
			        History obj = new History();
			        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		 }

	}

	protected void displayNotification() {
		try
		{
	
	    final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setAutoCancel(true);
		mBuilder.setContentTitle("You Hired " + cab_number.getText());
		mBuilder.setContentText("Please rate this cab.");
		mBuilder.setTicker("You've received CityCab Message.!");
		mBuilder.setSmallIcon(R.drawable.ic_launcher);	
		mBuilder.setNumber(++numMessages);	
		final String MyPREFERENCES = "MyPrefs";
 		SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
 		
		Editor edit = sharedpreferences.edit();
		edit.putString(sharedpreferences_cabnumber,String.valueOf(cab_number.getText()));
		edit.commit();
		
		Intent resultIntent = new Intent(this, Home.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(Home.class);	 
		stackBuilder.addNextIntent(resultIntent);		
		 PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
		if (notificationID < 5) {
			notificationID = notificationID + 1;
		}
		if (notificationID < 6) {
			mBuilder.setContentIntent(resultPendingIntent);			
			mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);	
			mNotificationManager.notify(notificationID, mBuilder.build());

		}	
		}
		catch(Exception e)
		  {
				    SQLiteDatabase mydatabase = createMobileDataBase(context);
			    	System.out.println(e);
			    	String service_name =  "displayNotification";
			        History obj = new History();
			        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		 }

	}
	public void clearAllRating() {
		try
		{
		submit_all_rating.setVisibility(View.VISIBLE);
		whitedriver.setBackgroundResource(R.drawable.driver);
		whitevehicle.setBackgroundResource(R.drawable.vehicle);
		whitefare.setBackgroundResource(R.drawable.fare);
		submit_all_rating.setEnabled(false);
		clear_rating.setEnabled(false);		
		
		reddrive.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
		yellowdrive.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
		greendrive.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);

		/*reddrive.setBackgroundColor(Color.parseColor("#80d3d3d3"));
		yellowdrive.setBackgroundColor(Color.parseColor("#80d3d3d3"));
		greendrive.setBackgroundColor(Color.parseColor("#80d3d3d3"));*/
		
		redvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
		yellowvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
		greenvehicle.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
		
		/*redvehicle.setBackgroundColor(Color.parseColor("#80d3d3d3"));
		yellowvehicle.setBackgroundColor(Color.parseColor("#80d3d3d3"));
		greenvehicle.setBackgroundColor(Color.parseColor("#80d3d3d3"));*/
		
		redfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
		yellowfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
		greenfare.getBackground().setColorFilter(Color.parseColor("#ff000000"), Mode.SRC_ATOP);
		

		/*redfare.setBackgroundColor(Color.parseColor("#80d3d3d3"));
		yellowfare.setBackgroundColor(Color.parseColor("#80d3d3d3"));
		greenfare.setBackgroundColor(Color.parseColor("#80d3d3d3"));*/

		final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
		cab_number.setText("");
		}
		catch(Exception e)
		  {
				    SQLiteDatabase mydatabase = createMobileDataBase(context);
			    	System.out.println(e);
			    	String service_name =  "clearAllRating";
			        History obj = new History();
			        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		 }
	}
     public void emptyCreateTable(SQLiteDatabase mydatabase) {
    	 try
    	 {
		System.out.println("I am in emptyCreate Table");
		
		//String DELETE_CONTACTS_TABLE = "drop table cab_information";
		//mydatabase.execSQL(DELETE_CONTACTS_TABLE);
		
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS cab_information(vehicle_id INTEGER PRIMARY KEY AUTOINCREMENT,vehicle_no VARCHAR(20),date_time DATETIME DEFAULT CURRENT_TIMESTAMP,UNIQUE (vehicle_no))";
		mydatabase.execSQL(CREATE_CONTACTS_TABLE);
		Log.d("neeraj", "CREATE TABLE IF NOT EXISTS cab_information");
		//String DELETE_CONTACTS_TABLE1 = "drop table cab_rating";
		//mydatabase.execSQL(DELETE_CONTACTS_TABLE1);
		
		String CREATE_CONTACTS_TABLE1 = "CREATE TABLE IF NOT EXISTS cab_rating(rating_id INTEGER PRIMARY KEY AUTOINCREMENT,vehicle_id Integer,rating_driver Integer,rating_vehicle Integer,rating_fare Integer,mobile_no VARCHAR(20),date_time DATETIME DEFAULT CURRENT_TIMESTAMP)";
		mydatabase.execSQL(CREATE_CONTACTS_TABLE1);
		
		String ERROR_LOG_TABLE = "CREATE TABLE IF NOT EXISTS error_log(id INTEGER PRIMARY KEY AUTOINCREMENT,error_type VARCHAR(100),error_message VARCHAR(30000),mobile_no VARCHAR(20),vehicle_no VARCHAR(45),date_time DATETIME DEFAULT CURRENT_TIMESTAMP,file_name VARCHAR(100),line_number int(11),error_name VARCHAR(100),android_version VARCHAR(100),mobile_model VARCHAR(100),service_name VARCHAR(100))";
		mydatabase.execSQL(ERROR_LOG_TABLE);
		

		String HISTORY_GETRATING_TABLE = "CREATE TABLE IF NOT EXISTS cab_history(mobile_no VARCHAR(20),id INTEGER PRIMARY KEY AUTOINCREMENT,vehicle_no varchar(50),date_time DATETIME DEFAULT CURRENT_TIMESTAMP,activity VARCHAR(10))";
		mydatabase.execSQL(HISTORY_GETRATING_TABLE);
		
    	 }
    	 catch(Exception e)
		  {
			    	System.out.println(e);
			    	String service_name =  "emptyCreateTable";
			        History obj = new History();
			        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		 }
	}
    public void ACTION_SEARCH_BUTTON(){
    	try
    	{
    	 final EditText cab_number = (EditText) findViewById(R.id.taxi_number);
    	 cab_number.setOnEditorActionListener(new OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		        boolean handled = false;
		       // if ((actionId == EditorInfo.IME_ACTION_SEARCH)&& (SearchEditText.getText() != null && !String.valueOf(SearchEditText.getText()).equals(""))) {	
		      if(actionId == EditorInfo.IME_ACTION_SEARCH){	
		       // layout_get_rating.setVisibility(View.VISIBLE);
		    	   if((cab_number.getText() != null && !String.valueOf(cab_number.getText()).equals(""))){
		    		     hideSoftKey(cab_number);
		    		     layout_get_rating.setVisibility(View.VISIBLE);
		    	    	 get_Rating_Details();
		    	    	 layout_ratenow.setVisibility(View.GONE);
		    	     }
		    	   else{
		    		   hideSoftKey(cab_number);
		    		   Toast.makeText(getApplicationContext(),"Please Enter the Taxi number.", Toast.LENGTH_SHORT).show();
		    	   }
		        	 handled = true;
		        }
		         return handled;
		         
		    }
		});
    	}
    	catch(Exception e)
		  {
				    SQLiteDatabase mydatabase = createMobileDataBase(context);
			    	System.out.println(e);
			    	String service_name =  "ACTION_SEARCH_BUTTON";
			        History obj = new History();
			        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		 }
     }     
    

 	public boolean isNetworkAvailable(Context context) {
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
				    SQLiteDatabase mydatabase = createMobileDataBase(context);
			    	System.out.println(e);
			    	String service_name =  "isNetworkAvailable";
			        History obj = new History();
			        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		 }
 		return false;
     }
 	
 	 public Document convertStringToDocument(String xmlStr,Context context) {
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
         DocumentBuilder builder;  
         try 
         {  
        	 if(xmlStr != null && !xmlStr.equals(""))
        	 {
             builder = factory.newDocumentBuilder();  
             Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
             return doc;
        	 }
         } catch(Exception e)
		  {
			    SQLiteDatabase mydatabase = createMobileDataBase(context);
		    	System.out.println(e);
		    	String service_name =  "convertStringToDocument";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
	      }
         return null;
     }
 	 

 	 public SQLiteDatabase createMobileDataBase(Context context)
 	 {
 		System.out.println("createMobileDataBase ----------- ");
 		System.out.println("mobileDatabaseName 1----------- "+context);
 		
 		System.out.println("mobileDatabaseName 2----------- "+context.getResources().getString(R.string.mobileDatabaseName));
 		SQLiteDatabase mydatabase = context.openOrCreateDatabase(context.getResources().getString(R.string.mobileDatabaseName),MODE_PRIVATE, null);
 		System.out.println("createMobileDataBase ------1----- ");
 		return mydatabase;
 	 }
 	 
 	
}