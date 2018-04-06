package test.test.java8_shangguigu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by akapandaroad on 2018-04-06.
 * java 8 -4 core function interface
 * Consumer<T>--void accept
 * <p>
 * Supplier<T>---T get
 * <p>
 * Function<T,R>(参数，返回值)--R apply
 * <p>
 * Predicate<T>----boolean test()
 */
public class TestLambda3 {

    @Test
    public void TestConsumer() {

        happy(10000, m -> System.out.println("akapandaroad has " + m + "CAD"));

    }

    public void happy(double money, Consumer<Double> con) {

        con.accept(money);
    }


    @Test
    public void TestSupplier() {

        List<Integer> result = getNumList(10, () -> 20);

        System.out.println(result);

    }

    //需求：产生指定数量的整数放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {


        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);

        }

        return list;
    }


    @Test
    public void TestFunction() {

        String str = stringHandler("\t\t\twoshinibaba  ", (s) -> s.trim());
        System.out.println(str);

    }

    public String stringHandler(String str, Function<String, String> fun) {

        return fun.apply(str);
    }


    @Test
    public void TestPredicate() {

        List<String> example= Arrays.asList("akapandaroad","woshinibaba","wjl");
        List<String> result=filterList(example,(s)->s.length()>5);
        System.out.println(result);


    }

    public List<String> filterList(List<String> list, Predicate<String> pre) {

        List<String> strlist = new ArrayList<>();
        for (String str :
                list) {

            if (pre.test(str)) {
                strlist.add(str);
            }
        }
        return strlist;

    }

}
