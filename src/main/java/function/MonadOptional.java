package function;

import java.util.Optional;
import java.util.function.Function;

public class MonadOptional {

    public static void main(String[] args) {
//        Единичная функция — Optional.of ()
        // LEFT IDENTITY
        Function<Integer, Optional<Integer>> addOneToX = x -> Optional.of(x + 1);

        System.out.println(Optional.of(5).flatMap(addOneToX)
                .equals(addOneToX.apply(4)));


        //  RIGHT IDENTITY
        System.out.println(Optional.of(5).map(Optional::of)
                .equals(Optional.of(5)));


        // ASSOCIATIONS
//        Function<Integer, Optional<Integer>> addOneToX1 = x −> {Optional.of(x + 1);
//        Function<Integer, Optional<Integer>> addTwoToX2 = x −> Optional.of(x + 2);
//        Function<Integer, Optional<Integer>> addThreeToX3 = x −> addOneToX.apply(x).flatMap(addOneToX1);
//        Optional.of(5).flatMap(addOneToX).flatMap(addTwoToX2)
//                .equals(Optional.of(5).flatMap(addThreeToX3));

    }
}
