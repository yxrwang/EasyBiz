package com.arvis.easybiz.android.event;

import com.arvis.easybiz.android.model.BusinessType;

public class BusinessTypeSelected {

    public final BusinessType businessType;

    public BusinessTypeSelected(BusinessType businessType){

        this.businessType = businessType;
    }
}
