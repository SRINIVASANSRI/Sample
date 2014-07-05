package com.example.taxiapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;


/*added For History ...*/
 public class History extends Activity {
	

		String sharedpreferences_mobModel = "", sharedpreferences_andVersion = "";
		Editor editor;
	    String sharedpreferences_mobnumber = "";
	    Context context;
	 
	private TableLayout addRowToTable(final TableLayout table, String contentCol1, String contentCol2,String contentCol3, Context context,String contentCol4,final int j) {
		   try
		   {
		   final TableRow row = new TableRow(context);
		   //System.out.println("Add Row table -- First");
		   System.out.println("row is "+row);
		   row.setOnTouchListener(new View.OnTouchListener() {
			   
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				final EditText hoverselect = (EditText) findViewById(R.id.hoverselect);
				 System.out.println("hoverselect is "+hoverselect);
				//System.out.println("Add Row table -- hoverselect "+hoverselect);				
				//System.out.println("hoverselect.getText() is before inside "+hoverselect.getText());
				
				if(hoverselect.getText() != null && !String.valueOf(hoverselect.getText()).equals(""))
				{
					final TableRow hoverselect1 = (TableRow) findViewById(Integer.parseInt(String.valueOf(hoverselect.getText())));
					
					//System.out.println("Add Row table if inside-- hoverselect "+hoverselect.getText());
					hoverselect1.setBackgroundColor(Color.parseColor("#FFFFFF"));
					hoverselect1.getChildAt(0).setBackgroundColor(Color.parseColor("#FFFFFF"));;
					hoverselect1.getChildAt(1).setBackgroundColor(Color.parseColor("#FFFFFF"));;
					hoverselect1.getChildAt(2).setBackgroundColor(Color.parseColor("#FFFFFF"));;
					hoverselect1.getChildAt(3).setBackgroundColor(Color.parseColor("#FFFFFF"));;
					
				}
				
				//System.out.println("row.getId() is : "+row.getId());
				hoverselect.setText(String.valueOf(row.getId()));
				//System.out.println("Clicked -----------------------");
				row.setBackgroundColor(Color.parseColor("#C0C0C0"));
				
				row.getChildAt(0).setBackgroundColor(Color.parseColor("#C0C0C0"));;
				row.getChildAt(1).setBackgroundColor(Color.parseColor("#C0C0C0"));;
				row.getChildAt(2).setBackgroundColor(Color.parseColor("#C0C0C0"));;
				row.getChildAt(3).setBackgroundColor(Color.parseColor("#C0C0C0"));;
				return false;
			}
		    });
		  
		   TableRow.LayoutParams rowParams = new TableRow.LayoutParams();
		   // Wrap-up the content of the row
		   rowParams.height = LayoutParams.WRAP_CONTENT;
		   rowParams.width = LayoutParams.WRAP_CONTENT;
		 
		   // The simplified version of the table of the picture above will have two columns
		   // FIRST COLUMN
		   
		   TableRow.LayoutParams col4Params = new TableRow.LayoutParams();
		   

		   final ImageView iv = new ImageView(this);
	       iv.setImageResource(R.drawable.historycar);
	       col4Params.height = LayoutParams.WRAP_CONTENT;
		   col4Params.width = LayoutParams.WRAP_CONTENT;
		   // Set the gravity to center the gravity of the column
		   col4Params.gravity = Gravity.LEFT;
	       
	       //if(contentCol4 != null && !contentCol4.equals("data"))
	       //{
	       row.addView(iv, col4Params);
	       //}
	       
		   TableRow.LayoutParams col1Params = new TableRow.LayoutParams();
		   // Wrap-up the content of the row
		   col1Params.height = LayoutParams.WRAP_CONTENT;
		   col1Params.width = LayoutParams.WRAP_CONTENT;
		   // Set the gravity to center the gravity of the column
		   col1Params.gravity = Gravity.CENTER;
	        
		   TextView col1 = new TextView(context);
		   col1.setText(contentCol1);
		   if(contentCol4 != null && !contentCol4.equals("data"))
		   col1.setTypeface(null, Typeface.BOLD);
		   col1.setTextColor(Color.parseColor("#000000"));
		   col1.setBackgroundColor(Color.parseColor("#FFFFFF"));
		   
		   row.addView(col1, col1Params);
		    
		   // SECOND COLUMN
		   TableRow.LayoutParams col2Params = new TableRow.LayoutParams();
		   // Wrap-up the content of the row
		   col2Params.height = LayoutParams.WRAP_CONTENT;
		   col2Params.width = LayoutParams.WRAP_CONTENT;
		   // Set the gravity to center the gravity of the column
		   col2Params.gravity = Gravity.CENTER;
		   TextView col2 = new TextView(context);
		   col2.setText(contentCol2);
		   col2.setTextColor(Color.parseColor("#000000"));
		   col2.setBackgroundColor(Color.parseColor("#FFFFFF"));
		   if(contentCol4 != null && !contentCol4.equals("data"))
		   col2.setTypeface(null, Typeface.BOLD);
		   row.addView(col2, col2Params);
		   
		   // SECOND COLUMN
		   TableRow.LayoutParams col3Params = new TableRow.LayoutParams();
		   // Wrap-up the content of the row
		   col3Params.height = LayoutParams.WRAP_CONTENT;
		   col3Params.width = LayoutParams.WRAP_CONTENT;
		   // Set the gravity to center the gravity of the column
		   col3Params.gravity = Gravity.CENTER;
		   TextView col3 = new TextView(context);
		   col3.setText(contentCol3);
		   col3.setTextColor(Color.parseColor("#000000"));
		   col3.setBackgroundColor(Color.parseColor("#FFFFFF"));
		   if(contentCol4 != null && !contentCol4.equals("data"))
		   col3.setTypeface(null, Typeface.BOLD);
		   row.setBackgroundColor(Color.parseColor("#FFFFFF"));
		   row.addView(col3, col3Params);
		   row.setId(j);
		   table.addView(row, rowParams);
		 
		   return table;
		   }
		   catch(Exception e)
			{
	                System.out.println(e);
	                Home obj = new Home();
	        		SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
			    	String service_name =  "addRowToTable";
			        errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
			}
		   return null;
		}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	try
    	{
        super.onCreate(savedInstanceState);      
        setContentView(R.layout.history);    
        context = getApplicationContext();
        final String MyPREFERENCES = "MyPrefs";
		SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		editor = sharedpreferences.edit();


        sharedpreferences_mobnumber = sharedpreferences.getString("mobile", "");
	    sharedpreferences_andVersion = sharedpreferences.getString("version_shared", "");
	    sharedpreferences_mobModel = sharedpreferences.getString("model_shared", "");
	    
		
        System.out.println("cabdata4 is : ");
        Home obj = new Home();
		SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
		errorInsert(String.valueOf(""),null,"",mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		String HISTORY_GETRATING_TABLE = "CREATE TABLE IF NOT EXISTS cab_history(mobile_no VARCHAR(20),id INTEGER PRIMARY KEY AUTOINCREMENT,vehicle_no varchar(50),date_time DATETIME DEFAULT CURRENT_TIMESTAMP,activity VARCHAR(10))";
		mydatabase.execSQL(HISTORY_GETRATING_TABLE);
		
		
        Context context = getApplicationContext();        
        TableLayout headerTable1 = (TableLayout) findViewById(R.id.content_table);
        addRowToTable(headerTable1, "Vehicle No", "Date Time" , "Activity" ,context,"header",0);
        
        TableLayout headerTable;
        String date_timearr[] = null;
        int i = 1;
        String date = "";
        String time = "";
        System.out.println("mydatabase is : "+mydatabase);
        String sql = "select vehicle_no,date_time,activity from cab_history";
        //where mobile_no=''
		Cursor mCursor = mydatabase.rawQuery(sql, null);
		System.out.println("mCursor count is : "+mCursor.getCount());
		if (mCursor.moveToFirst()) {
			do {
				i = 1;
				String vehicle_no = mCursor.getString(0);
				System.out.println("vehicle_no is : "+vehicle_no);
				String date_time = mCursor.getString(1);
				System.out.println("date_time is : "+date_time);
				date_timearr = date_time.split(" ",-1);
				date = date_timearr[0];
				System.out.println("date is "+date);
				SimpleDateFormat dateString1 = new SimpleDateFormat("yyyy-MM-dd");
				String dateString2 = "";
				try {
					dateString2 = new SimpleDateFormat("dd-MM-yyyy").format(dateString1.parse(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				time = date_timearr[1];
				System.out.println("time is "+time);
				date_time = dateString2 + " " + time;
				System.out.println("date_time is "+date_time);
				String activity = mCursor.getString(2);
				System.out.println("activity is "+activity);
				headerTable = (TableLayout) findViewById(R.id.content_table);
				System.out.println("headerTable is "+headerTable);
		        headerTable = addRowToTable(headerTable,vehicle_no,date_time,activity,context,"data",i);
		        i++;
			} while (mCursor.moveToNext());
		}
               
    	}
    	catch(Exception e)
		{
                System.out.println(e);
                Home obj = new Home();
        		SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
		    	String service_name =  "onCreate";
		        errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
  }
	public void history_getRating(String vehicle_no,String mobile_no,String activity,String datetime,SQLiteDatabase mydatabase){
		try
		{
		System.out.println(" save_All_Cab_Rating today6 is :");
		//System.out.println(" save_All_Cab_Rating today7 is :");
		
		//String DELETE_CONTACTS_TABLE1 = "drop table cab_history";
		//mydatabase.execSQL(DELETE_CONTACTS_TABLE1);
		int history_int = 0;
		String HISTORY_GETRATING_TABLE = "CREATE TABLE IF NOT EXISTS cab_history(mobile_no VARCHAR(20),id INTEGER PRIMARY KEY AUTOINCREMENT,vehicle_no varchar(50),date_time DATETIME DEFAULT CURRENT_TIMESTAMP,activity VARCHAR(10))";
		mydatabase.execSQL(HISTORY_GETRATING_TABLE);

		int id_int  = 0;
	    String sql1 = "select max(id) from cab_history";
		Cursor mCursor1 = mydatabase.rawQuery(sql1, null);
		System.out.println("mCursor Count is : "+mCursor1.getCount());
		if (mCursor1.moveToFirst()) {
			do {
				history_int = mCursor1.getInt(0);
				if(history_int > 50)
				{
					    String sql3 = "select id from cab_history";
						Cursor mCursor2 = mydatabase.rawQuery(sql1, null);
						//System.out.println("mCursor Count is : "+mCursor2.getCount());
						if (mCursor2.moveToFirst()) {
							do {
								id_int = mCursor2.getInt(0);
								break;
							} while (mCursor2.moveToNext());
								
					String sql2 = "delete from cab_history where id = '"+id_int+"' ";
					mydatabase.execSQL(sql2);
				}
			}
		} while (mCursor1.moveToNext());
		
		history_int = history_int + 1;
		String HISTORY_INSERT_TABLE = "INSERT INTO cab_history values('"+mobile_no+"','"+history_int+"','"+vehicle_no+"','"+datetime+"','"+activity+"')";
		mydatabase.execSQL(HISTORY_INSERT_TABLE);
		System.out.println(" cab_history inserted successfully.....");
		}
		}
		catch(Exception e)
		{
                System.out.println(e);
		    	String service_name =  "history_getRating";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(vehicle_no),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}
	

     
	public void errorInsert(String vehicle_no,Exception e,String service_name,SQLiteDatabase mydatabase,String mobile_no,String mobile_model,String android_version){
		int error_int = 0;
		String today = "";
		if(e != null && !e.equals(""))
		{
			Home obj = new Home();
            String error_message = e.getMessage();
	        String error_name = e.getClass().getName();
	        String error_type = "mobile_error";
	        String file_name = e.getStackTrace()[0].getFileName();
	        int line_number = e.getStackTrace()[0].getLineNumber();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		today = dateFormat.format(date);
		
		String HISTORY_GETRATING_TABLE = "CREATE TABLE IF NOT EXISTS error_log(id INTEGER PRIMARY KEY AUTOINCREMENT,error_type VARCHAR(100),error_message VARCHAR(30000),mobile_no VARCHAR(20),vehicle_no VARCHAR(45),date_time DATETIME DEFAULT CURRENT_TIMESTAMP,file_name VARCHAR(100),line_number int(11),error_name VARCHAR(100),android_version VARCHAR(100),mobile_model VARCHAR(100),service_name VARCHAR(100))";
		mydatabase.execSQL(HISTORY_GETRATING_TABLE);
		
		String sql1 = "select max(id) from error_log";
		Cursor mCursor1 = mydatabase.rawQuery(sql1, null);
		if (mCursor1.moveToFirst()) {
			do {
				error_int = mCursor1.getInt(0);
		} while (mCursor1.moveToNext());
		}
		
		error_int = error_int + 1;
		String HISTORY_INSERT_TABLE = "INSERT INTO error_log values('"+error_int+"','"+error_type+"','"+error_message+"','"+mobile_no+"','"+vehicle_no+"','"+today+"','"+file_name+"','"+line_number+"','"+error_name+"','"+android_version+"','"+mobile_model+"','"+service_name+"')";
		mydatabase.execSQL(HISTORY_INSERT_TABLE);
        }
		
	}
	
	
	public void errorlogTablemobileToServer(SQLiteDatabase mydatabase,Context context)
	{

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		try
		{
		String sql1 = "select error_type,error_message,mobile_no,vehicle_no,date_time,file_name,line_number,error_name,android_version,mobile_model,service_name,id from error_log";
		Cursor mCursor1 = mydatabase.rawQuery(sql1, null);
		String error_type = "";
		String error_message = "";
		String mobile_no = "";
		String vehicle_no = "";
		String date_time = "";
		String file_name = "";
		int line_number = 0; 
		String error_name = "";
		String android_version = "";
		String mobile_model = "";
		String service_name = "";
		int id = 0;
		if (mCursor1.moveToFirst()) {
			do {
				error_type = mCursor1.getString(0);
				error_message = mCursor1.getString(1);
				mobile_no = mCursor1.getString(2);
				vehicle_no = mCursor1.getString(3);
				date_time = mCursor1.getString(4);
				file_name = mCursor1.getString(5);
				line_number = mCursor1.getInt(6);
				error_name = mCursor1.getString(7);
				android_version = mCursor1.getString(8);
				mobile_model = mCursor1.getString(9);
				service_name = mCursor1.getString(10);
				id = mCursor1.getInt(11);
				System.out.println("service_name is : "+service_name);
				JSONObject jsonObject = null;
				try {
					jsonObject = new JSONObject("{\"errorType\":\""+error_type+"\",\"errorMessage\":\""+error_message+"\",\"mobileNo\":\""+mobile_no+"\",\"vehicleNo\":\""+vehicle_no+"\",\"dateTime\":\""+date_time+"\",\"fileName\":\""+file_name+"\",\"dateTime\":\""+date_time+"\",\"lineNumber\":\""+line_number+"\",\"errorName\":\""+error_name+"\",\"androidVersion\":\""+android_version+"\",\"mobileModel\":\""+mobile_model+"\",\"serviceName\":\""+service_name+"\"}");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
                System.out.println("context.getResources().getString(R.string.logError_url) is : "+context.getResources().getString(R.string.logError_url));
		        URL url = new URL(context.getResources().getString(R.string.logError_url));
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
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                connection.getInputStream()));
		        String line;
		        while ((line = in.readLine()) != null) {
		        System.out.println("!!!!-"+line+"\n");
		        }
		        mydatabase.execSQL("delete from error_log where id='"+id+"' ");
		    
		} while (mCursor1.moveToNext());
		}
	}
		catch(Exception e)
		{
                System.out.println(e);
		    	String service_name =  "errorlogTablemobileToServer";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}
	
	
	public void cabRatingTableDataToServer(SQLiteDatabase mydatabase)
	{
		try
		{
		int vehicle_id = 0;
		int rating_fare = 0;
		int rating_vehicle = 0;
		int rating_driver = 0;
		String mobile_no = "";
		String datetime = "";
		String vehicle_no = "";
		String sql1 = "select b.vehicle_id,b.rating_fare,b.rating_vehicle,b.rating_driver,b.mobile_no,b.date_time from cab_information as a, cab_rating as b where a.vehicle_id=b.vehicle_id ";
		Cursor mCursor1 = mydatabase.rawQuery(sql1, null);
		if (mCursor1.moveToFirst()) {
			do {
				vehicle_id = mCursor1.getInt(0);
				rating_fare = mCursor1.getInt(1);
				rating_vehicle = mCursor1.getInt(2);
				rating_driver = mCursor1.getInt(3);
				mobile_no = mCursor1.getString(4);
				datetime = mCursor1.getString(5);
				
				String sql2 = "select vehicle_no from cab_information where vehicle_id='"+vehicle_id+"' ";
				Cursor mCursor2 = mydatabase.rawQuery(sql2, null);
				if (mCursor2.moveToFirst()) {
					do {
						vehicle_no = mCursor2.getString(0);
				} while (mCursor2.moveToNext());
				}
				
		String mobileNo = sharedpreferences_mobnumber;
        JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject("{\"vehicleNo\":\""+vehicle_no+"\",\"mobileNo\":\""+mobile_no+"\",\"driverRating\":\""+rating_driver+"\",\"fareRating\":\""+rating_fare+"\",\"vehicleRating\":\""+rating_vehicle+"\",\"dateTime\":\""+datetime+"\"}");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(jsonObject);
        //Toast.makeText(getApplicationContext(),"isNetworkAvailable "+jsonObject, Toast.LENGTH_SHORT).show();
	
        URL url = new URL(getResources().getString(R.string.saveRating_url));
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
        String line;
        while ((line = in.readLine()) != null) {
        System.out.println("!!!!-"+line+"\n");
        }
        in.close();
			} while (mCursor1.moveToNext());
		}
		
	}
		catch(Exception e)
		{
                System.out.println(e);
		    	String service_name =  "cabRatingTableDataToServer";
		        History obj = new History();
		        obj.errorInsert(String.valueOf(""),e,service_name,mydatabase,sharedpreferences_mobnumber,sharedpreferences_andVersion,sharedpreferences_mobModel);
		}
	}
	
	/* For Testing ----------------------------------------------------- */
	
	public void testTimingTable(String taskname,Context context)
	{
		int max_int = 0;
		Home obj = new Home();
		System.out.println("testTimingTable ------ ");
		SQLiteDatabase mydatabase = obj.createMobileDataBase(context);
		System.out.println("mydatabase is : "+mydatabase);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String today = dateFormat.format(date);
		
		String HISTORY_GETRATING_TABLE = "CREATE TABLE IF NOT EXISTS testtiming(id INTEGER PRIMARY KEY AUTOINCREMENT,taskname VARCHAR(100),date_time DATETIME DEFAULT CURRENT_TIMESTAMP)";
		mydatabase.execSQL(HISTORY_GETRATING_TABLE);	
		
		String sql2 = "select max(id) from testtiming";
		Cursor mCursor2 = mydatabase.rawQuery(sql2, null);
		if (mCursor2.moveToFirst()) {
			do {
				max_int = mCursor2.getInt(0);
		} while (mCursor2.moveToNext());
		} 
		max_int = max_int + 1;
        String HISTORY_INSERT_TABLE = "INSERT INTO testtiming values('"+max_int+"','"+taskname+"','"+date+"')";
		mydatabase.execSQL(HISTORY_INSERT_TABLE);
	}
	
	/* For Testing ----------------------------------------------------- */
}
