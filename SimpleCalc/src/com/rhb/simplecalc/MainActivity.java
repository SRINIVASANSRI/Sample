package com.rhb.simplecalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView Result;
	Button Calculate;
	float num1,num2;
	Sting var3, var4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FirstValue = (EditText) findViewById(R.id.EditText01);
		SecondValue = (EditText) findViewById(R.id.EditText02);
		Result = (TextView) findViewById(R.id.TextView01);
		Result.setText("0.00");

		Calculate = (Button) findViewById(R.id.Button01);
		//Adding listener to button
		Calculate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Getting first & second values and passing to show result
				showResult(FirstValue.getText(), SecondValue.getText());

			}
		});
	}

	//Showing multiply results
	protected void showResult(Editable first, Editable second) 
	{
		float num1 = Float.parseFloat(first.toString());
		float num2 = Float.parseFloat(second.toString());
		float result = num1 * num2;
		String finalresult=var3+var4;
		Result.setText(String.valueOf(result));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
