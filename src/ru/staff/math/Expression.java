package ru.staff.math;


import ru.utils.Utils;

public class Expression {
    private double coefficient;
    private enum Type{
        sin, cos, exp, sqrt, sqr, tg, tan, reverse, def, num, ln;
    }
    private Type type;
    private Function Argument;

    private static boolean inBrackets(String input, int pos){
        if (pos == -1)
            return true;
        if (input.indexOf("(") == -1 || input.lastIndexOf(")") == -1)
            return false;
        else return ((input.indexOf("(") < pos) && (pos < input.lastIndexOf(")")));
    }

    public Expression(String input) {
        if (input.equals("x"))
        {
            type = type.def;
            coefficient = 1;
            Argument = null;

        } else if (!inBrackets(input,input.indexOf("*"))) {
            String stringCoefficient = input.substring(0, input.indexOf("*"));
            coefficient = Double.parseDouble(stringCoefficient);
            int toFind, fromFind;
            toFind = (input.indexOf("(") == -1) ? input.length() :  input.indexOf("(");
            String stringType =  input.substring(input.indexOf("*")+1, toFind);
            try{
                char test = stringType.charAt(0);
                switch (stringType){
                    case "sin":  type = Type.sin;  break;
                    case "cos":  type = Type.cos;  break;
                    case "exp":  type = Type.exp;  break;
                    case "sqr":  type = Type.sqr;  break;
                    case "tan":  type = Type.tan;  break;
                    case "sqrt": type = Type.sqrt; break;
                    case "tg":   type = Type.tg;   break;
                    case "ln":   type = Type.ln;   break;
                    default:     type = Type.def;  break;
                }
            }catch (Exception e){
                type = Type.def;
            }
            fromFind = (input.indexOf("(")==-1) ? input.indexOf("*")+1 : input.indexOf("(")+1;
            toFind = (input.lastIndexOf(")")==-1) ? input.length() : input.length()-1;
            String stringArgument = input.substring(fromFind, toFind);
            Argument = new Function(stringArgument);
        } else
        if (!inBrackets(input,input.indexOf("/"))) {
            String stringCoefficient = input.substring(0, input.indexOf("/"));
            coefficient = Double.parseDouble(stringCoefficient);
            type = Type.reverse;
            int fromFind, toFind;
            fromFind = (input.indexOf("(")==-1) ? input.indexOf("*")+1 : input.indexOf("(")+1;
            toFind = (input.lastIndexOf(")")==-1) ? input.length() : input.length()-1;
            String stringArgument = input.substring(fromFind, toFind);
            Argument = new Function(stringArgument);
        }
        else if (Utils.isNumber(input))
        {
            coefficient = Double.parseDouble(input);
            type = Type.num;
            Argument = null;

        } else {
            coefficient = 1;
            int toFind, fromFind;
            toFind = (input.indexOf("(") == -1) ? input.length() :  input.indexOf("(");
            String stringType =  input.substring(0, toFind);
            try{
                char test = stringType.charAt(0);
                switch (stringType){
                    case "sin":  type = Type.sin;  break;
                    case "cos":  type = Type.cos;  break;
                    case "exp":  type = Type.exp;  break;
                    case "sqr":  type = Type.sqr;  break;
                    case "tan":  type = Type.tan;  break;
                    case "sqrt": type = Type.sqrt; break;
                    case "tg":   type = Type.tg;   break;
                    case "ln":   type = Type.ln;   break;
                    default:                       break;
                }
            }catch (Exception e){
                type = Type.def;
            }
            fromFind = (input.indexOf("(")==-1) ? input.indexOf("*")+1 : input.indexOf("(")+1;
            toFind = (input.lastIndexOf(")")==-1) ? input.length() : input.length()-1;
            String stringArgument = input.substring(fromFind, toFind);
            Argument = new Function(stringArgument);
        }
    }

    @Override
    public String toString(){
        String output = Double.toString(coefficient);
        String toType;
        switch (type){
            case tg: toType = "*tan("; break;
            case tan: toType = "*tan("; break;
            case sin: toType = "*sin("; break;
            case cos: toType = "*cos("; break;
            case exp: toType = "*exp("; break;
            case sqr: toType = "*sqr("; break;
            case sqrt: toType = "*sqrt("; break;
            case reverse: toType = "/("; break;
            case ln:  toType = "*sqrt("; break;
            case def: toType = "*"; break;
            default: toType = "";
        }
        if (type != Type.num)
            output+=toType+Argument.toString()+")";
        return output;
    }


    public static boolean isExpression(String input){
        boolean answer = false;
        if (input == "x")
            answer = true;
        else if (Utils.isNumber(input))
            return answer=true;
            else if (Utils.isFunction(input))
                    answer = true;
                    else
                    {
                        int pos = input.indexOf("/");
                        if (pos ==-1)
                            pos = input.indexOf("*");
                        if (pos == -1)
                            answer = false;
                        else {
                            String a = input.substring(0, pos), b = input.substring(pos+1, input.length());
                            answer = (
                                (Utils.isNumber(a)
                                &&
                                Utils.isFunction(b))
                                        ||
                                        (Utils.isFunction(b)
                                                &&
                                        Utils.isNumber(a)
                                ));
                        }
                    }
        return answer;
    }

}
