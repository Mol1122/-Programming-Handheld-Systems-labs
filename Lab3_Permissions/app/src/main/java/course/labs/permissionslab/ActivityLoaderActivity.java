package course.labs.permissionslab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityLoaderActivity extends Activity {

	private static final String TAG = "Lab-Permissions";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader_activity);

		Button startPhoneStatusButton = (Button) findViewById(R.id.start_phone_status_button);
		// TODO - Add onClickListener to the startPhoneStatusButton to call startPhoneStatusActivity()

	}

	private void startPhoneStatusActivity() {
		Log.i(TAG, "Entered startPhoneStatusActivity()");
	
		// TODO - Start the PhoneStatusActivity
	}
}
