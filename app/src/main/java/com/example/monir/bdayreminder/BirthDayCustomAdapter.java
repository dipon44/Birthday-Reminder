package com.example.monir.bdayreminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by monir on 31-Jul-18.
 */

public class BirthDayCustomAdapter extends BaseAdapter {

    private  Context context;
    private  ArrayList<BirthDayModel> birthdayList;
    public BirthDayCustomAdapter(Context context, ArrayList<BirthDayModel> birthdayList)
    {
         this.context=context;
         this.birthdayList=birthdayList;
    }
    @Override
    public int getCount() {
        return birthdayList.size();
    }

    @Override
    public Object getItem(int position) {
        return birthdayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder
    {
        TextView friendName;
        TextView friendBday;
        //ImageView friendImage;

        public ViewHolder(View view)
        {
             friendName=view.findViewById(R.id.layoutNameId);
             friendBday=view.findViewById(R.id.layoutbdayId);
             //friendImage=view.findViewById(R.id.layoutimageid);

        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;

        if(convertView==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.custom_list_view,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        BirthDayModel birthDayModel= (BirthDayModel) getItem(position);
        viewHolder.friendName.setText(birthDayModel.getName());
        viewHolder.friendBday.setText(birthDayModel.getDate());
        //viewHolder.friendImage.setImageBitmap(birthDayModel.);

        return convertView;
    }
}
