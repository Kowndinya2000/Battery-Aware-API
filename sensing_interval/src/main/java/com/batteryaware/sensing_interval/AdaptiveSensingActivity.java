package com.batteryaware.sensing_interval;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import androidx.appcompat.app.AppCompatActivity;

public class AdaptiveSensingActivity  extends AppCompatActivity {
    public static int deviceStatus;
    public static boolean intentComplete = false;
    public static int batteryLevel;
    public static String batteryState;
    public static Context context;
//    public static final long  Original_UPDATE_INTERVAL_IN_MILLISECONDS = 13000;
//    public static  long UPDATE_INTERVAL_IN_MILLISECONDS = 13000;

    public static int batteryLevel(){
        IntentFilter intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        context.getApplicationContext().registerReceiver(broadcastreceiver,intentfilter);
        while(true)
        {
            if(intentComplete)
            {
                intentComplete = false;
                break;
            }
        }
        return batteryLevel;
    }
    public static int getBatteryLevel(){
        return batteryLevel();
    }
    private static BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            batteryLevel=(int)(((float)level / (float)scale) * 100.0f);
            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){
                 batteryState = "CHARGING";
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){
                batteryState = "DISCHARGING";
            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){
                batteryState = "FULL";
            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN) {
                batteryState = "UNKNOWN";
            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                batteryState = "NOT_CHARGING";
            }
            intentComplete = true;
        }
    };
}
