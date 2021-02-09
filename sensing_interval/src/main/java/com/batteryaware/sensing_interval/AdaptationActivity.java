package com.batteryaware.sensing_interval;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class AdaptationActivity extends Activity {
    public int level;
    public Boolean returned = false;
    public BroadcastReceiver InformationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            levelReturned();
        }
    };
    public void levelReturned(){
        returned = true;
    }
    public int returnLevel(){
        returned = false;
        this.registerReceiver(this.InformationReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        while(true)
        {
            if(returned)
            {
                return level;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.registerReceiver(this.InformationReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
