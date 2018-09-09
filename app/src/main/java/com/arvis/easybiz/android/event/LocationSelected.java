package com.arvis.easybiz.android.event;

import com.arvis.easybiz.android.model.ProposedLocation;

public class LocationSelected {

    public final ProposedLocation location;

    public LocationSelected(ProposedLocation location){

        this.location = location;
    }
}
