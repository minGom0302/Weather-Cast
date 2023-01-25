package com.example.weathercast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathercast.R;
import com.example.weathercast.dto.Weather_Day;

import java.util.List;

public class DayWeather extends RecyclerView.Adapter<DayWeather.MyViewHolder>{
    List<Weather_Day> list;

    public DayWeather(List<Weather_Day> list) {
        this.list = list;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateTv;
        private final TextView tempAM;
        private final TextView tempPM;
        private final ImageView imgAM;
        private final ImageView imgPM;

        public MyViewHolder(@NonNull View view) {
            super(view);
            dateTv = view.findViewById(R.id.listview_day_dateTv);
            tempAM = view.findViewById(R.id.listview_day_tempAM);
            tempPM = view.findViewById(R.id.listview_day_tempPM);
            imgAM = view.findViewById(R.id.listview_day_imgAM);
            imgPM = view.findViewById(R.id.listview_day_imgPM);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_day_weather, parent, false);
        return new DayWeather.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Weather_Day dto = list.get(position);
        holder.dateTv.setText(dto.getDay());
        holder.tempAM.setText(dto.getMinTemp());
        holder.tempPM.setText(dto.getMaxTemp());

        String maxPty = dto.getMaxPty();
        String maxSky = dto.getMaxSky();
        String minPty = dto.getMinPty();
        String minSky = dto.getMinSky();


        switch (maxPty) {
            case "0":
                switch (maxSky) {
                    case "1":
                        holder.imgAM.setImageResource(R.drawable.icon_sun);
                        break;
                    case "3":
                        holder.imgAM.setImageResource(R.drawable.icon_cloudsun);
                        break;
                    case "4":
                        holder.imgAM.setImageResource(R.drawable.icon_clouds);
                        break;
                }
                break;
            case "1":
            case "4":
                holder.imgAM.setImageResource(R.drawable.icon_rain);
                break;
            case "2":
                holder.imgAM.setImageResource(R.drawable.icon_snowing);
                break;
            case "3":
                holder.imgAM.setImageResource(R.drawable.icon_snow);
                break;
        }

        switch (minPty) {
            case "0":
                switch (minSky) {
                    case "1":
                        holder.imgPM.setImageResource(R.drawable.icon_sun);
                        break;
                    case "3":
                        holder.imgPM.setImageResource(R.drawable.icon_cloudsun);
                        break;
                    case "4":
                        holder.imgPM.setImageResource(R.drawable.icon_clouds);
                        break;
                }
                break;
            case "1":
            case "4":
                holder.imgPM.setImageResource(R.drawable.icon_rain);
                break;
            case "2":
                holder.imgPM.setImageResource(R.drawable.icon_snowing);
                break;
            case "3":
                holder.imgPM.setImageResource(R.drawable.icon_snow);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
