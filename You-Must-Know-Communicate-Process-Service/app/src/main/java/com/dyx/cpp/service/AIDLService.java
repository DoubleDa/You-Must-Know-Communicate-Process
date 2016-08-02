package com.dyx.cpp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * project name：You-Must-Know-Communicate-Process-Service
 * class describe：
 * create person：dayongxin
 * create time：16/8/2 下午11:32
 * alter person：dayongxin
 * alter time：16/8/2 下午11:32
 * alter remark：
 */
public class AIDLService extends Service {
    private CatBinder mCatBinder;
    private Timer timer = new Timer();
    private String[] colors = {"red", "blue", "green", "black"};
    private double[] weights = {2.3, 2.1, 2.0, 1.9};

    private String color;
    private double weight;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mCatBinder;
    }

    private class CatBinder extends ICat.Stub {

        @Override
        public String getColor() throws RemoteException {
            return color;
        }

        @Override
        public double getWeight() throws RemoteException {
            return weight;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCatBinder = new CatBinder();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * 4);
                color = colors[rand];
                weight = weights[rand];
            }
        }, 0, 1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
