package com.adobe.aem.guides.wknd.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
  public class DateComparator implements Comparator<Map<String, Object>> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public int compare(Map<String, Object> map1, Map<String, Object> map2) {
        String date1 = (String) map1.get("date");
        String date2 = (String) map2.get("date");

        Date date3 = null;
        Date date4 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date3 = dateFormat.parse(date1);
            date4 = dateFormat.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date4.compareTo(date3);
    }
}
