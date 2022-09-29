package function;

import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args) {
        /**
         * Функция первого класса — это та, которая использует объекты первого класса, такие как String, числа,
         * которые могут быть переданы в качестве аргументов, могут быть возвращены или присвоены переменной.
         * FIRST CLASS FUNCTION
         * */
        int[] array = {1, 2, 3, 4, 5, 6};
        SquareMaker squareMaker = item -> item * item;
        for (int i = 0; i < array.length; i++) {
            System.out.println(squareMaker.square(array[i]));
        }
        System.out.println("****************");


        /**
         * Функция высокого порядка — это функция , которая может принимать функцию в качестве аргумента и/или может возвращать функцию.
         * HIGH ORDER FUNCTION
         */

        Function<Integer, Integer> square = t -> t * t;
        Function<Integer,Integer> cube = t -> t * t * t;

        for (int i = 0; i < array.length; i++) {
            print(square,array[i]);
        }

        System.out.println("****************");


        for (int i = 0; i < array.length; i++) {
            print(cube,array[i]);
        }

        /**
         * Чистая функция не изменяет ни одну глобальную переменную или ссылку, переданную ей в качестве параметра.
         * Такие функции очень полезны и потокобезопасны.
         * PURE FUNCTION
         * */


        int a,b;
        a = 1;
        b = 2;
        System.out.println(sum(a,b));

    }

    private static int sum(int a,int b){
        return a + b;
    }

    private static <T,R> void print(Function<T,R> function,T t){
        System.out.println(function.apply(t));
    }
    interface SquareMaker {
        int square(int item);
    }
}
