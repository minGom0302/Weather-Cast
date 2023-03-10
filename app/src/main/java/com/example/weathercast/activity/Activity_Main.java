package com.example.weathercast.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weathercast.R;
import com.example.weathercast.adapter.DayWeather;
import com.example.weathercast.adapter.TimeWeather;
import com.example.weathercast.dto.Weather_Day;
import com.example.weathercast.dto.Weather_Time;
import com.example.weathercast.dto.Item;
import com.example.weathercast.dto.Weather;
import com.example.weathercast.etc.BackPressed;
import com.example.weathercast.etc.GeoCoding;
import com.example.weathercast.etc.GpsTransfer;
import com.example.weathercast.interface_zip.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("SimpleDateFormat")
public class Activity_Main extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    BackPressed backPressed = new BackPressed(this);
    GeoCoding geoCoding = new GeoCoding(this);
    GpsTransfer gpsTransfer;

    SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat setFormatDate = new SimpleDateFormat("yyyy.MM.dd");
    SimpleDateFormat formatTimer = new SimpleDateFormat("HH00");
    SimpleDateFormat setFormatTimer = new SimpleDateFormat("HH:mm");
    ProgressDialog progressDialog;

    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout infoLayout;
    RecyclerView timeWeatherRv, dayWeatherRv;
    TextView nowTimeTv, localTv, nowTempTv, windTv, humidityTv;
    EditText addrEt;
    Button searchBtn;
    ImageView weatherImg;

    String TAG = "Activity_Main";

    List<Weather_Time> weatherList = new ArrayList<>();
    List<Weather_Day> dayDTO = new ArrayList<>();
    List<Item> itemList;
    String x; // ?????? ??????
    String y; // ?????? ??????
    String baseDate; // ?????? ?????? ???, ?????? ??????
    String baseDate2;
    String baseTime; // ?????? ?????????
    String nowTime; // ?????? ?????????


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = ProgressDialog.show(Activity_Main.this, "?????? ??? ...", "Please Wait...", true, false);

        swipeRefreshLayout = findViewById(R.id.mainSwipeLayout);
        infoLayout = findViewById(R.id.main_infoLayout);
        timeWeatherRv = findViewById(R.id.main_timeWeatherRv);
        dayWeatherRv = findViewById(R.id.main_dayWeatherRv);
        nowTimeTv = findViewById(R.id.main_nowTimeTv);
        localTv = findViewById(R.id.main_localTv);
        nowTempTv = findViewById(R.id.main_nowTemTv);
        windTv = findViewById(R.id.main_windTv);
        humidityTv = findViewById(R.id.main_humidityTv);
        addrEt = findViewById(R.id.main_addrEt);
        searchBtn = findViewById(R.id.main_searchBtn);
        weatherImg = findViewById(R.id.main_weatherImg);

        recyclerViewSetting();

        // swipe ???????????? ????????? ??????
        swipeRefreshLayout.setOnRefreshListener(this);

        searchBtn.setOnClickListener(v -> {
            progressDialog.show();
            hideKeyboard();
            weatherSearch();
        });
    }

    // swipe ???????????? ?????????
    @Override
    public void onRefresh() {
        // ???????????? ??? ??? ??????
        weatherSearch();
        // ???????????? ?????? ??????
        swipeRefreshLayout.setRefreshing(false);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void weatherSearch() {
        String text = addrEt.getText().toString();
        Address address = geoCoding.getLatLng(text);
        if(address != null) {
            localTv.setText(text);
            gpsTransfer = new GpsTransfer();
            GpsTransfer.LatXLngY  latXLngY = gpsTransfer.transfer(0, address.getLatitude(), address.getLongitude());
            x = String.valueOf((int) latXLngY.x);
            y = String.valueOf((int) latXLngY.y);
            getDayAndTime();
        } else {
            Toast.makeText(this, "???????????? ?????? ????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void getDayAndTime() {
        // ??????????????? ?????? ?????? ????????? ?????? ?????? (?????? ???????????? -1??????)
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // API ?????? ?????? ?????? > 20220101
        baseDate = formatDate.format(date);
        baseDate2 = baseDate;
        // API ?????? ?????? ?????? > 0200 0500 0800 1100 1400 1700 2000 2300 ??? 8??? ??????
        nowTime = formatTimer.format(date);
        int time = Integer.parseInt(nowTime);


        // nowTime setting
        String setDate = setFormatDate.format(date) + " ";
        String setTime = setFormatTimer.format(date);
        nowTimeTv.setText(setDate + setTime);

        // ????????? ?????? ?????? ?????? ??????
        if(time < 200) {
            baseTime = "2300";
            baseDate2 = String.valueOf(Integer.parseInt(baseDate2) - 1);
            if(baseDate2.length() == 3) {
                baseDate2 = "0" + baseDate2;
            }
        } else if(time < 500) {
            baseTime = "0200";
        } else if(time < 800) {
            baseTime = "0500";
        } else if(time < 1100) {
            baseTime = "0800";
        } else if(time < 1400) {
            baseTime = "1100";
        } else if(time < 1700) {
            baseTime = "1400";
        } else if(time < 2000) {
            baseTime = "1700";
        } else if(time < 2300) {
            baseTime = "2000";
        } else {
            baseTime = "2300";
        }

        getData();
    }

    private void getData() {
        String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/";

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        WeatherApi api = retrofit.create(WeatherApi.class);
        // 576 : 48???????????? ?????????  ,  288 : 24???????????? ?????????  ,  2016 : 7?????? ?????????
        api.getWeather("JSON", "2016", "1", baseDate2, baseTime, x, y).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                if(response.isSuccessful()) {
                    //Log.i(TAG, "response is successful");
                    if(response.body() != null) {
                        Weather list = response.body();
                        String resultCode = list.getResponse().getHeader().getResultCode();
                        if(resultCode.equals("00")) {
                            itemList = list.getResponse().getBody().getItems().getItemList();
                            setLayout();
                        } else if(resultCode.equals("03")) {
                            Log.i(TAG, list.getResponse().getHeader().getResultMsg());
                            Toast.makeText(Activity_Main.this, list.getResponse().getHeader().getResultMsg(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                } else {
                    Log.i(TAG, "response is unsuccessful");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {
                Toast.makeText(Activity_Main.this, "?????? ??????????????? ????????????.", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Log.i(TAG, "response is failure");
                Log.i(TAG, "error : " + t.getMessage());
            }
        });
    }

    private void setLayout() {
        int i= Integer.parseInt(nowTime) + 100;
        String time;
        String baseDate2 = baseDate;
        if(i < 1000) {
            time = "0" + i;
        } else if (i <= 2400) {
            baseDate2 = String.valueOf(Integer.parseInt(baseDate) + 1);
            if(baseDate2.length() == 3) {
                baseDate2 = "0" + baseDate;
            }
            time = "0000";
        } else {
            time = String.valueOf(i);
        }

        String TempValue = "";
        String windValue = "";
        String SKYValue = "";
        String PTYValue = "";
        String REHValue = "";

        for(Item item : itemList) {
            if(item.getFcstDate().equals(baseDate2)) {
                if(item.getFcstTime().equals(time)) {
                    if ("TMP".equals(item.getCategory())) {
                        TempValue = item.getFcstValue();
                    } else if ("WSD".equals(item.getCategory())) {
                        windValue = item.getFcstValue();
                    } else if ("SKY".equals(item.getCategory())) {
                        SKYValue = item.getFcstValue();
                    } else if ("PTY".equals(item.getCategory())) {
                        PTYValue = item.getFcstValue();
                    } else if ("REH".equals(item.getCategory())) {
                        REHValue = item.getFcstValue();
                        break;
                    }
                }
            }
        }

        nowTempTv.setText(TempValue);
        windTv.setText(windValue);
        humidityTv.setText(REHValue);

        switch (PTYValue) {
            case "0":
                switch (SKYValue) {
                    case "1":
                        weatherImg.setImageResource(R.drawable.icon_sun);
                        break;
                    case "3":
                        weatherImg.setImageResource(R.drawable.icon_cloudsun);
                        break;
                    case "4":
                        weatherImg.setImageResource(R.drawable.icon_clouds);
                        break;
                }
                break;
            case "1":
            case "4":
                weatherImg.setImageResource(R.drawable.icon_rain);
                break;
            case "2":
                weatherImg.setImageResource(R.drawable.icon_snowing);
                break;
            case "3":
                weatherImg.setImageResource(R.drawable.icon_snow);
                break;
        }
        setRecyclerView();
    }

    private void setRecyclerView() {
        int i = 0;
        String day;
        String time;
        String temp = "";
        String sky = "";
        String pty = "";
        String pop = "";
        String pcp = "";
        String sno = "";
        String day_day = "";
        String minTemp = "100";
        String maxTemp = "-100";
        String minSky = "";
        String maxSky = "";
        String minPty = "";
        String maxPty = "";
        String minTime = "";
        String maxTime = "";
        String minDate = "";
        String maxDate = "";
        boolean first = true;
        for (Item item : itemList) {
            if(first) {
                if ("TMP".equals(item.getCategory())) {
                    temp = item.getFcstValue();
                } else if ("SKY".equals(item.getCategory())) {
                    sky = item.getFcstValue();
                } else if ("PTY".equals(item.getCategory())) {
                    pty = item.getFcstValue();
                } else if ("POP".equals(item.getCategory())) {
                    pop = item.getFcstValue();
                } else if ("PCP".equals(item.getCategory())) {
                    pcp = item.getFcstValue();
                } else if ("SNO".equals(item.getCategory())) {
                    sno = item.getFcstValue();
                }

                if (i % 12 == 0) {
                    if (i != 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(item.getFcstDate().substring(4));
                        sb.insert(2, ".");

                        day = String.valueOf(sb);
                        time = item.getFcstTime().substring(0, 2);

                        // ???????????? ???????????? ??????
                        // 1. ?????? ???????????? ??? ??????
                        // 2. ????????? ?????? ??????
                        if (Integer.parseInt(item.getFcstTime()) > Integer.parseInt(nowTime) || !item.getFcstDate().equals(baseDate)) {
                            Weather_Time wt = new Weather_Time(day, time, temp, sky, pty, pop, pcp, sno);
                            weatherList.add(wt);

                            temp = "";
                            sky = "";
                            pty = "";
                            pop = "";
                            pcp = "";
                            sno = "";
                        }
                    }
                }
            }

            if (!baseDate.equals(item.getFcstDate())) {
                // ??????????????? ????????? ??? (TMP)
                String fd = item.getFcstDate();
                String ft = item.getFcstTime();
                boolean minB = minDate.equals(fd) && minTime.equals(ft);
                boolean maxB = maxDate.equals(fd) && maxTime.equals(ft);
                if ("TMP".equals(item.getCategory())) {
                    int min = Integer.parseInt(minTemp);
                    int max = Integer.parseInt(maxTemp);
                    int now = Integer.parseInt(item.getFcstValue());
                    if (max < now) {
                        maxTemp = String.valueOf(now);
                        maxTime = item.getFcstTime();
                        maxDate = item.getFcstDate();
                    }
                    if (min > now) {
                        minTemp = String.valueOf(now);
                        minTime = item.getFcstTime();
                        minDate = item.getFcstDate();
                    }
                } else if ("SKY".equals(item.getCategory())) {
                    if (minB) {
                        minSky = item.getFcstValue();
                    } else if (maxB) {
                        maxSky = item.getFcstValue();
                    }
                } else if ("PTY".equals(item.getCategory())) {
                    if (minB) {
                        minPty = item.getFcstValue();
                    } else if (maxB) {
                        maxPty = item.getFcstValue();
                    }
                }
                // ?????? data ????????? 288???
                if (i % 288 == 0) {
                    if (i != 0) {
                        int year = Integer.parseInt(item.getFcstDate().substring(0, 4));
                        int month = Integer.parseInt(item.getFcstDate().substring(4, 6));
                        int d = Integer.parseInt(item.getFcstDate().substring(6));

                        // ?????? ????????? ????????? ????????? ?????? ??????
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            LocalDate ld = LocalDate.of(year, month, d);
                            DayOfWeek dayOfWeek = ld.getDayOfWeek();
                            day_day = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
                        }

                        Weather_Day wd = new Weather_Day(day_day, minTemp, maxTemp, minSky, maxSky, minPty, maxPty);

                        dayDTO.add(wd);
                    }
                }
            }
            i++;
            if(i==288) { first = false; }
        }
        recyclerViewSetting();
        infoLayout.setVisibility(View.VISIBLE);
    }

    private void recyclerViewSetting() {
        TimeWeather timeWeatherAdapter = new TimeWeather(weatherList);
        timeWeatherRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        timeWeatherRv.setAdapter(timeWeatherAdapter);
        DayWeather dayWeather = new DayWeather(dayDTO);
        dayWeatherRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        dayWeatherRv.setAdapter(dayWeather);

        progressDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        backPressed.onBackPressed("\"????????????\"??? ?????? ??? ????????? ???????????????.");
    }
}