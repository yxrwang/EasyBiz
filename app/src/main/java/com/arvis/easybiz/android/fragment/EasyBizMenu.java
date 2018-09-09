package com.arvis.easybiz.android.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arvis.easybiz.android.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EasyBizMenu extends Fragment{

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menu, container, false);

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
}
