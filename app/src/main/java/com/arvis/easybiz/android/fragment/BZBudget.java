package com.arvis.easybiz.android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arvis.easybiz.android.R;
import com.arvis.easybiz.android.event.BackToLocationSelection;
import com.arvis.easybiz.android.event.StartBizLocationSearch;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BZBudget extends Fragment{

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.budget_selection, container, false);

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

    @OnClick(R.id.back_to_loc_selection)
    public void backToLocationSelection(View view){

        EventBus.getDefault().post(new BackToLocationSelection());
    }

    @OnClick(R.id.btn_get_biz_location)
    public void getBusinessLocation(View view){

        EventBus.getDefault().post(new StartBizLocationSearch());
    }

}
