package ru.staff.math;

import ru.staff.Sector;
import ru.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Function {

    private enum Type{
        plus, minus, multiply, devide, end;
    }

    private List<Function> functions = new ArrayList<>();


    private List<Expression> expressions = new ArrayList<>();

    private List<Type> type = new ArrayList<>();

    private String stringValue;




    public static int findAnotherBracket(String input, int num){
        int numOfAnotherBracket;
        int stack = 0;
        numOfAnotherBracket = -1;
        for(int i = num; i < input.length(); i++){
            numOfAnotherBracket = i;
            if (input.charAt(i) == '(')
                stack++;
            if (input.charAt(i) == ')')
                stack--;
            if (stack <= 0)
                break;
        }
        if (stack < 0)
            numOfAnotherBracket = -1;
        return numOfAnotherBracket;
    }




    private List<Sector> sectors = new ArrayList<>();



    private void Sectorize(String input){
        int last = 0;
        String signs = "+-/*";
        for(int i = 0; i < input.length(); i++)
        {
            if (input.charAt(i) == '(')
                i = findAnotherBracket(input, i);
            if (signs.contains((String.valueOf(input.charAt(i))))){
                sectors.add(new Sector(input.charAt(i), input.substring(last, i)));
                last = i+1;
            }
        }
        sectors.add(new Sector(' ', input.substring(last, input.length())));
    }



    private void UpdateSectors(){
        for(int i = 0; i<sectors.size()-1; i++)
            if (Utils.isNumber((sectors.get(i)).toString())){
                if(sectors.get(i).getLink() == '*' || sectors.get(i).getLink() == '/'){
                    sectors.get(i).UpdateWithNext( sectors.get(i+1));
                    sectors.remove(i+1);
                } else if(sectors.get(i-1).getLink() == '*' || sectors.get(i-1).getLink() == '/'){
                    sectors.get(i-1).UpdateWithNext( sectors.get(i));
                    sectors.remove(i);
                }

            }
    }


    private List<Sector> multiply = new ArrayList<>();

    private void UpdateMultiply(){
        for(int i = 0; i<sectors.size(); i++)
        {
            String bufString = "";
            int j = i;
            while (j<sectors.size() && (sectors.get(j).getLink()=='/' || sectors.get(j).getLink()=='*'))
            {
                bufString+=sectors.get(j).getValue()+String.valueOf(sectors.get(j).getLink());
                j++;
            }
            if (i == j)
                multiply.add(sectors.get(i));
                else {
                multiply.add(new Sector(sectors.get(j).getLink(), bufString+sectors.get(j).getValue()));
                i = j;
            }
        }
    }

    public void outList(List<Sector> k){
        for(Sector a : k){
            System.out.println(a);
            System.out.println(a.getLink());
        }
    }

    private void MakeAll(){
        for(int i = 0; i<multiply.size(); i++){
            System.out.println(multiply.get(i).getValue());
            functions.add(new Function(multiply.get(i).getValue()));
            Type curType = Type.end;
            switch (multiply.get(i).getLink()){
                case '+' : curType = Type.plus; break;
                case '*' : curType = Type.multiply; break;
                case '/' : curType = Type.devide; break;
                case '-' : curType = Type.minus; break;
                case ' ' : curType = Type.end; break;
            }
            type.add(curType);
        }
    }

    public Function(String input){
        System.out.println(input);
        stringValue = input;
        if (Expression.isExpression(input))
        {
            expressions.add(new Expression(input));
        }
        else
    {
        Sectorize(input);
        UpdateSectors();
        UpdateMultiply();
        MakeAll();
        System.out.println("---------------------");
        outList(multiply);
    }

    }

    @Override
    public String toString(){
        return stringValue;
    }

}
