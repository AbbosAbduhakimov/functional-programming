package stream;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyCollector implements Collector<String,StringBuilder,String> {

    @Override
    public Supplier<StringBuilder> supplier() {
        // поставщик: эта функция создает новый контейнер результатов. При последовательном выполнении он вызывается только один раз,
        // тогда как при параллельном выполнении его можно вызывать несколько раз, чтобы получить новый экземпляр для разных параллельных потоков.
        System.out.println("call supplier");
        return StringBuilder::new;
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        // ассоциативная функция для включения текущего элемента в объект результата (объект результата создается в функции поставщика)
        System.out.println("call accumulator");
        return (stringBuilder, string) -> stringBuilder.append(" ").append(string);
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        // объединитель: при параллельном выполнении эта функция объединяет результаты, полученные от разных потоков. Это должна быть ассоциативная функция.
        System.out.println("call combiner");
        return StringBuilder::append;
    }

    @Override
    public Function<StringBuilder, String> finisher() {
        System.out.println("call finisher");
        return stringBuilder -> stringBuilder.toString();
    }

    @Override
    public Set<Characteristics> characteristics() {
//        return Collections.emptySet();
//        System.out.println("call characteristics");
        return EnumSet.of(Collector.Characteristics.CONCURRENT);
    }
}
