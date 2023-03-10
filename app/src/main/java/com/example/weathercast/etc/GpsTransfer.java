package com.example.weathercast.etc;

public class GpsTransfer {
    // mode : TO_GRID(위경도 -> 좌표), TO_GPS(좌표 -> 위경도)
    public static int TO_GRID = 0;
    public static int TO_GPS = 1;

    public LatXLngY transfer(int mode, double lat, double lng) {
        double RE = 6371.00877; // 지구 반경(km)
        double GRID = 5.0; // 격자 간격(km)
        double SLAT1 = 30.0; // 투영 위도1 (degree)
        double SLAT2 = 60.0; // 투영 위도2 (degree)
        double OLAT = 38.0; // 기준점 위도 (degree)
        double OLNG = 126.0; // 기준점 경도 (degree)
        double XO = 43; // 기준점 X좌표 (Grid)
        double YO = 146; // 기준점 Y좌표 (Grid)

        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olat = OLAT * DEGRAD;
        double olng = OLNG * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI + 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        LatXLngY rs = new LatXLngY();

        if(mode == TO_GRID) {
            rs.lat = lat;
            rs.lng = lng;
            double ra = Math.tan(Math.PI * 0.25 + (lat) * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            double theta = lng * DEGRAD - olng;
            if(theta > Math.PI) theta -= 2.0 * Math.PI;
            if(theta < -Math.PI) theta += 2.0 * Math.PI;
            theta *= sn;
            double x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
            double y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);

            rs.x = x;
            rs.y = y;

        } else if (mode == TO_GPS) {
            rs.x = lat;
            rs.y = lng;
            double xn = lat - XO;
            double yn = ro - lng + YO;
            double ra = Math.sqrt(xn * xn + yn * yn);

            if(sn < 0.0) {
                ra -= ra;
            }

            double alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

            double theta = 0.0;
            if(Math.abs(xn) <= 0.0) {
                theta = 0.0;
            } else {
                if(Math.abs(yn) <= 0.0) {
                    theta = Math.PI * 0.5;
                    if(xn < 0.0) {
                        theta = -theta;
                    }
                } else {
                    theta = Math.atan2(xn, yn);
                }
                double alon = theta / sn + olng;

                double reLat = alat * RADDEG;
                double reLng = alon * RADDEG;

                rs.lat = reLat;
                rs.lng = reLng;
            }
        }

        return rs;
    }

    public static class LatXLngY {
        public double lat;
        public double lng;

        public double x;
        public double y;
    }

}
