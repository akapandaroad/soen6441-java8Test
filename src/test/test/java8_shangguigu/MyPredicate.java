package test.test.java8_shangguigu;

/**
 * Created by akapandaroad on 2018-04-05.
 */
@FunctionalInterface
public interface MyPredicate<T>{

    public boolean test(T t);
    default void test(){
        System.out.println(123);

    }
    public static void test2(){

        System.out.println(456);
    }

}
