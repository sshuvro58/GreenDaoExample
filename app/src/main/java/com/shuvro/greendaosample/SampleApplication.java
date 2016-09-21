package com.shuvro.greendaosample;

import android.app.Application;

/**
 * Created by Mithun Sarker on 9/21/16.
 * For Surroundapps
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

