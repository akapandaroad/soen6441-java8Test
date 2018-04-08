package test.test.java8_shangguigu;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by akapandaroad on 2018-04-07.
 */
public class StreamExam {

    /**
     * 1。给定一个数字列表返回其平方
     */

    Integer[] nums=new Integer[]{1,2,3,4,5};

    @Test
    public void Test1(){

        List<Integer> result=Arrays.asList(nums).stream().map(n->n*n).collect(Collectors.toList());
        System.out.println(result);


    }

}
