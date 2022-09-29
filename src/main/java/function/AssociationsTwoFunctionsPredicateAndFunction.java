package function;

import java.util.function.Function;
import java.util.function.Predicate;

public class AssociationsTwoFunctionsPredicateAndFunction {

    public static void main(String[] args) {

        // associations two function using Predicate
        Predicate<String> hasName = text -> text.contains("name");
        Predicate<String> hasPassword = pass -> pass.contains("password");
        Predicate<String> hasNameAndPassword = hasName.and(hasPassword);
        Predicate<String> hasPasswordOrName = hasPassword.or(hasName);
        String queryString1 = "test=name,pass=test";
        String queryString2 = "test=name,pass=test";
        System.out.println(hasNameAndPassword.test(queryString1));
        System.out.println(hasPasswordOrName.test(queryString2));



        // associations two function using Function
        Function<Integer,Integer> multiply = t -> t * 3;
        Function<Integer,Integer> add = t -> t + 3;

        // first progress inside compose then calling function
        Function<Integer,Integer> firstAddingSecondMultiplying = multiply.compose(add);
        // first progress calling function then inside andThen
        Function<Integer,Integer> firstMultiplyingSecondAdding = multiply.andThen(add);

        System.out.println(firstAddingSecondMultiplying.apply(3));
        System.out.println(firstMultiplyingSecondAdding.apply(3));



    }
}
