package tablayoutdemo.android.com.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tablayoutdemo.android.com.broadcastdemo.AppUtil.AppConstants;

public class MainActivity extends AppCompatActivity {

    private Button btn_sendbroadcast = null;
    private TextView txt_status = null;

    private TTSstopListener receiver = null;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * initalizing view elements.
     */
    private void initView() {
        btn_sendbroadcast = (Button) findViewById(R.id.btn_sendbroadcast);
        txt_status = (TextView) findViewById(R.id.txt_broadcaststatus);

        btn_sendbroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("message", "this is a broadcast message");
                i.setAction(AppConstants.MY_CUSTOM_BROADCAST);
                sendBroadcast(i);
            }
        });


    }

    @Override
    protected void onStart() {

        try {
            receiver = new TTSstopListener();
            registerReceiver(receiver, new IntentFilter(AppConstants.MY_CUSTOM_BROADCAST));

        } catch (Exception e) {
        }

        super.onStart();
    }


    public class TTSstopListener extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(AppConstants.MY_CUSTOM_BROADCAST))
                Log.e(TAG, "broadcast received");
        }
    }
}
