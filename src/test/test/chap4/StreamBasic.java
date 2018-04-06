package test.test.chap4;

/**
 * Created by akapandaroad on 2018-02-13.
 */

import java.util.*;
import java.util.stream.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


public class StreamBasic {

    public static void main(String... args) {
        // Java 7


        int calories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        IntStream intStream = Dish.menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

//flatmap
        List<String> str = Arrays.asList("HELLO", "WROLD");
        List str2 = str.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(str2.toString());

/**给定两个数字列表，如何返回所有的数对呢?例如，给定列表[1, 2, 3]和列表[3, 4]，
 * 应 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
 * 为简单起见，你可以用有两个元素的数组来代 表数对。*/
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);


        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());


        System.out.println(pairs);


        /**如何扩展前一个例子，只返回总和能被3整除的数对呢?例如(2, 4)和(3, 3)是可以的。
         答案:你在前面看到了，filter可以配合谓词使用来筛选流中的元素。因为在flatMap
         操作后，你有了一个代表数对的int[]流，所以你只需要一个谓词来检查总和是否能被3整除
         就可以了:*/

        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4);


        List<int[]> pairs2 = numbers3.stream().flatMap(i -> numbers4.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList());

        System.out.println(pairs2);


//example
       // Stream<String> stream=Stream.of("JAVA","IS","MY","FAVOURITE");
        //stream.map(a->a.toLowerCase()).forEach(System.out::println);

//example

        Stream.iterate(0,n->n+2).limit(3).forEach(System.out::println);

        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

        boolean isHealthy=Dish.menu.stream().anyMatch(d->d.getCalories()>100);
        System.out.println(isHealthy);

        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

        List<String> names = Dish.menu.stream().filter(d -> {
            System.out.println("filtering" + d.getName());
            return d.getCalories() > 700;
        }).map(d -> {
            System.out.println("mapping" + d.getName());
            return d.getName();
        }).limit(2).collect(toList());

        System.out.println("name++++++++++" + names);



        long howmangdish=Dish.menu.stream().count();
        System.out.println(howmangdish);
        IntSummaryStatistics menuStatistics=Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);


        String jointest=Dish.menu.stream().map(Dish::getName).collect(joining("---"));
        System.out.println(jointest);


        int totalCalories = Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

        Optional<Integer> totalCalories2=Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum);
        //int totalCalories2 = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum);
        System.out.println(totalCalories);
        //System.out.println(totalCalories2);




        Map<Dish.Type,List<Dish>> dishByType=Dish.menu.stream().collect(groupingBy(Dish::getType));


        System.out.println(dishByType);


        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                Dish.menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));

        System.out.println(mostCaloricByType);


        

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }


    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }


}
