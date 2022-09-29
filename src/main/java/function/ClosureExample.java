package function;

import java.util.function.Function;

public class ClosureExample {

    public static void main(String[] args) {

        String[] weekDays = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        Function<Integer,String> usualWeekDay = weekDay(weekDays);
        System.out.println(usualWeekDay.apply(10));

    }


    private static Function<Integer,String> weekDay(String[] weekdays){
        return index -> index >= 0 ? weekdays[index % weekdays.length] : null;
    }
}
