package course.labs.locationlab;

import java.util.Date;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.widget.TextView;

public class PlaceViewActivity extends ListActivity implements LocationListener {
	private static final long FIVE_MINS = 5 * 60 * 1000;

	private static String TAG = "Lab-Location";

	public static String PACKAGE_NAME = "course.labs.locationlab.placerecord.PlaceDetail";
	public static String PLACE_DETAIL = "place_detail";
	public static String INTENT_DATA = "course.labs.locationlab.placerecord.IntentData";

	private Location mLastLocationReading;
	private PlaceViewAdapter mAdapter;

	// False if you don't have network access
	public static boolean sHasNetwork = true;

	// default minimum time between new readings
	private long mMinTime = 5000;

	// default minimum distance between old and new readings.
	private float mMinDistance = 1000.0f;

	private LocationManager mLocationManager;

	// A fake location provider used for testing
	private MockLocationProvider mMockLocationProvider;

	private boolean mockLocationOn = false;
	private boolean returningFromActivity = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set up the app's user interface
		// This class is a ListActivity, so it has its own ListView
		// ListView's adapter should be a PlaceViewAdapter

		// TODO - acquire reference to the LocationManager
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		ListView placesListView = getListView();

		// TODO - Set an OnItemClickListener on the placeListView to open a detail view when the user
		// clicks on a Place Badge.
		placesListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Log.i(TAG,
						"Entered placesListView.OnItemClickListener.onItemClick()");

				Intent intent1 = ((PlaceRecord)mAdapter.getItem(position)).packageToIntent();
				//intent1.setClass(PlaceViewActivity.this, PlaceBadgeDetailActivity.class);
				Intent intent2 = new Intent(PlaceViewActivity.this, PlaceBadgeDetailActivity.class);
				intent2.putExtras(intent1);
				startActivity(intent2);


			}
		});


		// TODO - add a footerView to the ListView
		// You can use footer_view.xml to define the footer
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View footerView = (TextView)inflater.inflate(R.layout.footer_view, null);


		// Can remove once footerView is implemented
		if (null == footerView) {
			return;
		}



		footerView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO - When the footerView's onClick() method is called, it must
				// issue the
				// following log call
				// Log.i(TAG,"Entered footerView.OnClickListener.onClick()");

				// footerView must respond to user clicks.
				// Must handle 3 cases:
				// 1) The current location is new - download new Place Badge. Issue the
				// following log call:
				// Log.i(TAG,"Starting Place Download");

				// 2) The current location has been seen before - issue Toast message.
				// Issue the following log call:
				// Log.i(TAG,"You already have this location badge");

				// 3) There is no current location - response is up to you. The best
				// solution is to disable the footerView until you have a location.
				// Issue the following log call:
				// Log.i(TAG,"Location data is not available");
				Log.i(TAG, "Entered footerView.OnClickListener.onClick()");

				if (mLastLocationReading != null &&
						(System.currentTimeMillis() - mLastLocationReading.getTime()) < FIVE_MINS){

					if (mAdapter.intersects(mLastLocationReading)){
						Log.i(TAG,"You already have this location badge");
						Toast.makeText(getApplicationContext(), "You already have this location badge", Toast.LENGTH_LONG).show();
					} else {
						Log.i(TAG,"Starting Place Download");
						//Implement this
						new PlaceDownloaderTask(PlaceViewActivity.this, sHasNetwork).execute(mLastLocationReading);
					}

				}  else {
					Log.i(TAG, "Location data is not available");
				}


			}

		});

		placesListView.addFooterView(footerView);
		mAdapter = new PlaceViewAdapter(getApplicationContext());
		setListAdapter(mAdapter);
	}
	public final static  int MY_PERMISSIONS_LOCATION= 4;

	@Override
	protected void onResume() {
		super.onResume();
		if (Build.VERSION.SDK_INT >= 23 &&
				ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != PackageManager.PERMISSION_GRANTED
				||ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") != PackageManager.PERMISSION_GRANTED) {

			ActivityCompat.requestPermissions(PlaceViewActivity.this,
					new String[]{"android.permission.ACCESS_FINE_LOCATION",
							"android.permission.ACCESS_COARSE_LOCATION"},
					MY_PERMISSIONS_LOCATION);
		}else
			getLocationUpdates();

	}

	private void getLocationUpdates()
	{
		try {
			startMockLocationManager();

			// TODO - Check NETWORK_PROVIDER and GPS_PROVIDER for an existing
			// location reading.
			// Only keep this last reading if it is fresh - less than 5 minutes old.
			Location gpsLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			Location networkLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

			if (gpsLocation != null && networkLocation != null) {
				if (gpsLocation.getTime() > networkLocation.getTime()) {
					mLastLocationReading = gpsLocation;
				} else {
					mLastLocationReading = networkLocation;
				}
			} else if (gpsLocation == null && networkLocation == null) {
				//do nothing
			} else if (gpsLocation != null) {
				mLastLocationReading = gpsLocation;
			} else {
				mLastLocationReading = networkLocation;
			}

			if (mLastLocationReading != null && System.currentTimeMillis() - mLastLocationReading.getTime() >= FIVE_MINS){
				mLastLocationReading = null;
			}

			// TODO - register to receive location updates from NETWORK_PROVIDER
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, mMinTime, mMinDistance, this);

		}catch (SecurityException e)
		{
			Log.d(TAG,e.getLocalizedMessage());
		}
	}
	@Override
	protected void onPause() {
		shutdownMockLocationManager();

		// TODO - unregister for location updates
		mLocationManager.removeUpdates(this);

		super.onPause();
	}

	// Callback method used by PlaceDownloaderTask
	public void addNewPlace(PlaceRecord place) {

		Log.i(TAG, "Entered addNewPlace()");
		if (place.getCountryName() == null || place.getCountryName().isEmpty()) {
			showToast(getString(R.string.no_country_string));
		} else if (!mAdapter.intersects(place.getLocation())) {
			place.setDateVisited(new Date());
			mAdapter.add(place);
			mAdapter.notifyDataSetChanged();
		} else {
			showToast(getString(R.string.duplicate_location_string));
		}
	}

	@Override
	public void onLocationChanged(Location currentLocation) {

		// TODO - Handle location updates
		// Cases to consider
		// 1) If there is no last location, keep the current location.
		// 2) If the current location is older than the last location, ignore
		// the current location
		// 3) If the current location is newer than the last locations, keep the
		// current location.
		if (mLastLocationReading == null) {
			mLastLocationReading = currentLocation;
		} else {
			//if the current location is newer
			if (currentLocation.getTime() > mLastLocationReading.getTime()){
				mLastLocationReading = currentLocation;
			}
		}


	}

	@Override
	public void onProviderDisabled(String provider) {
		// not implemented
	}

	@Override
	public void onProviderEnabled(String provider) {
		// not implemented
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// not implemented
	}

	private long ageInMilliseconds(Location location) {
		return System.currentTimeMillis() - location.getTime();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.delete_badges:
				mAdapter.removeAllViews();
				return true;
			case R.id.place_one:
				mMockLocationProvider.pushLocation(37.422, -122.084);
				return true;
			case R.id.place_no_country:
				mMockLocationProvider.pushLocation(0, 0);
				return true;
			case R.id.place_two:
				mMockLocationProvider.pushLocation(38.996667, -76.9275);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void showToast(final String toast) {
		Toast.makeText(PlaceViewActivity.this, toast, Toast.LENGTH_LONG)
				.show();
	}

	private void shutdownMockLocationManager() {
		if (mockLocationOn) {
			mMockLocationProvider.shutdown();
			mockLocationOn = false;
		}
	}

	private void startMockLocationManager() {
		if (!mockLocationOn) {
			mMockLocationProvider = new MockLocationProvider(
					LocationManager.NETWORK_PROVIDER, this);
			mockLocationOn = true;
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		switch (requestCode) {
			case MY_PERMISSIONS_LOCATION:
				int g = 0;
				Log.d(TAG,"Perm?: "+permissions.length+" -? "+grantResults.length);
				for(String perm: permissions)
					Log.d(TAG,"Perm: "+perm+" --> "+grantResults[g++]);
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED)
					getLocationUpdates();
				else {
					Log.i(TAG, "Permission was not granted to access location");
					finish();
				}
		}
	}
}