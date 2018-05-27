package com.dyg.test.dto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Header {
    // Header  (グループ名)
    // 認証キー  認証キー
    private String authKey;
    // ファイルID  ファイルID
    private String fileId = "56107101";
    // 送信元識別子  送信元識別子
    private String transmitFromCd = "1000000000000000000HD";
    // 通信時刻  通信時刻
    private String time;

    public Header() {
    }

    public String getAuthKey() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:00");
        String date = sdf.format(new Date());
        StringBuilder sb = null;
        long unixTimeStamp;
        try {
            unixTimeStamp = sdf.parse(date).getTime() / 1000;
            String gwkey = "A123456789";
            String motoKey = String.valueOf(unixTimeStamp) + gwkey;
            byte[] cipher_byte;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(motoKey.getBytes());
            cipher_byte = md.digest();
            sb = new StringBuilder(2 * cipher_byte.length);
            for (byte b : cipher_byte) {
                sb.append(String.format("%02x", b & 0xff));
            }
        } catch (ParseException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (sb != null) {
            authKey=sb.toString();
            return authKey;
        } else {
            return null;
        }
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getTransmitFromCd() {
        return transmitFromCd;
    }

    public void setTransmitFromCd(String transmitFromCd) {
        this.transmitFromCd = transmitFromCd;
    }

    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date sysTime = new Date();
        time=sdf.format(sysTime);
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Header{" +
                "authKey='" + authKey + '\'' +
                ", fileId='" + fileId + '\'' +
                ", transmitFromCd='" + transmitFromCd + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
