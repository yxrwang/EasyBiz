package com.arvis.easybiz.android.service;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;

import com.arvis.easybiz.android.R;
import com.arvis.easybiz.android.activity.ResultMap;
import com.arvis.easybiz.android.event.BackToBizTypeSelection;
import com.arvis.easybiz.android.event.BackToLocationSelection;
import com.arvis.easybiz.android.event.BackToStart;

import com.arvis.easybiz.android.event.BusinessTypeSelected;
import com.arvis.easybiz.android.event.GetStarted;
import com.arvis.easybiz.android.event.LocationSelected;
import com.arvis.easybiz.android.event.StartBizLocationSearch;
import com.arvis.easybiz.android.fragment.BZBudget;
import com.arvis.easybiz.android.fragment.BZSub;

import com.arvis.easybiz.android.fragment.BZType;
import com.arvis.easybiz.android.fragment.GetStart;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LocationFindUIFlow {

    private FragmentManager fragmentManager;

    private Context context;

    public LocationFindUIFlow(FragmentManager fragmentManager, Context context){

        this.fragmentManager = fragmentManager;

        this.context = context;

        fragmentManager.beginTransaction().replace(R.id.container, new GetStart()).commit();
    }

    public void onResume(){

        EventBus.getDefault().register(this);
    }

    public void onPause(){

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void onBusinessTypeSelected(BusinessTypeSelected typeSelected){

        fragmentManager.beginTransaction().replace(R.id.container, new BZSub()).commit();
    }

    @Subscribe
    public void backToBZType(BackToBizTypeSelection backToBizTypeSelection){

        fragmentManager.beginTransaction().replace(R.id.container, new BZType()).commit();
    }

    @Subscribe
    public void getStarted(GetStarted getStarted){

        fragmentManager.beginTransaction().replace(R.id.container, new BZType()).commit();
    }

    @Subscribe
    public void backToStart(BackToStart backToStart){

        fragmentManager.beginTransaction().replace(R.id.container, new GetStart()).commit();
    }

    @Subscribe
    public void backToBZLocation(BackToLocationSelection backToLocationSelection){

        fragmentManager.beginTransaction().replace(R.id.container, new BZSub()).commit();
    }

    @Subscribe
    public void businessLocationSelected(LocationSelected typeSelected){

        fragmentManager.beginTransaction().replace(R.id.container, new BZBudget()).commit();
    }

    @Subscribe
    public void getBestBusinessLocation(StartBizLocationSearch startBizLocationSearch){

        context.startActivity(new Intent(context, ResultMap.class));

        fragmentManager.beginTransaction().replace(R.id.container, new GetStart()).commit();
    }

}
