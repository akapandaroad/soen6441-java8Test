package test.test.java8_shangguigu;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by akapandaroad on 2018-04-05.
 */
public class TestLLambda {

    List<Employee> employees = Arrays.asList(
            new Employee("jialuwang", 25, 100000),
            new Employee("akapandaroad", 24, 20000),
            new Employee("aka潘大路", 26, 300000)
    );

    /**
     * @param
     * @return method before java 7
     */
    public List<Employee> filterEmployees(List<Employee> list) {

        List<Employee> emps = new ArrayList<>();

        for (Employee e :
                list) {
            if (e.getAge() > 25) {
                emps.add(e);
            }
        }
        return emps;
    }

    /**
     * 接口优化
     */


    public List<Employee> filterEmployeesByInter(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();
        for (Employee e :
                list) {

            if (mp.test(e)) {
                emps.add(e);
            }
        }

        return emps;


    }
//    @Test
//    public void test1() {
//
//
//        List<Employee> list = filterEmployees(employees);
//
//        for (Employee e :
//                list) {
//
//            System.out.println(e.toString());
//        }
//
////        List<Employee> filterEmployeesList = employees.stream().filter(employee -> employee.getAge() > 25).collect(Collectors.toList());
////
////
////        System.out.println(filterEmployeesList);
//
//    }

    /**
     * 利用接口并实现其抽象方法的实例进行优化的方法
     */
    @Test
    public void Test2() {


        List<Employee> list = filterEmployeesByInter(employees, new FilterEmployeesByAge());


        for (Employee e :
                list) {

            System.out.println(e.toString() + "-----");
        }
    }


    /**
     * 匿名内部类的优化方式
     */
    @Test
    public void Test3() {

        List<Employee> employeesList = filterEmployeesByInter(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() > 100;
            }
        });

        for (Employee e :
                employeesList) {

            System.out.println(e.toString());
        }

    }

    /**
     *
     *
     * 利用lambda进行优化
     */

    @Test
    public void Test4(){

        List<Employee> list=filterEmployeesByInter(employees,employee -> employee.getName()=="akapandaroad");

       list.forEach(System.out::println);


        List<Employee> sortedByAge = employees.stream().sorted(Comparator.comparing(Employee::getAge)).collect(Collectors.toList());

        System.out.println(sortedByAge);



        Collections.sort(employees,(e1,e2)->{
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }


                }



                );

    }

}
