package com.shuvro.greendaosample;

import android.content.Intent;
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
import SampleDaoModel.Person;
import SampleDaoModel.PersonDao;

/**
 * Author:Mithun Sarker Shuvro
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerview;
    EditText name;
    EditText city;
    Button button;
    ArrayList<Person> personArrayList = new ArrayList<Person>();
    PersonAdapter personAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        name = (EditText) findViewById(R.id.name);
        city = (EditText) findViewById(R.id.city);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(this);

        personArrayList.addAll(DBHelper.getPersonList());

        personAdapter = new PersonAdapter(this,personArrayList);

        recyclerview.setAdapter(personAdapter);
        recyclerview.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerview, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this,PersonDetailActivity.class);
                Person person = personArrayList.get(position);
                intent.putExtra("person_object",person);
                intent.putExtra("person_id",person.getId());
                intent.putExtra("person_name",person.getName());
                intent.putExtra("person_city",person.getAddress().getCity());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        personArrayList.clear();
        personArrayList.addAll(DBHelper.getPersonList());
        personAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            if(!name.getText().toString().equals("") && !city.getText().toString().equals("")){
                Person person = new Person();
                person.setName(name.getText().toString());
                PersonDao personDao = Constants.daoSession.getPersonDao();
                personDao.insertOrReplace(person);

                Address address = new Address();
                address.setCity(city.getText().toString());
                AddressDao addressDao = Constants.daoSession.getAddressDao();
                addressDao.insertOrReplace(address);


                personArrayList.clear();
                personArrayList.addAll(DBHelper.getPersonList());
                personAdapter.notifyDataSetChanged();


                name.setText("");
                city.setText("");
                name.requestFocus();


            }
        }
    }
}
