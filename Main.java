import java.util.*;

class Main {

    public static String calc(String inputString) {

        try {
            int c = Search.searchForArithmeticOperations(inputString);//ищем арифметический оператор
            String s1 = inputString.substring(0, c);//выделяем первое число из строки
            String s2 = inputString.substring(c + 1, inputString.length());//выделяем второе число из строки
            String outputString = Definition.definitionOfNumbers(inputString, s1, s2);//расчет результата
            return outputString;
        } catch (Exception e) {
            String outputString = "Исключение";
            return outputString;
        }
    }
}


class Search {
    //метод ищет индекс строки, на котором находится арифметическая операция     

    public static int searchForArithmeticOperations(String s) throws Exception {

        String[] arithmeticOperations = {"*", "/", "+", "-"};
        int c = -1;
        for (String a : arithmeticOperations) {
            if (s.indexOf(a) > -1) {
                c = s.indexOf(a);
                break;
            }
        }
        if (c == -1) {
            throw new Exception();
        }
        return c;
    }
}

class Definition {
    //метод определяет введенные числа и производит расчет результата

    public static String definitionOfNumbers(String s, String s1, String s2) throws Exception {

        String[] arabicNumerals = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        String out = null;
        OUTER:

        for (int i = 0; i < 10; i += 1) {

            if (s1.equals(arabicNumerals[i])) {//обработка арабских чисел
                int a = i + 1;

                INNER:
                for (int j = 0; j < 10; j++) {
                    if (s2.equals(arabicNumerals[j])) {
                        int b = j + 1;
                        char c = s.charAt(Search.searchForArithmeticOperations(s));

                        out = ("" + Calc.calculation(a, b, c) + "");
                        break OUTER;

                    }
                }
                throw new Exception();
            }

            if (s1.equals(romanNumerals[i])) {//обработка римских чисел
                int a = i + 1;

                INNER:
                for (int j = 0; j < 10; j++) {
                    if (s2.equals(romanNumerals[j])) {
                        int b = j + 1;
                        char c = s.charAt(Search.searchForArithmeticOperations(s));
                        if (Calc.calculation(a, b, c) > 0) {
                            out = Transformation.transformationArabicInRoman(Calc.calculation(a, b, c));
                        } else {
                            throw new Exception();
                        }
                        break OUTER;
                    }

                }
                throw new Exception();
            }
            if (i == 9) {
                throw new Exception();
            }
        }

        if (out == null) {
            throw new Exception();
        }

        return out;

    }
}

class Calc {
    //метод вычисляет значение выражения a (* / + -) b, c-символ арифметической операции  

    public static int calculation(int a, int b, char c) {
        int d = 0;
        switch (c) {
            case '*':
                d = (a * b);
                break;
            case '/':
                d = (a / b);
                break;
            case '+':
                d = (a + b);
                break;
            case '-':
                d = (a - b);
                break;
            default:
                ;
        }
        return d;
    }
}

/*метод принимает целое число от 1 до 100, 
    определяет число десятков и число единиц принятого числа и возвращает исходное число в римской записи
 */
class Transformation {

    public static String transformationArabicInRoman(int a) {

        int chisloDesytkov = a / 10;//определяем число десятков 
        String b = "";
        switch (chisloDesytkov) {//присваиваем числу десятков римские числа
            case 1:
                b = "X";
                break;
            case 2:
                b = "XX";
                break;
            case 3:
                b = "XXX";
                break;
            case 4:
                b = "XL";
                break;
            case 5:
                b = "L";
                break;
            case 6:
                b = "LX";
                break;
            case 7:
                b = "LXX";
                break;
            case 8:
                b = "LXXX";
                break;
            case 9:
                b = "XC";
                break;
            case 10:
                b = "C";
                break;
        }

        int chisloEdinitz = a % 10;//определяем число единиц 
        String c = "";
        switch (chisloEdinitz) {//присваиваем числу единиц римские числа
            case 1:
                c = "I";
                break;
            case 2:
                c = "II";
                break;
            case 3:
                c = "III";
                break;
            case 4:
                c = "IV";
                break;
            case 5:
                c = "V";
                break;
            case 6:
                c = "VI";
                break;
            case 7:
                c = "VII";
                break;
            case 8:
                c = "VIII";
                break;
            case 9:
                c = "IX";
                break;

        }

        return (b + c);//суммируем значение десятков и единиц     

    }
}

class Test1 {

    public static void main(String[] args) {

        System.out.println("Input:");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println("Output:");
        System.out.println(Main.calc(input));

    }

}
