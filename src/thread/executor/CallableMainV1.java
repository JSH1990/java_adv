package thread.executor;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableMainV1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
    ExecutorService es = Executors.newFixedThreadPool(1);
    Future<Integer> future = es.submit(new MyCallable());
    Integer result = future.get();
    log("result value = " + result);
    es.shutdown();
}

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws InterruptedException {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("create value = " + value);
            log("Callable 완료");
            return value;
        }
    }
}