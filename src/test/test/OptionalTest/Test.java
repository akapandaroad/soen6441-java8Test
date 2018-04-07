package test.test.OptionalTest;

import java.util.Optional;

/**
 * Created by akapandaroad on 2018-04-06.
 */
public class Test {

    public static void main(String[] args) {

        //1. 声明一个空的Optional
        Optional<Car> optCar=Optional.empty();
        System.out.println(optCar);

        //2. 依据一个非空值创建Optional
        Car car=new Car();
        Optional<Car> optCar2=Optional.of(car);
        System.out.println(optCar2.get());


        //3. 可接受null的Optional
        Optional<Car> optCar3 = Optional.ofNullable(car);
        


    }
}
