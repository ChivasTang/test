package com.dyg.test.dto;

public class ApiRequestData {
    private Header header;
    private Data data;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiRequestData{" +
                "header=" + header +
                ", data=" + data +
                '}';
    }
}
