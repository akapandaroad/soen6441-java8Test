package test.test.java8_shangguigu;

/**
 * Created by akapandaroad on 2018-04-05.
 */
public class FilterEmployeesByAge implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>22;
    }
}
