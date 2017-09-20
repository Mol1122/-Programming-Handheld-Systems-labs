package course.labs.permissionslab.tests;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestSuite;

import course.labs.permissionslab.ActivityLoaderActivity;

/**
 * Created by Heba Aly on 9/18/2017.
 */

public class AllTests  extends
        ActivityInstrumentationTestCase2<Activity> {

    public AllTests(Class<Activity> activityClass) {
        super(activityClass);
    }

    public static TestSuite suite() {
        TestSuite t = new TestSuite();
        t.addTestSuite(PermissionEnforcementTest.class);
        t.addTestSuite(TestPhoneStatus.class);
        t.addTestSuite(TestDangerousApp.class);
        return t;
    }

    @Override
    public void setUp() throws Exception {

    }


    @Override
    public void tearDown() throws Exception {
    }

}
