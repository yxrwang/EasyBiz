package com.arvis.easybiz.android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.arvis.easybiz.android.R;
import com.arvis.easybiz.android.event.BackToStart;
import com.arvis.easybiz.android.event.BusinessTypeSelected;
import com.arvis.easybiz.android.model.BusinessType;
import com.arvis.easybiz.android.view.BusinessTypesAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BZType extends Fragment implements AdapterView.OnItemSelectedListener{

    Unbinder unbinder;

    @BindView(R.id.selection_type_of_biz)
    Spinner typeSelection;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.biz_type_selection, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        typeSelection.setOnItemSelectedListener(this);

        typeSelection.setAdapter(new BusinessTypesAdapter());
    }

    @Override
    public void onDestroyView() {

        if(unbinder != null){

            unbinder.unbind();
        }

        super.onDestroyView();

    }

    @OnClick(R.id.btn_cancel)
    public void backToStart(View view){

        EventBus.getDefault().post(new BackToStart());
    }

    @OnClick(R.id.confirm_type_of_biz)
    public void bizTypeSelected(View view){

        EventBus.getDefault().post(new BusinessTypeSelected(BusinessType.Cafe));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
