package com.cs.util;

public class ArrayUtil {
    public static String toStringByArray(String[] array){
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < array.length;i++){
            if(i == array.length){
                sb.append(array[i]);
            }else{
                sb.append(array[i] + ",");
            }
        }
        return sb.toString();
    }
}
