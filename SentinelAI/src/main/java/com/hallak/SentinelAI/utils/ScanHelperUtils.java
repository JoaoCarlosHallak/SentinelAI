package com.hallak.SentinelAI.utils;

import java.util.ArrayList;
import java.util.List;

public class ScanHelperUtils {

    public static List<String> mixPayloadAndTarget(List<String> payloads, String target) {
        List<String> mixedList = new ArrayList<>();
        for (String payload : payloads) {
            mixedList.add(target.replace("INJECT", payload));
            mixedList.add(target.replace("INJECT", encode(payload)));
        }
        return mixedList;
    }

    public static boolean targetValidation(String target) {
        return target.contains("=") && target.contains("?");
    }

    private static String encode(String payload) {
        return java.net.URLEncoder.encode(payload, java.nio.charset.StandardCharsets.UTF_8);
    }




    
}
