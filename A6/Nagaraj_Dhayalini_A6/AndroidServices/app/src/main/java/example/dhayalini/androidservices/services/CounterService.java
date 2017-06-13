package example.dhayalini.androidservices.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import example.dhayalini.androidservices.MainActivity;
import example.dhayalini.androidservices.R;

public class CounterService extends Service {

    private static final int NOTIFICATION_ID = 1000;
    public static boolean isServiceRunning = false;
    public static final String TAG = "CounterService";
    CounterThread t ;
    private int initialValue ;
    public CounterService() {

    }

    /**
     * Starts the service & starts the counter thread.
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!isServiceRunning){
            initialValue = intent.getIntExtra("initial_value_counter",0);
            t = new CounterThread(initialValue);
            t.start();
            isServiceRunning = true;
        }

        Log.i(TAG,"Service started");
        return START_NOT_STICKY;
    }


    /**
     * Here, we are stopping the counter thread, as service is stopped in this state.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceRunning = false;
        if(t != null && t.isAlive()){
            t.stopThread();
        }
        Log.i(TAG,"Service destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        //For this assignment, since UI is not bind with this service, this is unsupported.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void vibrate(){
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }

    /**
     * Sends the notification with sound and vibration
     * @param msg
     */
     void sendNotification(String msg) {
        Log.d(TAG, "Preparing to send notification...: " + msg);
        NotificationManager mNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        android.support.v4.app.NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Android Services")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        Log.d(TAG, "Notification sent successfully.");
    }

    class CounterThread extends Thread {

        int counter = 0;
        int initialCounterValue;

        boolean isStopThread = false;

        public CounterThread(int initialValue){
            counter = initialValue;
            initialCounterValue = initialValue;
        }

        public void stopThread(){
            isStopThread = true;
        }

        @Override
        public void run() {

            while (!isStopThread){
                try {
                    //Sleeps the tread for one second.
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"Incrementing counter value "+ (++counter));

                if(counter % 10 == (initialCounterValue % 10)){
                    // Get a handler that can be used to post to the main thread
                    Handler mainHandler = new Handler(getApplicationContext().getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            sendNotification("Counter updated. Counter value: "+counter);
                        }
                    };
                    mainHandler.post(myRunnable);
                }
            }

        }
    }
}
