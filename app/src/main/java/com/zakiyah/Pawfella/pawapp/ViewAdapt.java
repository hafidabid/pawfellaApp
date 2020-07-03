package com.zakiyah.Pawfella.pawapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zakiyah.Pawfella.pprojekkk.R;

import java.util.ArrayList;

public class ViewAdapt extends RecyclerView.Adapter<ViewAdapt.holeder> {

    private ArrayList<rumahsakit> dataList;

    public ViewAdapt(ArrayList<rumahsakit> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public holeder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_rumahsakit, viewGroup, false);
        return new holeder(view);
    }

    public class holeder extends RecyclerView.ViewHolder {
        private TextView namaRs, alamatRs, keteranganRs, ratingRs;
        private Button btn_forcall;

        public holeder(@NonNull View itemView) {
            super(itemView);
            namaRs = (TextView) itemView.findViewById(R.id.emerClinicName);
            alamatRs = (TextView) itemView.findViewById(R.id.emergencyClinicaddress);
            keteranganRs = (TextView) itemView.findViewById(R.id.emerClinicInfo);
            ratingRs = (TextView) itemView.findViewById(R.id.textView_clinicrate);
            btn_forcall = (Button) itemView.findViewById(R.id.button3_callclinic);
        }
    }

    @Override
    public void onBindViewHolder(final holeder viewHolder, int i) {
        viewHolder.namaRs.setText(dataList.get(i).nama);
        viewHolder.alamatRs.setText(dataList.get(i).alamat);
        viewHolder.ratingRs.setText("Rating = " + dataList.get(i).rate + " / 5.0");
        viewHolder.keteranganRs.setText(dataList.get(i).keterangan);
        viewHolder.btn_forcall.setOnClickListener(new View.OnClickListener() {
            private int onlyinteger;
            String pnumb;
            String dial = "tel:";

            @Override
            public void onClick(View v) {
                pnumb = dataList.get(onlyinteger).phone;
                dial = dial + pnumb;
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    System.out.println("ga kenek cok");
                }else {
                    v.getContext().startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }

                }
                private View.OnClickListener hmm(int n){
                    this.onlyinteger = n;
                    System.out.println(this.onlyinteger);
                    return this;
                }
            }.hmm(i));

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    private void makePhoneCall(String pnumb, Context c){
        if(ContextCompat.checkSelfPermission(c,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            //ActivityCompat.requestPermissions(c,new String[]{Manifest.permission.CALL_PHONE},1);
        }else {
            String dial = "tel:"+pnumb;
            c.startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }
    }
}
