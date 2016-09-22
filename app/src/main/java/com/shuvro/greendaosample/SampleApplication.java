package com.shuvro.greendaosample;

import android.app.Application;

/**
 * Author:Mithun Sarker Shuvro
 */
public class SampleApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        if(Constants.daoSession == null){
            Constants.daoSession = new DBHelper(getApplicationContext()).getDaoSession();
        }

    }
}

