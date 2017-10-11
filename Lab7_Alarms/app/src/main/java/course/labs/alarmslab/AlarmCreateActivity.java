package course.labs.alarmslab;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AlarmCreateActivity extends Activity {

    // TODO look at these class/instance variables and see if you could have come up with them
    // on your own.

	public static final String TWEET_STRING = "TWEET";
	private AlarmManager mAlarmManager;

	private static final String TAG = "AlarmCreateActivity";
	private EditText mTweetTextView, mDelayTextView;
	private int mID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

        // TODO create reference to ALARM_SERVICE as well as locate necessary text views

	}

	public void set(View v) {

		String tweetText = mTweetTextView.getText().toString();
		Long delay = Integer.parseInt(mDelayTextView.getText().toString()) * 1000L;

        // TODO create Intents for the alarm service. Additionally you will need to create a
        // pending intent that will use the original intent you create to start the AlarmTweetService.
        // Use the PendingIntent.getService() method. Be aware you will need to pass a unique value
        // for the request code (why?) as well as the flag PendingIntent.FLAG_ONE_SHOT. Why? Could
        // be possible midterm questions





		// TODO make log statement that tweet was sent to AlarmTweetService


        // TODO use AlarmManager set method to set Alarm

	}

	public void clear(View v) {
        // TODO clear views

	}
}
