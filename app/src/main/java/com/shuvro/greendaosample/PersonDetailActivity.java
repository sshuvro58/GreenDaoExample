package com.shuvro.greendaosample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import SampleDaoModel.Address;
import SampleDaoModel.AddressDao;
import SampleDaoModel.Book;
import SampleDaoModel.BookDao;
import SampleDaoModel.Person;
import SampleDaoModel.PersonDao;

/**
 * Author:Mithun Sarker Shuvro
 */

public class PersonDetailActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText city;
    EditText editText;
    Button addBook;
    Button editPerson;
    Button deletePerson;
    Button savePerson;
    RecyclerView bookRecyclerView;
    BookPersonAdapter bookPersonAdapter;
    ArrayList<Book> bookArrayList = new ArrayList<Book>();

    Person person;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        Bundle bundle = getIntent().getExtras();

        person = bundle.getParcelable("person_object");


        name = (EditText) findViewById(R.id.name);
        name.setText(person.getName());
        city = (EditText) findViewById(R.id.city);
        city.setText(person.getAddress().getCity());
        editText = (EditText) findViewById(R.id.editText);
        addBook = (Button) findViewById(R.id.addBook);
        addBook.setOnClickListener(this);
        savePerson = (Button) findViewById(R.id.savePerson);
        savePerson.setOnClickListener(this);
        editPerson = (Button) findViewById(R.id.editPerson);
        deletePerson = (Button) findViewById(R.id.deletePerson);
        editPerson.setOnClickListener(this);
        deletePerson.setOnClickListener(this);

        bookRecyclerView = (RecyclerView) findViewById(R.id.bookRecyclerView);
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookArrayList.addAll(DBHelper.getPersonBookList(person.getId()));
        bookPersonAdapter = new BookPersonAdapter(this,bookArrayList);
        bookRecyclerView.setAdapter(bookPersonAdapter);

        name.setEnabled(false);
        city.setEnabled(false);
        name.setFocusableInTouchMode(false);
        city.setFocusableInTouchMode(false);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addBook){
            if(!editText.getText().toString().equals("")){
                Book book = new Book();
                book.setBookName(editText.getText().toString());
                book.setPersonId(person.getId());

                BookDao bookDao = Constants.daoSession.getBookDao();
                bookDao.insertOrReplace(book);

                bookArrayList.clear();
                bookArrayList.addAll(DBHelper.getPersonBookList(person.getId()));

                bookPersonAdapter.notifyDataSetChanged();
                editText.setText("");
                editText.requestFocus();

            }
        }else if(v.getId()== R.id.editPerson){
            name.setEnabled(true);
            city.setEnabled(true);
            name.setFocusableInTouchMode(true);
            city.setFocusableInTouchMode(true);
            savePerson.setVisibility(View.VISIBLE);
            editPerson.setVisibility(View.GONE);

        }else if(v.getId()== R.id.deletePerson){
            PersonDao personDao = Constants.daoSession.getPersonDao();
            AddressDao addressDao = Constants.daoSession.getAddressDao();
            BookDao bookDao = Constants.daoSession.getBookDao();

            //deleting person address
            addressDao.delete(Constants.daoSession.getAddressDao().queryBuilder()
                    .where(AddressDao.Properties.PersonId.eq(person.getId())).unique());

            //deleting person books
            bookDao.deleteInTx(Constants.daoSession.getBookDao().queryBuilder()
                    .where(BookDao.Properties.PersonId.eq(person.getId())).list());

            personDao.delete(person);
            super.onBackPressed();
        }

        else if(v.getId()== R.id.savePerson){

            PersonDao personDao = Constants.daoSession.getPersonDao();

            Address personAddress = DBHelper.getPersonAddress(person.getId());

            personAddress.setCity(city.getText().toString());

            AddressDao addressDao = Constants.daoSession.getAddressDao();

            addressDao.insertOrReplace(personAddress);

            person.setName(name.getText().toString());
            person.setAddress(personAddress);
            personDao.insertOrReplace(person);

            editPerson.setVisibility(View.VISIBLE);

            savePerson.setVisibility(View.GONE);

            name.setEnabled(false);
            city.setEnabled(false);
            name.setFocusableInTouchMode(false);
            city.setFocusableInTouchMode(false);

        }
    }
}
