package com.arvis.easybiz.android.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.arvis.easybiz.android.R;

import com.arvis.easybiz.android.model.ProposedLocation;

public class SubAdapter extends BaseAdapter{

    private ProposedLocation[] locations;

    public SubAdapter(ProposedLocation[] locations){

        this.locations = locations;
    }

    @Override
    public int getCount() {
        return locations.length;
    }

    @Override
    public ProposedLocation getItem(int position) {
        return locations[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        ((FontedTextView)convertView).setText(getItem(position).name());

        return convertView;
    }
}
