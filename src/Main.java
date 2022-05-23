import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите пример:\n");
        System.out.println(calc(in.next()));
    }

    public static String calc(String input){
        int num1 = 0, num2 = 0;
        boolean isRoma = false;

        //РАЗБИЕНИЕ СТРОКИ НА ЭЛЕМЕНТЫ
        String[] numArray = input.split("[+*/-]");
        char[] symbolArray = input.toCharArray();

        //ПРОВЕРКА УСЛОВИЙ И ЗАДАЧА ЦИФРОВЫХ ЗНАЧЕНИЙ
        switch (numArray.length){
            case 0: return "Вы ничего не указали!(0)";
            case 2:
                try{
                    num1 = Integer.valueOf(numArray[0]);
                    num2 = Integer.valueOf(numArray[1]);
                }catch(NumberFormatException e1){
                    isRoma = true;
                }
                if(isRoma) {
                    try {
                        num1 = RomanianNumber.valueOf(numArray[0]).getNum();
                        num2 = RomanianNumber.valueOf(numArray[1]).getNum();
                    } catch (IllegalArgumentException e2) {
                        return "Недопустимые значения для чисел!(3)";
                    }
                }
                if((num1> 10)||(num1<0)||(num2>10)||(num2<0)){
                    return "Числа не должны быть меньше нуля или больше десяти!(2)";
                }
                break;
            default: return "Надо указать 2 целых числа без пробелов и арифметический знак между ними!(+, -, *, /)(1)";
        }

        //СЧИТЫВАНИЕ АРИФМЕТИЧЕСКОГО ЗНАКА И ВЫДАЧА РЕЗУЛЬТАТА ПРИМЕРА
        for (int i = 1; i < symbolArray.length; i++){
            if(!isRoma){
                switch (symbolArray[i]){
                    case '+': return String.valueOf(num1 + num2);
                    case '-': return String.valueOf(num1 - num2);
                    case '*': return String.valueOf(num1 * num2);
                    case '/': return String.valueOf(num1 / num2);
                }
            }else{
                switch (symbolArray[i]){
                    case '+':return String.valueOf(RomanianNumber.values()[num1 + num2 - 1]);
                    case '-': try{
                        return String.valueOf(RomanianNumber.values()[num1 - num2 - 1]);
                    }catch(ArrayIndexOutOfBoundsException e){
                        return "С римскими числами не может быть отрицательного ответа!(4)";
                    }
                    case '*': return String.valueOf(RomanianNumber.values()[(num1 * num2) - 1]);
                    case '/': return String.valueOf(RomanianNumber.values()[(num1 / num2) - 1]);
                }
            }
        }

        return null;
    }
}