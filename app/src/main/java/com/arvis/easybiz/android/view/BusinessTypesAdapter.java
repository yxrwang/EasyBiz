package com.arvis.easybiz.android.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.arvis.easybiz.android.R;
import com.arvis.easybiz.android.model.BusinessType;



public class BusinessTypesAdapter extends BaseAdapter{

    BusinessType[] businessTypes = {BusinessType.TechStartup, BusinessType.Cafe, BusinessType.Retail, BusinessType.Hotel, BusinessType.Pub, BusinessType.Showroom};

    @Override
    public int getCount() {
        return businessTypes.length;
    }

    @Override
    public Object getItem(int position) {
        return businessTypes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        ((FontedTextView)convertView).setText(((BusinessType)getItem(position)).name());

        return convertView;
    }


}
