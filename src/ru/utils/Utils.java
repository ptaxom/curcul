package ru.utils;

public class Utils {


    public static boolean isNumber(String input){
        int k = 0;
        if (input.charAt(0) == '-')
            k++;
        while (k<input.length() &&
                ((input.charAt(k)>='0' && input.charAt(k)<='9')
                        || input.charAt(k) == '.'))
            k++;
        return (k==input.length());
    }

    public static boolean isFunction(String input){
        int pos = input.indexOf("(");
        boolean answer = false;
        if (pos != -1 && input.lastIndexOf(")") == input.length())
        {
            String funString = input.substring(0, pos);
            answer = (funString.equals("sin")  ||
                    funString.equals("cos")  ||
                    funString.equals("exp")  ||
                    funString.equals("sqr") ||
                    funString.equals("tan")  ||
                    funString.equals("sqrt") ||
                    funString.equals("tg")   ||
                    funString.equals("ln"));
        }
        else answer = false;
        if (input.equals("x"))
            answer = true;
        return answer;
    }


}
