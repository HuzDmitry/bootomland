import java.util.Scanner;

public class Main
{
    private static final char[] ZNAK={'+','-','*','/'};
    private  static final String[] ARAB={"1","2","3","4","5","6","7","8","9","10"};


    public static void main (String[] args) throws Exception
    {
        System.out.println ("Могу вычислять математические выражения типа a+b, a-b, a*b, a/b из арабских или римских чисел до 10");
        System.out.print ("Введите выражение: ");
        Scanner scanner=new Scanner (System.in);
        String output= Main.calc (scanner.next ());
        System.out.println ("= "+output);

    }
    public static String calc (String input)throws Exception //Можно было и с помощью регулярных выражений. Но пока не изученная тема
    {
        int charPosition=znaki (input);
        String output="";
        if (charPosition<0)
            throw new Exception ();
        else
        {
            String input1 = input.substring (0,charPosition);
            String input2 = input.substring (charPosition+1);
            char sign=input.charAt (charPosition);
            boolean arab1=arabiza (input1);
            boolean arab2=arabiza (input2);
            if (arab1 && arab2)
            {
                output=Main.compute (input1, input2, sign);
            }
            else
            {
                RimMath rimMath=new RimMath(input1, input2, sign);
                output=rimMath.mathRim ();
            }
        }
        return output;
    }
    private static int znaki (String input) throws Exception // определение знака вычисления
    {
        int znak =1;

        for (char d : ZNAK)
        {
            int temp = input.indexOf (d);
            if (temp >= 0)
            {
                znak = temp;

                String data2 = input.substring (znak + 1);
                for (char t : ZNAK)
                {
                    temp = data2.indexOf (t);
                    if (temp >= 0)
                    {
                        throw new Exception ();
                    }
                }
                break;
            }
        }
        return znak;
    }
    private static boolean arabiza (String input)// определение принадлежности к СИ
    {
        boolean temp=false;
        for (String d: ARAB)
        {
            if (input.equals (d))
            {
                temp=true;
                break;
            }
        }
        return temp;
    }
    private static String compute (String in1, String in2, char sign)//вычисление арабских числами
    {
        int i1 = Integer.parseInt (in1);
        int i2 = Integer.parseInt (in2);
        int result=0;
        String output;
        switch (sign)
        {
            case '+':
                result = i1+i2;
                break;
            case '-':
                result=i1-i2;
                break;
            case '*':
                result=i1*i2;
                break;
            case '/':
                result=i1/i2;
                break;
        }
        return output=String.valueOf (result);
    }
}
class RimMath {   //класс для работы с римскими числами
    private String input1;
    private String input2;
    private char sign;
    private final String[] ARAB={"I","II","III","IV","V","VI","VII","VIII","IX","X","L","C"};
    RimMath(String input1, String input2, char sign){
        this.input1=input1;
        this.input2=input2;
        this.sign=sign;
    }
    private int convertRimInt(String input) throws Exception //конверитрование Римских чисел в целые числа
    {
        int out=0;
        for (String temp: ARAB)
        {
            if (input.equals (temp))
            {
                out++;
                break;
            }
            else if (out<9)
                out++;
            else
                throw new Exception ();
        }
        return out;
    }
    private String convertIntRim(int input){ //конвертирование целых чисел в римские
        String out=null;
        int temp1=input / 10; //десятки
        int temp2=input % 10; //остаток
        switch (temp1){
            case 0:
                out=ARAB[temp2-1];
                break;
            case 1:
                if (temp2 == 0)
                    out = ARAB[9];
                else
                    out = ARAB[9] + ARAB[temp2 - 1];
                break;
            case 2:
                if (temp2 == 0)
                    out = ARAB[9]+ARAB[9];
                else
                    out = ARAB[9]+ARAB[9] + ARAB[temp2 - 1];
                break;
            case 3:
                if (temp2 == 0)
                    out = ARAB[9]+ARAB[9]+ARAB[9];
                else
                    out = ARAB[9]+ARAB[9] +ARAB[9]+ ARAB[temp2 - 1];
                break;
            case 4:
                if (temp2 == 0)
                    out = ARAB[9]+ARAB[10];
                else
                    out = ARAB[9]+ARAB[10] + ARAB[temp2 - 1];
                break;
            case 5:
                if (temp2 == 0)
                    out = ARAB[10];
                else
                    out = ARAB[10] + ARAB[temp2 - 1];
                break;
            case 6:
                if (temp2 == 0)
                    out = ARAB[10]+ARAB[9];
                else
                    out = ARAB[10]+ARAB[9] + ARAB[temp2 - 1];
                break;
            case 7:
                if (temp2 == 0)
                    out = ARAB[10]+ARAB[9];
                else
                    out = ARAB[10]+ARAB[9]+ARAB[9] + ARAB[temp2 - 1];
                break;
            case 8:
                if (temp2 == 0)
                    out = ARAB[10]+ARAB[9]+ARAB[9];
                else
                    out = ARAB[10]+ARAB[9]+ARAB[9] + ARAB[temp2 - 1];
                break;
            case 9:
                if (temp2 == 0)
                    out = ARAB[9]+ARAB[11];
                else
                    out = ARAB[9]+ARAB[11] + ARAB[temp2 - 1];
                break;
            case 10:
                if (temp2 == 0)
                    out = ARAB[11];
                else
                    out = ARAB[11] + ARAB[temp2 - 1];
                break;
            default:
                out=">109";
        }
        return out;
    }
    public String mathRim () throws Exception { //вычисление значения
        int i1=convertRimInt (input1);
        int i2=convertRimInt (input2);
        String out;
        int result=0;
        switch (sign)
        {
            case '+':
                result = i1+i2;
                break;
            case '-':
                if (i1>i2)
                {result=i1-i2;}
                else
                    throw new Exception ();
                break;
            case '*':
                result=i1*i2;
                break;
            case '/':
                if (i1>i2)
                {result=i1/i2;}
                else
                    throw new Exception ();
                break;
        }
        if (result<0)
            throw new Exception ();
        else
            return out=convertIntRim (result);
    }
}
