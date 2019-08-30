package com.zt.pugongyingapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class DataUtil {


    public static String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }


    public static Date yearsLater(int year){

        try {
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            String now = sdf.format(new Date());
            String[] nowStr = now.split("-");
            int years = Integer.parseInt(nowStr[0])+year;
            StringBuffer sb = new StringBuffer();
            sb.append(years+"-"+nowStr[1]+"-"+nowStr[2]);
            return sdf.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
