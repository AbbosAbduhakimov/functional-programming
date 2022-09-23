package stream;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {


    static Map<Integer, String> singular = Map.of(
            1, "bir",
            2, "ikki",
            3, "uch",
            4, "to'rt",
            5, "besh",
            6, "olti",
            7, "yetti",
            8, "sakkiz",
            9, "to'qiz"

    );

    static Map<Integer, String> tenDigit = Map.of(
            1, "o'n",
            20, "yigirma",
            30, "o'ttiz",
            40, "qiriq",
            50, "ellik",
            60, "oltmish",
            70, "yetmish",
            80, "sakson",
            90, "to'qson"

    );


    public static void main(String[] args) {
        /***************************************************************************************/
        List<Integer> arrayList = Arrays.asList(1, 2, 4, 8, 9, 4, 6, 1, 10, 15, 9, 45, 9, 87, 9, 6, 13, 12, 10, 8);
        List<Integer> arrayList2 = Arrays.asList(1, 2, 4, 8, 9, 4, 6, 1);

        Function<Integer, String> function = Streams::numberToWord;

        Predicate<Integer> predicate = i -> i % 2 != 1;

        List<String> streamResult = arrayList.stream()
                // filter biron bir listdagi elementlarni bu ko'rinishda i-- filter true yoki false qaytaradi undan faqat true qaytgan qiymatlar o'tadi
                /** yuqoridagi @param Predicate ishlaydi filter ichida*/
                .filter(i -> i % 2 != 1)
                // map(преобразование) bir turdan ikkinchi bir turga yoki ikkita bir hil tiplarni bir biriga o'zgartiradi
                // ikki nuqtdan foydalansak Streams klassini numberToWord methodini ichidagi funksiyasi(bajariyotgan ishini) chaqirib olishimiz mumkin bo'ladi
                /** yuqoridagi @param Function ishlaydi shu yerdagi mapda */
                .map(Streams::numberToWord).toList();

//        streamResult.forEach(System.out::println);

        // Возвращает true, если условие выполняется для всех элементов
        boolean result1 = arrayList.stream().allMatch(i -> i % 2 == 0);
//        System.out.println(result1);

        // Возвращает true, если условие выполняется хотя бы для одного элемента
        boolean result2 = arrayList.stream().anyMatch(i -> i % 2 == 0);
//        System.out.println(result2);

        // Возвращает true, если условие не выполняется ни для одного элемента
        boolean result3 = arrayList.stream().noneMatch(i -> i % 2 == 0);
//        System.out.println(result3);


        Integer reduceResult = arrayList2.stream()
                .reduce(1, (a, b) -> a * b, Integer::sum);


        System.out.println(reduceResult);

        int multiply = IntStream.range(2, 8)
                .reduce((a, b) -> a * b)
                .orElse(-1);

        System.out.println(multiply);

        /***************************************************************************************/


        List<User> users = new ArrayList<>();
        users.add(new User(10, "name1"));
        users.add(new User(20, "name2"));
        users.add(new User(30, "name3"));
        users.add(new User(40, "name4"));

        IntStream intStream = users.stream()
                .mapToInt(User::getAge);

        // Объект состояния для сбора статистики, такой как количество, минимум, максимум, сумма и среднее значение.
        // IntSummaryStatistics не обезательно использвать в потоке,но часто используется в потоке
        IntSummaryStatistics stats = intStream.peek(System.out::println)
                .summaryStatistics();

//        System.out.println(stats);

        // создание потока
        Stream<List<User>> stream = Stream.of(users);

        // в большинстве случае reduce возвращает null по этому лучше использвать Optional или orElse(other) значение
        int maxAge = users.stream()
                .mapToInt(User::getAge)
                .reduce(Math::max).orElse(-1);


        Stream<String> text1 = Stream.of("one", "two", "three");
        Stream<String> text2 = Stream.of("four", "five", "six");


        List<String> concatAndUpper = Stream.concat(text1, text2)
                .map(String::toUpperCase)
                .toList();

//        System.out.println(concatAndUpper);


        List<String> text3 = List.of("c", "d", "a", "b", "e");


        text3.stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        List<String> generate = Stream.generate(() -> "k").limit(15).toList();


        /*************************************************************************************/

        List<String> list = Arrays.asList("John","Tom","Bob");

        String s = list.stream()
                .collect(StringBuilder::new,
                        (sb, s1) -> sb.append(" ").append(s1),
                        (sb1, sb2) -> sb1.append(sb2.toString())).toString();
        System.out.println(s);

        // alternative
        String s1 = list.stream()
                .parallel()
//                .unordered()
                .collect(new MyCollector());
        System.out.println(s1);



    }


    public static String numberToWord(int number) {
        String tenDigit = Streams.tenDigit.get((number % 100) / 10);
        String singular = Streams.singular.get((number % 10));
        return (singular == null ? "" : singular) + (tenDigit == null ? "" : tenDigit);
    }

    static class User {
        public int age;
        public String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }
}
