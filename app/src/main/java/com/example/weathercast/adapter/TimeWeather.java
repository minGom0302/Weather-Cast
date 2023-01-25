package com.example.weathercast.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathercast.R;
import com.example.weathercast.dto.Weather_Time;
import java.util.List;

public class TimeWeather extends RecyclerView.Adapter<TimeWeather.MyViewHolder>{
    List<Weather_Time> list;

    public TimeWeather(List<Weather_Time> list) {
        this.list = list;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView timeTv;
        private final TextView tempTv;
        private final TextView percentTv;
        private final TextView volumeTv;
        private final ImageView img;
        private final LinearLayout layoutVolume;

        public MyViewHolder(@NonNull View view) {
            super(view);
            timeTv = view.findViewById(R.id.timeWeather_time);
            tempTv = view.findViewById(R.id.timeWeather_temperature);
            percentTv = view.findViewById(R.id.timeWeather_percent);
            volumeTv = view.findViewById(R.id.timeWeather_volume);
            img = view.findViewById(R.id.timeWeather_img);
            layoutVolume = view.findViewById(R.id.timeWeather_layoutVolume);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_time_weather, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Weather_Time item = list.get(position);
        holder.tempTv.setText(item.getTemp());
        holder.percentTv.setText(item.getPop());

        if(item.getTime().equals("00")) {
            holder.timeTv.setText(item.getDay());
        } else {
            holder.timeTv.setText(item.getTime() + "시");
        }

        if(item.getPcp().equals("강수없음")) {
            holder.layoutVolume.setVisibility(View.GONE);
        } else {
            holder.volumeTv.setText(item.getPcp());
        }

        String PTYValue = item.getPty();
        String SKYValue = item.getSky();

        switch (PTYValue) {
            case "0":
                switch (SKYValue) {
                    case "1":
                        holder.img.setImageResource(R.drawable.icon_sun);
                        break;
                    case "3":
                        holder.img.setImageResource(R.drawable.icon_cloudsun);
                        break;
                    case "4":
                        holder.img.setImageResource(R.drawable.icon_clouds);
                        break;
                }
                break;
            case "1":
            case "4":
                holder.img.setImageResource(R.drawable.icon_rain);
                break;
            case "2":
                holder.img.setImageResource(R.drawable.icon_snowing);
                break;
            case "3":
                holder.img.setImageResource(R.drawable.icon_snow);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
