package course.labs.permissionslab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PhoneStatusActivity extends Activity {
	
	private static final String TAG = "Lab-Permissions";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_status_activity);

		Button getPhoneNumButton = (Button) findViewById(R.id.get_phone_number_button);
		// TODO - Add onClickListener to the getPhoneNumButton to call loadPhoneNumber()


		Button goToDangerousActivityButton = (Button) findViewById(R.id.go_to_dangerous_activity_button);
		// TODO - Add onClickListener to the goToDangerousActivityButton to call startGoToDangerousActivity()


	}

	private void loadPhoneNumber() {

		Log.i(TAG, "Entered loadPhoneNumber()");

		TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String mPhoneNumber = tMgr.getLine1Number();

		TextView box = (TextView) findViewById(R.id.text);
		box.setText("Phone Number: "+mPhoneNumber);

		Log.i(TAG, "Phone Number loaded");
	}

	private void startGoToDangerousActivity() {

		Log.i(TAG, "Entered startGoToDangerousActivity()");

		// TODO - Start the GoToDangerousActivity


	}

}
