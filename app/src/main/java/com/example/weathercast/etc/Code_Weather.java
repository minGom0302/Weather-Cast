package com.example.weathercast.etc;

public class Code_Weather {
    // 1시간 기온 ºC
    String TMP;
    // 퐁속(동서성분) m/s
    String UUU;
    // 풍속(남북성분) m/s
    String VVV;
    // 풍향 deg
    String VEC;
    // 풍속 m/s
    String WSD;
    // 하늘 상태 > 1=맑음, 3=구름많음, 4=흐림
    String SKY;
    // 강수 형태 > 0=없음, 1=비, 2=비/눈, 3=눈, 4=소나기
    String PTY;
    // 강수확률 %
    String POP;
    // 파고 M
    String WAV;
    // 1시간 강수량 범주 1mm
    String PCP;
    // 습도 %
    String REH;
    // 1시간 신적설 범주 1mm
    String SNO;

    public String getSKYValue(String value) {
        String skyValue;
        if(value.equals("1")) {
            skyValue = "맑음";
        } else if(value.equals("3")) {
            skyValue = "구름 많음";
        } else {
            skyValue = "흐림";
        }
        return skyValue;
    }

    public String getPTYValue(String value) {
        String ptyValue;
        if(value.equals("0")) {
            ptyValue = "없음";
        } else if(value.equals("1")) {
            ptyValue = "비";
        } else if(value.equals("2")) {
            ptyValue = "비/눈";
        } else if(value.equals("3")) {
            ptyValue = "눈";
        } else {
            ptyValue = "소나기";
        }
        return ptyValue;
    }

    public String getTMP() {
        return TMP;
    }

    public void setTMP(String TMP) {
        this.TMP = TMP;
    }

    public String getUUU() {
        return UUU;
    }

    public void setUUU(String UUU) {
        this.UUU = UUU;
    }

    public String getVVV() {
        return VVV;
    }

    public void setVVV(String VVV) {
        this.VVV = VVV;
    }

    public String getVEC() {
        return VEC;
    }

    public void setVEC(String VEC) {
        this.VEC = VEC;
    }

    public String getWSD() {
        return WSD;
    }

    public void setWSD(String WSD) {
        this.WSD = WSD;
    }

    public String getSKY() {
        return SKY;
    }

    public void setSKY(String SKY) {
        this.SKY = SKY;
    }

    public String getPTY() {
        return PTY;
    }

    public void setPTY(String PTY) {
        this.PTY = PTY;
    }

    public String getPOP() {
        return POP;
    }

    public void setPOP(String POP) {
        this.POP = POP;
    }

    public String getWAV() {
        return WAV;
    }

    public void setWAV(String WAV) {
        this.WAV = WAV;
    }

    public String getPCP() {
        return PCP;
    }

    public void setPCP(String PCP) {
        this.PCP = PCP;
    }

    public String getREH() {
        return REH;
    }

    public void setREH(String REH) {
        this.REH = REH;
    }

    public String getSNO() {
        return SNO;
    }

    public void setSNO(String SNO) {
        this.SNO = SNO;
    }
}
