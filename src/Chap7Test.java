/**
 * Created by akapandaroad on 2018-02-27.
 *
 * parallelStream
 * and
 * SequentialStream
 */
public class Chap7Test {

    public static void main(String[] args) {


        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(System.nanoTime()/1_000);

    }
}
