package useThread;

import java.util.concurrent.*;

public class UseExecutorservice {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    Thread.sleep(10000);
                }catch (Exception ex) {}
                return 10;
            }
        });
        System.out.println("Thread Start");
        executorService.shutdown();
        System.out.println("Thread Finish");
        Integer end = future.get();
        System.out.println("End : " + end);

        System.out.println("This Line Call");

    }
}
