package com.unitbean.UnitBean.Utils;

public class Auxiliary {

    public static String firstLetterToUpperCase(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String stringCut(String s, int maxLength){
        String result = s;
        int strLength = s.length();
        if(strLength - maxLength > 3){
            result = s.substring(0, maxLength) + " ...";
        }

        return result;
    }

    public static String stringAdjust(String s){
        String result = null;
        for (int i=0 ; i< s.length(); i++){
            String sign = s.substring(i,i+1);
            if (sign.equals("\n")){
                result += "." + sign;
                sign = s.substring(i+1,i+2).toUpperCase();
            }
            result += sign;
        }
        result += ".";
        return result;
    }


}
