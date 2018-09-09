package com.arvis.easybiz.android.service;

import com.arvis.easybiz.android.comm.EBHttpClient;
import com.arvis.easybiz.android.event.DataAPIError;
import com.arvis.easybiz.android.event.HospitalityBusinessFetched;
import com.arvis.easybiz.android.model.HospitalityBusiness;
import com.arvis.easybiz.android.model.ProposedLocation;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BarAndPubDataService {

    private String PUB_BAR_DATA_URL = "https://data.melbourne.vic.gov.au/resource/csij-zzny.json";

    public void getBarAndPubData(ProposedLocation location){

        switch (location){

            case EastSub:

                PUB_BAR_DATA_URL += "?$where=within_circle(location, -37.821517, 145.125254, 300)";

                break;

            case MelbourneCBD:

                PUB_BAR_DATA_URL += "?$where=within_circle(location, -37.812333, 144.961945, 300)";

                break;

            case NorthernSub:

                PUB_BAR_DATA_URL += "?$where=within_circle(location, -37.800702, 144.966900, 300)";

                break;

            case WesternSub:

                PUB_BAR_DATA_URL += "?$where=within_circle(location, -37.800559, 144.906483, 300)";

                break;

            case SouthSub:

                PUB_BAR_DATA_URL += "?$where=within_circle(location, -37.864096, 144.981769, 300)";

                break;
        }

        EBHttpClient.getInstance().setGetRequest(PUB_BAR_DATA_URL, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                EventBus.getDefault().postSticky(new DataAPIError(e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()){

                    try {

                        JSONArray jsonArray = new JSONArray(response.body().string());

                        List<HospitalityBusiness> hospitalityBusinesses = new ArrayList<>();

                        for(int i=0; i<jsonArray.length();i++){

                            hospitalityBusinesses.add(new Gson().fromJson(jsonArray.optJSONObject(i).toString(), HospitalityBusiness.class));
                        }

                        EventBus.getDefault().postSticky(new HospitalityBusinessFetched(hospitalityBusinesses));

                    } catch (JSONException e) {

                        EventBus.getDefault().postSticky(new DataAPIError(e.getMessage()));
                    }
                }
            }
        });
    }
}
