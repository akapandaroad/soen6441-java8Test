import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by akapandaroad on 2018-02-05.
 */
public class Codelambda {
    public static void main(String[] args) {

        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);

        List<Integer> evennumber=Arrays.asList(1,2,3,4);

        int sumtest=evennumber.stream().reduce(0,Integer::sum);
        System.out.println(sumtest);

        evennumber.stream().filter(n->n>2).limit(1).forEach(System.out::println);

//        Consumer list=System.out::print;
        IntPredicate evenNumbers=(int i)->i%2==0;


        System.out.println(evenNumbers.test(100));


        List<String> title=Arrays.asList("Java8", "In", "Action");

//        Stream<String> s=title.stream();
//        s.forEach(System.out::print);

        title.stream().forEach(System.out::print);
        System.out.println();
//
//        Stream newevenNumbers = Stream.rangeClosed(1, 100)
//                .filter(n -> n % 2 == 0);
//        System.out.println(newevenNumbers.count());

        //------------
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        IntStream evenNumberss = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumberss.count());

        //---
        List<Integer> number=Arrays.asList(1,2,3,4,4,5,6);

        int sum=number.stream().reduce(10,(a,b)->(a+b));//identity init value of sum

        System.out.println(sum);

        int sum2 = number.parallelStream().reduce(0, Integer::sum);
        System.out.println(sum2+"sum2");


        Optional<Integer> min=number.stream().reduce(Integer::max);
        System.out.println(min.get()+"=======");



        List<Integer> number2=number.stream().filter(i->i%2==0).distinct().limit(2).skip(1).map(n->n+1).collect(toList());
        System.out.println(number2);
        System.out.println();
//----------------------------
        boolean iseven=number.stream().anyMatch(n->n%2==0);

        System.out.println(iseven+"----");
        List<String> words = Arrays.asList("Java8","Lambdas","In","Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
//---------------------------
        List<String> wordss =Arrays.asList("woshinibaba");
        List<String> uniqueCharacters = wordss.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)//--------flatmap
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);


//        List<Integer> dishNameLengths = menu.stream()
//                .map(Dish::getName)
//                .map(String::length)
//                .collect(toList());
        ArrayList<String> list=new ArrayList<>();
        list.add("wo");
        list.add("shi");
        list.add("ni");
        list.add("ba");
        list.add("ba");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });


        list.sort((o1,o2)->o1.compareTo(o2));
        System.out.println(list);
        //list.forEach(n-> System.out.println(n));

       // list.forEach(System.out::println);


        List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        //(List<String> list5)->list.isEmpty();
        costBeforeTax.stream().map((cost) ->cost).forEach(System.out::println);
    }



    public static void filter(ArrayList list, Predicate p){
        for (Object str: list) {

            if(p.test(str)){
                System.out.println(str);
            }
        }


    }
}
