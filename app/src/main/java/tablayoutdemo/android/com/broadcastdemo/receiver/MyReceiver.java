package tablayoutdemo.android.com.broadcastdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by amitrai on 12/2/16.
 */
public class MyReceiver extends BroadcastReceiver {
    private final String TAG = getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "broadcast received");
    }
}
