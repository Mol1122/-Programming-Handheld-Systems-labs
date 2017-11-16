package course.labs.locationlab.tests;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 21)

public class PermGrantedTest {

	private static final int LAUNCH_TIMEOUT = 10000;
	private UiDevice mDevice;
	private static final String BASIC_SAMPLE_PACKAGE
			= "course.labs.locationlab";
	@Before
	public void startMainActivityFromHomeScreen() {
		// Initialize UiDevice instance
		mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

		// Start from the home screen
		mDevice.pressHome();

		// Wait for launcher
		final String launcherPackage = mDevice.getLauncherPackageName();
		assertThat(launcherPackage, is(notNullValue()));
		mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
				LAUNCH_TIMEOUT);

		// Launch the app
		Context context = InstrumentationRegistry.getContext();
		final Intent intent = context.getPackageManager()
				.getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);

		if (null == intent) fail();

		// Clear out any previous instances
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		context.startActivity(intent);

		// Wait for the app to appear
		mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
				LAUNCH_TIMEOUT);
		//Give permission if it is not given already
		//
		UiObject2 PermissionDialog = mDevice.wait(Until.findObject(By.text("ALLOW")), 2000);
		if (PermissionDialog != null)
			PermissionDialog.click();

	}

	@Test
	public void testPermissionRequested() {
		UiObject2 getNewPlace = mDevice.wait(Until.findObject(By.text("Get New Place")), 2000);
		assertNotNull("Permission wasn't granted", getNewPlace);
		getNewPlace.click();

	}
}
