package com.example.weathercast.dto;

import com.google.gson.annotations.SerializedName;


public class Weather {
    @SerializedName("response")
    Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {
        @SerializedName("header")
        Header header;
        @SerializedName("body")
        Body body;

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }
    }
    public class Header {
        @SerializedName("resultCode")
        String resultCode;
        @SerializedName("resultMsg")
        String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    public class Body {
        @SerializedName("dataType")
        String dataType;
        @SerializedName("items")
        Items items;

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }
    }
}
