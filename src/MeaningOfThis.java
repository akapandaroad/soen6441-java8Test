/**
 * Created by akapandaroad on 2018-01-19.
 */
interface inter{


    int func();
}

public class MeaningOfThis implements inter
{

    public static void main(String[] args) {
        MeaningOfThis mot=new MeaningOfThis();
        mot.func();

        Runnable r1 = () -> System.out.println("Hello World 1");
        Runnable r2 = new Runnable(){
            public void run(){
                System.out.println("Hello World 2");
            }
        };


        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));

    }
    public static void process(Runnable r){
        r.run();
    }

    @Override
    public int func() {
        return 1;
    }
}
