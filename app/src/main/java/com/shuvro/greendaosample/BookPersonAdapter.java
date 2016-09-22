package com.shuvro.greendaosample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import SampleDaoModel.Book;

/**
 * Author:Mithun Sarker Shuvro
 */
public class BookPersonAdapter extends RecyclerView.Adapter<BookPersonAdapter.ViewHolder> {
    Context context;
    ArrayList<Book> bookArrayList;

    public BookPersonAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.person_row,parent,false);

        return new ViewHolder(view1);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = bookArrayList.get(position);

        holder.textView.setText("Name: "+book.getBookName());

    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
