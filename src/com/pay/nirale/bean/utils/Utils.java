package com.pay.nirale.bean.utils;

import java.util.Random;
import java.sql.Timestamp;

public class Utils {
    
     // Generate 11 Digit Random Number
    public static String generateAcNum()
    {
        Random rnd = new Random();
        int part1 = rnd.nextInt(654321);
        int part2 = rnd.nextInt(99999);
        
        return String.valueOf(part1+""+part2);
    }
    
    // Get Timestamp Method
    public static String getTimestamp()
    {
        Timestamp timestamp = new Timestamp(System.nanoTime());
        return String.valueOf(timestamp);
    }
}
