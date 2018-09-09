package com.arvis.easybiz.android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.arvis.easybiz.android.R;
import com.arvis.easybiz.android.event.BackToBizTypeSelection;
import com.arvis.easybiz.android.event.LocationSelected;
import com.arvis.easybiz.android.model.ProposedLocation;
import com.arvis.easybiz.android.view.SubAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BZSub extends Fragment implements AdapterView.OnItemSelectedListener{

    Unbinder unbinder;

    @BindView(R.id.selection_area)
    Spinner areaSelection;

    ProposedLocation selectedLocation = ProposedLocation.MelbourneCBD;

    ProposedLocation[] locations = {ProposedLocation.MelbourneCBD, ProposedLocation.SouthSub, ProposedLocation.EastSub, ProposedLocation.NorthernSub, ProposedLocation.WesternSub};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.location_selection, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {

        if(unbinder != null){

            unbinder.unbind();
        }

        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        areaSelection.setOnItemSelectedListener(this);

        areaSelection.setAdapter(new SubAdapter(locations));
    }

    @OnClick(R.id.confirm_area_selection)
    public void onBizSubSelected(View view){

        EventBus.getDefault().postSticky(new LocationSelected(selectedLocation));
    }

    @OnClick(R.id.back_to_type_selection)
    public void backToBizSelection(View view){

        EventBus.getDefault().post(new BackToBizTypeSelection());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selectedLocation = locations[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
