import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by akapandaroad on 2018-03-07.
 */



public class FunctionInterfaceTest {
    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T i: list){ c.accept(i);
        } }


    public static <T> List<T> filter(List<T> list, Predicate<T> p) { List<T> results = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){ results.add(s);
            } }
        return results;
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) { List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result; }

    public static void main(String[] args) {
        List<String> str=Arrays.asList("lambdas","in","action");
        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));


        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
       // List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

        List<Integer> l=map(Arrays.asList("lambdas","in","action"),(String s)->s.length());
        System.out.println(l);

        str.stream().map((String s)->s.length()).forEach(System.out::println);
    }
}
