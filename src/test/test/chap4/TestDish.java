package test.test.chap4;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collectors.*;

/**
 * Created by akapandaroad on 2018-04-03.
 */
public class TestDish<T> implements Collector<T, List<T>, List<T>>{
    @Override
    public Supplier supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }



    public enum CaloricLevel { DIET, NORMAL, FAT }
    public static void main(String[] args) {

        long howManyDishs=Dish.menu.stream().collect(Collectors.counting());
        System.out.println(howManyDishs);
        long howManyDishs2=Dish.menu.stream().count();
        System.out.println(howManyDishs2);

        /**compare
         *
         */
        Comparator<Dish> dishCaloriesComparator=Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish=Dish.menu.stream().collect(maxBy(dishCaloriesComparator));

        System.out.println(mostCalorieDish.get()+"print max value");

        /**summing int
         *
         */

        int totoalCalorie=Dish.menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totoalCalorie+"summing int");

        /**average
         *
         */

        double averageCalorie=Dish.menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(averageCalorie+"average");

        /**summary
         *
         */

       IntSummaryStatistics intSummaryStatistics =Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(intSummaryStatistics+"summary");

        /**
         * Concatenate Strings
         Use the joining factory method
         */

        String shortmenu=Dish.menu.stream().map(Dish::getName).collect(joining());
       // String shortmenu2 = Dish.menu.stream().collect(joining());
        System.out.println(shortmenu);

        String shortmenu2=Dish.menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortmenu2);
        System.out.println(Dish.menu.stream().map(dish -> dish.getType().toString())
                .collect(joining(",")));//输出类型字符串的join格式


        /**
         *
         * Collectors.reducing
         All previous collectors are specializations of Collectors.reducing:
         • First argument: starting value (also value of empty stream)
         • Second argument: function to transform object
         • Third argument: BinaryOperator to sum two items into one
         */


        int totoalCalories=Dish.menu.stream().collect(reducing(0,Dish::getCalories,(i,j)->i+j));
        int totoalCalories2=Dish.menu.stream().collect(reducing(0,Dish::getCalories,Integer::sum));

        System.out.println(totoalCalories+"  "+totoalCalories2);

       // Example: find highest-calorie dish

        Optional<Dish> mostCalorieDish2 =Dish.menu.stream().collect(
                reducing((d1,d2)->d1.getCalories()>d2.getCalories()?d1:d2));
        System.out.println(mostCalorieDish2.get());


        //with reduce
        int totalCalories3 =
                Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();

        //With stream specialization
        int totalCalories =
        Dish.menu.stream().mapToInt(Dish::getCalories).sum();

        /**
         * Grouping
         Task: Group dishes by type ([MEAT, FISH, OTHER])
         */
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel=Dish.menu.stream().collect(groupingBy(dish->{
            if (dish.getCalories()<=400 ) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
                }
        ));

        System.out.println(dishesByCaloricLevel);

        //Second collector can be any type
        Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(groupingBy(Dish::getType, counting()));

        /**
         * Definition
         Partitioning: grouping with a predicate, called partitioning function
         Example: Partition menu into vegetarian and non-vegetarian dishes
         */

        Map <Boolean,List<Dish> > partitionedMenu =Dish.menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));

        System.out.println(partitionedMenu);

        /**
         * With partitioning, we keep both true and false elements
         • E.g., access true results with
         */

        List<Dish> vegetarianDishes = partitionedMenu.get(true);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType)));



        /**
         *
         * Lab Session #11
         */

        String shortMenu1 = Dish.menu.stream()
                .map(Dish::getName)
                .collect( reducing( (s1, s2) -> s1 + s2 ) ).get();
// String shortMenu2 = Dish.menu.stream()
//            .collect( reducing( (d1, d2) -> d1.getName() + d2.getName() ) ).get();
        String shortMenu3 = Dish.menu.stream()
                .collect( reducing( "", Dish::getName, (s1, s2) -> s1 + s2 ) );
        System.out.println(shortMenu1);
        System.out.println(shortMenu3);


        Optional<Dish> minCalories = Dish.menu.stream().collect(minBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println(minCalories.get());

        System.out.println(Dish.menu.stream().filter(dish -> dish.getCalories()>5000)
                .map(Dish::getCalories).reduce(Integer::sum));
/**
 *
 * collectingAndThen 包裹另一个收集器，对其结果应用转换函数
 */
        //
//        Map<Dish.Type, Optional<Dish>> mostCaloricByType = Dish.menu.stream().
//                collect(groupingBy(Dish::getName,
//                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));


       // System.out.println(mostCaloricByType);

        Map<Boolean, Dish> mostCaloriePartitionByVegetatble = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian
                , collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloriePartitionByVegetatble);


    }









}
