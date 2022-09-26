package stream;

import java.util.function.*;

public class Lambdas {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
            }
        };

        Function function = new Function() {
            @Override
            public Object apply(Object o) {
                return o.toString();
            }
        };

        Runnable run = ()-> System.out.println("running");
        Thread thread1 = new Thread(run);
        thread1.start();

        // Predicate input argument if matches output -- true or false
        Predicate<Integer> predicate = x -> x > 5;
        System.out.println(predicate.test(10));


        // Function input two argument then mapping first argument to second argument and return second argument
        Function<Integer,String> function1 = String::valueOf;
        String apply = function1.apply(10);
        System.out.println(apply);

        // Consumer accepts argument then returns nothing example foreach on StreamApi
        Consumer<Integer> consumer = x -> System.out.println("print something" + x);


        // Supplier returns only something
        Supplier<Throwable> supplier1 = () -> new RuntimeException("error");
        Supplier<Integer> supplier2 = () -> 10;
        System.out.println(supplier1);


        // UnaryOperator returns always input argument,expand Supplier
        UnaryOperator<Exception> unaryOperator = (exception) -> new RuntimeException("exception");
        System.out.println(unaryOperator);




    }
}
