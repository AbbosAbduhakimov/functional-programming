package function;

import java.util.function.Function;

public class CurryingFunction {
    public static void main(String[] args) {


        Function<Integer, Function<Integer, Function<Integer, Integer>>>
                curryingFuncAdding = first -> second -> third -> first + second + third;

        int result = curryingFuncAdding.apply(5).apply(5).apply(5);
        System.out.println(result);

    }

}
