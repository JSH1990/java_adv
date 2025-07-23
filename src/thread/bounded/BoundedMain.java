package thread.bounded;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class BoundedMain {
    public static void main(String[] args) throws InterruptedException {
        // 1. BoundedQueue 선택
        BoundedQueue queue = new BoundedQueueV6_1(2);
        // 2. 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!
        producerFirst(queue); // 생산자 먼저 실행
        // consumerFirst(queue); // 소비자 먼저 실행
    }

    private static void producerFirst(BoundedQueue queue) throws InterruptedException {
        log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
        List<Thread> threads = new ArrayList<>(); //스레드 결과 상태를 한꺼번에 출력하기 위해 생성한 스레드를 보관해둠둠
        startProducer(queue, threads); // 생산자 스레드 3개를 만들어서 실행
        printAllState(queue, threads); // 생산자 스레드 3개가 실행되는 동안 큐의 상태를 출력
        startConsumer(queue, threads); // 소비자 스레드 3개를 만들어서 실행
        printAllState(queue, threads); // 소비자 스레드 3개가 실행되는 동안 큐의 상태를 출력
        log("== [생산자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + " ==");
    }

    private static void consumerFirst(BoundedQueue queue) throws InterruptedException {
        log("== [소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");
        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
        log("== [소비자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + " ==");
    }

    private static void startProducer(BoundedQueue queue, List<Thread> threads) throws InterruptedException {
        System.out.println();
        log("생산자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i),
                    "producer" + i);
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }

    private static void startConsumer(BoundedQueue queue, List<Thread> threads) throws InterruptedException {
        System.out.println();
        log("소비자 시작");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" +
                    i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 데이터: " + queue);
        for (Thread thread : threads) {
            log(thread.getName() + ": " + thread.getState());
        }
    }
}