package com.ngalain.admin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts the given object to JSON string.
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反射toString方法, 只显示不为空的参数.
     */
    public static String reflectToString(Object paramObj) {
        StringBuilder strBuf = new StringBuilder();
        try {
            Field[] fields = paramObj.getClass().getDeclaredFields();

            strBuf.append(paramObj.getClass().getSimpleName());
            strBuf.append("(");
            for (Field field : fields) {
                String fieldName = field.getName();
                if (!"serialVersionUID".equals(fieldName)) {
                    field.setAccessible(true);
                    Object fieldValue = field.get(paramObj);
                    if (fieldValue != null) {
                        strBuf.append(field.getName() + ":");
                        strBuf.append(fieldValue);
                        strBuf.append("|");
                    }
                }
            }
            strBuf.append(")");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strBuf.toString();
    }

    public static String dateFormatToString(Date date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDate.format(date);
    }

    /**
     * javadoc.
     */
    public static String dateFormatToStringCustom(Date date) {
        String[] month = {"Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        SimpleDateFormat simpleDate = new SimpleDateFormat("MM dd, yyyy");
        String format = simpleDate.format(date);
        String monthStr = format.substring(0, 2);
        return month[Integer.parseInt(monthStr) - 1]
                + format.substring(2);
    }

    /**
     * Counts the total number of words in a text.
     */
    public static int countWords(String text) {
        String tempStr = text;
        if (StringUtil.isNullorBlank(text)) {
            return 0;
        }
        text = text.replaceAll("[^\\x00-\\xff]", " ");
        text = text.replaceAll("(<p>)|(<\\/p>)|\\,|\\.|\\?|(&nbsp;)|(<br>)|(\\n)", " ");
        text = text.replaceAll("\\s+", " ");
        text = text.trim();
        if (StringUtil.isNullorBlank(text)) {
            return !StringUtil.isNullorBlank(tempStr) ? 1 : 0;
        }
        String reg = "\\s";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);

        int count = 0;
        while (m.find()) {
            count++;
        }
        return count + 1;
    }
}
