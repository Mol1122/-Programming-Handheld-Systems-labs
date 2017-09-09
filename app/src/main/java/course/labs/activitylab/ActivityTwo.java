package course.labs.activitylab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends Activity {

    private static final String RESTART_KEY = "restart";
    private static final String RESUME_KEY = "resume";
    private static final String START_KEY = "start";
    private static final String CREATE_KEY = "create";

    // String for LogCat documentation
    private final static String TAG = "Lab-ActivityTwo";

    // Lifecycle counters

    // TODO:
    // Create counter variables for onCreate(), onRestart(), onStart() and
    // onResume()
    // You will need to increment these variables' values when their
    // corresponding lifecycle methods get called


    // TODO: Create variables for each of the TextViews

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // TODO: Assign the appropriate TextViews to the TextView variables
        // Hint: Access the TextView by calling Activity's findViewById()
        // textView1 = (TextView) findViewById(R.id.textView1);


        Button closeButton = findViewById(R.id.bClose);
        closeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO:
                // This function closes Activity Two



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

    }

    // Lifecycle callback methods overrides

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

        // TODO:
        // Emit LogCat message
        // Follow the previous 2 examples provided

        // TODO:
        // Update the appropriate count variable

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
        // Save counter state information with a collection of key-value pairs
        // 4 lines of code, one for every count variable

    }

    // Updates the displayed counters
    private void displayCounts() {

        // TODO:
        // Update the user interface with the 4 counter variables
        

    }
}
