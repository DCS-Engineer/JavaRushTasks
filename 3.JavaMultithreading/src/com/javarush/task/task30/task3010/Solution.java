package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigDecimal;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        try {
            for (int i = 2; i < 37; i++){
                if (isInputStringNumber(args[0], i)){
                    System.out.println(i);
                    break;
                }
                else if (i == 36){
                    System.out.println("incorrect");
                }
                else continue;
            }
        }
        catch (Exception e){}
    }

    public static boolean isInputStringNumber(String inputString, int radix){
        boolean isNumber = false;
        try {
            BigDecimal decimal = new BigDecimal(new BigInteger(inputString, radix));
            isNumber = true;
        }
        catch (Exception e){}
        return isNumber;
    }
}