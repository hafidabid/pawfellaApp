package com.zakiyah.Pawfella.pawapp;

import android.os.AsyncTask;

public class getSinkronClass extends AsyncTask<Void,Void,String> {
    String link;
    String res;
    public getSinkronClass(){}
    public getSinkronClass(String yourURL){link=yourURL;}
    @Override
    protected String doInBackground(Void... voids) {
        requestHandler rh = new requestHandler();
        res = rh.sendGetRequest(link);
        return res;
    }
}
