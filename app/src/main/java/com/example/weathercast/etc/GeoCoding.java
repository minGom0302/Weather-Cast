package com.example.weathercast.etc;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoCoding {
    Context context;
    List<Address> list = null;

    public GeoCoding(Context context) {
        this.context = context;
    }

    public Address getLatLng(String addr) {
        Geocoder geoCoder = new Geocoder(context, Locale.KOREA);
        Address address = null;

        try {
            list = geoCoder.getFromLocationName(addr, 3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(list != null) {
            if(list.size() != 0)  {
                address = list.get(0);
            }
        }

        return address;
    }
}
