package com.arvis.easybiz.android.event;

import com.arvis.easybiz.android.model.HospitalityBusiness;

import java.util.List;

public class HospitalityBusinessFetched {

    public final List<HospitalityBusiness> hospitalityBusinesses;

    public HospitalityBusinessFetched(List<HospitalityBusiness> businesses){

        this.hospitalityBusinesses = businesses;
    }

}
