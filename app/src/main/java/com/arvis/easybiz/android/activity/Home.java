package com.arvis.easybiz.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.arvis.easybiz.android.R;
import com.arvis.easybiz.android.service.LocationFindUIFlow;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    LocationFindUIFlow uiFlow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        ButterKnife.bind(this);

        initBurgerMenu();

        uiFlow = new LocationFindUIFlow(getFragmentManager(), this);
    }

    private void initBurgerMenu(){


        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.draw_open,
                R.string.draw_close
        );

        drawerLayout.addDrawerListener(drawerToggle);

        ActionBar aActionBar = getSupportActionBar();
        if (aActionBar != null) {
            aActionBar.setHomeButtonEnabled(true);
            aActionBar.setDisplayHomeAsUpEnabled(true);
            aActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();

        uiFlow.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();

        uiFlow.onPause();
    }
}
