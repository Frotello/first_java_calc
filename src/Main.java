import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String arg = input.nextLine();
        System.out.println(calc(arg));
    }
    public static String calc(String input){
        String result;
        String [] arrArg = input.split(" ");
        String num1 = String.valueOf((arrArg[0]));
        String num2 = String.valueOf((arrArg[2]));
        String operation = arrArg[1];
        if (arrArg.length > 3) { // ИСКЛЮЧЕНИЕ т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)
            try {
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
        }}
        result = verification(num1, num2, operation);
        return result;
    }
    public static String verification(String num1, String num2, String operation) { // метод проверки на валидность элементов и один тип
        String[] rimOk = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String [] arabicOk = {"1","2","3","4","5","6","7","8","9","10"};
        String[] operationOk = {"+", "-", "*", "/"};
        int rimResult;
        String result = "";
        if (Arrays.asList(rimOk).contains(num1) && Arrays.asList(rimOk).contains(num2) && Arrays.asList(operationOk).contains(operation)){
            rimResult = (calculation(toArabic(num1), toArabic(num2), operation));
            result = (toArabicResult(rimResult));
        } else if (Arrays.asList(arabicOk).contains(num1) && Arrays.asList(arabicOk).contains(num2) && Arrays.asList(operationOk).contains(operation)) {
            result = Integer.toString(calculation(convectorToInt(num1), convectorToInt(num2), operation));
        } else {
            try { // ИСКЛЮЧЕНИЕ т.к. используются одновременно разные системы счисления либо больше 10
                throw new IOException();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

   public static int convectorToInt(String num){    // костыль для перевода строки в число для работы метода calc
        int toInt = Integer.parseInt(num.trim());
        return toInt;
   }

    public static int toArabic (String num) {  // метод для перевода римских чисел в арабские
        int result = 0;
        String[] rimOk = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 0; i < 10; i++) {
            if (rimOk[i].equals(num))
                result = i +1;
        }
        return result;
    }
    public static String toArabicResult (int num){ // перевод результата в римский вид
         String result = "";
         if (num < 0){ // ИСКЛЮЧЕНИЕ т.к. в римской системе нет отрицательных чисел
             try {
                 throw new IOException();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         while (num > 0){
            if (num == 100){
                num = 0;
                result = result + "C";
            } else if (num > 90 || num == 90) {
                result = result + "XC";
                num = num - 90;
            } else if (num > 50 || num == 50) {
                result = result + "L";
                num = num - 50;
            } else if (num > 40 || num == 40) {
                result = result + "XL";
                num = num - 40;
            } else if (num > 10 || num == 10) {
                result = result + "X";
                num = num - 10;
            } else if (num > 9 || num == 9) {
                result = result + "IX";
                num = num - 9;
            } else if (num > 5 || num == 5) {
                result = result + "V";
                num = num - 5;
            } else if (num > 4 || num == 4) {
                result = result + "IV";
                num = num - 4;
            } else if (num > 1 || num == 1) {
                result = result + "I";
                num = num - 1;
            }
         }
         if (result == ""){
             result = result + "0";
         }
         return result;
    }

   public static int calculation(int num1, int num2, String operation){  // метод производящий расчеты в зависимости от знака
       int result = 0;
        switch (operation){
            case "+" -> result = num1+num2;
            case "-" -> result = num1-num2;
            case "/" -> result = num1/num2;
            case "*" -> result = num1*num2;

        }
        return result;
   }
}

