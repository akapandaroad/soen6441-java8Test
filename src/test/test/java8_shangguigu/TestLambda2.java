package test.test.java8_shangguigu;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by akapandaroad on 2018-04-06.
 *
 * 类型可以省略不写，jvm中有类型推断
 * 只有一个抽象方法的接口称为函数式接口@FunctionInterface annotation
 */
public class TestLambda2 {


    @Test
    public void Test1(){

        Runnable r=()-> System.out.println("akapandaroad");
        r.run();

        Consumer<String> con=(x)-> System.out.println(x);
        con.accept("wangjialu");

//        Consumer<String> con2=new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };


    }

    @Test
    public void Test2(){

        Integer result=operation(10,i->i*i);


        System.out.println(result);
    }

    public Integer operation(Integer num,MyFun mf){
       return mf.getValue(num);



    }
}
