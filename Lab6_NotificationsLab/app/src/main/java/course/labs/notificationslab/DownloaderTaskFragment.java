package course.labs.notificationslab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;



public class DownloaderTaskFragment extends Fragment {

	private DownloadFinishedListener mCallback;
	private Context mContext;
	private final int MY_NOTIFICATION_ID = 11151990;

	@SuppressWarnings("unused")
	private static final String TAG = "Lab-Notifications";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Preserve across reconfigurations
		setRetainInstance(true);

		// TODO: Create new DownloaderTask that "downloads" data
		DownloaderTask downloaderTask=new DownloaderTask();

		// TODO: Retrieve arguments from DownloaderTaskFragment
		// Prepare them for use with DownloaderTask.
		Bundle bundle = this.getArguments();
		ArrayList<Integer> friendResIds = bundle.getIntegerArrayList(MainActivity.TAG_FRIEND_RES_IDS);

		// TODO: Start the DownloaderTask
		downloaderTask.execute(friendResIds);

	}

	// Assign current hosting Activity to mCallback
	// Store application context for use by downloadTweets()
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext();

		// Make sure that the hosting activity has implemented
		// the correct callback interface.
		try {
			mCallback = (DownloadFinishedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement DownloadFinishedListener");
		}
	}

	// Null out mCallback
	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;
	}


<<<<<<< HEAD
	public class DownloaderTask extends AsyncTask<ArrayList<Integer>,Void,String[]>{

		@Override
		protected String[] doInBackground(ArrayList<Integer>... arrayLists) {

			ArrayList<Integer> data=arrayLists[0];
			Integer[] dataArray=new Integer[data.size()];
			for(int i=0;i<data.size();i++){
				dataArray[i]=data.get(i);
			}
			return downloadTweets(dataArray);
		}

		@Override
		protected void onPostExecute(String[] strings) {
			mCallback.notifyDataRefreshed(strings);
		}

	}

	// TODO: Uncomment this helper method and put them inside the DownLoaderTask subclass
	// Simulates downloading Twitter data from the network
	private String[] downloadTweets(Integer resourceIDS[]) {
		final int simulatedDelay = 2000;
		String[] feeds = new String[resourceIDS.length];
		boolean downLoadCompleted = false;

		try {
			for (int idx = 0; idx < resourceIDS.length; idx++) {
				InputStream inputStream;
				BufferedReader in;
				try {
					// Pretend downloading takes a long time
					Thread.sleep(simulatedDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				inputStream = mContext.getResources().openRawResource(
						resourceIDS[idx]);
				in = new BufferedReader(new InputStreamReader(inputStream));

				String readLine;
				StringBuffer buf = new StringBuffer();

				while ((readLine = in.readLine()) != null) {
					buf.append(readLine);
				}

				feeds[idx] = buf.toString();

				if (null != in) {
					in.close();
				}
			}

			downLoadCompleted = true;
			saveTweetsToFile(feeds);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Notify user that downloading has finished
		notify(downLoadCompleted);
		return feeds;

	}


	// If necessary, notifies the user that the tweet downloads are
	// complete. Sends an ordered broadcast back to the BroadcastReceiver in
	// MainActivity to determine whether the notification is necessary.
=======
	public class DownloaderTask extends AsyncTask<Integer, Void, String[]> {

		@Override
		protected String[] doInBackground(Integer... resourceIDs) {
			return downloadTweets(resourceIDs);
		}

		// Simulates downloading Twitter data from the network
		private String[] downloadTweets(Integer resourceIDS[]) {
			final int simulatedDelay = 2000;
			String[] feeds = new String[resourceIDS.length];
			boolean downLoadCompleted = false;

			try {
				for (int idx = 0; idx < resourceIDS.length; idx++) {
					InputStream inputStream;
					BufferedReader in;
					try {
						// Pretend downloading takes a long time
						Thread.sleep(simulatedDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					inputStream = mContext.getResources().openRawResource(
							resourceIDS[idx]);
					in = new BufferedReader(new InputStreamReader(inputStream));

					String readLine;
					StringBuffer buf = new StringBuffer();

					while ((readLine = in.readLine()) != null) {
						buf.append(readLine);
					}

					feeds[idx] = buf.toString();

					if (null != in) {
						in.close();
					}
				}

				downLoadCompleted = true;
				saveTweetsToFile(feeds);

			} catch (IOException e) {
				e.printStackTrace();
			}

			// Notify user that downloading has finished
			notify(downLoadCompleted);

			return feeds;

		}

		// If necessary, notifies the user that the tweet downloads are
		// complete. Sends an ordered broadcast back to the BroadcastReceiver in
		// MainActivity to determine whether the notification is necessary.
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026

		private void notify(final boolean success) {
			final Intent restartMainActivityIntent = new Intent(mContext,
					MainActivity.class);
			restartMainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Sends an ordered broadcast to determine whether MainActivity is
			// active and in the foreground. Creates a new BroadcastReceiver
			// to receive a result indicating the state of MainActivity

			// The Action for this broadcast Intent is
			// MainActivity.DATA_REFRESHED_ACTION
			// The result, MainActivity.IS_ALIVE, indicates that MainActivity is
			// active and in the foreground.

			mContext.sendOrderedBroadcast(new Intent(
<<<<<<< HEAD
					MainActivity.DATA_REFRESHED_ACTION), null,
=======
							MainActivity.DATA_REFRESHED_ACTION), null,
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026
					new BroadcastReceiver() {

						final String failMsg = mContext
								.getString(R.string.download_failed_string);
						final String successMsg = mContext
								.getString(R.string.download_succes_string);
						final String notificationSentMsg = mContext
								.getString(R.string.notification_sent_string);

						@Override
						public void onReceive(Context context, Intent intent) {

<<<<<<< HEAD
							// TODO: Check whether or not the MainActivity
							// received the broadcast

							if (getResultCode()!=MainActivity.IS_ALIVE) {
=======
							// TODO: Change the if condition (false) to Check whether or not the MainActivity
							// received the broadcast

							if (false) {
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026

								// TODO: If not, create a PendingIntent using
								// the
								// restartMainActivityIntent and set its flags
								// to FLAG_UPDATE_CURRENT
<<<<<<< HEAD
								Intent restartMainActivityIntent = new Intent(mContext, MainActivity.class);
								PendingIntent pendIntent = PendingIntent.getActivity(mContext, 0, restartMainActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

=======

								final PendingIntent pendingIntent = null;
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026

								// Uses R.layout.custom_notification for the
								// layout of the notification View. The xml
								// file is in res/layout/custom_notification.xml
<<<<<<< HEAD
								RemoteViews mContentView = new RemoteViews(
										mContext.getPackageName(),
										R.layout.custom_notification);
=======
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026



								// TODO: Set the notification View's text to
								// reflect whether the download completed
<<<<<<< HEAD
								// successfully (successMsg or failMsg)
								mContentView.setTextViewText(R.id.text,success ? successMsg : failMsg);
=======
								// successfully
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026



								// TODO: Use the Notification.Builder class to
								// create the Notification. You will have to set
								// several pieces of information. You can use
								// android.R.drawable.stat_sys_warning
								// for the small icon. You should also
								// setAutoCancel(true).
<<<<<<< HEAD
								// To support API Level 26 implement the TODOs in createNotificationChannel
								Notification.Builder notificationBuilder = new Notification.Builder(mContext)
										.setSmallIcon(android.R.drawable.stat_sys_warning)
										.setAutoCancel(true)
										.setContentIntent(pendIntent)
										.setContent(mContentView);


								createNotificationChannel();


								// TODO: Send the notification
								NotificationManager notificationManager=(NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
								notificationManager.notify(MY_NOTIFICATION_ID,notificationBuilder.build());

								Toast.makeText(mContext, notificationSentMsg,
										Toast.LENGTH_LONG).show();
=======
								// To support API level 26, implement the TODOs in createNotificationChannel()
								createNotificationChannel();

								// TODO: Send the notification and create a toast with notificationSentMsg text
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026

							} else {
								Toast.makeText(mContext,
										success ? successMsg : failMsg,
										Toast.LENGTH_LONG).show();
							}
						}
					}, null, 0, null, null);
		}
		private String channelID = "my_channel_01";

		private void createNotificationChannel()
		{
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				// TODO: Create Notification Channel with id channelID,
				// name R.string.channel_name
				// and description R.string.channel_description of high importance
<<<<<<< HEAD
				NotificationManager notificationManager=(NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
				CharSequence name = getString(R.string.channel_name);
				String description = getString(R.string.channel_description);
				int importance = NotificationManager.IMPORTANCE_HIGH;
				NotificationChannel mChannel = new NotificationChannel(channelID, name, importance);

				// Configure the notification channel.
				mChannel.setDescription(description);
				mChannel.enableLights(true);
				// Sets the notification light color for notifications posted to this
				// channel, if the device supports this feature.
				mChannel.setLightColor(Color.RED);
				mChannel.enableVibration(true);

				notificationManager.createNotificationChannel(mChannel);


=======
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026

			}
		}
		// Saves the tweets to a file
		private void saveTweetsToFile(String[] result) {
			PrintWriter writer = null;
			try {
				FileOutputStream fos = mContext.openFileOutput(
						MainActivity.TWEET_FILENAME, Context.MODE_PRIVATE);
				writer = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(fos)));

				for (String s : result) {
					writer.println(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (null != writer) {
					writer.close();
				}
			}
		}
<<<<<<< HEAD
}
=======

		// Pass newly available data back to hosting Activity
		// using the DownloadFinishedListener interface
		@Override
		protected void onPostExecute(String[] result) {
			super.onPostExecute(result);

			if (null != mCallback) {
				mCallback.notifyDataRefreshed(result);
			}
		}
	}


}
>>>>>>> 4d5b296ab4a16c538e097246d52c762dd62b8026
