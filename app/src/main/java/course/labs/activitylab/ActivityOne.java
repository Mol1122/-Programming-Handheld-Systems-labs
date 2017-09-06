package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends Activity {

	// Use these as keys when you're saving state between reconfigurations
	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";

	// Lifecycle counters

	// TODO:
	// Create variables named
	// mCreate, mRestart, mStart and mResume
	// to count calls to onCreate(), onRestart(), onStart() and
	// onResume(). These variables should not be defined as static.

	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called.

	// TODO: Create variables for each of the TextViews
	// named mTvCreate, mTvRestart, mTvStart, mTvResume.
	// for displaying the current count of each counter variable

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);

		// TODO: Assign the appropriate TextViews to the TextView variables
		// Hint: Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);

		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);
		launchActivityTwoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO:
				// Launch Activity Two
				// Hint: use Context's startActivity() method

				// Create an intent stating which Activity you would like to
				// start
				Intent intent = null;

				// Launch the Activity using the intent

			}
		});

		// Emit LogCat message using the Log.i method

	}



	@Override
	public void onStart() {
		super.onStart();

		// Emit LogCat message using the Log.i method
	}

	@Override
	public void onResume() {
        super.onResume();
		// Emit LogCat message using the Log.i method
	}

	@Override
	public void onPause() {
        super.onPause();
		// Emit LogCat message using the Log.i method
	}

	@Override
	public void onStop() {
        super.onStop();
		// Emit LogCat message using the Log.i method
	}

	@Override
	public void onRestart() {
        super.onRestart();
		// Emit LogCat message using the Log.i method

	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		// Emit LogCat message using the Log.i method
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

	}


	public void displayCounts() {

	}
}
