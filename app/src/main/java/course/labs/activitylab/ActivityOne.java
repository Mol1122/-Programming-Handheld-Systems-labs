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

    private static final String RESTART_KEY = "restart";
    private static final String RESUME_KEY = "resume";
    private static final String START_KEY = "start";
    private static final String CREATE_KEY = "create";

    // String for LogCat documentation
    private final static String TAG = "Lab-ActivityOne";

    // Lifecycle counters

    // TODO:
    // Create counter variables for onCreate(), onRestart(), onStart() and
    // onResume()


    // TODO: Create variables for each of the TextViews


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        // TODO: Assign the appropriate TextViews to the TextView variables
        // Hint: Access the TextView by calling Activity's findViewById()
        // textView1 = (TextView) findViewById(R.id.textView1);


        Button launchActivityTwoButton = findViewById(R.id.bLaunchActivityTwo);
        launchActivityTwoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO:
                // Launch Activity Two
                // Hint: use Context's startActivity() method

                // Create an intent stating which Activity you would like to start

                // Launch the Activity using the intent

            }
        });

        // Has previous state been saved?
        if (savedInstanceState != null) {

            // TODO:
            // Restore value of counters from saved state

        }

        // Emit LogCat message
        Log.i(TAG, "Entered the onCreate() method");

        // TODO:
        // Update the appropriate count variable
        // Update the user interface via the displayCounts() method


    }

    // Lifecycle callback overrides

    @Override
    public void onStart() {
        super.onStart();

        // Emit LogCat message
        Log.i(TAG, "Entered the onStart() method");

        // TODO:
        // Update the appropriate count variable
        // Update the user interface

    }

    @Override
    public void onResume() {
        super.onResume();

        // Emit LogCat message
        Log.i(TAG, "Entered the onResume() method");

        // TODO:
        // Update the appropriate count variable
        // Update the user interface

    }

    @Override
    public void onPause() {
        super.onPause();

         // TODO:
        // Emit LogCat message
        // Follow the previous 2 examples provided
    }

    @Override
    public void onStop() {
        super.onStop();

        // TODO:
        // Emit LogCat message
        // Follow the previous 2 examples provided
    }

    @Override
    public void onRestart() {
        super.onRestart();

        // TODO:
        // Emit LogCat message
        // Follow the previous 2 examples provided

        // TODO:
        // Update the appropriate count variable
        // Update the user interface

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

         // TODO:
        // Emit LogCat message
        // Follow the previous 2 examples provided

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // TODO:
        // Save state information with a collection of key-value pairs
    }

    // Updates the displayed counters
    private void displayCounts() {

        // TODO:
        // Update the user interface via the 4 counter variables

    }
}
