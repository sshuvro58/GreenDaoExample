package com.shuvro.greendaosample;

import android.content.Context;

import java.util.List;

import SampleDaoModel.Address;
import SampleDaoModel.AddressDao;
import SampleDaoModel.Book;
import SampleDaoModel.BookDao;
import SampleDaoModel.DaoMaster;
import SampleDaoModel.DaoSession;
import SampleDaoModel.Person;
import de.greenrobot.dao.database.Database;

/**
 * Author:Mithun Sarker Shuvro
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


        //encrypted database with plain secret key
        DaoMaster.EncryptedDevOpenHelper helper = new DaoMaster.EncryptedDevOpenHelper(context, "secrets.db");
        Database database = helper.getWritableDatabase("12345678");
        DaoMaster daoMaster = new DaoMaster(database);

        //encrypted database with generated  secret key which is saved in shared pref
//        DaoMaster.EncryptedDevOpenHelper helper = new DaoMaster.EncryptedDevOpenHelper(context, "secrets.db");
//        Database database = helper.getWritableDatabase("12345678");
//        DaoMaster daoMaster = new DaoMaster(database);



        daoSession = daoMaster.newSession();
    }

    public static List<Person> getPersonList(){

        List<Person> personList = Constants.daoSession.getPersonDao().loadAll();

        return personList;
    }


    public static List<Book> getPersonBookList(long peronId){

        List<Book> bookList = Constants.daoSession.getBookDao().queryBuilder().where(BookDao.Properties.PersonId.eq(peronId)).list();

        return bookList;
    }


    public static Address getPersonAddress(long personId){
        return Constants.daoSession.getAddressDao().queryBuilder().where(AddressDao.Properties.PersonId.eq(personId)).unique();
    }
}
