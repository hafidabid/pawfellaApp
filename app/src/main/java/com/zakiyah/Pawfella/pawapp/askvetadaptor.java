package com.zakiyah.Pawfella.pawapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zakiyah.Pawfella.pprojekkk.R;

import java.util.ArrayList;

public class askvetadaptor extends RecyclerView.Adapter<askvetadaptor.holder> {
    private ArrayList<listdokter> datalist;
    private  Context context;
    public askvetadaptor(ArrayList<listdokter> a){
        this.datalist = a;
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_askvet,viewGroup,false);
        context = viewGroup.getContext();
        return new holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {
        private TextView namadr, ratingdr, praktekdr, experiencedr;
        private Button detailbutton, chatbutton;

        public holder(@NonNull View itemView) {
            super(itemView);
            namadr = (TextView) itemView.findViewById(R.id.vetClinicName);
            ratingdr = (TextView) itemView.findViewById(R.id.vetRating);
            praktekdr = (TextView) itemView.findViewById(R.id.vetLocPraktek);
            experiencedr = (TextView) itemView.findViewById(R.id.vetExperience);
            detailbutton = (Button) itemView.findViewById(R.id.button2_vetdetail);
            chatbutton = (Button) itemView.findViewById(R.id.button3_chatvet);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull holder viewHolder, int i) {
            viewHolder.namadr.setText(datalist.get(i).name);
            viewHolder.experiencedr.setText(datalist.get(i).experience);
            viewHolder.praktekdr.setText(datalist.get(i).clinic);
            viewHolder.ratingdr.setText("Rating = " + datalist.get(i).rating + " / 5.0");
        viewHolder.detailbutton.setOnClickListener(new View.OnClickListener() {
            int onlyinteger;
            //vetDetail vd = new vetDetail(datalist.get(onlyinteger).name,datalist.get(onlyinteger).rating,datalist.get(onlyinteger).graduate,datalist.get(onlyinteger).clinic,datalist.get(onlyinteger).startyear,datalist.get(onlyinteger).experience);
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,vetDetail.class);
                intent.putExtra("nama dokter",datalist.get(onlyinteger).name);
                intent.putExtra("rating dokter",datalist.get(onlyinteger).rating);
                intent.putExtra("graduate from",datalist.get(onlyinteger).graduate);
                intent.putExtra("klinik",datalist.get(onlyinteger).clinic);
                intent.putExtra("pengalaman kerja",datalist.get(onlyinteger).experience);
                intent.putExtra("start kerja",datalist.get(onlyinteger).startyear);
                //System.out.println(datalist.get(onlyinteger).name);
                v.getContext().startActivity(intent);
                ((Activity)context).finish();
            }
            private View.OnClickListener hmm(int n){
                this.onlyinteger = n;
                System.out.println(this.onlyinteger);
                return this;
            }
        }.hmm(i));

            viewHolder.chatbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogkamingsun comingsoonDialog = new dialogkamingsun();
                    comingsoonDialog.judul="Coming Soon Peeps !";
                    comingsoonDialog.pesan="this feature is on construction right now, stay update  for further notification";
                    comingsoonDialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"example Dialog");
                }
            });
    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() : 0;
    }
}
