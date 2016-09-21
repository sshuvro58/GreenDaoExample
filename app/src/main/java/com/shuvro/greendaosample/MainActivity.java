package com.shuvro.greendaosample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import SampleDaoModel.Address;
import SampleDaoModel.AddressDao;
import SampleDaoModel.Person;
import SampleDaoModel.PersonDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerview;
    EditText editText;
    Button button;
    ArrayList<Person> personArrayList = new ArrayList<Person>();
    PersonAdapter personAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        editText = (EditText) findViewById(R.id.name);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        personArrayList.addAll(DBHelper.getPersonList());

        personAdapter = new PersonAdapter(this,personArrayList);

        recyclerview.setAdapter(personAdapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            if(!editText.getText().toString().equals("")){
                Person person = new Person();
                person.setName("John");
                PersonDao personDao = Constants.daoSession.getPersonDao();
                personDao.insertOrReplace(person);

                Address address = new Address();
                address.setCity("Dhk");
                AddressDao addressDao = Constants.daoSession.getAddressDao();
                addressDao.insertOrReplace(address);
                personAdapter.notifyDataSetChanged();



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        personAdapter.notifyDataSetChanged();
                        recyclerview.invalidate();
                    }
                });

            }
        }
    }
}
