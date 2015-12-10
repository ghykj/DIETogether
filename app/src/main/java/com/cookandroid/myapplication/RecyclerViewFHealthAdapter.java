package com.cookandroid.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LGPC on 2015-12-04.
 */
public class RecyclerViewFHealthAdapter extends RecyclerView.Adapter<ListFHealthViewHolder>{

    private Context context;
    private List<ListFHealthItems> items;
    int itemLayout;
    HealthDBManager healthDBmanager;
    NetworkManager networkManager;

    public RecyclerViewFHealthAdapter(Context context, List<ListFHealthItems> items, int itemLayout){
        this.context = context;
        this.items = items;
        this.itemLayout=itemLayout;
    }
    @Override
    public ListFHealthViewHolder onCreateViewHolder(final ViewGroup viewGroup,int viewType){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_low,null);
        return new ListFHealthViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(final ListFHealthViewHolder ListFHealthViewHolder, int position) {

    }
}
