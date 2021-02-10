package com.batteryaware.sensing_interval;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

public class AdaptationActivity extends Activity {
    private class BatteryLevel{
        private int BATTERY_HIGH;
        private int BATTERY_LOW;
        private int BATTERY_MEDIUM;
    }
    private BatteryLevel defineLevel = new BatteryLevel();
    private void setDefineLevel(BatteryLevel defineLevel){
        this.defineLevel = defineLevel;
        this.defineLevel.BATTERY_HIGH = 60;
        this.defineLevel.BATTERY_MEDIUM = 20;
        this.defineLevel.BATTERY_LOW = 0;
    }
    protected void BatteryThresholdPoints(int THRESHOLD_HIGH, int THRESHOLD_MEDIUM)
    {
        defineLevel.BATTERY_HIGH = THRESHOLD_HIGH;
        defineLevel.BATTERY_MEDIUM = THRESHOLD_MEDIUM;
    }
    private class AdaptiveEngine
    {
        private long SENSING_INTERVAL;
        private long SLOPE;
        private String BatteryAwareFunction;
    }
    private AdaptiveEngine setUpPolicy_HCF = new AdaptiveEngine();
    private void setSetUpPolicy_HCF(AdaptiveEngine setUpPolicy_HCF) {
        this.setUpPolicy_HCF = setUpPolicy_HCF;
        this.setUpPolicy_HCF.SENSING_INTERVAL = 1000;
        this.setUpPolicy_HCF.SLOPE = 0;
        this.setUpPolicy_HCF.BatteryAwareFunction = "Linear";
    }
    protected void AdaptationPolicy_H_C_F(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_HCF.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_HCF.SLOPE = STEP;
        setUpPolicy_HCF.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_HCB = new AdaptiveEngine();
    private void setSetUpPolicy_HCB(AdaptiveEngine setUpPolicy_HCB) {
        this.setUpPolicy_HCB = setUpPolicy_HCB;
        this.setUpPolicy_HCB.SENSING_INTERVAL = 1000;
        this.setUpPolicy_HCB.SLOPE = 0;
        this.setUpPolicy_HCB.BatteryAwareFunction = "Exponential";
    }
    private void AdaptationPolicy_H_C_B(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_HCB.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_HCB.SLOPE = STEP;
        setUpPolicy_HCB.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_HDF = new AdaptiveEngine();
    private void setSetUpPolicy_HDF(AdaptiveEngine setUpPolicy_HDF) {
        this.setUpPolicy_HDF = setUpPolicy_HDF;
        this.setUpPolicy_HDF.SENSING_INTERVAL = 1000;
        this.setUpPolicy_HDF.SLOPE = 1;
        this.setUpPolicy_HDF.BatteryAwareFunction = "Linear";
    }
    protected void AdaptationPolicy_H_D_F(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_HDF.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_HDF.SLOPE = STEP;
        setUpPolicy_HDF.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_HDB = new AdaptiveEngine();
    private void setSetUpPolicy_HDB(AdaptiveEngine setUpPolicy_HDB) {
        this.setUpPolicy_HDB = setUpPolicy_HDB;
//        this.setUpPolicy_HDB.SENSING_INTERVAL = setUpPolicy_HCB.SENSING_INTERVAL + ((int) Math.exp(Math.sqrt(setUpPolicy_HCB.SLOPE))*100*(100-defineLevel.BATTERY_HIGH));
        this.setUpPolicy_HDB.SENSING_INTERVAL = 1000;
        this.setUpPolicy_HDB.SLOPE = 2;
        this.setUpPolicy_HDB.BatteryAwareFunction = "Exponential";
    }
    protected void AdaptationPolicy_H_D_B(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_HDB.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_HDB.SLOPE = STEP;
        setUpPolicy_HDB.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_MCF = new AdaptiveEngine();
    private void setSetUpPolicy_MCF(AdaptiveEngine setUpPolicy_MCF) {
        this.setUpPolicy_MCF = setUpPolicy_MCF;
        //  this.setUpPolicy_MCF.SENSING_INTERVAL = this.setUpPolicy_HDB.SENSING_INTERVAL = setUpPolicy_HCB.SENSING_INTERVAL + ((int) Math.exp(Math.sqrt(setUpPolicy_HCB.SLOPE))*100*(100-defineLevel.BATTERY_HIGH));;
        this.setUpPolicy_MCF.SENSING_INTERVAL = 1000;
        this.setUpPolicy_MCF.SLOPE = 0;
        this.setUpPolicy_MCF.BatteryAwareFunction = "Linear";
    }
    protected void AdaptationPolicy_M_C_F(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_MCF.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_MCF.SLOPE = STEP;
        setUpPolicy_MCF.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_MCB = new AdaptiveEngine();
    private void setSetUpPolicy_MCB(AdaptiveEngine setUpPolicy_MCB) {
        this.setUpPolicy_MCB = setUpPolicy_MCB;
        this.setUpPolicy_MCB.SENSING_INTERVAL = 1000;
        this.setUpPolicy_MCB.SLOPE = 0;
        this.setUpPolicy_MCB.BatteryAwareFunction = "Exponential";
    }
    protected void AdaptationPolicy_M_C_B(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_MCB.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_MCB.SLOPE = STEP;
        setUpPolicy_MCB.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_MDF = new AdaptiveEngine();
    private void setSetUpPolicy_MDF(AdaptiveEngine setUpPolicy_MDF) {
        this.setUpPolicy_MDF = setUpPolicy_MDF;
        this.setUpPolicy_MDF.SENSING_INTERVAL = setUpPolicy_HDF.SENSING_INTERVAL + (setUpPolicy_HDF.SLOPE*100*(100-defineLevel.BATTERY_HIGH));
        this.setUpPolicy_MDF.SLOPE = 2;
        this.setUpPolicy_MDF.BatteryAwareFunction = "Linear";
    }
    protected void AdaptationPolicy_M_D_F(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_MDF.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_MDF.SLOPE = STEP;
        setUpPolicy_MDF.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_MDB = new AdaptiveEngine();
    private void setSetUpPolicy_MDB(AdaptiveEngine setUpPolicy_MDB) {
        this.setUpPolicy_MDB = setUpPolicy_MDB;
        this.setUpPolicy_MDB.SENSING_INTERVAL = setUpPolicy_MDF.SENSING_INTERVAL;
        this.setUpPolicy_MDB.SLOPE = 2;
        this.setUpPolicy_MDB.BatteryAwareFunction = "Exponential";
    }
    protected void AdaptationPolicy_M_D_B(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_MDB.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_MDB.SLOPE = STEP;
        setUpPolicy_MDB.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_LCF = new AdaptiveEngine();
    private void setSetUpPolicy_LCF(AdaptiveEngine setUpPolicy_LCF) {
        this.setUpPolicy_LCF = setUpPolicy_LCF;
        this.setUpPolicy_LCF.SENSING_INTERVAL = 1000;
        this.setUpPolicy_LCF.SLOPE = 0;
        this.setUpPolicy_LCF.BatteryAwareFunction = "Exponential";
    }
    protected void AdaptationPolicy_L_C_F(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_LCF.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_LCF.SLOPE = STEP;
        setUpPolicy_LCF.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_LCB = new AdaptiveEngine();
    private void setSetUpPolicy_LCB(AdaptiveEngine setUpPolicy_LCB) {
        this.setUpPolicy_LCB = setUpPolicy_LCB;
        this.setUpPolicy_LCB.SENSING_INTERVAL = 1000;
        this.setUpPolicy_LCB.SLOPE = 0;
        this.setUpPolicy_LCB.BatteryAwareFunction = "Exponential";
    }
    protected void AdaptationPolicy_L_C_B(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_LCB.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_LCB.SLOPE = STEP;
        setUpPolicy_LCB.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_LDF = new AdaptiveEngine();
    private void setSetUpPolicy_LDF(AdaptiveEngine setUpPolicy_LDF) {
        this.setUpPolicy_LDF = setUpPolicy_LDF;
        this.setUpPolicy_LDF.SENSING_INTERVAL = setUpPolicy_MDF.SENSING_INTERVAL + (setUpPolicy_MDF.SLOPE*100*(100-defineLevel.BATTERY_LOW));
        this.setUpPolicy_LDF.SLOPE = 2;
        this.setUpPolicy_LDF.BatteryAwareFunction = "Exponential";
    }
    protected void AdaptationPolicy_L_D_F(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_LDF.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_LDF.SLOPE = STEP;
        setUpPolicy_LDF.BatteryAwareFunction = BatteryAwareFunction;
    }

    private AdaptiveEngine setUpPolicy_LDB = new AdaptiveEngine();
    private void setSetUpPolicy_LDB(AdaptiveEngine setUpPolicy_LDB) {
        this.setUpPolicy_LDB = setUpPolicy_LDB;
        this.setUpPolicy_LDB.SENSING_INTERVAL = setUpPolicy_LDF.SENSING_INTERVAL;
        this.setUpPolicy_LDB.SLOPE = 2;
        this.setUpPolicy_LDB.BatteryAwareFunction = "Exponential";
    }
    protected void AdaptationPolicy_L_D_B(long INTERVAL, long STEP, String BatteryAwareFunction)
    {
        setUpPolicy_LDB.SENSING_INTERVAL = INTERVAL;
        setUpPolicy_LDB.SLOPE = STEP;
        setUpPolicy_LDB.BatteryAwareFunction = BatteryAwareFunction;
    }

    private int level;
    private Boolean returned = false;
    private boolean isCharging;
    private boolean appInForeground;
    private BroadcastReceiver InformationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
            levelReturned();
        }
    };
    private void levelReturned(){
        returned = true;
    }
    protected int returnLevel() {
        int BATTERY_AWARE_SI = 1000;
        returned = false;
        this.registerReceiver(this.InformationReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }
        catch(Exception e){
            System.out.println("Fallen into exception");
        }
        if (appInForeground)
        {
            if(level >= defineLevel.BATTERY_HIGH)
            {
                if(isCharging)
                {
                    if(setUpPolicy_HCF.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_HCF.SENSING_INTERVAL + (setUpPolicy_HCF.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_HCF.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_HCF.SLOPE))*100*(100-level)));
                    }
                }
                else {
                    if(setUpPolicy_HDF.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_HDF.SENSING_INTERVAL + (setUpPolicy_HDF.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_HDF.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_HDF.SLOPE))*100*(100-level)));
                    }
                }
            }
            else if(level >= defineLevel.BATTERY_MEDIUM)
            {
                if(isCharging)
                {
                    if(setUpPolicy_MCF.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_MCF.SENSING_INTERVAL + (setUpPolicy_MCF.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_MCF.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_MCF.SLOPE))*100*(100-level)));
                    }
                }
                else {
                    if(setUpPolicy_MDF.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_MDF.SENSING_INTERVAL + (setUpPolicy_MDF.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_MDF.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_MDF.SLOPE))*100*(100-level)));
                    }
                }
            }
            else
            {
                if(isCharging)
                {
                    if(setUpPolicy_LCF.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_LCF.SENSING_INTERVAL + (setUpPolicy_LCF.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_LCF.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_LCF.SLOPE))*100*(100-level)));
                    }
                }
                else {
                    if(setUpPolicy_LDF.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_LDF.SENSING_INTERVAL + (setUpPolicy_LDF.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_LDF.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_LDF.SLOPE))*100*(100-level)));
                    }
                }
            }
        }
        else {
            if(level >= defineLevel.BATTERY_HIGH)
            {
                if(isCharging)
                {
                    if(setUpPolicy_HCB.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_HCB.SENSING_INTERVAL + (setUpPolicy_HCB.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_HCB.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_HCB.SLOPE))*100*(100-level)));
                    }
                }
                else {
                    if(setUpPolicy_HDB.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_HDB.SENSING_INTERVAL + (setUpPolicy_HDB.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_HDB.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_HDB.SLOPE))*100*(100-level)));
                    }
                }
            }
            else if(level >= defineLevel.BATTERY_MEDIUM)
            {
                if(isCharging)
                {
                    if(setUpPolicy_MCB.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_MCB.SENSING_INTERVAL + (setUpPolicy_MCB.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_MCB.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_MCB.SLOPE))*100*(100-level)));
                    }
                }
                else {
                    if(setUpPolicy_MDB.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_MDB.SENSING_INTERVAL + (setUpPolicy_MDB.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_MDB.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_MDB.SLOPE))*100*(100-level)));
                    }
                }
            }
            else
            {
                if(isCharging)
                {
                    if(setUpPolicy_LCB.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_LCB.SENSING_INTERVAL + (setUpPolicy_LCB.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_LCB.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_LCB.SLOPE))*100*(100-level)));
                    }
                }
                else {
                    if(setUpPolicy_LDB.BatteryAwareFunction.equals("Linear"))
                    {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_LDB.SENSING_INTERVAL + (setUpPolicy_LDB.SLOPE*100*(100-level)));
                    }
                    else {
                        BATTERY_AWARE_SI = (int) (setUpPolicy_LDB.SENSING_INTERVAL + (Math.exp(Math.sqrt(setUpPolicy_LDB.SLOPE))*100*(100-level)));
                    }
                }
            }
        }
        return BATTERY_AWARE_SI;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefineLevel(defineLevel);
        setSetUpPolicy_HCF(setUpPolicy_HCF);
        setSetUpPolicy_HCB(setUpPolicy_HCB);
        setSetUpPolicy_HDF(setUpPolicy_HDF);
        setSetUpPolicy_HDB(setUpPolicy_HDB);

        setSetUpPolicy_MCF(setUpPolicy_MCF);
        setSetUpPolicy_MCB(setUpPolicy_MCB);
        setSetUpPolicy_MDF(setUpPolicy_MDF);
        setSetUpPolicy_MDB(setUpPolicy_MDB);

        setSetUpPolicy_LCF(setUpPolicy_LCF);
        setSetUpPolicy_LCB(setUpPolicy_LCB);
        setSetUpPolicy_LDF(setUpPolicy_LDF);
        setSetUpPolicy_LDB(setUpPolicy_LDB);
        appInForeground = true;
        this.registerReceiver(this.InformationReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        appInForeground = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        appInForeground = true;
    }
}
