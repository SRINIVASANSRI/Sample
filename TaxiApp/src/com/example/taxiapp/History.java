package com.example.taxiapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
/*added For History ...*/
 public class History extends Activity {
	
		
	 
	private TableLayout addRowToTable(final TableLayout table, String contentCol1, String contentCol2,String contentCol3, Context context,String contentCol4,final int j) {
		
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

	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);      
        setContentView(R.layout.history);    
        
        System.out.println("cabdata4 is : ");
        SQLiteDatabase mydatabase = openOrCreateDatabase("cabdata4",MODE_PRIVATE, null);	
        
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
				SimpleDateFormat dateString1 = new SimpleDateFormat("dd-MM-yyyy");
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
	public void history_getRating(String vehicle_no,String mobile_no,String activity,String datetime,SQLiteDatabase mydatabase){
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
}
