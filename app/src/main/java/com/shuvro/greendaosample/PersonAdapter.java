package com.shuvro.greendaosample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import SampleDaoModel.Person;

/**
 * Created by paranoid on 9/22/2016.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    Context context;
    ArrayList<Person> personArrayList;

    public PersonAdapter(Context context, ArrayList<Person> personArrayList) {
        this.context = context;
        this.personArrayList = personArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.person_row,parent,false);

        return new ViewHolder(view1);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = personArrayList.get(position);

        holder.textView.setText("Name: "+person.getName()+"  city: "+person.getAddress().getCity());

    }

    @Override
    public int getItemCount() {
        return personArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
