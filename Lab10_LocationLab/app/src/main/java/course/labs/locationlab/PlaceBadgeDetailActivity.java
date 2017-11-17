package course.labs.locationlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceBadgeDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO - implement the Activity
		//Hint: You will get the PlacePadge info from the intent
		//Hint: The layout file for this activity is the place_badge detail
		setContentView(R.layout.place_badge_detail);
		Log.i("Lab-Location", "in place detail activity");

		ImageView flag = (ImageView)findViewById(R.id.flag);
		TextView placeName = (TextView)findViewById(R.id.place_name);
		TextView countryName = (TextView)findViewById(R.id.country_name);
		TextView dateVisited = (TextView)findViewById(R.id.date_visited);
		TextView gps_coor = (TextView)findViewById(R.id.gps_coordinates);

		PlaceRecord newPlace = new PlaceRecord(getIntent());
		Log.i("Lab-Location", "after getting intent");
		flag.setImageBitmap(newPlace.getFlagBitmap());
		placeName.setText(newPlace.getPlace());
		countryName.setText(newPlace.getCountryName());
		dateVisited.setText(newPlace.getDateVisited().toString());
		gps_coor.setText(newPlace.getLocation().toString());
	}
}
