package com.cookandroid.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by LGPC on 2015-12-04.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ListLowViewHolder>{

    private Context context;
    private List<ListItems> items;
    int itemLayout;

    public RecyclerViewAdapter(Context context, List<ListItems>  items, int itemLayout){
        this.context = context;
        this.items = items;
        this.itemLayout=itemLayout;
    }
    @Override
    public ListLowViewHolder onCreateViewHolder(final ViewGroup viewGroup,int viewType){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_low,null);
        return new ListLowViewHolder(v);
       /* ListLowViewHolder holder = new ListLowViewHolder(v);

        holder.layout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TextView healthName = (TextView) v.findViewById(R.id.textView14);
                String healthNameStr = healthName.getText().toString();
                TextView healthNum = (TextView) v.findViewById(R.id.textView15);

                Intent intent = new Intent(mContext,HealthInfoActivity.class);
                intent.putExtra("health name",healthNameStr);
                mContext.startActivity(intent);*/
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(ListLowViewHolder listLowViewHolder, int position){
        final ListItems item = items.get(position);

        listLowViewHolder.healthName.setText(item.getHealthName());
        listLowViewHolder.healthNum.setText(item.getHealthNum());
        listLowViewHolder.checkBox.setSelected(item.isCheck());

        listLowViewHolder.delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


            }
        });

    }


}
