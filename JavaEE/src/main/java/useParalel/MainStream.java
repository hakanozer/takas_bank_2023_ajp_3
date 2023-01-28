package useParalel;

import java.util.List;

public class MainStream {
    static int count = 0;
    public static void main(String[] args) {

        Result result = new Result();
        List<Customer> ls = result.data();

        long start = System.currentTimeMillis();
        ls
        .parallelStream()
        .filter( item -> item.getAge() > 30 )
        .forEach( item -> {
            try {
                Thread.sleep(20);
            }catch (Exception ex) {}
            count++;
            System.out.println( count + " - " +  item.getName());
        });

        //ls.parallelStream().filter(item -> item.getAge() > 0).parallel();
        long end = System.currentTimeMillis();
        long beetween = end - start;
        System.out.println("beetween Stream : " + beetween);

    }
}

// beetween Stream : 23115
// beetween ParelelStream : 3092
