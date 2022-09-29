package function;

import java.util.function.Supplier;

public class LazyAndEagerFunctionsSupplier {

    public static void main(String[] args) {

        String queryString = "password=test";
        String queryString2 = "name=test";


        /**
         * Здесь функция checkInEagerWay() сначала оценивает параметры, а затем выполняет свой оператор. Принимая во внимание,
         * что checkInLazyWay() выполняет свой оператор и оценивает параметр по мере необходимости. Поскольку && является оператором короткого замыкания,
         * checkInLazyWay оценивает только первый параметр, который оказывается ложным, и вообще не оценивает второй параметр.
         * */


        System.out.println(checkingEagerWay(hasName(queryString), hasPassword(queryString)));
        System.out.println(checkingLazyWay(
                () -> hasName(queryString2), () -> hasPassword(queryString)
        ));


    }


    private static boolean hasName(String queryString) {
        System.out.println("Checking Name :");
        return queryString.contains("name");
    }

    private static boolean hasPassword(String queryString) {
        System.out.println("Checking Password :");
        return queryString.contains("password");
    }

    private static String checkingEagerWay(boolean result1, boolean result2) {
        return (result1 && result2) ? "all condition is passed" : "failed";
    }

    private static String checkingLazyWay(Supplier<Boolean> result1, Supplier<Boolean> result2) {
        return (result1.get() && result2.get()) ? "all condition is passed" : "failed";
    }


}
