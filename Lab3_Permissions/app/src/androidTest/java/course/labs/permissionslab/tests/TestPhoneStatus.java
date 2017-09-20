package course.labs.permissionslab.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import course.labs.permissionslab.ActivityLoaderActivity;
import course.labs.permissionslab.PhoneStatusActivity;

public class TestPhoneStatus extends
		ActivityInstrumentationTestCase2<ActivityLoaderActivity> {
	private Solo solo;

	public TestPhoneStatus() {
		super(ActivityLoaderActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		getActivity();
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	// Executes TestPhoneStatus
	public void testRun() {
		
		int delay = 2000;
		
		// ================ Section One ===============
		// Wait for activity:
		// 'course.labs.permissionslab.ActivityLoaderActivity'
		assertTrue(
				"TestPhoneStatus:" +
				"Section One:" +
				"ActivityLoaderActivity did not load correctly.",
				solo.waitForActivity(course.labs.permissionslab.ActivityLoaderActivity.class, delay));

		// Click on Bookmarks Activity
		solo.clickOnView(solo
				.getView(course.labs.permissionslab.R.id.start_phone_status_button));

		// Wait for activity: 'course.labs.permissionslab.PhoneStatusActivity'
		assertTrue(
				"TestPhoneStatus:" +
				"Section One:" +
				"PhoneStatusActivity did not load correctly.",
				solo.waitForActivity(PhoneStatusActivity.class, delay));
		
		// ================ Section Two ===============
		// Click on Get Bookmarks
		solo.clickOnView(solo
				.getView(course.labs.permissionslab.R.id.get_phone_number_button));

		// Check for at least one bookmark
		assertTrue("TestPhoneStatus:" +
				   "Section Two:" +
				   "The phone number is not correctly displayed.",
				solo.waitForText("Phone Number: 1"));

		

	}
}
