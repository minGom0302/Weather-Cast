package com.example.weathercast.interface_zip;

import com.example.weathercast.dto.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("getVilageFcst?serviceKey=IigVgrA8KY3lQIG3RDCY3MPQRvX6mP5LfayEe7bv5LaX9RgF//tcJQB1Bg6C2xGAvlwl5yTNRAckKCL2kUuSOw==")
    Call<Weather> getWeather(
            @Query("dataType") String dataType,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("base_date") String baseDate,
            @Query("base_time") String baseTime,
            @Query("nx") String nx,
            @Query("ny") String ny
    );
}
