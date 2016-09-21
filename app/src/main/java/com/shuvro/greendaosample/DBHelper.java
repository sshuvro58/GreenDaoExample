package com.shuvro.greendaosample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import SampleDaoModel.DaoMaster;
import SampleDaoModel.DaoSession;
import SampleDaoModel.Person;
import de.greenrobot.dao.database.Database;

/**
 * Created by Mithun Sarker on 9/21/16.
 * For Surroundapps
 */
public class DBHelper {
    DaoSession daoSession;
    Context context;


    public DBHelper(Context context) {
        this.context = context;
        setupDatabase(new SecretsDatabaseKeyHolder(context));
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * Setting up db
     */
    private void setupDatabase(SecretsDatabaseKeyHolder
                                       secretsDatabaseKeyHolder) {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "sample_db", null);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);

        DaoMaster.EncryptedDevOpenHelper helper = new DaoMaster.EncryptedDevOpenHelper(context, "secrets.db");
        Database database = helper.getWritableDatabase(secretsDatabaseKeyHolder.getKey());
        DaoMaster daoMaster = new DaoMaster(database);



        daoSession = daoMaster.newSession();
    }

    public static List<Person> getPersonList(){

        List<Person> personList = Constants.daoSession.getPersonDao().loadAll();

        return personList;
    }

}
